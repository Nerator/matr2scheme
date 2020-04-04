package quant.algs

import cats.data.Chain
import cats.instances.int._
import cats.syntax.eq._
import cats.syntax.functor._
import quant.instances.matrix._
import quant.math.Matrix
import spire.math.{Complex, Real}
import spire.syntax.multiplicativeSemigroup._

object Razl {
  // As most of the code for algorithm is the same, we extract it in separate private method
  private def alg(m: Matrix[Complex[Real]], method: Method): List[Matrix[Complex[Real]]] = {
    // Edge case: eye matrix
    if (m === Matrix.eye[Complex[Real]](m.rows))
      List(m)
    else {
      // Constructing list of matrix indexes below main diagonal
      val ij = for {
        j <- 0 until m.cols
        i <- 0 until m.rows if j < i
      } yield (i, j)

      val (lst, _) = ij.foldLeft((Chain.empty[Matrix[Complex[Real]]], m)) {
        case ((acc, mat), (i, j)) =>
          val res = if (i === mat.rows - 1 && j === mat.rows - 2) {
            Matrix.eye[Complex[Real]](m.rows).replaceSquareAt(j, i, Matrix(
              Vector(mat(j, j).conjugate, mat(i, j).conjugate),
              Vector(mat(j, i).conjugate, mat(i, i).conjugate)
            ))
          } else {
            val n = (mat(j, j).abs ** 2 + mat(i, j).abs ** 2).sqrt
            if (mat(j, j) =!= Complex.one[Real] && n =!= Real.zero) {
              Matrix.eye[Complex[Real]](m.rows).replaceSquareAt(j, i, Matrix(
                Vector(mat(j, j).conjugate / n, mat(i, j).conjugate / n),
                method match {
                  case Nielsen => Vector(mat(i, j) / n, -mat(j, j) / n)
                  case Nakahara => Vector(-mat(i, j) / n, mat(j, j) / n)
                }
              ))
            } else {
              Matrix.eye[Complex[Real]](m.rows)
            }
          }
          (acc :+ res.map(_.conjugate).transpose, res * mat)
      }
      lst.filterNot(_ === Matrix.eye[Complex[Real]](m.rows)).toList
    }
  }

  // Source: Nielsen & Chang
  def algNielsenChang(m: Matrix[Complex[Real]]): List[Matrix[Complex[Real]]] = alg(m, Nielsen)

  // Source: Nakahara & Ohmi
  def algNakaharaOhmi(m: Matrix[Complex[Real]]): List[Matrix[Complex[Real]]] = alg(m, Nakahara)

  private sealed trait Method

  private case object Nielsen extends Method

  private case object Nakahara extends Method

}
