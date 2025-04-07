package quant.tools

import java.io.File

import scala.io.{BufferedSource, Source}

import cats.data.EitherT
import cats.effect.{ExitCode, IO, IOApp, Resource}
import cats.instances.list._
import cats.syntax.applicative._
import cats.syntax.either._
import cats.syntax.eq._
import cats.syntax.foldable._
import cats.syntax.show._
import quant.algs.Razl
import quant.instances.complex._
import quant.instances.matrix._
import quant.math.Matrix
import quant.tools.parsing.ComplexParser
import spire.math.{Complex, Real}
import spire.syntax.multiplicativeSemigroup._

object Runner extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- println(
      """Программа для разложения унитарной матрицы на произведение двухуровневых матриц.
        |Автор: Довжиков Сергей
        |""".stripMargin
    ).pure[IO]
    code <- checkArgsLength(args).fold(
      usage => println(usage).pure[IO] *> ExitCode.Success.pure[IO],
      _ => {
        val matrixAndResults = runChecks(args.head).semiflatMap(m =>
          runAlgs(m).map { case (r1, r2) =>
            (m, r1, r2)
          }
        )
        val printResAndCheckProperty =
          matrixAndResults.semiflatMap { case (matrix, r1, r2) =>
            for {
              _ <- printRes(r1, "Нильсен, Чанг")
              _ <- checkMatrixProductCloseToMatrix(matrix, r1)
              _ <- printRes(r2, "Накахара, Оми")
              _ <- checkMatrixProductCloseToMatrix(matrix, r2)
            } yield ()
          }
        printResAndCheckProperty.foldF(
          er => println(s"\nОшибка: $er").pure[IO] *> ExitCode.Error.pure[IO],
          _ => ExitCode.Success.pure[IO]
        )
      }
    )
  } yield code

  def checkArgsLength(args: List[String]): Either[String, Unit] =
    Either.cond(args.length == 1, (), usage)

  val usage: String =
    """Использование:
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

  def runChecks(
      fileName: String
  ): EitherT[IO, String, Matrix[Complex[Real]]] = {
    for {
      _ <- checkIfFileExists(fileName).toEitherT[IO]
      m <- readData(fileName)
      _ <- EitherT.liftF(println(s"Исходная матрица:\n${m.show}").pure[IO])
      cm <- checkInputData(m).toEitherT[IO]
    } yield cm
  }

  def checkIfFileExists(filename: String): Either[String, Unit] =
    Either.cond(
      new File(filename).exists,
      (),
      s"""файл "$filename" не найден."""
    )

  def readData(filename: String): EitherT[IO, String, Matrix[Complex[Real]]] = {
    val res: Resource[IO, BufferedSource] =
      Resource.make(IO(Source.fromFile(filename))) { s =>
        IO(s.close()).handleErrorWith(t =>
          println(s"Ошибка при закрытии ресурса: ${t.getMessage}").pure[IO]
        )
      }
    val abc = res.use { bs =>
      val lines = bs.getLines().toList
      ComplexParser.parseMatrix(lines).pure[IO]
    }
    EitherT(abc)
  }

  def checkInputData(
      matrix: Matrix[Complex[Real]]
  ): Either[String, Matrix[Complex[Real]]] =
    matrix.asRight
      .ensure("неквадратная матрица")(_.isSquare)
      .ensure("неунитарная матрица")(Matrix.isUnitary)

  def runAlgs(
      matrix: Matrix[Complex[Real]]
  ): IO[(List[Matrix[Complex[Real]]], List[Matrix[Complex[Real]]])] = {
    val r1 = Razl.algNielsenChang(matrix)
    val r2 = Razl.algNakaharaOhmi(matrix)
    IO((r1, r2))
  }

  def checkMatrixProductCloseToMatrix(
      m: Matrix[Complex[Real]],
      ms: List[Matrix[Complex[Real]]]
  ): IO[Unit] = {
    val check = ms reduce (_ * _)
    IO {
      if (check === m)
        println("Произведение матриц равно исходной.")
      else {
        println(s"""Произведение матриц НЕ РАВНО исходной!
             |Произведение:
             |${check.show}
             |""".stripMargin)
      }
    }
  }

  def printRes(res: List[Matrix[Complex[Real]]], method: String): IO[Unit] =
    for {
      _ <- println(s"\nРезультат (по $method):").pure[IO]
      _ <- res.zipWithIndex.map { case (m, i) =>
        println(s"""Матрица №${i + 1}
             |${m.show}
             |""".stripMargin).pure[IO]
      }.sequence_
    } yield ()
}
