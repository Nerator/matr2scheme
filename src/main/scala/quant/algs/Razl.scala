package quant.algs

import breeze.linalg.{DenseMatrix, all, convert, isClose}
import breeze.math.Complex

import quant.implicits._

object Razl {

  def findNonzero(m: DenseMatrix[Complex]): (Int, Int) = {    
    for (j <- 0 until m.rows) {
      if (j != m.rows-1 && (isClose(m(j,j), -Complex.one))) {
        return (j+1,j)
      }
      if (!isClose(m(j,j), Complex.one)) {
        for (i <- j + 1 until m.cols) {
          if (!isClose(m(i,j), Complex.zero)) {
            return (i, j)
          }
        }
      }
    }
    (m.rows-1,m.rows-2)
  }

  private def findU(m: DenseMatrix[Complex], i: Int, j: Int): DenseMatrix[Complex] = {
    require(j < i, s"j = $j, i = $i\n${m.toString}")
    val res = DenseMatrix.eye[Complex](m.rows)

    if (i == m.rows-1 && j == m.rows-2) {
      res(j,j) = m(j,j).conjugate
      res(j,i) = m(i,j).conjugate
      res(i,j) = m(j,i).conjugate
      res(i,i) = m(i,i).conjugate
    } else {
      val n = convert(math.sqrt(math.pow(m(j,j).abs, 2.0) + math.pow(m(i,j).abs, 2.0)), Complex)
      res(j,j) = m(j,j).conjugate / n
      res(j,i) = m(i,j).conjugate / n
      res(i,j) = m(i,j) / n
      res(i,i) = -m(j,j) / n
    }
    res
  }

  def alg(m: DenseMatrix[Complex]): List[DenseMatrix[Complex]] = {
    if (all(isClose(m, DenseMatrix.eye[Complex](m.rows))))
      Nil
    else {
      val (i,j) = findNonzero(m)
      val u = findU(m, i, j)
      val newM = u * m
      u.t :: alg(newM)
    }
  }

}
