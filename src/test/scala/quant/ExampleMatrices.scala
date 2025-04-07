package quant

import cats.syntax.functor._
import quant.instances.matrix._
import quant.math.Matrix
import spire.math.{Complex, Real}

object ExampleMatrices {
  val razlMatrices: List[Matrix[Complex[Real]]] = List(
    // Раздел 2 задание 1
    Matrix(
      Vector(0, 0, 0, 1),
      Vector(0, 0, 1, 0),
      Vector(1, 0, 0, 0),
      Vector(0, 1, 0, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(0, 0, 1, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 1, 0, 0),
      Vector(1, 0, 0, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(0, 0, 1, 0),
      Vector(1, 0, 0, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 1, 0, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(0, 1, 0, 0),
      Vector(0, 0, 0, 1),
      Vector(1, 0, 0, 0),
      Vector(0, 0, 1, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(0, 1, 0, 0),
      Vector(0, 0, 1, 0),
      Vector(0, 0, 0, 1),
      Vector(1, 0, 0, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(0, 0, 0, 1),
      Vector(1, 0, 0, 0),
      Vector(0, 1, 0, 0),
      Vector(0, 0, 1, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(1, 0, 0, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 1, 0, 0),
      Vector(0, 0, 1, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(1, 0, 0, 0),
      Vector(0, 0, 1, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 1, 0, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector((0, 0), (0, -1), (0, 0), (0, 0)),
      Vector((0, 1), (0, 0), (0, 0), (0, 0)),
      Vector((0, 0), (0, 0), (0, 0), (0, 1)),
      Vector((0, 0), (0, 0), (0, -1), (0, 0))
    ) map { case (r, i) => Complex(r, i) },
    Matrix(
      Vector(0, 0, 0, 1),
      Vector(0, 0, 1, 0),
      Vector(0, 1, 0, 0),
      Vector(1, 0, 0, 0)
    ) map (Complex(_)),
    // Раздел 2 задание 3
    Matrix(
      Vector((1, 0), (0, 0), (0, 0), (0, 0)),
      Vector((0, 0), (0, 0), (1, 0), (0, 0)),
      Vector((0, 0), (1, 0), (0, 0), (0, 0)),
      Vector((0, 0), (0, 0), (0, 0), (0, 1))
    ) map { case (r, i) => Complex(r, i) },
    Matrix(
      Vector((1, 0), (1, 0), (1, 0), (1, 0)),
      Vector((1, 0), (0, 1), (-1, 0), (0, -1)),
      Vector((1, 0), (-1, 0), (1, 0), (-1, 0)),
      Vector((1, 0), (0, -1), (-1, 0), (0, 1))
    ).map { case (r, i) => Complex[Real](r, i) } :* Complex(Real.one / 2),
    // Раздел 2 задание 6
    Matrix(
      Vector(-1, 0, 0, 0),
      Vector(0, 1, 0, 0),
      Vector(0, 0, 1, 0),
      Vector(0, 0, 0, 1)
    ) map (a => Complex(a)),
    Matrix(
      Vector(1, 0, 0, 0),
      Vector(0, -1, 0, 0),
      Vector(0, 0, -1, 0),
      Vector(0, 0, 0, -1)
    ) map (a => Complex(a)),
    Matrix(
      Vector(1, 0, 0, 0),
      Vector(0, 0, -1, 0),
      Vector(0, -1, 0, 0),
      Vector(0, 0, 0, -1)
    ) map (a => Complex(a)),
    // Раздел 3 задание 3
    Matrix(
      Vector(1, 0, 0, 0, 0, 0, 0, 0),
      Vector(0, 0, 0, 0, 0, 0, 0, 1),
      Vector(0, 1, 0, 0, 0, 0, 0, 0),
      Vector(0, 0, 1, 0, 0, 0, 0, 0),
      Vector(0, 0, 0, 1, 0, 0, 0, 0),
      Vector(0, 0, 0, 0, 1, 0, 0, 0),
      Vector(0, 0, 0, 0, 0, 1, 0, 0),
      Vector(0, 0, 0, 0, 0, 0, 1, 0)
    ) map (a => Complex(a)),
    Matrix(
      Vector(1, 0, 0, 0, 0, 0, 0, 0),
      Vector(0, 0, 1, 0, 0, 0, 0, 0),
      Vector(0, 0, 0, 1, 0, 0, 0, 0),
      Vector(0, 0, 0, 0, 1, 0, 0, 0),
      Vector(0, 0, 0, 0, 0, 1, 0, 0),
      Vector(0, 0, 0, 0, 0, 0, 1, 0),
      Vector(0, 0, 0, 0, 0, 0, 0, 1),
      Vector(0, 1, 0, 0, 0, 0, 0, 0)
    ) map (a => Complex(a)),
    // Раздел 4 задание 1, в (б) и (в) подставим b = sqrt(1/2)
    Matrix(
      Vector(0, 1, 0, 0),
      Vector(1, 0, 0, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 0, 1, 0)
    ) map (a => Complex(a)), {
      val b = Real.sqrt1By2
      Matrix(
        Vector(Real.zero, b, Real.zero, -b),
        Vector(Real.zero, Real.zero, Real.one, Real.zero),
        Vector(Real.zero, b, Real.zero, b),
        Vector(Real.one, Real.zero, Real.zero, Real.zero)
      ) map (a => Complex(a))
    }, {
      val b = Real.sqrt1By2
      Matrix(
        Vector(Real.zero, Real.zero, Real.zero, Real.one),
        Vector(Real.zero, Real.zero, Real.one, Real.zero),
        Vector(b, b, Real.zero, Real.zero),
        Vector(b, -b, Real.zero, Real.zero)
      ) map (a => Complex(a))
    }
  )

  val parserMatrices: List[Matrix[Complex[Real]]] = List(
    Matrix(
      Vector(1, 0, 0, 0),
      Vector(0, 1, 0, 0),
      Vector(0, 0, 0, 1),
      Vector(0, 0, 1, 0)
    ).map(a => Complex(Real(a))),
    Matrix(
      Vector((0, 0), (0, -1), (0, 0), (0, 0)),
      Vector((0, 1), (0, 0), (0, 0), (0, 0)),
      Vector((0, 0), (0, 0), (0, 0), (0, 1)),
      Vector((0, 0), (0, 0), (0, -1), (0, 0))
    ) map { case (r, i) => Complex(Real(r), Real(i)) },
    Matrix(
      Vector((0, 0), (0, -1), (0, 0), (0, 0)),
      Vector((0, 1), (0, 0), (0, 0), (0, 0)),
      Vector((0, 0), (0, 0), (0, 0), (0, 1)),
      Vector((0, 0), (0, 0), (0, -1), (0, 0))
    ) map { case (r, i) => Complex(Real(r), Real(i)) },
    Matrix(
      Vector(Real.sqrt1By2, Real.sqrt1By2),
      Vector(Real.sqrt1By2, -Real.sqrt1By2)
    ).map(Complex(_)),
    Matrix(
      Vector(Real.sqrt1By2, Real.sqrt1By2),
      Vector(Real.sqrt1By2, -Real.sqrt1By2)
    ).map(Complex(_)),
    Matrix(
      Vector(Real.cos(Real.pi * 3 / 4), Real.sin(Real.pi * 3 / 4)),
      Vector(Real.sin(Real.pi * 3 / 4), Real.cos(Real.pi * 3 / 4))
    ) map (Complex(_)),
    Matrix(
      Vector(-1, -1),
      Vector(-1, -1)
    ) map (a => Complex(Real(a))),
    Matrix(
      Vector(Complex(Real.cos(Real.cos(Real.pi * Real.sin(Real.two / 3)))))
    ),
    Matrix(
      Vector(
        (Real.zero, Real.zero),
        (Real.zero, Real.zero),
        (Real.zero, Real.zero),
        (Real.one, Real.zero)
      ),
      Vector(
        (Real.zero, Real.zero),
        (Real.cos(Real.pi / 12), Real.zero),
        (Real.zero, -Real.sin(Real.pi / 12)),
        (Real.zero, Real.zero)
      ),
      Vector(
        (Real.zero, Real.zero),
        (Real.zero, -Real.sin(Real.pi / 12)),
        (Real.cos(Real.pi / 12), Real.zero),
        (Real.zero, Real.zero)
      ),
      Vector(
        (Real.one, Real.zero),
        (Real.zero, Real.zero),
        (Real.zero, Real.zero),
        (Real.zero, Real.zero)
      )
    ) map { case (r, i) => Complex(r, i) },
    Matrix(
      Vector(Real.zero, Real.zero, Real.zero, Real.one),
      Vector(Real.zero, Real.zero, Real.one, Real.zero),
      Vector(Real.sqrt1By2, Real.sqrt1By2, Real.zero, Real.zero),
      Vector(Real.sqrt1By2, -Real.sqrt1By2, Real.zero, Real.zero)
    ).map(Complex(_))
  )
}
