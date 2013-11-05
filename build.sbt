organization := "fr.applicius"

name := "errol"

version := "1.0.6"

scalaVersion := "2.10.3"

scalacOptions += "-feature"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.2"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.4"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
