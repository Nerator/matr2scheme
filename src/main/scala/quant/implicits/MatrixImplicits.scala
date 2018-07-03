package quant.implicits

import breeze.linalg.DenseMatrix
import breeze.math.Complex

trait DenseMatrixImplicits {

  implicit class RichDenseMatrix(x: DenseMatrix[Complex]) {
    def isUnitary = (x * x.t) == DenseMatrix.eye[Complex](x.rows)
    def isSquare = x.rows == x.cols
  }

}
