name := "hello-scala"

version := "1.0"

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "[3.5,)"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.3"

