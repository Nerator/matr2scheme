package quant.implicits

import breeze.linalg.isClose
import breeze.math.Complex

trait ComplexImplicits {

  implicit class RichComplex(c: Complex) {
    def prettyPrint: String =
      if (isClose(c.imag.abs, 0.0))
        f"${c.real}%.3f".replace(',','.')
      else if (isClose(c.real.abs, 0.0))
        if (isClose(c.imag, 1.0))
          "i"
        else if (isClose(c.imag, -1.0))
          "-i"
        else
          f"${c.imag}%.3f*i".replace(',','.')
      else if (c.imag < 0.0)
        if (isClose(c.imag, -1.0))
          f"${c.real}%.3f-i".replace(',','.')
        else
          f"${c.real}%.3f-${c.imag.abs}%.3f*i".replace(',','.')
      else
      if (isClose(c.imag, 1.0))
        f"${c.real}%.3f+i".replace(',','.')
      else
        f"${c.real}%.3f+${c.imag}%.3f*i".replace(',','.')
  }

}
