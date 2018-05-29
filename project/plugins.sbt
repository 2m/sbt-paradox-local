addSbtPlugin("com.dwijnand" % "sbt-dynver" % "3.0.0")
addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.6.0-RC1")
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.0.0")

libraryDependencies += { "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value }
