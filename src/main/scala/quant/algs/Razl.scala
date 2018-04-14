package quant.algs

import quant.complex.Complex
import quant.implicits._
import quant.matrix._
import java.io.File
import java.io.PrintWriter

object Razl {
  
  def findNonzero(m: Matrix[Complex]): (Int, Int) = {    
    for (j <- 0 until m.length) {
      if (j != m.length-1 && (m(j)(j) ~= -Complex.one)) {
        return (j+1,j)
      }
      if (m(j)(j) !~= Complex.one) {
        for (i <- j + 1 until m(j).length) {
          if (m(i)(j) !~= Complex.zero) {
            return (i, j)
          }
        }
      }
    }
    (m.length-1,m.length-2)
  }

  private def findU(m: Matrix[Complex], i: Int, j: Int): Matrix[Complex] = {
    require(j < i, s"j = $j, i = $i\n${m.prettyToString}")
    val res = Matrix.eye[Complex](m.length)
    
    if (i == m.length-1 && j == m.length-2) {
      res(j)(j) = m(j)(j).conj
      res(j)(i) = m(i)(j).conj
      res(i)(j) = m(j)(i).conj
      res(i)(i) = m(i)(i).conj
    } else {
      val n = math.sqrt(math.pow(m(j)(j).abs, 2.0) + math.pow(m(i)(j).abs, 2.0)).toComplex
      res(j)(j) = m(j)(j).conj / n
      res(j)(i) = m(i)(j).conj / n
      res(i)(j) = m(i)(j) / n
      res(i)(i) = -m(j)(j) / n
    }
    res
  }

  def alg(m: Matrix[Complex]): List[Matrix[Complex]] = {
    if (m ~= Matrix.eye(m.length))
      Nil
    else {
      val (i,j) = findNonzero(m)
      val u = findU(m, i, j)
      val newM = u * m
      u.hermTrans :: alg(newM)
    }
  }

}
