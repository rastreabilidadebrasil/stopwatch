import com.jsuereth.sbtsite.SiteKeys

name := "stopwatch"

organization := "org.alexboisvert"

version := "1.0-SNAPSHOT"

description := "A project for profiling Scala code"

scalaVersion := "2.8.2"

unmanagedResourceDirectories in Compile <+= baseDirectory { _ / "src" / "main" / "scala" }

// add all setting from the site plugin to the project
seq(site.settings:_*)

// add all setting from the ghpages plugin to the project
seq(ghpages.settings:_*)

// read-write git repository URI of the current project
git.remoteRepo := "git@github.com:sbt-android-mill/stopwatch.git"

// I have chosen to publish my Scaladocs under the /api/... path
// instead of /api/latest/...
// so this line adds new target location for the generated Scaladocs
site.addMappingsToSiteDir(mappings in packageDoc in Compile, "api")
