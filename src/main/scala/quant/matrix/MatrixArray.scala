package quant.matrix

//import quant.matrix.MatrixElement._
import quant.complex.Complex
import quant.matrix.MatrixElement._
import scala.reflect.ClassTag
import quant.implicits._

class MatrixArray[A: ClassTag](private val matrix: Array[Array[A]])(implicit ev: MatrixElement[A]) {

  MatrixArray.check(matrix)

  def rows = matrix.size
  def columns = matrix.head.size

  def row(i: Int) = {
    require(i <= rows)
    matrix(i)
  }
  
  def column(j: Int) = {
    require(j <= columns)
    MatrixArray(matrix.map(r => Array(r(j))))
  }

  def transpose = MatrixArray(matrix.transpose)

  def +(that: MatrixArray[A]) = {
    require(rows == that.rows && columns == that.columns)
    val vv: Array[Array[A]] =
      for ((rows1, rows2) <- this.matrix zip that.matrix)
        yield for ((el1, el2) <- rows1 zip rows2)
          yield (el1 + el2)
    MatrixArray(vv)
  } ensuring (_.rows == rows)

  def *(that: A) =
    MatrixArray(matrix.map(_.map(_ *  that)))

  def *(that: MatrixArray[A]) = {
    require(columns == that.rows)
    val vv =
      for (r <- matrix)
      yield for (c <- that.transpose.matrix)
      yield r zip c map {case (x,y) => x * y} reduceLeft (_ + _)
    MatrixArray(vv)
  } ensuring (res => res.rows == rows && res.columns == that.columns)

  // def minor(row: Int, col: Int) = MatrixArray(MatrixArray.minor(matrix, row-1, col-1))

  override def toString = {
    matrix.map(_.mkString("(","\t",")")).mkString("\n")
  }

  def hermTrans = MatrixArray(matrix map (_ map (_.conj))).transpose

  def apply(i: Int, j: Int) = matrix(i)(j)

}

object MatrixArray {
  def apply[A: ClassTag](m: Array[Array[A]])(implicit ev: MatrixElement[A]) =
    new MatrixArray(m)

  def apply[A: ClassTag](l: List[List[A]])(implicit ev: MatrixElement[A]): MatrixArray[A] =
    MatrixArray((l map (_.toArray)).toArray)

  def apply[A: ClassTag](r: Int, c: Int, f: (Int,Int) => A)(implicit ev: MatrixElement[A]): MatrixArray[A] =
    MatrixArray(Array.tabulate(r, c)(f(_,_)))

  def eye[A](n: Int): Array[Array[Double]] = {
    Array.tabulate(n, n) { case (i,j) => if (i == j) 1 else 0 }
  }

  def toComplexM(m: MatrixArray[Double]): MatrixArray[Complex] =
    MatrixArray(m.matrix map (_ map (_.toComplex)))

  private def check[A](m: Array[Array[A]]) = {
    require(m.nonEmpty)
    require(m.head.nonEmpty)
    val size = m.head.size
    for (sv <- m.tail)
      require(sv.size == size)
  }

}
