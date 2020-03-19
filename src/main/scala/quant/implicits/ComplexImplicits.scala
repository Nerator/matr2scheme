package quant.implicits

import breeze.linalg.isClose
import breeze.math.Complex

trait ComplexImplicits {

  implicit class RichComplex(c: Complex) {
    def prettyPrint: String =
      if (isClose(c.imag, 0.0))
        if (c.real.isValidInt)
          c.real.toInt.toString
        else
          f"${c.real}%.3f".replaceAll("0+$", "").replace(',', '.')
      else if (isClose(c.real, 0.0))
        if (isClose(c.imag, 1.0))
          "i"
        else if (isClose(c.imag, -1.0))
          "-i"
        else {
          val is =
            if (c.imag.isValidInt) c.imag.toInt.toString
            else f"${c.imag}%.3f".replaceAll("0+$", "").replace(',', '.')
          s"$is*i"
        }
      else {
        val rs =
          if (c.real.isValidInt) c.real.toInt.toString
          else f"${c.real}%.3f".replaceAll("0+$", "").replace(',', '.')
        if (c.imag < 0.0)
          if (isClose(c.imag, -1.0))
            s"$rs-i"
          else {
            val is =
              if (c.imag.isValidInt) c.imag.toInt.toString
              else f"${c.imag.abs}%.3f".replaceAll("0+$", "").replace(',', '.')
            s"$rs-$is*i"
          }
        else if (isClose(c.imag, 1.0))
          s"$rs+i"
        else {
          val is =
            if (c.imag.isValidInt) c.imag.toInt.toString
            else f"${c.imag}%.3f".replaceAll("0+$", "").replace(',', '.')
          s"$rs+$is*i"
        }
      }
  }

}
