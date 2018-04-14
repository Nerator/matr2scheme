package quant.implicits

import quant.complex.Complex

trait ComplexImplicits {
  implicit class DoubleComplexOps(x: Double) {
    def toComplex = Complex(x,0)
    def +(that: Complex) = toComplex + that
    def -(that: Complex) = toComplex - that
    def *(that: Complex) = toComplex * that
    def /(that: Complex) = toComplex / that
  }
}
