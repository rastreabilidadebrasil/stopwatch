import sbt._

class StopwatchProject(info: ProjectInfo) extends ParentProject(info) with ScalaProject {
  val snapshots = "Scala Tools Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  lazy val core = project("core", "Stopwatch Core", new CoreProject(_))
  lazy val web = project("web", "Stopwatch Web Interface", new WebProject(_), core)

  class CoreProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
    val scalacheck = "org.scala-tools.testing" % "scalacheck_2.9.0" % "1.9" % "test"
    val junit      = "junit" % "junit" % "4.5" % "test"
  }

  class WebProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"

    override val mainClass = Some("stopwatch.web.SampleServer")
  }

}
