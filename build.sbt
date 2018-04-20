import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ru.dovzhikov",
      crossScalaVersions := Seq("2.11.12", "2.12.5"),
      version      := "0.2.0"
    )),
    name := "matr2scheme",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      parserComb
    )
  )

// set the prompt (for this build) to include the project id.
// shellPrompt in ThisBuild := { state => name.value + "> " }