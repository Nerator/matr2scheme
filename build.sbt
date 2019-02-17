import Dependencies._

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    inThisBuild(List(
      organization       := "ru.dovzhikov",
      crossScalaVersions := Seq("2.11.12", "2.12.8"),
      version            := "0.3.1",
      scalacOptions      ++= Seq("-unchecked", "-deprecation")
    )),
    name := "matr2scheme",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      breeze,
      parserComb
    ),
    (mappings in Universal) := {
      val universalMappings = (mappings in Universal).value
      val filtered = universalMappings filter { 
        case (_, name) => 
          !name.endsWith(".jar") || name.contains("scala-library") || 
          name.contains("parser") || name.contains("breeze_") || name.contains("matr2scheme")
      }
      filtered
    },
    initialCommands in console := 
"""import breeze.numerics._
import breeze.math._
import breeze.linalg._
import quant.implicits._
"""
  )

lazy val packageVer = taskKey[File]("package zip file")

packageVer := {
  val output = baseDirectory.value / "package" / s"matr2scheme_${(scalaBinaryVersion in root).value}-${(version in root).value}.zip"
  val genfile = (packageBin in Universal).value
  IO.move(genfile, output)
  output
}
