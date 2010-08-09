import sbt._

class StopwatchProject(info: ProjectInfo) extends ParentProject(info) with ScalaProject {
  val snapshots = "Scala Tools Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

  lazy val core = project("core", "Stopwatch Core", new CoreProject(_))
  lazy val web = project("web", "Stopwatch Web Interface", new WebProject(_), core)

  class CoreProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest  = "org.scalatest" % "scalatest" % "1.2-for-scala-2.8.0.final-SNAPSHOT" % "test"
    val scalacheck = "org.scala-tools.testing" % "scalacheck_2.8.0" % "1.7" % "test"
    val junit      = "junit" % "junit" % "4.5" % "test"
  }

  class WebProject(info: ProjectInfo) extends DefaultProject(info) {
    val scalatest  = "org.scalatest" % "scalatest" % "1.2-for-scala-2.8.0.final-SNAPSHOT" % "test"

    override val mainClass = Some("stopwatch.web.SampleServer")
  }

}
