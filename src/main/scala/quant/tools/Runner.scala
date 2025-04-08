package quant.tools

import java.nio.file.{Files, Path}

import scala.jdk.CollectionConverters.*

import cats.Monad
import cats.data.EitherT
import cats.effect.kernel.Sync
import cats.effect.std.Console
import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.applicative.*
import cats.syntax.either.*
import cats.syntax.eq.*
import cats.syntax.flatMap.*
import cats.syntax.foldable.*
import cats.syntax.functor.*
import cats.syntax.show.*
import quant.algs.Razl
import quant.instances.complex.*
import quant.instances.matrix.*
import quant.math.Matrix
import quant.tools.parsing.ComplexParser
import spire.math.{Complex, Real}
import spire.syntax.multiplicativeSemigroup.*

object Runner extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    program[IO](args)

  def program[F[_]: Sync: Console](args: List[String]): F[ExitCode] =
    for {
      _ <- printHeader
      code <-
        if (args.length != 1)
          printUsage.as(ExitCode.Success)
        else {
          checkInputPrintResultAndCheck[F](args.head).foldF(
            er => Console[F].println(s"\nОшибка: $er").as(ExitCode.Error),
            _ => ExitCode.Success.pure[F]
          )
        }
    } yield code

  def printHeader[F[_]: Console]: F[Unit] =
    Console[F].println(
      """
        |Программа для разложения унитарной матрицы на произведение двухуровневых матриц.
        |Автор: Довжиков Сергей
        |""".stripMargin
    )

  def printUsage[F[_]: Console]: F[Unit] =
    Console[F].println(
      """
        |Использование:
        |./matr2scheme <имя файла>     (*nix)
        |matr2scheme.bat <имя файла>   (windows)
        |Файл должен содержать матрицу комплексных чисел по следующим правилам:
        |  - можно вводить целые и вещественные числа (включая число Пи)
        |  - комплексные числа вводятся в виде пар (re,im), где re - вещественная часть, im - мнимая
        |      например для ввода числа 2+2i вводите (2,2)
        |  - поддерживается несколько функций вещественных чисел:
        |      * sin - синус
        |      * cos - косинус
        |      * sqrt - квадратный корень
        |      Внутри функций возможно использование умножения и деления
        |  - элементы матрицы в строке отделяются запятыми"
        |  - каждая строка матрицы находится на отдельной строке файла
        |""".stripMargin
    )

  def checkInputPrintResultAndCheck[F[_]: Sync: Console](
      arg: String
  ): EitherT[F, String, Unit] =
    for {
      m <- checkInput(arg)
      rs <- EitherT.right(runAlgs(m).pure[F])
      (r1, r2) = rs
      _ <- EitherT.right(printRes[F](r1, "Нильсен, Чанг"))
      _ <- EitherT.right(checkMatrixProductCloseToMatrix(m, r1))
      _ <- EitherT.right(printRes[F](r2, "Накахара, Оми"))
      _ <- EitherT.right(checkMatrixProductCloseToMatrix(m, r2))
    } yield ()

  def checkInput[F[_]: Sync: Console](
      fileName: String
  ): EitherT[F, String, Matrix[Complex[Real]]] = {
    for {
      _ <- checkIfFileExists(fileName)
      m <- readData(fileName)
      _ <- EitherT.right(Console[F].println(s"Исходная матрица:\n${m.show}"))
      cm <- checkMatrix(m).toEitherT[F]
    } yield cm
  }

  def checkIfFileExists[F[_]: Sync](
      filename: String
  ): EitherT[F, String, Unit] =
    EitherT(
      Sync[F]
        .blocking(Files.exists(Path.of(filename)))
        .ifF(().asRight, s"файл \"$filename\" не найден.".asLeft)
    )

  def readData[F[_]: Sync](
      filename: String
  ): EitherT[F, String, Matrix[Complex[Real]]] =
    EitherT(
      Sync[F]
        .blocking(Files.readAllLines(Path.of(filename)).asScala.toList)
        .map(ComplexParser.parseMatrix)
    )

  def checkMatrix(
      matrix: Matrix[Complex[Real]]
  ): Either[String, Matrix[Complex[Real]]] =
    matrix.asRight
      .ensure("неквадратная матрица")(_.isSquare)
      .ensure("неунитарная матрица")(Matrix.isUnitary)

  def runAlgs(
      matrix: Matrix[Complex[Real]]
  ): (List[Matrix[Complex[Real]]], List[Matrix[Complex[Real]]]) = {
    val r1 = Razl.algNielsenChang(matrix)
    val r2 = Razl.algNakaharaOhmi(matrix)
    (r1, r2)
  }

  def checkMatrixProductCloseToMatrix[F[_]: Console](
      m: Matrix[Complex[Real]],
      ms: List[Matrix[Complex[Real]]]
  ): F[Unit] = {
    val check = ms reduce (_ * _)
    if (check === m)
      Console[F].println("Произведение матриц равно исходной.")
    else {
      Console[F].println(s"""Произведение матриц НЕ РАВНО исходной!
           |Произведение:
           |${check.show}
           |""".stripMargin)
    }
  }

  def printRes[F[_]: Monad: Console](
      res: List[Matrix[Complex[Real]]],
      method: String
  ): F[Unit] =
    for {
      _ <- Console[F].println(s"\nРезультат (по $method):")
      _ <- res.zipWithIndex.traverseVoid { case (m, i) =>
        Console[F].println(s"""Матрица №${i + 1}
             |${m.show}
             |""".stripMargin)
      }
    } yield ()
}
