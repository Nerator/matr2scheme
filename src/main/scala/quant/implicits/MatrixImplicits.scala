package quant.implicits

import breeze.linalg.{DenseMatrix, all, convert}
import breeze.math.Complex

trait DenseMatrixImplicits {

  implicit class RichDenseMatrix(x: DenseMatrix[Complex]) {
    def isUnitary: Boolean = (x * x.t) == DenseMatrix.eye[Complex](x.rows)
    def isSquare: Boolean = x.rows == x.cols
    def isClose(other: Any): Boolean = {
      other match {
        case m: DenseMatrix[_] =>
          if (m.size == 0)
            x.size == 0
          else m.data.head match {
            case _: Int =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Int]], Complex)).toDenseVector)
            case _: Double =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Double]], Complex)).toDenseVector)
            case _: Float =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Float]], Complex)).toDenseVector)
            case _: Long =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Long]], Complex)).toDenseVector)
            case _: Char =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Char]], Complex)).toDenseVector)
            case _: Short =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Short]], Complex)).toDenseVector)
            case _: Complex =>
              all(breeze.linalg.isClose(x, m.asInstanceOf[DenseMatrix[Complex]]).toDenseVector)
            case _ => false
          }
        case _ => false
      }
    }
  }

}
