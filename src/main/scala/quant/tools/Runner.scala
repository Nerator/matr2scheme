package quant.tools

import java.io.File

import breeze.linalg.DenseMatrix
import breeze.math.Complex

import quant.algs.Razl
import quant.implicits._
import quant.tools.parsing.ComplexParser

import scala.io.Source

object Runner extends App with ComplexParser {

  def runAlg(matrix: DenseMatrix[Complex]): Unit = {
    // Выведем её на экран
    println(s"Исходная матрица:\n${matrix.map(_.prettyPrint)}")
    println()

    // Checks
    if (!matrix.isSquare) {
      println(s"Error: non-square matrix.")
      sys.exit
    } else if (!matrix.isUnitary) {
      println(s"Error: non-unitary matrix.")
      sys.exit
    } else {
      // Запустим алгоритм
      val res1 = Razl.algNielsenChang(matrix)
      val res2 = Razl.algNakaharaOhmi(matrix)

      // Красиво выведем результат
      println("Результат (по Нильсен, Чанг):")
      res1.zipWithIndex foreach {
        case (m, i) =>
          println(s"Матрица №${i + 1}")
          println(m.map(_.prettyPrint))
      }

      println("Проверка равенства произведения матриц исходной:")
      val check1 = res1.reduce(_ * _)
      if (check1.isClose(matrix))
        println("Произведение матриц равно исходной.")
      else {
        println("Произведение матриц НЕ РАВНО исходной!")
        println("Произведение:")
        println(check1.map(_.prettyPrint))
      }
      println()

      println("Результат (по Накахара, Оми):")
      res2.zipWithIndex foreach {
        case (m, i) =>
          println(s"Матрица №${i + 1}")
          println(m.map(_.prettyPrint))
      }

      println("Проверка равенства произведения матриц исходной:")
      val check2 = res2.reduce(_ * _)
      if (check2.isClose(matrix))
        println("Произведение матриц равно исходной.")
      else {
        println("Произведение матриц НЕ РАВНО исходной!")
        println("Произведение:")
        println(check2.map(_.prettyPrint))
      }
    }
  }

  def printUsage(): Unit = {
    println("Использование:")
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
    printUsage()
  else if (!new File(args(0)).exists)
    println(s"""Ошибка: файл "${args(0)}" не найден.""")
  else {
    val source = Source.fromFile(args(0))
    try {
      val lines = source.getLines.toList
      val matrixOrError = parseMatrix(lines)

      matrixOrError.fold(
        msg => println(s"Parsing error: $msg"),
        m => runAlg(m)
      )
    } finally {
      source.close()
    }

  }

}
