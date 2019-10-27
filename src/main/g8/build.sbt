val ZioVersion       = "1.0.0-RC15"
val ZioCatsVersion   = "2.0.0.0-RC6"
val Specs2Version    = "4.8.0"
val ScalaTestVersion = "3.2.0-M1"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val root = (project in file("."))
  .settings(
    organization := "ZIO",
    name := "zio-top-project",
    version := "0.0.1",
    scalaVersion := "2.12.10",
    maxErrors := 3,
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"              % ZioVersion,
      "dev.zio"       %% "zio-interop-cats" % ZioCatsVersion,
      "dev.zio"       %% "zio-test"         % ZioVersion % "test",
      "dev.zio"       %% "zio-test-sbt"     % ZioVersion % "test",
      "org.specs2"    %% "specs2-core"      % Specs2Version % "test",
      "org.scalactic" %% "scalactic"        % ScalaTestVersion,
      "org.scalatest" %% "scalatest"        % ScalaTestVersion % "test"
    )
  )

testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))

// Refine scalac params from tpolecat
scalacOptions --= Seq(
  "-Xfatal-warnings"
)
addCompilerPlugin(scalafixSemanticdb)
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("lint", "; compile:scalafix --check ; test:scalafix --check")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheckAll")
addCommandAlias("cov", "; clean; coverage; test; coverageReport")
