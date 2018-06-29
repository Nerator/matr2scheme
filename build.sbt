import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ru.dovzhikov",
      crossScalaVersions := Seq("2.10.7","2.11.12", "2.12.6"),
      version      := "0.2.1"
    )),
    name := "matr2scheme",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= (scalaBinaryVersion.value match {
      case "2.10" => Seq()
      case _      => Seq(parserComb)
    })
  )

// set the prompt (for this build) to include the project id.
// shellPrompt in ThisBuild := { state => name.value + "> " }