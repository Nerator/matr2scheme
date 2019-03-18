package quant.implicits

import breeze.linalg.{DenseMatrix, all, isClose}
import breeze.math.Complex

trait UFuncs {

  implicit object implIsCloseComplex extends isClose.Impl3[Complex, Complex, Double, Boolean] {
    override def apply(v: Complex, v2: Complex, v3: Double): Boolean = (v - v2).abs <= v3
  }

  implicit object implAllDenseMatrixBoolean extends all.Impl[DenseMatrix[Boolean], Boolean] {
    override def apply(v: DenseMatrix[Boolean]): Boolean = all(v.toDenseVector)
  }

}
