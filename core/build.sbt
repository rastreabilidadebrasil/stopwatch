name := "stopwatch-core"

scalaVersion := "2.10.4"

exportJars := true

version := "1.1-SNAPSHOT"

description := "A project for profiling Scala code"

publishTo  <<= baseDirectory  { (base) => Some(Resolver.file("file",  base / "../publish/releases" )) }

libraryDependencies ++= Seq(
  "org.scalatest" %%  "scalatest" % "2.2.0" % "test",
  "org.scala-lang" % "scala-actors" % "2.10.1",
  "junit" % "junit" % "4.11" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)
