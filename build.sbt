//
// Copyright (c) 2012 Alexey Aksenov ezh@ezh.msk.ru
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import com.jsuereth.sbtsite.SiteKeys

site.settings

ghpages.settings

organization := "org.digimead"

version := "1.0-SNAPSHOT"

crossScalaVersions := Seq("2.8.2", "2.9.1", "2.9.2")

git.remoteRepo := "git@github.com:sbt-android-mill/stopwatch.git"

site.addMappingsToSiteDir(mappings in core in packageDoc in Compile, "core-api")

site.addMappingsToSiteDir(mappings in web in packageDoc in Compile, "web-api")

SiteKeys.siteMappings <<=
  (SiteKeys.siteMappings, PamfletKeys.write, PamfletKeys.output, baseDirectory) map {
    (mappings, _, pamfletDir, baseDirectory) =>
      val publishDir = baseDirectory / "publish"
      val releasesDir = baseDirectory / "publish/releases"
      mappings ++ (pamfletDir ** "*.*" x relativeTo(pamfletDir)) ++ (releasesDir ** "*.*" x relativeTo(publishDir))
  }

PamfletKeys.docs <<= baseDirectory / "publish/docs"

TaskKey[Unit]("publish-github") <<= (streams, com.jsuereth.ghpages.GhPages.ghpages.pushSite) map { (s, push) =>
  s.log.info("publishing project to github")
}

TaskKey[Unit]("publish-github") <<= TaskKey[Unit]("publish-github").dependsOn(
  Keys.publish in Compile in core,
  Keys.publish in Compile in web,
  PamfletKeys.write)
