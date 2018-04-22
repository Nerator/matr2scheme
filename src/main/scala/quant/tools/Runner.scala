package quant.tools

import quant.algs.Razl
import quant.complex.Complex
import quant.matrix._
import quant.implicits._
import quant.tools.parsing.ComplexParser

import scala.io.Source
import java.io.File

object Runner extends App with ComplexParser {

  def runAlg(m: Matrix[Complex]): Unit = {
    // Выведем её на экран
    println(s"Исходная матрица:\n${m.prettyToString}")

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
          println(m.prettyToString)
      }
    }
  }

  def printUsage: Unit = {
    println("Использование: scala ???.jar <имя файла>.")
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
