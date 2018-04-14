package quant.matrix

import quant.complex._
import quant.matrix._
import quant.implicits._
import org.scalatest._

class MatrixSpec extends FlatSpec with Matchers {

  val im = Array(
    Array(1, 2),
    Array(3, 4))
  
  val dm = Array(
    Array(1.0, 2.0),
    Array(3.0, 4.0))
  
  val cm = Array(
    Array(Complex(1.0, 2.0), Complex(3.0, 4.0)),
    Array(Complex(5.0, 6.0), Complex(7.0, 8.0)))

  "Matrix.eye" should "construct eye matrix" in {
    Matrix.eye[Int](1) shouldEqual Array(
      Array(1))
    Matrix.eye[Double](1) shouldEqual Array(
      Array(1)).map(_ map (_.toDouble))
    Matrix.eye[Complex](1) shouldEqual Array(
      Array(1)).map(_ map (_.toComplex))
    Matrix.eye[Int](2) shouldEqual Array(
      Array(1, 0),
      Array(0, 1))
    Matrix.eye[Double](2) shouldEqual Array(
      Array(1, 0),
      Array(0, 1)).map(_ map (_.toDouble))
    Matrix.eye[Complex](2) shouldEqual Array(
      Array(1, 0),
      Array(0, 1)).map(_ map (_.toComplex))
    Matrix.eye[Int](3) shouldEqual Array(
      Array(1, 0, 0),
      Array(0, 1, 0),
      Array(0, 0, 1))
    Matrix.eye[Double](3) shouldEqual Array(
      Array(1, 0, 0),
      Array(0, 1, 0),
      Array(0, 0, 1)).map(_ map (_.toDouble))
    Matrix.eye[Complex](3) shouldEqual Array(
      Array(1, 0, 0),
      Array(0, 1, 0),
      Array(0, 0, 1)).map(_ map (_.toComplex))
    Matrix.eye[Int](4) shouldEqual Array(
      Array(1, 0, 0, 0),
      Array(0, 1, 0, 0),
      Array(0, 0, 1, 0),
      Array(0, 0, 0, 1))
    Matrix.eye[Double](4) shouldEqual Array(
      Array(1, 0, 0, 0),
      Array(0, 1, 0, 0),
      Array(0, 0, 1, 0),
      Array(0, 0, 0, 1)).map(_ map (_.toDouble))
    Matrix.eye[Complex](4) shouldEqual Array(
      Array(1, 0, 0, 0),
      Array(0, 1, 0, 0),
      Array(0, 0, 1, 0),
      Array(0, 0, 0, 1)).map(_ map (_.toComplex))
  }
  
  "Matrix" should "multiply by element of same type correctly" in {
    im * 2 shouldEqual Array(
      Array(2, 4),
      Array(6, 8))
    dm * 0.5 shouldEqual Array(
      Array(0.5, 1.0),
      Array(1.5, 2.0))
    cm * (1 + Complex.i) shouldEqual Array(
      Array(Complex(-1.0,  3.0), Complex(-1.0,  7.0)),
      Array(Complex(-1.0, 11.0), Complex(-1.0, 15.0)))
  }
  
  it should "multiply by matrix of same type correctly" in {
    im * im shouldEqual Array(
      Array( 7, 10),
      Array(15, 22))
    dm * dm shouldEqual Array(
      Array( 7.0, 10.0),
      Array(15.0, 22.0))
    cm * cm shouldEqual Array(
      Array(Complex(-12.0, 42.0), Complex(-16.0,  62.0)),
      Array(Complex(-20.0, 98.0), Complex(-24.0, 150.0)))
  }
  
  it should "check approximate equality correctly" in {
    (im ~= im) shouldBe true
    (dm ~= dm) shouldBe true
    (cm ~= cm) shouldBe true
  }
  
  it should "compute Hermitian transpose correctly" in {
    im.hermTrans shouldEqual im.transpose
    dm.hermTrans shouldEqual dm.transpose
    cm.hermTrans shouldEqual Array(
      Array(Complex(1.0, -2.0), Complex(5.0, -6.0)),
      Array(Complex(3.0, -4.0), Complex(7.0, -8.0)))
    val cm1 = Array(
      Array(Complex.zero,    Complex.i, Complex.zero, Complex.zero),
      Array(  -Complex.i, Complex.zero, Complex.zero, Complex.zero),
      Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
      Array(Complex.zero, Complex.zero, Complex.zero,  Complex.one))
    cm1.hermTrans shouldEqual cm1
  }

}
