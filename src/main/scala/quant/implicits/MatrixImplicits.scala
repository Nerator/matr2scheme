package quant.implicits

import quant.complex.Complex
import quant.matrix._

trait MatrixImplicits {

  implicit class DoubleWithMatrMult(x: Double) {
    def *(that: Matrix[Double]) = that * x
    //def *(that: Matrix[Complex]) = that * x
  }

  implicit class ComplexWithMatrMult(x: Complex) {
    def *(that: Matrix[Complex]) = that * x
  }

  implicit class IntWithMatrMult(x: Int) {
    def *(that: Matrix[Int]) = that * x
  }

}
