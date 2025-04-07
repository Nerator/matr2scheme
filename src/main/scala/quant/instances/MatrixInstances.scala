package quant.instances

import algebra.ring.{AdditiveCommutativeSemigroup, MultiplicativeSemigroup}
import cats.instances.int._
import cats.instances.vector._
import cats.kernel.Eq
import cats.syntax.eq._
import cats.syntax.foldable._
import cats.syntax.show._
import cats.{Eval, Foldable, Functor, Show}
import quant.math.Matrix
import spire.syntax.additiveSemigroup._
import spire.syntax.multiplicativeSemigroup._

trait MatrixInstances {
  implicit def matrixShowInstance[A: Show]: Show[Matrix[A]] = (t: Matrix[A]) =>
    {
      val vvShow = t.m map (_ map (_.show))
      val maxLen = vvShow.flatten.map(_.length).maximumOption
      maxLen.fold("") { mL =>
        vvShow
          .map(_.map(t => {
            val l = t.length
            if (l < mL) " " * (mL - l) + t else t
          }).mkString(" "))
          .mkString("\n")
      }
    }

  implicit def matrixEqInstance[A: Eq]: Eq[Matrix[A]] =
    (x: Matrix[A], y: Matrix[A]) => {
      if ((x.rows === y.rows) && (x.cols === y.cols)) {
        (x.m zip y.m).forall { case (vx, vy) =>
          (vx zip vy).forall { case (xv, yv) =>
            xv === yv
          }
        }
      } else false
    }

  // TODO: should be with types: we can only add matrices of same size
  implicit def matrixAdditiveCommutativeSemigroupInstance[
      A: AdditiveCommutativeSemigroup
  ]: AdditiveCommutativeSemigroup[Matrix[A]] =
    (x: Matrix[A], y: Matrix[A]) => {
      // FIXME: dirty, may throw an exception
      require(
        x.rows === y.rows && x.cols === y.cols,
        s"Matrices should have same size for addition: got sizes (${x.rows}, ${x.cols}) and (${y.rows}, ${y.cols})"
      )
      Matrix((x.m zip y.m).map { case (r1, r2) =>
        (r1 zip r2).map { case (e1, e2) =>
          e1 + e2
        }
      })
    }

  implicit def matrixMultiplicativeSemigroupInstance[
      A: MultiplicativeSemigroup: AdditiveCommutativeSemigroup
  ]: MultiplicativeSemigroup[Matrix[A]] =
    (x: Matrix[A], y: Matrix[A]) => {
      // FIXME: dirty, may throw an exception
      require(
        x.cols === y.rows,
        s"First matrix columns was not equal second matrix rows: ${x.cols} and ${x.rows}"
      )
      Matrix(
        x.m.map(r =>
          y.m.transpose.map(c =>
            (r zip c)
              .map { case (rv, cv) =>
                rv * cv
              }
              .reduce(_ + _)
          )
        )
      )
    }

  implicit val matrixFunctorInstance: Functor[Matrix] = new Functor[Matrix] {
    override def map[A, B](fa: Matrix[A])(f: A => B): Matrix[B] =
      Matrix(fa.m.map(_.map(f)))
  }

  implicit val matrixFoldableInstance: Foldable[Matrix] = new Foldable[Matrix] {
    override def foldLeft[A, B](fa: Matrix[A], b: B)(f: (B, A) => B): B =
      Foldable[Vector].foldLeft(fa.m.flatten, b)(f)

    override def foldRight[A, B](fa: Matrix[A], lb: Eval[B])(
        f: (A, Eval[B]) => Eval[B]
    ): Eval[B] =
      Foldable[Vector].foldRight(fa.m.flatten, lb)(f)
  }
}
