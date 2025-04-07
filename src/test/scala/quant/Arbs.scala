package quant

import org.scalacheck.{Arbitrary, Gen}
import quant.math.Matrix
import spire.math.{Complex, Rational, Real}

object Arbs {
  implicit def arbReal: Arbitrary[Real] = Arbitrary(for {
    l1 <- Gen.choose(Long.MinValue, Long.MaxValue)
    l2 <- Gen.choose(Long.MinValue, Long.MaxValue)
  } yield Real(Rational(l1, l2)))

  implicit def arbCmplx[A: Arbitrary]: Arbitrary[Complex[A]] = Arbitrary(for {
    re <- Arbitrary.arbitrary[A]
    im <- Arbitrary.arbitrary[A]
  } yield Complex(re, im))

  implicit def arbMtrx[A: Arbitrary]: Arbitrary[Matrix[A]] = {
    val abc = for {
      rows <- Gen.oneOf(1 to 100)
      cols <- Gen.oneOf(1 to 100)
      l <- Gen
        .listOfN(
          rows,
          Gen.listOfN(cols, Arbitrary.arbitrary[A]).map(_.toVector)
        )
        .map(_.toVector)
    } yield l
    Arbitrary(abc map (vvt => Matrix.apply(vvt)))
  }
}
