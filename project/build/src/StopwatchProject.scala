import sbt._

class StopwatchProject(info: ProjectInfo) extends ParentProject(info) with ScalaProject {
  val snapshots = "Scala Tools Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  lazy val core = project("core", "Stopwatch Core", new CoreProject(_))
  lazy val web = project("web", "Stopwatch Web Interface", new WebProject(_), core)

  class CoreProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest  = "org.scalatest" % "scalatest" % "1.0.1-SNAPSHOT" % "test"
    val scalacheck = "org.scala-tools.testing" % "scalacheck" % "1.6" % "test"
    val junit      = "junit" % "junit" % "4.5" % "test"
  }

  class WebProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest  = "org.scalatest" % "scalatest" % "1.0.1-SNAPSHOT"

    override val mainClass = Some("stopwatch.web.SampleServer")
  }

}
