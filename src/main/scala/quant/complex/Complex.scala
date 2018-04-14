package quant.complex

case class Complex(re: Double, im: Double) {

  import Complex.EPS

  def +(that: Complex) =
    Complex(re+that.re, im+that.im)
  def +(that: Double) =
    Complex(re+that, im)

  def *(that: Complex) =
    Complex(re*that.re - im*that.im, re*that.im + im*that.re)
  def *(that: Double) =
    Complex(re*that, im*that)

  def unary_+ = this
  def unary_- = if (this ~= Complex.zero) this else Complex(-re, -im)
  
  def -(that: Complex) =
    this + (-that)
  
  def conj =
    Complex(re, -im)
  
  def inv =
    Complex(re / (re*re + im*im), -im / (re*re + im*im))

  def /(that: Double) =
    this * (1 / that)
  def /(that: Complex) =
    this * that.inv

  def abs =
    math.sqrt(re*re + im*im)

  def arg =
    if (re > EPS)
      Math.atan(im/re)
    else if (re < -EPS)
      if (im < -EPS)
        Math.PI + Math.atan(im/re)
      else
        -Math.PI + Math.atan(im/re)
    else
      if (im > EPS)
        Math.PI / 2
      else if (im < -EPS)
        -Math.PI / 2
      else
         throw new IllegalArgumentException("Argument of zero is undefined")

  def pow(that: Int) =
    Complex.trigToArith(Math.pow(this.abs, that), that*this.arg)

  def rootn(n: Int) =
    (for (k <- 0 to n-1)
      yield Complex.trigToArith(Math.pow(this.abs, 1.0 / n), (this.arg+2*k*Math.PI)/n))
        .toList

  override def toString =
    if (im.abs < EPS)
      f"$re%.3f".replace(',','.')
    else if (re.abs < EPS)
      if ((im-1).abs < EPS)
        "i"
      else if ((im+1).abs < EPS)
        "-i"
      else
        f"$im%.3f*i".replace(',','.')
    else if (im < 0)
      if ((im+1).abs < EPS)
        f"$re%.3f-i".replace(',','.')
      else
        f"$re%.3f-${im.abs}%.3f*i".replace(',','.')
    else
      if ((im-1).abs < EPS)
        f"$re%.3f+i".replace(',','.')
      else
        f"$re%.3f+$im%.3f*i".replace(',','.')

  def toDouble: Option[Double] =
    if (im < EPS) Some(re)
    else None

  override def equals(that: Any) = that match {
    case that: Complex => re == that.re && im == that.im
    case that: Double  => re == that && im == 0.0
    case that: Int     => re == that && im == 0.0
    case _ => false
  }

  def ~=(that: Any) = that match {
    case that: Complex => (re-that.re).abs<EPS && (im-that.im).abs<EPS
    case that: Double  => (re-that).abs<EPS && im.abs<EPS
    case that: Int     => (re-that).abs<EPS && im.abs<EPS
    case _ => false
  }
  
  def !~=(that: Any) = !(this ~= that)
}

object Complex {

  private val EPS = 1E-6

  //implicit def DoubleToComplex(x: Double) = Complex(x,0)

  def zero = Complex(0,0)
  def one = Complex(1,0)
  def i = Complex(0,1)

  def trigToArith(z: Double, arg: Double): Complex = {
    if (arg > Math.PI)
      trigToArith(z, arg-2*Math.PI)
    else if (arg <= -Math.PI)
      trigToArith(z, arg+2*Math.PI)
    else if (z == 0 && arg == 0)
      throw new IllegalArgumentException("Undefined complex number")
    else
      Complex(z*Math.cos(arg), z*Math.sin(arg))
  }

}
