name := "stopwatch-web"



version := "1.1-SNAPSHOT"

description := "A project for profiling Scala code"

publishTo  <<= baseDirectory  { (base) => Some(Resolver.file("file",  base / "../publish/releases" )) }

libraryDependencies ++= Seq(
  "org.scalatest" %%  "scalatest" % "2.2.0" % "test",
  "org.scala-lang" % "scala-actors" % "2.10.1",
  "junit" % "junit" % "4.11" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.4" % "test"
)

resolvers += "stopwatch" at "https://github.com/rastreabilidadebrasil/stopwatch/raw/master/publish/releases"

libraryDependencies +="stopwatch-core" %% "stopwatch-core" % "latest.integration"