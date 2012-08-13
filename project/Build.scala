import sbt._
import Keys._

object StopwatchBuild extends Build {
  lazy val root = Project(id = "stopwatch", base = file(".")) settings (publish := { }) dependsOn(web) aggregate(web)
  lazy val web = Project(id = "web", base = file("web")) dependsOn(core) aggregate(core)
  lazy val core = Project(id = "core", base = file("core"))
}
