package quant.tools.parsing

import org.scalatest._
import scala.io.Source
import java.io.File
import quant.complex.Complex
import scala.util.parsing.input.CharSequenceReader

class ComplexParserSpec extends FlatSpec with Matchers with ComplexParser {

  "Complex Parser" should "parse complex number" in {
    parse(complex, new CharSequenceReader("(0,1)")) match {
      case NoSuccess(msg, _) => fail(s"Parsing failed: $msg")
      case Success(c, _) => c shouldEqual Complex.i
    }
    parse(complex, new CharSequenceReader("(0,-1)")) match {
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
          Array(math.sqrt(1.0/2.0),  math.sqrt(1.0/2.0)),
          Array(math.sqrt(1.0/2.0), -math.sqrt(1.0/2.0)))
    }
  }
  
  it should "parse matrix with sqrt and expressions inside" in {
    val file = Source.fromURL(getClass.getResource("/matrix4.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(math.sqrt(1.0/2.0),  math.sqrt(1.0/2.0)),
          Array(math.sqrt(1.0/2.0), -math.sqrt(1.0/2.0)))
    }
  }
  
  it should "parse matrix with trigonometric functions and expressions inside" in {
    val file = Source.fromURL(getClass.getResource("/matrix5.txt"))
    parseMatrix(file.getLines.toList) match {
      case Left(msg) => fail(s"Parsing failed: $msg")
      case Right(m) =>
        m shouldEqual Array(
          Array(math.cos(3*math.Pi/4), math.sin(3*math.Pi/4)),
          Array(math.sin(3*math.Pi/4), math.cos(3*math.Pi/4)))
    }
  }

}