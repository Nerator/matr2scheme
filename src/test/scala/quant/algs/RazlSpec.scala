package quant.algs

import breeze.linalg.{DenseMatrix, convert}
import breeze.math.Complex
import org.scalactic.Equality
import org.scalatest._

import quant.implicits._

class RazlSpec extends FlatSpec with Matchers {

  // Переопределение равенства комплексных матриц для ScalaTest - с
  // использованием приближенного равенства
  implicit val meq: Equality[DenseMatrix[Complex]] = (a, b) => a isClose b

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

  // Tests
  // TODO: do we need exact tests?

  "Algorithm" should "work correctly for eye matrix" in {
    val m = DenseMatrix.eye[Complex](4)
    val r1 = Razl.algNielsenChang(m)
    val r2 = Razl.algNakaharaOhmi(m)

    r1 reduce (_ * _) shouldEqual m
    r2 reduce (_ * _) shouldEqual m
  }

  it should "work correctly for m01" in {
    val r1 = Razl.algNielsenChang(m01)
    val r2 = Razl.algNakaharaOhmi(m01)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, -1, 0),
//          Array(0, 1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, -1),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, -1),
//          Array(0, 0, -1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}: ${r.map(_.toString(Int.MaxValue, Int.MaxValue))}")
//    }
    r1 reduce (_ * _) shouldEqual m01
    r2 reduce (_ * _) shouldEqual m01
  }

  it should "work correctly for m02" in {
    val r1 = Razl.algNielsenChang(m02)
    val r2 = Razl.algNakaharaOhmi(m02)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, 0, -1),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(1, 0, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, -1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, -1),
//          Array(0, 0, -1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m02
    r2 reduce (_ * _) shouldEqual m02
  }

  it should "work correctly for m03" in {
    val r1 = Razl.algNielsenChang(m03)
    val r2 = Razl.algNakaharaOhmi(m03)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, -1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, -1),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m03
    r2 reduce (_ * _) shouldEqual m03
  }

  it should "work correctly for m04" in {
    val r1 = Razl.algNielsenChang(m04)
    val r2 = Razl.algNakaharaOhmi(m04)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, -1, 0),
//          Array(0, 1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, -1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m04
    r2 reduce (_ * _) shouldEqual m04
  }

  it should "work correctly for m05" in {
    val r1 = Razl.algNielsenChang(m05)
    val r2 = Razl.algNakaharaOhmi(m05)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, 0, -1),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(1, 0, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0),
//          Array(0, -1, 0, 0)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m05
    r2 reduce (_ * _) shouldEqual m05
  }

  it should "work correctly for m06" in {
    val r1 = Razl.algNielsenChang(m06)
    val r2 = Razl.algNakaharaOhmi(m06)
//    r match {
//      case List(m1,m2,m3) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m06
    r2 reduce (_ * _) shouldEqual m06
  }

  it should "work correctly for m07" in {
    val r1 = Razl.algNielsenChang(m07)
    val r2 = Razl.algNakaharaOhmi(m07)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m07
    r2 reduce (_ * _) shouldEqual m07
  }

  it should "work correctly for m08" in {
    val r1 = Razl.algNielsenChang(m08)
    val r2 = Razl.algNakaharaOhmi(m08)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m08
    r2 reduce (_ * _) shouldEqual m08
  }

  it should "work correctly for m09" in {
    val r1 = Razl.algNielsenChang(m09)
    val r2 = Razl.algNakaharaOhmi(m09)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual DenseMatrix(
//          Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
//          Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
//          Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
//          Array(Complex.zero, Complex.zero, Complex.zero,  Complex.one)
//        )
//        m2 shouldEqual DenseMatrix(
//          Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
//          Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
//          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
//          Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero)
//        )
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m09
    r2 reduce (_ * _) shouldEqual m09
  }

  it should "work correctly for m10" in {
    val r1 = Razl.algNielsenChang(m10)
    val r2 = Razl.algNakaharaOhmi(m10)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, 0, 1),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(1, 0, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m10
    r2 reduce (_ * _) shouldEqual m10
  }

  it should "work correctly for m11" in {
    val r1 = Razl.algNielsenChang(m11)
    val r2 = Razl.algNakaharaOhmi(m11)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual DenseMatrix(
//          Array( Complex.one, Complex.zero, Complex.zero, Complex.zero),
//          Array(Complex.zero,  Complex.one, Complex.zero, Complex.zero),
//          Array(Complex.zero, Complex.zero,  Complex.one, Complex.zero),
//          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i)
//        )
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m11
    r2 reduce (_ * _) shouldEqual m11
  }

  it should "work correctly for m12" in {
    val r1 = Razl.algNielsenChang(m12)
    val r2 = Razl.algNakaharaOhmi(m12)
//    r match {
//      case List(m1,m2,m3,m4,m5,m6) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array( math.sqrt(1.0/2.0),  math.sqrt(1.0/2.0),  0.0,  0.0),
//          Array( math.sqrt(1.0/2.0), -math.sqrt(1.0/2.0),  0.0,  0.0),
//          Array(                0.0,                 0.0,  1.0,  0.0),
//          Array(                0.0,                 0.0,  0.0,  1.0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array( math.sqrt(2.0/3.0),  0.0,  math.sqrt(1.0/3.0),  0.0),
//          Array(                0.0,  1.0,                 0.0,  0.0),
//          Array( math.sqrt(1.0/3.0),  0.0, -math.sqrt(2.0/3.0),  0.0),
//          Array(                0.0,  0.0,                 0.0,  1.0)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(math.sqrt(3.0/4.0), 0.0, 0.0,             1.0/2.0),
//          Array(               0.0, 1.0, 0.0,                 0.0),
//          Array(               0.0, 0.0, 1.0,                 0.0),
//          Array(           1.0/2.0, 0.0, 0.0, -math.sqrt(3.0/4.0))
//        ), Complex)
//        m4 shouldEqual DenseMatrix(
//          Array( Complex.one,                      Complex.zero,                       Complex.zero, Complex.zero),
//          Array(Complex.zero, math.sqrt(3.0/16.0)*(1-Complex.i),              (3.0 - Complex.i)/4.0, Complex.zero),
//          Array(Complex.zero,             (3.0 + Complex.i)/4.0, math.sqrt(3.0/16.0)*(-1-Complex.i), Complex.zero),
//          Array(Complex.zero,                      Complex.zero,                       Complex.zero,  Complex.one)
//        )
//        m5 shouldEqual DenseMatrix(
//          Array( Complex.one,                   Complex.zero, Complex.zero,                    Complex.zero),
//          Array(Complex.zero, Complex(math.sqrt(2.0/3.0), 0), Complex.zero, -Complex.i * math.sqrt(1.0/3.0)),
//          Array(Complex.zero,                   Complex.zero,  Complex.one,                    Complex.zero),
//          Array(Complex.zero, Complex.i * math.sqrt(1.0/3.0), Complex.zero, Complex(-math.sqrt(2.0/3.0), 0))
//        )
//        m6 shouldEqual DenseMatrix(
//          Array( Complex.one, Complex.zero,                    Complex.zero,                   Complex.zero),
//          Array(Complex.zero,  Complex.one,                    Complex.zero,                   Complex.zero),
//          Array(Complex.zero, Complex.zero,  Complex(math.sqrt(1.0/2.0), 0), Complex.i * math.sqrt(1.0/2.0)),
//          Array(Complex.zero, Complex.zero, Complex(-math.sqrt(1.0/2.0), 0), Complex.i * math.sqrt(1.0/2.0))
//        )
//      case _ => fail(s"Didn't get 6 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m12
    r2 reduce (_ * _) shouldEqual m12
  }

  it should "work correctly for m13" in {
    val r1 = Razl.algNielsenChang(m13)
    val r2 = Razl.algNakaharaOhmi(m13)
//    r match {
//      case List(m1) =>
//        m1 shouldEqual m13
//      case _ => fail(s"Didn't get 1 matrix, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m13
    r2 reduce (_ * _) shouldEqual m13
  }

  it should "work correctly for m14" in {
    val r1 = Razl.algNielsenChang(m14)
    val r2 = Razl.algNakaharaOhmi(m14)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array( 1,  0,  0,  0),
//          Array( 0, -1,  0,  0),
//          Array( 0,  0,  1,  0),
//          Array( 0,  0,  0,  1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array( 1,  0,  0,  0),
//          Array( 0,  1,  0,  0),
//          Array( 0,  0, -1,  0),
//          Array( 0,  0,  0, -1)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m14
    r2 reduce (_ * _) shouldEqual m14
  }

  it should "work correctly for m15" in {
    val r1 = Razl.algNielsenChang(m15)
    val r2 = Razl.algNakaharaOhmi(m15)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array( 1,  0,  0,  0),
//          Array( 0,  0, -1,  0),
//          Array( 0, -1,  0,  0),
//          Array( 0,  0,  0,  1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array( 1,  0,  0,  0),
//          Array( 0,  1,  0,  0),
//          Array( 0,  0,  1,  0),
//          Array( 0,  0,  0, -1)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m15
    r2 reduce (_ * _) shouldEqual m15
  }

  it should "work correctly for m16" in {
    val r1 = Razl.algNielsenChang(m16)
    val r2 = Razl.algNakaharaOhmi(m16)
//    r match {
//      case List(m1,m2,m3,m4,m5,m6) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1)
//        ), Complex)
//        m4 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1)
//        ), Complex)
//        m5 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1)
//        ), Complex)
//        m6 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 0, 0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 6 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m16
    r2 reduce (_ * _) shouldEqual m16
  }

  it should "work correctly for m17" in {
    val r1 = Razl.algNielsenChang(m17)
    val r2 = Razl.algNakaharaOhmi(m17)
//    r match {
//      case List(m1,m2,m3,m4,m5,m6) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0)
//        ), Complex)
//        m4 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0)
//        ), Complex)
//        m5 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 0, 0, 0, 1, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0)
//        ), Complex)
//        m6 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0, 0, 0, 0, 0),
//          Array(0, 1, 0, 0, 0, 0, 0, 0),
//          Array(0, 0, 1, 0, 0, 0, 0, 0),
//          Array(0, 0, 0, 1, 0, 0, 0, 0),
//          Array(0, 0, 0, 0, 1, 0, 0, 0),
//          Array(0, 0, 0, 0, 0, 1, 0, 0),
//          Array(0, 0, 0, 0, 0, 0, 0, 1),
//          Array(0, 0, 0, 0, 0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 6 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m17
    r2 reduce (_ * _) shouldEqual m17
  }

  it should "work correctly for m18" in {
    val r1 = Razl.algNielsenChang(m18)
    val r2 = Razl.algNakaharaOhmi(m18)
//    r match {
//      case List(m1,m2) =>
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 2 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m18
    r2 reduce (_ * _) shouldEqual m18
  }

  it should "work correctly for m19" in {
    val r1 = Razl.algNielsenChang(m19)
    val r2 = Razl.algNakaharaOhmi(m19)
//    r match {
//      case List(m1,m2,m3) =>
//        val b = math.sqrt(1.0/2.0)
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, 0, 1),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(1, 0, 0, 0)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1.0, 0.0, 0.0, 0.0),
//          Array(0.0,   b, 0.0,   b),
//          Array(0.0, 0.0, 1.0, 0.0),
//          Array(0.0,   b, 0.0,  -b)
//        ), Complex)
//      case _ => fail(s"Didn't get 3 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m19
    r2 reduce (_ * _) shouldEqual m19
  }

  it should "work correctly for m20" in {
    val r1 = Razl.algNielsenChang(m20)
    val r2 = Razl.algNakaharaOhmi(m20)
//    r match {
//      case List(m1,m2,m3,m4) =>
//        val b = math.sqrt(1.0/2.0)
//        m1 shouldEqual convert(DenseMatrix(
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0),
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1)
//        ), Complex)
//        m2 shouldEqual convert(DenseMatrix(
//          Array(  b, 0.0, 0.0,   b),
//          Array(0.0, 1.0, 0.0, 0.0),
//          Array(0.0, 0.0, 1.0, 0.0),
//          Array(  b, 0.0, 0.0,  -b)
//        ), Complex)
//        m3 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0),
//          Array(0, 1, 0, 0)
//        ), Complex)
//        m4 shouldEqual convert(DenseMatrix(
//          Array(1, 0, 0, 0),
//          Array(0, 1, 0, 0),
//          Array(0, 0, 0, 1),
//          Array(0, 0, 1, 0)
//        ), Complex)
//      case _ => fail(s"Didn't get 4 matrices, got ${r.length}")
//    }
    r1 reduce (_ * _) shouldEqual m20
    r2 reduce (_ * _) shouldEqual m20
  }

}
