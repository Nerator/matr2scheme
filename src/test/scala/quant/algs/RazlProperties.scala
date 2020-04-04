package quant.algs

import cats.syntax.eq._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import quant.ExampleMatrices
import quant.instances.matrix._
import quant.math.Matrix
import spire.math.{Complex, Real}
import spire.syntax.multiplicativeSemigroup._

object RazlProperties extends Properties("Razl") {
  def algProperty(m: Matrix[Complex[Real]]): Boolean = {
    val r1 = Razl.algNielsenChang(m)
    val r2 = Razl.algNakaharaOhmi(m)
    r1.reduce(_ * _) === m &&
      r2.reduce(_ * _) === m
  }

  property("eye") = forAll(Gen.choose(1, 32)) { i =>
    algProperty(Matrix.eye[Complex[Real]](i))
  }

  property("examples") = ExampleMatrices.razlMatrices.forall(algProperty)
}
