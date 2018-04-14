package quant.tools.parsing

import quant.implicits._
import quant.complex.Complex
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.CharSequenceReader
import scala.util.parsing.input.Reader
import scala.util.parsing.combinator.lexical.Scanners
import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.combinator.PackratParsers

trait ComplexParser extends JavaTokenParsers with PackratParsers {
  
  def number: Parser[Double] = 
    floatingPointNumber ^^ (_.toDouble) |
    "[Pp][Ii]".r ^^ (_ => math.Pi)
  
  def doubleAsComplex = number ^^ (_.toComplex)
  
  lazy val dexpr: PackratParser[Double] =
    dexpr ~ ("*" ~> number) ^^ { case md ~ n => md * n } |
    dexpr ~ ("/" ~> number) ^^ { case md ~ n => md / n } |
    number
  
  def complex: Parser[Complex] = 
    ("(" ~> number ~ ("," ~> number <~ ")")) ^^ { case re ~ im => Complex(re,im) }
  
  def doubleFuncCall: Parser[Double] =
    "-" ~> doubleFuncCall ^^ (-_) |
    (ident ~ ("(" ~> doubleFuncCall <~ ")")) ^^ {
      case "sin" ~ n => math.sin(n)
      case "cos" ~ n => math.cos(n)
      case "sqrt" ~ n => math.sqrt(n)
      case _ => ???
    } |
    (ident ~ ("(" ~> dexpr <~ ")")) ^^ {
      case "sin" ~ n => math.sin(n)
      case "cos" ~ n => math.cos(n)
      case "sqrt" ~ n => math.sqrt(n)
      case _ => ???
    }
  
  def row =
    (doubleAsComplex | complex | (doubleFuncCall ^^ (_.toComplex))).* ^^ (_.toArray)

  def parseMatrix(ss: List[String]) =
    (for (l <- ss) yield parse(row, new PackratReader(new CharSequenceReader(l))) match {
      case Success(res, _)      => Right(res)
      case NoSuccess(msg, next) => Left(s"$msg next: ${next.pos}")
    }).foldRight[Either[String, List[Array[Complex]]]](Right(Nil)) {
      case (Left(msg), _)          => Left(msg)
      case (Right(row), Left(msg)) => Left(msg)
      case (Right(row), Right(m))  => Right(row :: m)
    } map (_.toArray)
    
  
}