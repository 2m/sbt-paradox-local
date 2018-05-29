package lt.dvim.paradox

import sbt._

trait ParadoxLocalKeys {
  val paradoxLocalApiKey = settingKey[String]("Paradox properties key for the project API docs")
  val paradoxLocalApiDir = settingKey[File]("Directory containing project API docs")
}
