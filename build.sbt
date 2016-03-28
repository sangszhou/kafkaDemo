name := "KafkaDemo_1"

version := "1.0"

scalaVersion := "2.11.8"

val akkaV = "2.3.6"

libraryDependencies += "org.apache.kafka"  %   "kafka_2.11"   %   "0.8.2.0" excludeAll(ExclusionRule(organization="org.slf4j"))

libraryDependencies +=  "com.typesafe.akka"                %%  "akka-actor"               %   akkaV
libraryDependencies +=  "com.typesafe.akka"                %%  "akka-slf4j"               %   akkaV
libraryDependencies +=  "com.typesafe.akka"                %%  "akka-testkit"             %   akkaV
libraryDependencies +=  "ch.qos.logback"                   %   "logback-classic"          %   "1.1.2"
