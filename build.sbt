organization := "fr.applicius"

name := "errol"

version := "1.0.7"

scalaVersion := "2.11.2"

scalacOptions += "-feature"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies += "org.specs2" %% "specs2" % "2.4.1"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.0"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
