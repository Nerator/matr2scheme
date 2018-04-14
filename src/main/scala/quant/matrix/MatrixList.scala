package quant.matrix

import MatrixElement._

class MatrixList[A](val matrix: List[List[A]])(implicit ev: MatrixElement[A]) {

  MatrixList.check(matrix)

  def rows = matrix.size
  def columns = matrix.head.size

  def row(i: Int) = {
    require(i <= rows)
    matrix(i)
  }
  
  def column(j: Int) = {
    require(j <= columns)
    MatrixList(matrix.map((r) => List(r(j))))
  }

  def transpose = MatrixList(MatrixList.transpose(matrix))

  def +(that: MatrixList[A]) = {
    require(rows == that.rows && columns == that.columns)
    val vv =
      for ((rows1, rows2) <- this.matrix zip that.matrix)
        yield for ((el1, el2) <- rows1 zip rows2)
          yield el1 + el2
    MatrixList(vv)
  } ensuring (_.rows == rows)

  def *(that: A) =
    MatrixList(matrix.map(_.map(_ *  that)))

  def *(that: MatrixList[A]) = {
    require(columns == that.rows)
    val vv =
      for (r <- matrix) yield
                          for (c <- that.transpose.matrix) yield
                                                             r zip c map {case (x,y) => x * y} reduceLeft (_ + _)
    MatrixList(vv)
  } ensuring (res => res.rows == rows && res.columns == that.columns)

  def minor(row: Int, col: Int) = MatrixList(MatrixList.minor(matrix, row-1, col-1))

  override def toString = {
    matrix.map(_.mkString("(","\t",")")).mkString("\n")
  }

  def hermTrans = MatrixList(matrix map (_ map (_.conj))).transpose

}

object MatrixList {
  import quant.complex.Complex

  def apply[A](m: List[List[A]])(implicit ev: MatrixElement[A]) = new MatrixList(m)

  def apply[A](r: Int, c: Int, f: (Int,Int) => A)(implicit ev: MatrixElement[A]) =
    new MatrixList(
    (for (i <- 1 to r) yield
      (for (j <- 1 to c) yield
        f(i,j)
      ).toList
    ).toList)


  private def check(m: List[List[_]]) = {
    require(m.nonEmpty)
    require(m.head.nonEmpty)
    val size = m.head.size
    for (sv <- m.tail)
      require(sv.size == size)
  }

  private def transpose[A](m: List[List[A]]): List[List[A]] = m.head match {
    case Nil => Nil
    case _   => m.map(_.head) :: transpose(m.map(_.tail))
  }

  import scala.annotation.tailrec
  @tailrec
  private def minor[A](m: List[List[A]], row: Int, col: Int,
                       macc: List[List[A]] = List[List[A]](), racc: Int = 0): List[List[A]] = {
    @tailrec
    def minorR(r: List[A], col: Int, racc: List[A] = List[A](), cacc: Int = 0): List[A] = r match {
      case Nil => racc
      case e :: es =>
        if (col == cacc)
          minorR(es, col, racc, cacc+1)
        else
          minorR(es, col, e :: racc, cacc+1)
    }
    m match {
      case Nil => macc
      case r :: rs =>
        if (row == racc)
          minor(rs, row, col, macc, racc+1)
        else
          minor(rs, row, col, minorR(r, col) :: macc, racc+1)
    }
  }
}
