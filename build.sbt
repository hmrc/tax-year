lazy val taxYear = (project in file("."))
  .settings(
    name := "tax-year",
    crossSbtVersions := List("0.13.18", "1.3.4", "1.6.2", "1.7.2", "1.9.7"),
    crossScalaVersions := List("2.12.15", "2.13.8", "2.13.12"),
    developers := List(
      Developer(
        "andy-gray",
        "Andrew Gray",
        "andrew.gray@digital.hmrc.gov.uk",
        new URL("http://www.hmrc.gov.uk")
      )
    )
  )
  .settings(majorVersion := 4)
  .settings(isPublicArtefact := true)
  .settings(ScoverageSettings())
  .settings(
    resolvers += Resolver.typesafeRepo("releases"),
    scalafmtOnCompile := true
  )

libraryDependencies ++= Seq(
  "org.threeten"         % "threeten-extra" % "1.7.2",
  "com.vladsch.flexmark" % "flexmark-all"   % "0.36.8" % Test,
  "org.pegdown"          % "pegdown"        % "1.6.0"  % Test
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor <= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2" % Test
      )
    case _ =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2" % Test
      )
  }
}
