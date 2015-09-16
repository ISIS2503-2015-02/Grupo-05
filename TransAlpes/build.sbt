name := """TransAlpes"""

version := "1.0-SNAPSHOT"
lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion:="2.11.6"

herokuAppName in Compile := "arqui201326232"

herokuJdkVersion in Compile := "1.8"

herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/transalpes -Dhttp.port=$PORT -Dplay.evolutions.db.default.autoApply=true -Ddb.default.url=${DATABASE_URL}"
)

//herokuStack in Compile := "cedar-14"

//ivyScala:= ivyScala.value map{_.copy(overrideScalaVersion = true)}


libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)     

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator



fork in run := true