package quant.matrix

import quant.complex._

trait MatrixElement[A] {
  def add(e1:A, e2: A): A
  def mult(e1:A, e2: A): A
  def conj(e: A): A
  def almostEq(e1: A, e2: A): Boolean
  def one: A
  def zero: A
}

object MatrixElement {
  private val EPS = 1e-10
  implicit lazy val doubleMatrixElement = new MatrixElement[Double] {
    def add(e1: Double, e2: Double): Double = e1 + e2
    def mult(e1: Double, e2: Double): Double = e1 * e2
    def conj(e: Double) = e
    def almostEq(e1: Double, e2: Double) = (e1-e2).abs < EPS
    def one = 1.0
    def zero = 0.0
  }
  implicit lazy val complexMatrixElement = new MatrixElement[Complex] {
    def add(e1: Complex, e2: Complex): Complex = e1 + e2
    def mult(e1: Complex, e2: Complex): Complex = e1 * e2
    def conj(e: Complex) = e.conj
    def almostEq(e1: Complex, e2: Complex) = e1 ~= e2
    def one = Complex.one
    def zero = Complex.zero
  }
  implicit lazy val intMatrixElement = new MatrixElement[Int] {
    def add(e1: Int, e2: Int): Int = e1 + e2
    def mult(e1: Int, e2: Int): Int = e1 * e2
    def conj(e: Int) = e
    def almostEq(e1: Int, e2: Int) = e1 == e2
    def one = 1
    def zero = 0
  }
  implicit class MatrixElementOps[A](e: A)(implicit ev: MatrixElement[A]) {
    def +(that: A) = ev.add(e, that)
    def *(that: A) = ev.mult(e, that)
    def conj = ev.conj(e)
    def ~=(that:A) = ev.almostEq(e, that)
  }
}
