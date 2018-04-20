[![Build Status](https://travis-ci.org/Nerator/matr2scheme.svg?branch=master)](https://travis-ci.org/Nerator/matr2scheme)

# matr2scheme

## Overview

Implementation of the algorithm for decomposition of arbitrary unitaty matrix into product of two-level matrices. The algorithm is described in the book __"Quantum computing : from linear algebra to physical realizations"__ by Mikio Nakahara and Tetsuo Ohmi in chapter 4.6.

## Building

For Scala 2.12.5:
``` shell
sbt ++2.12.5 package
```

For Scala 2.11.12:
``` shell
sbt ++2.11.12 package
```

Resulting JAR-files will be located at `target/scala2.12` or `target/scala2.11` respectively.

## Usage

Running:
``` shell
scala matr2scheme_<version> <filename>
```

`file` should be text file with unitary matrix (no check for this yet). Complex numbers enteren like `(Re,Im)`.
