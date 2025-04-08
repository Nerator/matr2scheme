import org.typelevel.scalacoptions.ScalacOptions

val catsCoreVersion = "2.13.0"
val catsEffectVersion = "3.6.0"
val spireVersion = "0.18.0"
val attoVersion = "0.9.5"
val scalaCheckVersion = "1.18.1"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    tpolecatScalacOptions += ScalacOptions.source3,
    libraryDependencies ++= (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq.empty
      case _ => Seq(
        "org.typelevel" % "kind-projector" % "0.13.3" cross CrossVersion.full,
        "com.olegpy" %% "better-monadic-for" % "0.3.1"
      )
    })
  )
  .settings(
    inThisBuild(List(
      organization       := "ru.dovzhikov",
      scalaVersion       := "2.13.16",
      crossScalaVersions := Seq("2.13.16", "3.6.4"),
      version            := "0.6.0",
    )),
    name := "matr2scheme",
    libraryDependencies ++= Seq(
      "org.typelevel"          %% "cats-core"                % catsCoreVersion,
      "org.typelevel"          %% "cats-effect"              % catsEffectVersion,
      "org.typelevel"          %% "spire"                    % spireVersion,
      "org.tpolecat"           %% "atto-core"                % attoVersion,
      "org.scalameta"          %% "munit"                    % "1.1.0" % Test,
      "org.scalameta"          %% "munit-scalacheck"         % "1.1.0" % Test,
      "org.typelevel"          %% "munit-cats-effect"        % "2.1.0" % Test,
      "org.typelevel"          %% "discipline-munit"         % "2.0.0" % Test,
      "org.scalacheck"         %% "scalacheck"               % scalaCheckVersion % Test,
      "org.typelevel"          %% "cats-laws"                % catsCoreVersion % Test,
      "org.typelevel"          %% "spire-laws"               % spireVersion % Test,
    ),
    Compile / console / initialCommands := 
"""import quant.instances.all._
"""
  )

lazy val packageVer = taskKey[File]("package zip file")

packageVer := {
  val output = baseDirectory.value / "package" / s"matr2scheme_${(root / scalaBinaryVersion).value}-${(root / version).value}.zip"
  val genfile = (Universal / packageBin).value
  IO.move(genfile, output)
  output
}
