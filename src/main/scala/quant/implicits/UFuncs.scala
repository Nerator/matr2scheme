package quant.implicits

import breeze.math.Complex
import breeze.linalg.isClose

trait UFuncs {

  implicit object implIsCloseComplex extends isClose.Impl3[Complex, Complex, Double, Boolean] {
    override def apply(v: Complex, v2: Complex, v3: Double): Boolean = (v - v2).abs <= v3
  }

}
