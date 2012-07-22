name := "stopwatch"

organization := "org.alexboisvert"

version := "1.0-SNAPSHOT"

description := "A project for profiling Scala code"

scalaVersion := "2.8.2"

unmanagedResourceDirectories in Compile <+= baseDirectory { _ / "src" / "main" / "scala" }
