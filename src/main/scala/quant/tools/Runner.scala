package quant.tools

import quant.algs.Razl
import quant.implicits._
import quant.tools.parsing.ComplexParser
import breeze.linalg.{DenseMatrix, all, isClose}
import breeze.math.Complex

import scala.io.Source
import java.io.File

object Runner extends App with ComplexParser {

  def runAlg(m: DenseMatrix[Complex]): Unit = {
    // Выведем её на экран
    println(s"Исходная матрица:\n${m.toString(Int.MaxValue, Int.MaxValue)}")

    // Checks
    if (!m.isSquare) {
      println(s"Error: non-square matrix.")
      sys.exit
    } else if (!m.isUnitary) {
      println(s"Error: non-unitary matrix.")
      sys.exit
    } else {
      // Запустим алгоритм
      val res = Razl.alg(m)

      // Красиво выведем результат
      println("Результат:")
      res.zipWithIndex foreach {
        case (m, i) =>
          println(s"Матрица №${i + 1}")
          println(m.toString(Int.MaxValue, Int.MaxValue))
      }

      println("Проверка равенства произведения матриц исходной:")
      val check = res.reduce(_ * _)
      if (all(isClose(check, m)))
        println("Произведение матриц равно исходной.")
      else {
        println("Произведение матриц НЕ РАВНО исходной!")
        println("Произведение:")
        println(check.toString(Int.MaxValue, Int.MaxValue))
      }
    }
  }

  def printUsage: Unit = {
    println("Использование: ./matr2scheme <имя файла>")
    println("./matr2scheme <имя файла>     (*nix)")
    println("matr2scheme.bat <имя файла>   (windows)")
    println("Файл должен содержать матрицу комплексных чисел по следующим правилам:")
    println("  - можно вводить целые и вещественные числа (включая число Пи)")
    println("  - комплексные числа вводятся в виде пар (re,im), где re - вещественная часть, im - мнимая")
    println("      например для ввода числа 2+2i вводите (2,2)")
    println("  - поддерживается несколько функций вещественных чисел:")
    println("      * sin - синус")
    println("      * cos - косинус")
    println("      * sqrt - квадратный корень")
    println("      Внутри функций возможно использование умножения и деления")
    println("  - элементы матрицы в строке отделяются запятыми")
    println("  - каждая строка матрицы находится на отдельной строке файла")
  }

  println("Программа для разложения унитарной матрицы на произведение двухуровневых матриц.")
  println("Автор: Довжиков Сергей")
  println()

  if (args.length != 1)
    printUsage
  else if (!(new File(args(0)).exists))
    println(s"""Ошибка: файл "${args(0)}" не найден.""")
  else {
    val lines = Source.fromFile(args(0)).getLines.toList
    val matrixOrError = parseMatrix(lines)

    matrixOrError.fold(
      msg => println(s"Parsing error: $msg"),
      m => runAlg(m)
    )
  }

}
