[![Build Status](https://travis-ci.org/Nerator/matr2scheme.svg?branch=master)](https://travis-ci.org/Nerator/matr2scheme)

# matr2scheme

## Обзор

Реализация алгоритма разложения произвольной унитарной матрицы в произведение двухуровневых матриц. Алгоритм описан в книге Микио Накахары и Тетсуо Оми __"Quantum computing : from linear algebra to physical realizations"__ в главе 4.6.

## Сборка

Для Scala 2.12.5:
``` shell
sbt ++2.12.5 package
```

Для Scala 2.11.12:
``` shell
sbt ++2.11.12 package
```

Скомпилированные JAR-файлы будут находиться в `target/scala2.12` или `target/scala2.11` соответственно.

## Использование

Запуск:
``` shell
scala matr2scheme_<version> <filename>
```

`file` должен быть текстовым файлом с унитарной матрицей (пока что не проверяется). Комплексные числа вводятся в виде `(Re,Im)`.
