import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization       := "ru.dovzhikov",
      crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.6"),
      version            := "0.3.0",
      scalacOptions      ++= Seq("-unchecked", "-deprecation")
    )),
    name := "matr2scheme",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      breeze
    ),
    libraryDependencies ++= (scalaBinaryVersion.value match {
      case "2.10" => Seq()
      case _      => Seq(parserComb)
    }),
    initialCommands in console := 
"""
import breeze.numerics._
import breeze.math._
import breeze.linalg._
import quant.implicits._
"""
  )
