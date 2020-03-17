package quant.implicits

import breeze.linalg.{DenseMatrix, all, convert}
import breeze.math.Complex

trait MatrixImplicits {

  implicit class RichDenseMatrix(x: DenseMatrix[Complex]) {
//    def isUnitary: Boolean = (x * x.t) == DenseMatrix.eye[Complex](x.rows)
    def isUnitary: Boolean = (x * x.t).isClose(DenseMatrix.eye[Complex](x.rows))
    def isSquare: Boolean = x.rows == x.cols
    def isClose(other: Any): Boolean = {
      other match {
        case m: DenseMatrix[_] =>
          if (m.size == 0)
            x.size == 0
          else if (x.rows != m.rows || x.cols != m.cols)
            false
          else m.data.head match {
            case _: Int =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Int]], Complex)))
            case _: Double =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Double]], Complex)))
            case _: Float =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Float]], Complex)))
            case _: Long =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Long]], Complex)))
            case _: Char =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Char]], Complex)))
            case _: Short =>
              all(breeze.linalg.isClose(x, convert(m.asInstanceOf[DenseMatrix[Short]], Complex)))
            case _: Complex =>
              all(breeze.linalg.isClose(x, m.asInstanceOf[DenseMatrix[Complex]]))
            case _ => false
          }
        case _ => false
      }
    }
  }

}
