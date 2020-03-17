package quant

import breeze.linalg.{DenseMatrix, convert}
import breeze.math.Complex

object ExampleMatrices {
  // Раздел 2 задание 1
  val m01: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0),
    Array(1, 0, 0, 0),
    Array(0, 1, 0, 0)
  ), Complex)

  val m02: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0)
  ), Complex)

  val m03: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 0, 1, 0),
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0)
  ), Complex)

  val m04: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m05: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0)
  ), Complex)

  val m06: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0),
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m07: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m08: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(1, 0, 0, 0),
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0)
  ), Complex)

  val m09: DenseMatrix[Complex] = DenseMatrix(
    Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
    Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
    Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero)
  )

  val m10: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0),
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0)
  ), Complex)

  // Раздел 2 задание 3

  val m11: DenseMatrix[Complex] = DenseMatrix(
    Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
    Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i)
  )

  val m12: DenseMatrix[Complex] = Complex(0.5, 0) * DenseMatrix(
    Array( Complex.one,  Complex.one,  Complex.one,  Complex.one),
    Array( Complex.one,    Complex.i, -Complex.one,   -Complex.i),
    Array( Complex.one, -Complex.one,  Complex.one, -Complex.one),
    Array( Complex.one,   -Complex.i, -Complex.one,    Complex.i)
  )

  // Раздел 2 задание 6

  val m13: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(-1, 0, 0, 0),
    Array( 0, 1, 0, 0),
    Array( 0, 0, 1, 0),
    Array( 0, 0, 0, 1)
  ), Complex)

  val m14: DenseMatrix[Complex] = convert(DenseMatrix(
    Array( 1,  0,  0,  0),
    Array( 0, -1,  0,  0),
    Array( 0,  0, -1,  0),
    Array( 0,  0,  0, -1)
  ), Complex)

  val m15: DenseMatrix[Complex] = convert(DenseMatrix(
    Array( 1,  0,  0,  0),
    Array( 0,  0, -1,  0),
    Array( 0, -1,  0,  0),
    Array( 0,  0,  0, -1)
  ), Complex)

  // Раздел 3 задание 3

  val m16: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(1, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 1),
    Array(0, 1, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 0)
  ), Complex)

  val m17: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(1, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 1),
    Array(0, 1, 0, 0, 0, 0, 0, 0)
  ), Complex)

  // Раздел 4 задание 1, в (б) и (в) подставим b = sqrt(1/2)

  val m18: DenseMatrix[Complex] = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0)
  ), Complex)

  val m19: DenseMatrix[Complex] = {
    val b = math.sqrt(1.0/2.0)
    convert(DenseMatrix(
      Array(0.0,   b, 0.0,  -b),
      Array(0.0, 0.0, 1.0, 0.0),
      Array(0.0,   b, 0.0,   b),
      Array(1.0, 0.0, 0.0, 0.0)
    ), Complex)
  }

  val m20: DenseMatrix[Complex] = {
    val b = math.sqrt(1.0/2.0)
    convert(DenseMatrix(
      Array(0.0, 0.0, 0.0, 1.0),
      Array(0.0, 0.0, 1.0, 0.0),
      Array(  b,   b, 0.0, 0.0),
      Array(  b,  -b, 0.0, 0.0)
    ), Complex)
  }
}
