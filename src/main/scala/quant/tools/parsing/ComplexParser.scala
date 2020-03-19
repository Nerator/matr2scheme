package quant.tools.parsing

import breeze.linalg.DenseMatrix
import breeze.math.Complex

import scala.util.parsing.combinator.{JavaTokenParsers, PackratParsers}
import scala.util.parsing.input.CharSequenceReader

trait ComplexParser extends JavaTokenParsers with PackratParsers {

  def number: Parser[Double] =
    floatingPointNumber ^^ (_.toDouble) |
      "[Pp][Ii]".r ^^ (_ => math.Pi)

  lazy val dexpr: PackratParser[Double] =
    "-" ~> dexpr ^^ (-_) |
      dexpr ~ ("*" ~> dexpr) ^^ { case md ~ n => md * n } |
      dexpr ~ ("/" ~> dexpr) ^^ { case md ~ n => md / n } |
      (ident ~ ("(" ~> dexpr <~ ")")) ^^ {
        case "sin" ~ n => math.sin(n)
        case "cos" ~ n => math.cos(n)
        case "sqrt" ~ n => math.sqrt(n)
        case _ => ???
      } |
      number

  def complex: Parser[Complex] =
    ("(" ~> dexpr ~ ("," ~> dexpr <~ ")")) ^^ { case re ~ im => Complex(re, im) } |
      dexpr ^^ (Complex(_, 0.0))

  def row: Parser[Array[Complex]] =
    complex.* ^^ (_.toArray)

  def parseMatrix(ss: List[String]): Either[String, DenseMatrix[Complex]] =
    (for (l <- ss) yield parse(row, new PackratReader(new CharSequenceReader(l))) match {
      case Success(res, _) => Right(res)
      case NoSuccess(msg, next) => Left(s"$msg next: ${next.pos}")
    }).foldRight[Either[String, List[Array[Complex]]]](Right(Nil)) {
      case (Left(msg), _) => Left(msg)
      case (Right(_), Left(msg)) => Left(msg)
      case (Right(row), Right(m)) => Right(row :: m)
    } match {
      case Left(msg) => Left(msg)
      case Right(m) => Right(DenseMatrix(m: _*))
    }

}
