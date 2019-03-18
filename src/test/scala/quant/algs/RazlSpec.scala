package quant.algs

import quant.implicits._

import org.scalatest._
import org.scalactic.Equality

import breeze.math.Complex
import breeze.linalg.{DenseMatrix, isClose, convert}

class RazlSpec extends FlatSpec with Matchers {

  // Переопределение равенства комплексных матриц для ScalaTest - с
  // использованием приближенного равенства
  implicit val meq: Equality[DenseMatrix[Complex]] = (a, b) =>
    b match {
      case m: DenseMatrix[_] => breeze.linalg.all(isClose(a, m.asInstanceOf[DenseMatrix[Complex]]))
      case _ => false
    }

  // Раздел 2 задание 1

  val m01 = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0),
    Array(1, 0, 0, 0),
    Array(0, 1, 0, 0)
  ), Complex)

  val m02 = convert(DenseMatrix(
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0)
  ), Complex)

  val m03 = convert(DenseMatrix(
    Array(0, 0, 1, 0),
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0)
  ), Complex)

  val m04 = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m05 = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0)
  ), Complex)

  val m06 = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(1, 0, 0, 0),
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m07 = convert(DenseMatrix(
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0),
    Array(0, 0, 1, 0)
  ), Complex)

  val m08 = convert(DenseMatrix(
    Array(1, 0, 0, 0),
    Array(0, 0, 1, 0),
    Array(0, 0, 0, 1),
    Array(0, 1, 0, 0)
  ), Complex)

  val m09 = DenseMatrix(
    Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
    Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
    Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero)
  )

  val m10 = convert(DenseMatrix(
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0),
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0)
  ), Complex)

  // Раздел 2 задание 3

  val m11 = DenseMatrix(
    Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
    Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
    Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i)
  )

  val m12 = Complex(0.5, 0) * DenseMatrix(
    Array( Complex.one,  Complex.one,  Complex.one,  Complex.one),
    Array( Complex.one,    Complex.i, -Complex.one,   -Complex.i),
    Array( Complex.one, -Complex.one,  Complex.one, -Complex.one),
    Array( Complex.one,   -Complex.i, -Complex.one,    Complex.i)
  )

  // Раздел 2 задание 6

  val m13 = convert(DenseMatrix(
    Array(-1, 0, 0, 0),
    Array( 0, 1, 0, 0),
    Array( 0, 0, 1, 0),
    Array( 0, 0, 0, 1)
  ), Complex)

  val m14 = convert(DenseMatrix(
    Array( 1,  0,  0,  0),
    Array( 0, -1,  0,  0),
    Array( 0,  0, -1,  0),
    Array( 0,  0,  0, -1)
  ), Complex)

  val m15 = convert(DenseMatrix(
    Array( 1,  0,  0,  0),
    Array( 0,  0, -1,  0),
    Array( 0, -1,  0,  0),
    Array( 0,  0,  0, -1)
  ), Complex)

  // Раздел 3 задание 3

  val m16 = convert(DenseMatrix(
    Array(1, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 1),
    Array(0, 1, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 1, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 1, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 1, 0)
  ), Complex)

  val m17 = convert(DenseMatrix(
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

  val m18 = convert(DenseMatrix(
    Array(0, 1, 0, 0),
    Array(1, 0, 0, 0),
    Array(0, 0, 0, 1),
    Array(0, 0, 1, 0)
  ), Complex)

  val m19 = {
    val b = math.sqrt(1.0/2.0)
    convert(DenseMatrix(
      Array(0.0,   b, 0.0,  -b),
      Array(0.0, 0.0, 1.0, 0.0),
      Array(0.0,   b, 0.0,   b),
      Array(1.0, 0.0, 0.0, 0.0)
    ), Complex)
  }

  val m20 = {
    val b = math.sqrt(1.0/2.0)
    convert(DenseMatrix(
      Array(0.0, 0.0, 0.0, 1.0),
      Array(0.0, 0.0, 1.0, 0.0),
      Array(  b,   b, 0.0, 0.0),
      Array(  b,  -b, 0.0, 0.0)
    ), Complex)
  }

  // Тесты

  "findNonzero" should "work" in {
    Razl.findNonzero(m01) shouldEqual (2,0)
    Razl.findNonzero(m02) shouldEqual (3,0)
    Razl.findNonzero(m03) shouldEqual (1,0)
    Razl.findNonzero(m04) shouldEqual (2,0)
    Razl.findNonzero(m05) shouldEqual (3,0)
    Razl.findNonzero(m06) shouldEqual (1,0)
    Razl.findNonzero(m07) shouldEqual (2,1)
    Razl.findNonzero(m08) shouldEqual (3,1)
    Razl.findNonzero(m09) shouldEqual (1,0)
    Razl.findNonzero(m10) shouldEqual (3,0)
    Razl.findNonzero(m11) shouldEqual (2,1)
    Razl.findNonzero(m12) shouldEqual (1,0)
    Razl.findNonzero(m13) shouldEqual (1,0)
    Razl.findNonzero(m14) shouldEqual (2,1)
    Razl.findNonzero(m15) shouldEqual (2,1)
    Razl.findNonzero(m16) shouldEqual (2,1)
    Razl.findNonzero(m17) shouldEqual (7,1)
    Razl.findNonzero(m18) shouldEqual (1,0)
    Razl.findNonzero(m19) shouldEqual (3,0)
    Razl.findNonzero(m20) shouldEqual (2,0)
  }

  "Algorithm" should "work correctly for m01" in {
    val r = Razl.alg(m01)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m01
  }

  it should "work correctly for m02" in {
    val r = Razl.alg(m02)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 0, 1),
          Array(0, 1, 0, 0),
          Array(0, 0, 1, 0),
          Array(1, 0, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m02
  }

  it should "work correctly for m03" in {
    val r = Razl.alg(m03)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m03
  }

  it should "work correctly for m04" in {
    val r = Razl.alg(m04)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m04
  }

  it should "work correctly for m05" in {
    val r = Razl.alg(m05)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 0, 1),
          Array(0, 1, 0, 0),
          Array(0, 0, 1, 0),
          Array(1, 0, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m05
  }

  it should "work correctly for m06" in {
    val r = Razl.alg(m06)
    r match {
      case List(m1,m2,m3) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m06
  }

  it should "work correctly for m07" in {
    val r = Razl.alg(m07)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m07
  }

  it should "work correctly for m08" in {
    val r = Razl.alg(m08)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m08
  }

  it should "work correctly for m09" in {
    val r = Razl.alg(m09)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual DenseMatrix(
          Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
          Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
          Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
          Array(Complex.zero, Complex.zero, Complex.zero,  Complex.one)
        )
        m2 shouldEqual DenseMatrix(
          Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
          Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
          Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero)
        )
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m09
  }

  it should "work correctly for m10" in {
    val r = Razl.alg(m10)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 0, 1),
          Array(0, 1, 0, 0),
          Array(0, 0, 1, 0),
          Array(1, 0, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m10
  }

  it should "work correctly for m11" in {
    val r = Razl.alg(m11)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual DenseMatrix(
          Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
          Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
          Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i)
        )
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m11
  }

  it should "work correctly for m12" in {
    val r = Razl.alg(m12)
    r match {
      case List(m1,m2,m3,m4,m5,m6) =>
        m1 shouldEqual convert(DenseMatrix(
          Array( math.sqrt(1.0/2.0),  math.sqrt(1.0/2.0),  0.0,  0.0),
          Array( math.sqrt(1.0/2.0), -math.sqrt(1.0/2.0),  0.0,  0.0),
          Array(                0.0,                 0.0,  1.0,  0.0),
          Array(                0.0,                 0.0,  0.0,  1.0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array( math.sqrt(2.0/3.0),  0.0,  math.sqrt(1.0/3.0),  0.0),
          Array(                0.0,  1.0,                 0.0,  0.0),
          Array( math.sqrt(1.0/3.0),  0.0, -math.sqrt(2.0/3.0),  0.0),
          Array(                0.0,  0.0,                 0.0,  1.0)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(math.sqrt(3.0/4.0), 0.0, 0.0,             1.0/2.0),
          Array(               0.0, 1.0, 0.0,                 0.0),
          Array(               0.0, 0.0, 1.0,                 0.0),
          Array(           1.0/2.0, 0.0, 0.0, -math.sqrt(3.0/4.0))
        ), Complex)
        m4 shouldEqual DenseMatrix(
          Array( Complex.one,                      Complex.zero,                       Complex.zero, Complex.zero),
          Array(Complex.zero, math.sqrt(3.0/16.0)*(1-Complex.i),              (3.0 - Complex.i)/4.0, Complex.zero),
          Array(Complex.zero,             (3.0 + Complex.i)/4.0, math.sqrt(3.0/16.0)*(-1-Complex.i), Complex.zero),
          Array(Complex.zero,                      Complex.zero,                       Complex.zero,  Complex.one)
        )
        m5 shouldEqual DenseMatrix(
          Array( Complex.one,                   Complex.zero, Complex.zero,                    Complex.zero),
          Array(Complex.zero, Complex(math.sqrt(2.0/3.0), 0), Complex.zero, -Complex.i * math.sqrt(1.0/3.0)),
          Array(Complex.zero,                   Complex.zero,  Complex.one,                    Complex.zero),
          Array(Complex.zero, Complex.i * math.sqrt(1.0/3.0), Complex.zero, Complex(-math.sqrt(2.0/3.0), 0))
        )
        m6 shouldEqual DenseMatrix(
          Array( Complex.one, Complex.zero,                    Complex.zero,                   Complex.zero),
          Array(Complex.zero,  Complex.one,                    Complex.zero,                   Complex.zero),
          Array(Complex.zero, Complex.zero,  Complex(math.sqrt(1.0/2.0), 0), Complex.i * math.sqrt(1.0/2.0)),
          Array(Complex.zero, Complex.zero, Complex(-math.sqrt(1.0/2.0), 0), Complex.i * math.sqrt(1.0/2.0))
        )
      case _ => fail("Didn't get 6 matrices.")
    }
    r reduce (_ * _) shouldEqual m12
  }

  it should "work correctly for m13" in {
    val r = Razl.alg(m13)
    r match {
      case List(m1) =>
        m1 shouldEqual m13
      case _ => fail("Didn't get 1 matrix.")
    }
    r reduce (_ * _) shouldEqual m13
  }

  it should "work correctly for m14" in {
    val r = Razl.alg(m14)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array( 1,  0,  0,  0),
          Array( 0, -1,  0,  0),
          Array( 0,  0,  1,  0),
          Array( 0,  0,  0,  1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array( 1,  0,  0,  0),
          Array( 0,  1,  0,  0),
          Array( 0,  0, -1,  0),
          Array( 0,  0,  0, -1)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m14
  }

  it should "work correctly for m15" in {
    val r = Razl.alg(m15)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array( 1,  0,  0,  0),
          Array( 0,  0, -1,  0),
          Array( 0, -1,  0,  0),
          Array( 0,  0,  0,  1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array( 1,  0,  0,  0),
          Array( 0,  1,  0,  0),
          Array( 0,  0,  1,  0),
          Array( 0,  0,  0, -1)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m15
  }

  it should "work correctly for m16" in {
    val r = Razl.alg(m16)
    r match {
      case List(m1,m2,m3,m4,m5,m6) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1)
        ), Complex)
        m4 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1)
        ), Complex)
        m5 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1)
        ), Complex)
        m6 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 6 matrices.")
    }
    r reduce (_ * _) shouldEqual m16
  }

  it should "work correctly for m17" in {
    val r = Razl.alg(m17)
    r match {
      case List(m1,m2,m3,m4,m5,m6) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0)
        ), Complex)
        m4 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0)
        ), Complex)
        m5 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 0, 0, 1, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0)
        ), Complex)
        m6 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 1, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 0, 0, 0),
          Array(0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 6 matrices.")
    }
    r reduce (_ * _) shouldEqual m17
  }

  it should "work correctly for m18" in {
    val r = Razl.alg(m18)
    r match {
      case List(m1,m2) =>
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 2 matrices.")
    }
    r reduce (_ * _) shouldEqual m18
  }

  it should "work correctly for m19" in {
    val r = Razl.alg(m19)
    r match {
      case List(m1,m2,m3) =>
        val b = math.sqrt(1.0/2.0)
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 0, 1),
          Array(0, 1, 0, 0),
          Array(0, 0, 1, 0),
          Array(1, 0, 0, 0)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1.0, 0.0, 0.0, 0.0),
          Array(0.0,   b, 0.0,   b),
          Array(0.0, 0.0, 1.0, 0.0),
          Array(0.0,   b, 0.0,  -b)
        ), Complex)
      case _ => fail("Didn't get 3 matrices.")
    }
    r reduce (_ * _) shouldEqual m19
  }

  it should "work correctly for m20" in {
    val r = Razl.alg(m20)
    r match {
      case List(m1,m2,m3,m4) =>
        val b = math.sqrt(1.0/2.0)
        m1 shouldEqual convert(DenseMatrix(
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0),
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1)
        ), Complex)
        m2 shouldEqual convert(DenseMatrix(
          Array(  b, 0.0, 0.0,   b),
          Array(0.0, 1.0, 0.0, 0.0),
          Array(0.0, 0.0, 1.0, 0.0),
          Array(  b, 0.0, 0.0,  -b)
        ), Complex)
        m3 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0),
          Array(0, 1, 0, 0)
        ), Complex)
        m4 shouldEqual convert(DenseMatrix(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0)
        ), Complex)
      case _ => fail("Didn't get 4 matrices.")
    }
    r reduce (_ * _) shouldEqual m20
  }

}
