package quant.instances

import algebra.ring.Ring
import cats.kernel.laws.discipline.EqTests
import cats.laws.discipline.{FoldableTests, FunctorTests}
import cats.syntax.eq._
import cats.syntax.functor._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Cogen, Gen}
import org.typelevel.discipline.Predicate
import quant.Arbs._
import quant.instances.matrix._
import quant.math.Matrix
import spire.laws.RingLaws
import spire.math.{Complex, Real}
import spire.syntax.additiveSemigroup._

class MatrixTests extends munit.ScalaCheckSuite with munit.DisciplineSuite {
  implicit val cogenReal: Cogen[Real] = Cogen(_.toLong)

  implicit def arbFMtrx[A: Ring]: Arbitrary[Matrix[A] => Matrix[A]] = {
    import spire.syntax.ring._
    Arbitrary(
      Gen.oneOf[Matrix[A] => Matrix[A]](
        (m: Matrix[A]) => m map (_ + Ring[A].one),
        (m: Matrix[A]) => m map (_ * (Ring[A].one + Ring[A].one)),
        (m: Matrix[A]) => m map Ring[A].negate
      )
    )
  }

  def staticSizeMatrixArb[A: Arbitrary](size: Int): Arbitrary[Matrix[A]] =
    Arbitrary(
      Gen
        .listOfN(
          size,
          Gen.listOfN(size, Arbitrary.arbitrary[A]).map(_.toVector)
        )
        .map(_.toVector)
        .map(Matrix.apply)
    )

  // Needed for RingLaws construction (not for semigroup tests)
  val p: Predicate[Matrix[Complex[Real]]] = Predicate.const(true)

  // Cats kernel laws
  checkAll("EqLaws", EqTests[Matrix[Complex[Real]]].eqv)
  // Cats laws
  checkAll("FunctorLaws", FunctorTests[Matrix].functor[Real, Double, Int])
  checkAll("FoldableLaws", FoldableTests[Matrix].foldable[Double, Int])
  // Spire laws (on square matrices, because of exceptions)
  checkAll(
    "RingLaws.additive",
    RingLaws[Matrix[Complex[Real]]](using
      matrixEqInstance,
      staticSizeMatrixArb(32),
      p
    ).additiveSemigroup
  )
  // Additional test for commutativity
  property("csemigroup.commutative") {
    forAll(
      staticSizeMatrixArb[Complex[Real]](32).arbitrary,
      staticSizeMatrixArb[Complex[Real]](32).arbitrary
    ) { case (a, b) =>
      a + b === b + a
    }
  }
  checkAll(
    "RingLaws.multiplicative",
    RingLaws[Matrix[Complex[Real]]](using
      matrixEqInstance,
      staticSizeMatrixArb(4),
      p
    ).multiplicativeSemigroup
  )
}
