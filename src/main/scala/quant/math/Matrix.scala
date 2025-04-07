package quant.math

import algebra.ring.{
  AdditiveMonoid,
  CommutativeRing,
  MultiplicativeSemigroup,
  Rig
}
import cats.instances.int._
import cats.kernel.Eq
import cats.syntax.eq._
import cats.syntax.functor._
import cats.syntax.parallel._
import quant.instances.matrix._
import spire.algebra.Signed
import spire.math.Complex
import spire.syntax.multiplicativeSemigroup._

class Matrix[T](private[quant] val m: Vector[Vector[T]]) extends Serializable {
  def rows: Int = m.size

  def cols: Int = m.headOption.map(_.size).getOrElse(0)

  def row(i: Int): Vector[T] = m(i)

  def col(j: Int): Vector[T] = m map (_.apply(j))

  def apply(i: Int, j: Int): T = m(i)(j)

  def *:(that: T)(implicit rigA: MultiplicativeSemigroup[T]): Matrix[T] =
    this map (_ * that)

  def :*(that: T)(implicit rigA: MultiplicativeSemigroup[T]): Matrix[T] =
    this map (_ * that)

  def replaceSquareAt(i1: Int, i2: Int, rep: Matrix[T]): Matrix[T] =
    Matrix.vvReplaceSquareAt(this)(i1, i2, rep)

  def transpose: Matrix[T] = Matrix(m.transpose)

  def isSquare: Boolean = rows === cols

  def zip[S](that: Matrix[S]): Matrix[(T, S)] =
    Matrix((m, that.m).parTupled.map(_.parTupled))

  def zipWith[S, R](that: Matrix[S])(f: (T, S) => R): Matrix[R] =
    Matrix((m, that.m).parMapN { case (r1, r2) =>
      (r1, r2).parMapN(f)
    })

  def zipWithIndex[A]: Matrix[(T, Int, Int)] =
    Matrix(m.zipWithIndex.map { case (r, ri) =>
      r.zipWithIndex.map { case (v, ci) =>
        (v, ri, ci)
      }
    })
}

object Matrix {
  def apply[T](v: Vector[T]*) = new Matrix(v.toVector)

  def apply[T](vvt: Vector[Vector[T]]) = new Matrix(vvt)

  def eye[T: Rig](size: Int): Matrix[T] =
    new Matrix(
      Vector.tabulate(size, size)((r, c) =>
        if (r === c) Rig[T].one else Rig[T].zero
      )
    )

  def zeros[T: AdditiveMonoid](rows: Int, cols: Int): Matrix[T] =
    new Matrix[T](Vector.tabulate(rows, cols)((_, _) => AdditiveMonoid[T].zero))

  def isUnitary[T: CommutativeRing: Eq: Signed](
      m: Matrix[Complex[T]]
  ): Boolean =
    (m * m.map(_.conjugate).transpose) === eye[Complex[T]](m.rows)

  // TODO: rep should be 1x2
  private def vReplaceSquareAt[T](
      v: Vector[T]
  )(j1: Int, j2: Int, rep: Vector[T]): Vector[T] = {
    require(
      rep.size === 2,
      s"Replacement vector should have size 2, got: ${rep.size}"
    )
    v.zipWithIndex.foldLeft(Vector.empty[T]) { case (vt, (t, ind)) =>
      if (ind === j1)
        vt :+ rep(0)
      else if (ind === j2)
        vt :+ rep(1)
      else
        vt :+ t
    }
  }

  // TODO: rep should be 2x2
  private def vvReplaceSquareAt[T](
      vv: Matrix[T]
  )(i1: Int, i2: Int, rep: Matrix[T]): Matrix[T] = {
    require(
      rep.cols === 2 && rep.rows === 2,
      s"Replacement matrix should 2x2, got: ${rep.rows}x${rep.cols}"
    )
    Matrix(vv.m.zipWithIndex.foldLeft(Vector.empty[Vector[T]]) {
      case (vvt, (vt, indR)) =>
        if (indR === i1)
          vvt :+ vReplaceSquareAt(vt)(i1, i2, rep.row(0))
        else if (indR === i2)
          vvt :+ vReplaceSquareAt(vt)(i1, i2, rep.row(1))
        else
          vvt :+ vt
    })
  }
}
