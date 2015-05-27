name := "mnist-lib"

version := "1.0-SNAPSHOT"

organization := "me.kevinp"

scalaVersion := "2.11.1"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/kevinpeterson/MNIST-lib</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:kevinpeterson/MNIST-lib.git</url>
    <connection>scm:git:git@github.com:kevinpeterson/MNIST-lib.git</connection>
    <developerConnection>scm:git:git@github.com:kevinpeterson/MNIST-lib.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>kevinpeterson</id>
      <name>Kevin Peterson</name>
      <url>http://kevinp.me</url>
    </developer>
  </developers>)