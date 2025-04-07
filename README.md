[![Build Status](https://travis-ci.org/Nerator/matr2scheme.svg?branch=master)](https://travis-ci.org/Nerator/matr2scheme)

# matr2scheme

## Overview

Implementation of the algorithm for decomposition of arbitrary unitaty matrix
into product of two-level matrices. The algorithm is described in books
__"Quantum Computation and Quantum Information"__ by Michael Nilsen and Isaac
Chang (4.5.1 Two-level unitary gates are universal) and __"Quantum computing :
from linear algebra to physical realizations"__ by Mikio Nakahara and Tetsuo
Ohmi (4.6 Universal Quantum Gates).

## Building

For Scala 3.6.4:
``` shell
sbt ++3.6.4 packageVer
```

For Scala 2.13.16:
``` shell
sbt ++2.13.16 packageVer
```

Resulting zip-files will be located at `package` folder.

## Usage

Unzip archive with required Scala version to use.

Running (Linux):
``` shell
./matr2scheme <filename>
```

Running (Windows):
``` shell
matr2scheme.bat <filename>
```

`filename` should be text file with unitary matrix. Complex numbers entered like
`(Re,Im)`. For more info run `matr2scheme` without parameters.
