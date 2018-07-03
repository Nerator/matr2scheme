package quant.implicits

import org.scalatest._

import breeze.math.Complex
import breeze.linalg.isClose

import quant.implicits._

class ComplexImplicitsSpec extends FlatSpec with Matchers {

  "isClose" should "work correctly" in {
    val c1 = Complex(1.0, 2.0)
    val c2 = Complex(3.0, 4.0)
    val c3 = Complex(1.0 + 1e-9, 2.0 + 1e-9)

    isClose(c1, c2) shouldBe false
    isClose(c1, c3) shouldBe true
    isClose(c1, c3, 1e-10) shouldBe false
  }

}
