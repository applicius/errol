organization := "fr.applicius"

name := "errol"

version := "1.0.5"

scalaVersion := "2.10.2"

scalacOptions += "-feature"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies += "org.specs2" %% "specs2" % "1.14"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.2"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
