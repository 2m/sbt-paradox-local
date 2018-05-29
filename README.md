# [sbt-paradox-local][]

[sbt-paradox-local]:        https://github.com/2m/sbt-paradox-local

This plugin adds a `Local` sbt configuration to the [sbt-paradox](https://github.com/lightbend/paradox) enabled projects which then can be used to generate paradox documentation with local API links.

## Usage

Add this to your sbt build plugins in `project/plugins.sbt`:

```scala
addSbtPlugin("lt.dvim.paradox" % "sbt-paradox-local" % "0.1")
```

Set values for the following sbt keys:

```scala
// paradox properties key for the local project api base url
paradoxLocalApiKey := "scaladoc.<my.project>.base_url"

// directory where the local API scaladoc can be found
paradoxLocalApiDir := (myProject / Compile / doc).value
```

This plugin is then enabled automatically and you can use `Local/paradox` task to generate documentation with links to the locally generated API docs.
