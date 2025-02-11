name := """sample-scala-web-app"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.4.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// RDB系
libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.7.3",

  "org.scalikejdbc" %% "scalikejdbc"                  % "4.3.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "4.3.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "3.0.0-scalikejdbc-4.2"
)

// DI
libraryDependencies += guice

// Cats
libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"



















