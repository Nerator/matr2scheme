package quant.complex

import org.scalatest._
import quant.implicits._

class ComplexSpec extends FlatSpec with Matchers {
  val c1 = Complex(1, 2)
  val c2 = Complex(3, 4)

  "A complex number" should "access its fields correctly" in {
    c1.re shouldEqual 1
    c1.im shouldEqual 2
    c2.re shouldEqual 3
    c2.im shouldEqual 4
  }

  it should "add complex correctly" in {
    c1 + c2 shouldEqual Complex(4, 6)
  }

  it should "add Double correctly from both sides" in {
    c1 + 2 shouldEqual Complex(3, 2)
    2 + c1 shouldEqual Complex(3, 2)
  }

  it should "add zero as Complex and Double correctly" in {
    c1 + 0 shouldEqual c1
    0 + c2 shouldEqual c2
    c1 + Complex.zero shouldEqual c1
    Complex.zero + c2 shouldEqual c2
  }

  it should "multiply by Complex correctly" in {
    c1 * c2 shouldEqual Complex(-5, 10)
    c1 * Complex.zero shouldEqual Complex.zero
    Complex.zero * c2 shouldEqual Complex.zero
  }

  it should "multiply by Double correctly" in {
    c1 * 2 shouldEqual Complex(2, 4)
    2 * c1 shouldEqual Complex(2, 4)
    c2 * 3 shouldEqual Complex(9, 12)
    3 * c2 shouldEqual Complex(9, 12)
    c1 * 0 shouldEqual Complex.zero
    0 * c1 shouldEqual Complex.zero
  }

  it should "compute absolute value correctly" in {
    c1.abs shouldEqual 2.236067 +- 1E-6
    c2.abs shouldEqual 5
    Complex.zero.abs shouldEqual 0
    Complex.i.abs shouldEqual 1
  }

  it should "compute argument correctly" in {
    c1.arg shouldEqual 1.107148 +- 1E-6
    c2.arg shouldEqual 0.927295 +- 1E-6
    Complex(0,2).arg shouldEqual Math.PI / 2 +- 1E-6
    Complex(0,-2).arg shouldEqual -Math.PI / 2 +- 1E-6
  }

  it should "throw exception if argument of zero is accessed" in {
    intercept[IllegalArgumentException] {
      Complex.zero.arg
    }
  }
}
