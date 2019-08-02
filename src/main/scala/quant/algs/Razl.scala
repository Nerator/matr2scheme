package quant.algs

import breeze.linalg.{DenseMatrix, convert, isClose}
import breeze.math.Complex

import quant.implicits._

object Razl {

  // As most of the code for algorithm is the same, we extract it in separate private method
  private def alg(m: DenseMatrix[Complex], method: Method): List[DenseMatrix[Complex]] = {
    // Edge case: eye matrix
    if (m isClose DenseMatrix.eye[Complex](m.rows))
      List(m)
    else {
      // Constructing list of matrix indexes below main diagonal
      val ij = for {
        j <- 0 until m.cols
        i <- 0 until m.rows if j < i
      } yield (i,j)

      // sanity check
      require(ij.length == (1 until m.cols).sum)

      ij.foldLeft((List[DenseMatrix[Complex]](), m)) {
        case ((acc, mat), (i,j)) =>
          val res = DenseMatrix.eye[Complex](m.rows)

          if (i == mat.rows - 1 && j == mat.rows - 2) {
            res(j, j) = mat(j, j).conjugate
            res(j, i) = mat(i, j).conjugate
            res(i, j) = mat(j, i).conjugate
            res(i, i) = mat(i, i).conjugate
          } else if (!isClose(mat(j, j), Complex.one)) {
            val n = convert(math.sqrt(math.pow(mat(j, j).abs, 2.0) + math.pow(mat(i, j).abs, 2.0)), Complex)
            if (!isClose(n, Complex.zero)) {
              res(j, j) = mat(j, j).conjugate / n
              res(j, i) = mat(i, j).conjugate / n
              // Only difference between Nielsen and Nakahara methods
              method match {
                case Nielsen =>
                  res(i, j) = mat(i, j) / n
                  res(i, i) = -mat(j, j) / n
                case Nakahara =>
                  res(i, j) = -mat(i, j) / n
                  res(i, i) = mat(j, j) / n
              }
            }
          }
          (res.t :: acc, res * mat)
      }._1.reverse.filterNot(_.isClose(DenseMatrix.eye[Complex](m.rows)))
    }
  }

  // Source: Nielsen & Chang
  def algNielsenChang(m: DenseMatrix[Complex]): List[DenseMatrix[Complex]] = alg(m, Nielsen)

  // Source: Nakahara & Ohmi
  def algNakaharaOhmi(m: DenseMatrix[Complex]): List[DenseMatrix[Complex]] = alg(m, Nakahara)

  private sealed trait Method
  private case object Nielsen extends Method
  private case object Nakahara extends Method

}
