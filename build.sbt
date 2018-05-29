organization := "lt.dvim.paradox"
name := "sbt-paradox-local"
description := "Generate paradox documentation with local API links"

libraryDependencies ++= Seq(
)
addSbtPlugin("com.lightbend.paradox" % "sbt-paradox" % "0.3.3")

sbtPlugin := true
scriptedLaunchOpts += ("-Dproject.version=" + version.value)
scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
  a => Seq("-Xmx", "-Xms", "-XX", "-Dfile").exists(a.startsWith)
)
scriptedBufferLog := false

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/2m/sbt-paradox-local"))
scmInfo := Some(ScmInfo(url("https://github.com/2m/sbt-paradox-local"), "git@github.com:2m/sbt-paradox-local.git"))
developers += Developer("contributors",
                        "Contributors",
                        "https://gitter.im/lightbend/paradox",
                        url("https://github.com/2m/sbt-paradox-local/graphs/contributors"))
bintrayOrganization := Some("2m")
bintrayRepository := (if (isSnapshot.value) "snapshots" else "maven")
organizationName := "https://github.com/2m/sbt-paradox-local/graphs/contributors"
startYear := Some(2018)

scalafmtOnCompile := true
enablePlugins(AutomateHeaderPlugin)
