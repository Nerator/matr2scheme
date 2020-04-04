package quant.tools.parsing

import atto.Atto._
import atto.{ParseResult, Parser}
import cats.instances.either._
import cats.instances.vector._
import cats.syntax.apply._
import cats.syntax.either._
import cats.syntax.traverse._
import quant.math.Matrix
import spire.math.{Complex, Real}

object ComplexParser {
  def number: Parser[Real] =
    token(stringCI("pi")).map(_ => Real.pi) |
      token(double).map(Real.apply)

  // Fixes default parens, so p is really lazy
  def myParens[A](p: => Parser[A]): Parser[A] =
    bracket(char('('), token(p), char(')')) // Notice the absence of named

  def rexpr: Parser[Real] = {
    (char('-') ~> rexpr).map(-_) |
      (stringOf(letterOrDigit), token(myParens(rexpr)), token(rexpr1)).mapN {
        case ("sin", n, de1) => de1(Real.sin(n))
        case ("cos", n, de1) => de1(Real.cos(n))
        case ("sqrt", n, de1) => de1(n.sqrt())
        case _ => ???
      } |
      (number, token(rexpr1)).mapN {
        case (n, de1) => de1(n)
      }
  }

  // Rewrite to avoid left recursion
  private def rexpr1: Parser[Real => Real] =
    (token(char('*')) ~> rexpr, delay(rexpr1)).mapN { case (r, efu) =>
      (l: Real) => efu(l * r)
    } |
      (token(char('/')) ~> rexpr, delay(rexpr1)).mapN { case (r, efu) =>
        (l: Real) => efu(l / r)
      } |
      ok(()).map(_ => identity)

  def complex: Parser[Complex[Real]] =
    myParens((token(rexpr), token(char(',')) ~> token(rexpr)).mapN(Complex.apply)) |
      rexpr.map(Complex(_))

  def row: Parser[Vector[Complex[Real]]] =
    many(token(complex)).map(_.toVector)

  def parseMatrix(ss: List[String]): Either[String, Matrix[Complex[Real]]] =
    ss.toVector.map(l =>
      row.parse(l.trim).done match {
        case ParseResult.Fail(input, stack, message) => s"Parsing failed. Input: $input, stack: $stack, message: $message".asLeft
        case ParseResult.Partial(_) => s"Parsing not finished".asLeft
        case ParseResult.Done(_, result) => result.asRight
      }
    ).sequence map Matrix.apply
}
