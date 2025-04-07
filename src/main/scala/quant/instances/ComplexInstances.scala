package quant.instances

import cats.Show
import spire.math.{Complex, Real}
import spire.syntax.order._

trait ComplexInstances {

  implicit val complexRealShowInstance: Show[Complex[Real]] =
    new Show[Complex[Real]] {
      override def show(c: Complex[Real]): String =
        if (c.imag === Real.zero)
          if (c.real.isValidInt)
            c.real.toInt.toString
          else
            f"${c.real.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
        else if (c.real == Real.zero)
          if (c.imag == Real.one)
            "i"
          else if (c.imag === Real.one.unary_-)
            "-i"
          else {
            val is =
              if (c.imag.isValidInt) c.imag.toInt.toString
              else
                f"${c.imag.toDouble}%.3f"
                  .replaceAll("0+$", "")
                  .replace(',', '.')
            s"$is*i"
          }
        else {
          val rs =
            if (c.real.isValidInt) c.real.toInt.toString
            else
              f"${c.real.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
          if (c.imag < Real.zero)
            if (c.imag == -Real.one)
              s"$rs-i"
            else {
              val is =
                if (c.imag.isValidInt) c.imag.toInt.toString
                else
                  f"${c.imag.abs.toDouble}%.3f"
                    .replaceAll("0+$", "")
                    .replace(',', '.')
              s"$rs-$is*i"
            }
          else if (c.imag === Real.one)
            s"$rs+i"
          else {
            val is =
              if (c.imag.isValidInt) c.imag.toInt.toString
              else
                f"${c.imag.toDouble}%.3f"
                  .replaceAll("0+$", "")
                  .replace(',', '.')
            s"$rs+$is*i"
          }
        }
    }

  //  implicit class RichComplex(c: Complex[Real]) {
  //    def prettyPrint: String =
  //      if (c.imag == Real.zero)
  //        if (c.real.isValidInt)
  //          c.real.toInt.toString
  //        else
  //          f"${c.real.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
  //      else if (c.real == Real.zero)
  //        if (c.imag == Real.one)
  //          "i"
  //        else if (c.imag == -Real.one)
  //          "-i"
  //        else {
  //          val is =
  //            if (c.imag.isValidInt) c.imag.toInt.toString
  //            else f"${c.imag.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
  //          s"$is*i"
  //        }
  //      else {
  //        val rs =
  //          if (c.real.isValidInt) c.real.toInt.toString
  //          else f"${c.real.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
  //        if (c.imag < Real.zero)
  //          if (c.imag == -Real.one)
  //            s"$rs-i"
  //          else {
  //            val is =
  //              if (c.imag.isValidInt) c.imag.toInt.toString
  //              else f"${c.imag.abs.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
  //            s"$rs-$is*i"
  //          }
  //        else if (c.imag == Real.one)
  //          s"$rs+i"
  //        else {
  //          val is =
  //            if (c.imag.isValidInt) c.imag.toInt.toString
  //            else f"${c.imag.toDouble}%.3f".replaceAll("0+$", "").replace(',', '.')
  //          s"$rs+$is*i"
  //        }
  //      }
  //  }

}
