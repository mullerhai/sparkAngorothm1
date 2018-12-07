
//import scalapb.compiler.Version.grpcJavaVersion
//import scalapb.compiler.Version.scalapbVersion
//import com.trueaccord.scalapb.compiler.Version.grpcJavaVersion
import com.trueaccord.scalapb.compiler.Version.scalapbVersion
name := "sparkAngorothm"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/org.nd4j/nd4j-native-platform
// "org.nd4j" % "nd4j-native-platform" % "1.0.0-beta3",
//// https://mvnrepository.com/artifact/org.deeplearning4j/deeplearning4j-modelimport
// "org.deeplearning4j" % "deeplearning4j-modelimport" % "1.0.0-beta3",
 "com.typesafe" % "config" % "1.3.1",
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
 "org.apache.spark" %% "spark-core" % "2.4.0")
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.2"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.7.2"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.2"

// https://mvnrepository.com/artifact/io.grpc/grpc-all
libraryDependencies += "io.grpc" % "grpc-all" % "1.17.0"

// https://mvnrepository.com/artifact/org.apache.thrift/libthrift
libraryDependencies ++= Seq(
    "org.apache.thrift" % "libthrift" % "0.10.0",
    "com.twitter" %% "scrooge-core" % "18.10.0" exclude("com.twitter", "libthrift"),
    "com.twitter" %% "finagle-thrift" % "18.10.0" exclude("com.twitter", "libthrift")
)

// https://mvnrepository.com/artifact/com.trueaccord.scalapb/scalapb-runtime
libraryDependencies += "com.trueaccord.scalapb" %%% "scalapb-runtime" % "0.6.7"
// https://mvnrepository.com/artifact/com.trueaccord.scalapb/scalapb-runtime-grpc
libraryDependencies += "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.6.7"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}
//
assemblyMergeStrategy in assembly := {
 case PathList("javax", "servlet", xs @ _*)               => MergeStrategy.first
 case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
 case PathList(ps @ _*) if ps.last endsWith ".xml"        => MergeStrategy.first
 case PathList(ps @ _*) if ps.last endsWith ".types"      => MergeStrategy.first
 case PathList(ps @ _*) if ps.last endsWith ".class"      => MergeStrategy.first
 case PathList(ps @ _*) if ps.last endsWith ".json"       => MergeStrategy.last
 case PathList(ps @ _*) if ps.last endsWith ".provides"   => MergeStrategy.last
 case PathList(ps @ _*) if ps.last endsWith ".factories"  => MergeStrategy.last
 case "application.conf"                                  => MergeStrategy.concat
 case "unwanted.txt"                                      => MergeStrategy.discard
 case x =>
  val oldStrategy = (assemblyMergeStrategy in assembly).value
  oldStrategy(x)
}

//libraryDependencies+="com.trueaccord.scalapb"%%"scalapb-runtime"%com.trueaccord.scalapb.compiler.Version.scalapbVersion%"protobuf"


libraryDependencies ++= Seq(
 // For finding google/protobuf/descriptor.proto
 "com.thesamet.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf",
// "io.grpc" % "grpc-netty" % grpcJavaVersion,
 "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion
)

//PB.targets in Compile := Seq(
//
// scalapb.gen() -> (sourceManaged in Compile).value
//)
//libraryDependencies ++= Seq(
// // For finding google/protobuf/descriptor.proto
// "com.thesamet.scalapb" %% "scalapb-runtime" % scalapbVersion % "protobuf"
//)