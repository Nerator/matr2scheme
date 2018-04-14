package quant

import matrix.MatrixElement._
import scala.reflect.ClassTag

package object matrix {

  type Matrix[T] = Array[Array[T]]

  object Matrix {
    def eye[T: ClassTag](n: Int)(implicit ev: MatrixElement[T]): Matrix[T] =
      Array.tabulate(n, n) {
        case (i,j) => if (i == j) ev.one else ev.zero
      }
  }

  implicit class MatrixOps[T: ClassTag](m: Matrix[T])(implicit ev: MatrixElement[T]) {
    def *(that: T): Matrix[T] =
      m map (_ map (_ * that))
    
    def *(that: Matrix[T]): Matrix[T] =
      for (r <- m)
      yield for (c <- that.transpose)
      yield r zip c map {case (x,y) => x * y} reduceLeft (_ + _)
     
    def ~=(that: Matrix[T]): Boolean =
      (for (i <- 0 until m.length; j <- 0 until m.head.length)
      yield m(i)(j) ~= that(i)(j))
        .reduceLeft(_ && _)

    def prettyToString = 
      //(m map ((r => r map (c => f"${c.toString}16s")).mkString("(", " ", ")"))).mkString("\n")
    (m map (r => (r map (c => f"${c.toString}%16s")).mkString("(", " ", ")"))).mkString("\n")
    
    def hermTrans = (m map (_ map (_.conj))).transpose
  }

}
