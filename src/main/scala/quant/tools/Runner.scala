package quant.tools

import java.io.File

import breeze.linalg.DenseMatrix
import breeze.math.Complex
import cats.data.EitherT
import cats.effect.{ExitCode, IO, IOApp, Resource}
//import cats.implicits._
import cats.syntax.applicative._
import cats.syntax.apply._
import cats.syntax.either._
import quant.algs.Razl
import quant.implicits._
import quant.tools.parsing.ComplexParser

import scala.io.{BufferedSource, Source}

object Runner extends IOApp with ComplexParser {
  override def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- println(
        """Программа для разложения унитарной матрицы на произведение двухуровневых матриц.
          |Автор: Довжиков Сергей
          |""".stripMargin).pure[IO]
      code <- checkArgsLength(args).fold(
        usage => println(usage).pure[IO] *> ExitCode.Success.pure[IO],
        _ => (for {
          _ <- EitherT.fromEither[IO](checkIfFileExists(args.head))
          m <- readData(args.head)
          _ <- EitherT.liftF(println(s"Исходная матрица:\n${m.map(_.prettyPrint)}\n").pure[IO])
          cm <- EitherT.fromEither[IO](checkInputData(m))
        } yield cm).foldF(
          er => println(s"Error: $er").pure[IO] *> ExitCode.Error.pure[IO],
          m => runAlg(m) *> ExitCode.Success.pure[IO]
        )
      )
    } yield code
  }

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

  def checkIfFileExists(filename: String): Either[String, Unit] =
    if (!new File(filename).exists)
      s"""Ошибка: файл "$filename" не найден.""".asLeft
    else ().asRight

  def readData(filename: String): EitherT[IO, String, DenseMatrix[Complex]] = {
    val res: Resource[IO, BufferedSource] = Resource.make(IO(Source.fromFile(filename))) {
      s => IO(s.close()).handleErrorWith(t => println(s"Error in closing resource: ${t.getMessage}").pure[IO])
    }
    val abc = res.use { bs =>
      val lines = bs.getLines.toList
      parseMatrix(lines).pure[IO]
    }
    EitherT(abc)
  }

  def checkInputData(matrix: DenseMatrix[Complex]): Either[String, DenseMatrix[Complex]] = {
    if (!matrix.isSquare)
      "non-square matrix.".asLeft
    else if (!matrix.isUnitary)
      "non-unitary matrix.".asLeft
    else matrix.asRight
  }

  def runAlg(matrix: DenseMatrix[Complex]): IO[Unit] = {
    val res1 = Razl.algNielsenChang(matrix)
    val res2 = Razl.algNakaharaOhmi(matrix)
    for {
      _ <- printRes(res1, "Нильсен, Чанг")
      _ <- checkMatrixProductCloseToMatrix(matrix, res1)
      _ <- printRes(res2, "Накахара, Оми")
      _ <- checkMatrixProductCloseToMatrix(matrix, res2)
    } yield ()
  }

  def checkMatrixProductCloseToMatrix(m: DenseMatrix[Complex], ms: List[DenseMatrix[Complex]]): IO[Unit] = {
    val check = ms reduce (_ * _)
    IO {
      if (check isClose m)
        println("Произведение матриц равно исходной.")
      else {
        println(
          s"""Произведение матриц НЕ РАВНО исходной!
             |Произведение:
             |${check map (_.prettyPrint)}
             |""".stripMargin)
      }
    }
  }

  def printRes(res: List[DenseMatrix[Complex]], method: String): IO[Unit] = for {
    _ <- println(s"Результат (по $method):").pure[IO]
    _ <- res.zipWithIndex map {
      case (m, i) =>
        println(
          s"""Матрица №${i + 1}
             |${m.map(_.prettyPrint)}
             |""".stripMargin).pure[IO]
    } reduce {
      _ *> _
    }
  } yield ()
}
