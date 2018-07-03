package quant.implicits

import org.scalatest._

import breeze.math.Complex
import breeze.linalg.{DenseMatrix, convert}

import quant.implicits._

class MatrixImplicitsSpec extends FlatSpec with Matchers {

  val m = DenseMatrix(
    Array(Complex(1.0, 2.0), Complex(3.0, 4.0)),
    Array(Complex(5.0, 6.0), Complex(7.0, 8.0))
  )

  "DenseMatrix" should "check if it's square matrix correctly" in {
    m.isSquare shouldBe true
    convert(DenseMatrix(
        Array(1, 2, 3),
        Array(4, 5, 6)
    ), Complex).isSquare shouldBe false
  }

  it should "check if it's unitary matrix correctly" in {
    m.isUnitary shouldBe false
    convert(DenseMatrix(
        Array(1, 0, 0, 0),
        Array(0, 1, 0, 0),
        Array(0, 0, 0, 1),
        Array(0, 0, 1, 0)
    ), Complex).isUnitary shouldBe true
  }

}
