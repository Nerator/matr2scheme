package quant.implicits

import breeze.linalg.{DenseMatrix, isClose}
import breeze.math.Complex
import org.scalatest._

class UFuncsSpec extends FlatSpec with Matchers {

  "isClose" should "work correctly for complex numbers" in {
    val c1 = Complex(1.0, 2.0)
    val c2 = Complex(3.0, 4.0)
    val c3 = Complex(1.0 + 1e-9, 2.0 + 1e-9)

    isClose(c1, c2) shouldBe false
    isClose(c1, c3) shouldBe true
    isClose(c1, c3, 1e-10) shouldBe false
  }

  "all" should "work correctly for boolean matrices" in {
    val m1 = DenseMatrix(
      Array(true, true),
      Array(true, true)
    )
    val m2 = DenseMatrix(
      Array(true, true),
      Array(false, true)
    )

    breeze.linalg.all(m1) shouldBe true
    breeze.linalg.all(m2) shouldBe false
  }

}
