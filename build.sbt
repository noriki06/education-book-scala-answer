/*
 * Copyright IxiaS, Inc. All Rights Reserved.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

import com.scalapenos.sbt.prompt._

organization := "net.ixias"
name         := "education-book-scala-answer"
scalaVersion := "3.6.4"

resolvers ++= Seq(
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
  "Sonatype Release"  at "https://oss.sonatype.org/content/repositories/releases/"
)

scalacOptions ++= Seq(
  "-deprecation",       // Emit warning and location for usages of deprecated APIs.
  "-feature",           // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",         // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings",   // Fail the compilation if there are any warnings.
  "-Wunused:imports",   // Warn if an import selector is not referenced.
  "-Wvalue-discard"     // Warn when non-Unit expression results are unused.
)
Compile / run / fork := true

val root         = (project in file("."))
val defaultTheme = PromptTheme(List(
  text("[SBT] ", fg(green)),
  text(state => { Project.extract(state).get(organization) + "@" }, fg(magenta)),
  text(state => { Project.extract(state).get(name) },               fg(magenta)),
  text(":", NoStyle),
  gitBranch(clean = fg(green), dirty = fg(yellow)).padLeft("[").padRight("]"),
  text(" > ", NoStyle)
))
promptTheme := defaultTheme
shellPrompt := (implicit state => promptTheme.value.render(state))
libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0"

resolvers ++= Seq(
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/ivy-releases/",
  "Sonatype Release"  at "https://oss.sonatype.org/content/repositories/releases/",
  "IxiaS Releases"    at "https://s3-ap-northeast-1.amazonaws.com/maven.ixias.net/releases",
  "IxiaS Snapshots"   at "https://s3-ap-northeast-1.amazonaws.com/maven.ixias.net/snapshots"
)

libraryDependencies += "net.ixias" %% "ixias-core" % "3.1.3-SNAPSHOT"
