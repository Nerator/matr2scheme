package quant.tools.parsing

import atto.Atto._
import atto.ParseResult
import cats.instances.parallel._
import cats.instances.vector._
import cats.syntax.either._
import cats.syntax.eq._
import cats.syntax.foldable._
import cats.syntax.parallel._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import quant.ExampleMatrices
import quant.instances.matrix._
import quant.math.Matrix
import quant.tools.parsing.ComplexParser._
import spire.math.{Complex, Real}

import scala.io.Source

object ComplexParserProperties extends Properties("ComplexParser") {
  private def parseResultAssert[T](pr: ParseResult[T])(f: T => Boolean): Boolean =
    pr match {
      case ParseResult.Done(_, result) => f(result)
      case ParseResult.Fail(_, _, message) => println(s"Parsing failed: $message"); false
      case _ => false
    }

  private def genDouble = Gen.choose[Double](0.0, 32.0)

  private def genFun = Gen.oneOf("sin", "cos", "sqrt")

  property("number") = forAll(genDouble) { d =>
    parseResultAssert(number.parse(d.toString).done)(parsed => Real(d) === parsed)
  }

  property("pi") =
    List("pi", "pI", "Pi", "PI").forall(s => parseResultAssert(number.parse(s).done) {
      res => res === Real.pi
    })

  private def genRexpr = Gen.oneOf(
    // minus
    for {
      d <- genDouble
    } yield (s"-$d", (r: Real) => r === -Real(d)),
    // mult
    for {
      d1 <- genDouble
      d2 <- genDouble
    } yield (s"$d1 * $d2", (r: Real) => r === Real(d1) * Real(d2)),
    // div
    for {
      d1 <- genDouble
      d2 <- genDouble suchThat (_ != 0)
    } yield (s"$d1 / $d2", (r: Real) => r === Real(d1) / Real(d2)),
    // fun
    for {
      d <- genDouble
      f <- genFun
    } yield (s"$f($d)", (r: Real) => r === (f match {
      case "sin" => Real.sin(Real(d))
      case "cos" => Real.cos(Real(d))
      case "sqrt" => Real(d).sqrt()
    }))
  )

  property("dexpr") = forAll(genRexpr) {
    case (s, f) => parseResultAssert(rexpr.parse(s).done)(f)
  }

  property("dexpr.minus") = forAll(genDouble) { d =>
    parseResultAssert(rexpr.parse(s"-$d").done)(parsed => -Real(d) === parsed)
  }

  property("dexpr.mult") = forAll(genDouble, genDouble) { (d1, d2) =>
    parseResultAssert(rexpr.parse(s"$d1 * $d2").done)(parsed => Real(d1) * Real(d2) === parsed)
  }

  property("dexpr.div") = forAll(genDouble, genDouble.suchThat(_ != 0.0)) { (d1, d2) =>
    parseResultAssert(rexpr.parse(s"$d1 / $d2").done)(parsed => Real(d1) / Real(d2) === parsed)
  }

  property("dexpr.fun") = forAll(genDouble, genFun) { (d, f) =>
    parseResultAssert(rexpr.parse(s"$f($d)").done) { parsed =>
      val expected = f match {
        case "sin" => Real.sin(Real(d))
        case "cos" => Real.cos(Real(d))
        case "sqrt" => Real(d).sqrt
      }
      expected === parsed
    }
  }

  property("complex.single") = forAll(genDouble) { r =>
    parseResultAssert(complex.parse(r.toString).done)(parsed => Complex(Real(r)) === parsed)
  }

  property("complex.both") = forAll(genDouble, genDouble) { (r, i) =>
    parseResultAssert(complex.parse(s"($r, $i)").done)(parsed => Complex(Real(r), Real(i)) === parsed)
  }

  private def genRow = for {
    s <- Gen.choose(0, 32)
    l <- Gen.listOfN(s, genDouble)
  } yield l.toVector

  property("row.single") = forAll(genRow) { r =>
    parseResultAssert(row.parse(r.mkString(" ")).done)(parsed => r.map(v => Complex(Real(v))) === parsed)
  }

  private def genRowBoth = for {
    s <- Gen.choose(0, 32)
    l <- Gen.listOfN(s, for {
      d1 <- genDouble
      d2 <- genDouble
    } yield (d1, d2))
  } yield l.toVector

  property("row.both") = forAll(genRowBoth) { r =>
    parseResultAssert(row.parse(r.map {
      case (d1, d2) => s"($d1, $d2)"
    }.mkString(" ")).done) { parsed =>
      r.map {
        case (d1, d2) => Complex(Real(d1), Real(d2))
      } === parsed
    }
  }

  private def genMatSingle = for {
    r <- Gen.choose(0, 32)
    l <- Gen.listOfN(r, genRow)
  } yield l

  property("matrix.single") = forAll(genMatSingle) { m =>
    val result = parseMatrix(m.map(_.mkString(" ")))
    val expected = Matrix(m.toVector.map(_.map(d => Complex(Real(d)))))
    result.exists(r => (r zip expected).forall {
      case (rv, ev) => rv === ev
    })
  }

  private def genMatBoth = for {
    r <- Gen.choose(0, 32)
    l <- Gen.listOfN(r, genRowBoth)
  } yield l

  property("matrix.both") = forAll(genMatBoth) { m =>
    val result = parseMatrix(m.map(_.map {
      case (d1, d2) => s"($d1, $d2)"
    }.mkString(" ")))
    val expected = Matrix(m.toVector.map(_.map {
      case (d1, d2) => Complex(Real(d1), Real(d2))
    }))
    result.exists(r => (r zip expected).forall {
      case (rv, ev) => rv === ev
    })
  }

  property("matrix.concrete") = {
    // Amount of test matrices
    val n = 10

    // Sanity
    require(n == ExampleMatrices.parserMatrices.length,
      "Not same amount of files and test matrices")

    val input = (List.tabulate(n) { i =>
      if (i == 0) s"/matrix.txt" else s"/matrix$i.txt"
    }, ExampleMatrices.parserMatrices).parTupled

    input.forall { case (fname, exm) =>
      val file = Source.fromURL(getClass.getResource(fname))
      parseMatrix(file.getLines.toList).exists(_ === exm)
    }
  }
}
