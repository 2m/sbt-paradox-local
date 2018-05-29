/*
 * Copyright 2018 https://github.com/2m/sbt-paradox-local/graphs/contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lt.dvim.paradox

import java.io.File

import com.lightbend.paradox.sbt.ParadoxPlugin
import com.lightbend.paradox.sbt.ParadoxPlugin.autoImport._
import sbt._
import sbt.io.Path.rebase
import sbt.Keys._

object ParadoxLocalPlugin extends AutoPlugin {
  object autoImport extends ParadoxLocalKeys
  import autoImport._

  val Local = config("local")

  override def requires: Plugins = ParadoxPlugin
  override def trigger = AllRequirements

  override def projectConfigurations = Seq(Local)
  override def projectSettings =
    Seq(
      ParadoxPlugin.paradoxSettings(Local) ++ forwarderSettings ++ propertiesRemapSettings
    ).flatMap(inConfig(Local))

  val propertiesRemapSettings: Seq[Setting[_]] = Seq(
    paradoxProperties in Local ++= Map(
      // point API doc links to locally generated API docs
      paradoxLocalApiKey.value -> relativeRebase(
        (ThisBuild/baseDirectory).value,
        (Compile/paradox/target).value
      )(paradoxLocalApiDir.value).get
    )
  )

  val forwarderSettings: Seq[Setting[_]] = Seq(
    sourceDirectory := (Compile/sourceDirectory).value,
    paradoxGroups := (Compile/paradoxGroups).value,
    paradoxProperties := (Compile/paradoxProperties ).value
  )

  /**
   * A path mapper that pairs a descendent of `commonBase` with a relative path from `newBase`.
   * For example, if `commonBase = /common/` and `newBase = /common/a`, then `/common/b/c` gets paired with `../b/c`.
   */
  private def relativeRebase(commonBase: File, newBase: File): Path.PathMap = {
    val pathDiff = newBase.getAbsolutePath.replaceFirst(commonBase.getAbsolutePath, "")
    val levelDiff = pathDiff.split("/").tail.map(_ => "../").mkString("")
    rebase(commonBase, levelDiff)
  }
}
