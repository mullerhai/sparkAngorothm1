addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.10")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")


addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.19")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.8.1"