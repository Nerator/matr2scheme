package quant.tools.parsing

import org.scalatest._

import quant.implicits._
import quant.complex.Complex

import scala.io.Source
import java.io.File
import scala.util.parsing.input.CharSequenceReader
import math._

class ComplexParserSpec extends FlatSpec with Matchers with ComplexParser {

  "Complex Parser" should "parse complex number" in {
    parse(complex, new PackratReader(new CharSequenceReader("(0,1)"))) match {
      case NoSuccess(msg, _) => fail(s"Parsing failed: $msg")
      case Success(c, _) => c shouldEqual Complex.i
    }
    parse(complex, new PackratReader(new CharSequenceReader("(0,-1)"))) match {
      case NoSuccess(msg, _) => fail(s"Parsing failed: $msg")
      case Success(c, _) => c shouldEqual -Complex.i
    }
  }

  it should "parse 4x4 double matrix" in {
    val file = Source.fromURL(getClass.getResource("/matrix.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(1, 0, 0, 0),
          Array(0, 1, 0, 0),
          Array(0, 0, 0, 1),
          Array(0, 0, 1, 0))
    }
  }

  it should "parse 4x4 complex matrix (with whitespaces)" in {
    val file = Source.fromURL(getClass.getResource("/matrix1.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
          Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
          Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero))
    }
  }

  it should "parse 4x4 complex matrix with doubles" in {
    val file = Source.fromURL(getClass.getResource("/matrix2.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(Complex.zero,   -Complex.i, Complex.zero, Complex.zero),
          Array(   Complex.i, Complex.zero, Complex.zero, Complex.zero),
          Array(Complex.zero, Complex.zero, Complex.zero,    Complex.i),
          Array(Complex.zero, Complex.zero,   -Complex.i, Complex.zero))
    }
  }

  it should "parse matrix with sqrt" in {
    val file = Source.fromURL(getClass.getResource("/matrix3.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(sqrt(1.0/2.0),  sqrt(1.0/2.0)),
          Array(sqrt(1.0/2.0), -sqrt(1.0/2.0)))
    }
  }

  it should "parse matrix with sqrt and expressions inside" in {
    val file = Source.fromURL(getClass.getResource("/matrix4.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(sqrt(1.0/2.0),  sqrt(1.0/2.0)),
          Array(sqrt(1.0/2.0), -sqrt(1.0/2.0)))
    }
  }

  it should "parse matrix with trigonometric functions and expressions inside" in {
    val file = Source.fromURL(getClass.getResource("/matrix5.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(cos(3*Pi/4), sin(3*Pi/4)),
          Array(sin(3*Pi/4), cos(3*Pi/4)))
    }
    val file1 = Source.fromURL(getClass.getResource("/matrix8.txt"))
    parseMatrix(file1.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(Complex.zero,  Complex.zero,          Complex.zero,         Complex.one ),
          Array(Complex.zero,  cos(Pi/12).toComplex, -Complex.i*sin(Pi/12), Complex.zero),
          Array(Complex.zero, -Complex.i*sin(Pi/12),  cos(Pi/12).toComplex, Complex.zero),
          Array(Complex.one,   Complex.zero,          Complex.zero,         Complex.zero)
        )
    }
  }

  it should "parse matrix with trigonometric functions and expressions inside complex number definitions" in {
    val file = Source.fromURL(getClass.getResource("/matrix6.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(-Complex.one, -Complex.one),
          Array(-Complex.one, -Complex.one))
    }
  }

  it should "parse complex expressions with heavily nested structure" in {
    val file = Source.fromURL(getClass.getResource("/matrix7.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(Complex(cos(cos(Pi * sin(2.0 / 3.0))),0))
        )
    }
  }

}