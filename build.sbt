organization := "fr.applicius"

name := "errol"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

scalacOptions += "-feature"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

libraryDependencies += "org.specs2" %% "specs2" % "1.14"
