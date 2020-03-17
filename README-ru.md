[![Build Status](https://travis-ci.org/Nerator/matr2scheme.svg?branch=master)](https://travis-ci.org/Nerator/matr2scheme)

# matr2scheme

## Обзор

Реализация алгоритма разложения произвольной унитарной матрицы в произведение
двухуровневых матриц. Алгоритм описан в книгах __"Quantum Computation and
Quantum Information"__ Майкла Нильсена и Исаака Чанга (4.5.1 Two-level unitary
gates are universal) и __"Quantum computing : from linear algebra to physical
realizations"__ Микио Накахары и Тетсуо Оми (4.6 Universal Quantum Gates).

## Сборка

Для Scala 2.13.1:
``` shell
sbt ++2.13.1 packageVer
```

Для Scala 2.12.11:
``` shell
sbt ++2.12.11 packageVer
```

Для Scala 2.11.12:
``` shell
sbt ++2.11.12 packageVer
```

Полученные zip-файлы будут находиться в папке `package`.

## Использование

Распакуйте архив с указанной требуемой версией Scala. Если вы используете Java 8
или выше, используйте версию 2.13 или 2.12. В противном случае - 2.11.

Запуск (Linux):
``` shell
./matr2scheme <filename>
```

Запуск (Windows):
``` shell
matr2scheme.bat <filename>
```

`filename` должен быть текстовым файлом с унитарной матрицей. Комплексные числа
вводятся в виде `(Re,Im)`. Для полной информации запустите `matr2scheme` без
параметров.
