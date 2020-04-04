package quant.math

import cats.syntax.foldable._
import cats.syntax.order._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import quant.Arbs
import quant.Arbs._
import quant.instances.matrix._
import spire.math.{Complex, Real}

class MatrixProperties extends Properties("Matrix") {
  def genSquareSizeMatrix[A: Arbitrary]: Gen[Matrix[A]] = for {
    size <- Gen.choose(1, 32)
    m <- Gen.listOfN(size, Gen.listOfN(size, Arbitrary.arbitrary[A]).map(_.toVector)).map(_.toVector).map(Matrix.apply)
  } yield m

  property("isSquare") =
    forAll(genSquareSizeMatrix[Complex[Real]]) { m: Matrix[Complex[Real]] =>
      m.isSquare
    } && forAll(Arbs.arbMtrx[Complex[Real]].arbitrary.suchThat(m => m.rows != m.cols)) { m: Matrix[Complex[Real]] =>
      !m.isSquare
    }
  property("scalar mult by zero") =
    forAll { m: Matrix[Complex[Real]] =>
      (Complex.zero[Real] *: m) === Matrix.zeros[Complex[Real]](m.rows, m.cols)
    }

  property("scalar mult by one") =
    forAll { m: Matrix[Complex[Real]] =>
      //      import cats.syntax.show._
      //      import quant.instances.complex._
      //      println(s"Generated:\n${m.show}")
      (Complex.one[Real] *: m) === m
    }

  property("scalar mult") =
    forAll { (m: Matrix[Complex[Real]], s: Complex[Real]) =>
      import cats.implicits._
      val multed = s *: m
      (m zip multed).forall {
        case (vm, vmult) => s * vm === vmult
      }
    }

  def genUnitaryComplexRealMatrix1x1: Gen[Matrix[Complex[Real]]] = for {
    ar <- arbReal.arbitrary.suchThat(r => (r ** 2) < Real.one)
  } yield Matrix(Vector(Vector(Complex(ar, (1 - ar ** 2).sqrt))))

  property("unitary 1x1") = forAll(genUnitaryComplexRealMatrix1x1) { m =>
    Matrix.isUnitary(m)
  }

  // Companion object functions properties

  property("zeros") = forAll(Gen.choose(1, 32), Gen.choose(1, 32)) { (i, j) =>
    Matrix.zeros[Complex[Real]](i, j).forall(_ === Complex.zero[Real])
  }

  property("ones") = forAll(Gen.choose(1, 32)) { i =>
    Matrix.eye[Complex[Real]](i).zipWithIndex.forall {
      case (v, r, c) => r == c && v === Complex.one[Real] || r != c && v === Complex.zero[Real]
    }
  }
}
