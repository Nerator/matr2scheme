import Dependencies._

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
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
    //exportJar := true,
    (mappings in Universal) := {
      val universalMappings = (mappings in Universal).value
      val filtered = universalMappings filter { 
        case (_, name) =>  !name.endsWith(".jar") || name.contains("scala-library") || name.contains("parser") || name.contains("breeze_") || name.contains("matr2scheme")
      }
      filtered
    },
    initialCommands in console := 
"""
import breeze.numerics._
import breeze.math._
import breeze.linalg._
import quant.implicits._
"""
  )

addCommandAlias("packageAll", "; clean " + 
                              "; ++2.12.6 " +
                              "; package212 " +
                              "; clean " + 
                              "; ++2.11.12 " +
                              "; package212 " +
                              "; clean " + 
                              "; ++2.10.7 " +
                              "; package212 " +
                              "; clean")

lazy val package212 = taskKey[Unit]("package 2.12")
//lazy val package211 = taskKey[Unit]("package 2.11")
//lazy val package210 = taskKey[Unit]("package 2.10")

package212 := {
  val binver = (scalaBinaryVersion in root).value
  println(s"binver: $binver")
  val ver = (version in root).value
  println(s"ver: $ver")
  //val output = baseDirectory.value / "package" / s"matr2scheme_${binver}-${ver}.zip"
  val output = baseDirectory.value / "package" / s"matr2scheme_${(scalaBinaryVersion in root).value}-${(version in root).value}.zip"
  println(s"out: $output")
  val genfile = (packageBin in Universal).value
  IO.move(genfile, output)
  //output
  //genfile
}
/*
  val sl = (dependencyClasspath in Compile).value.filter(x => x.data.getName.contains("scala-library"))
  println(sl)
  val pc = (dependencyClasspath in Compile).value.filter(x => x.data.getName.contains("parser"))
  println(pc)
  val br = (dependencyClasspath in Compile).value.filter(x => x.data.getName.contains("breeze_"))
  println(br)
  val writedir = baseDirectory.value / "target" / "dist"
  println(writedir)
}
*/