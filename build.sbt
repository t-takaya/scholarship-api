name := "scholarship-api"
organization := "com.example"
scalaVersion := "2.12.11"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.jsoup"              %  "jsoup"              % "1.13.1",
  "org.typelevel"          %% "cats-kernel"        % "2.1.1",
  "org.typelevel"          %% "cats-core"          % "2.1.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  guice
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
