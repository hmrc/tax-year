lazy val taxYear = (project in file("."))
  .settings(
    name := "tax-year",
    crossSbtVersions := List("0.13.18", "1.3.4", "1.6.2"),
    crossScalaVersions := List("2.11.12", "2.12.15", "2.13.8"),
    developers := List(
      Developer(
        "andy-gray",
        "Andrew Gray",
        "andrew.gray@digital.hmrc.gov.uk",
        new URL("http://www.hmrc.gov.uk")
      )
    )
  )
  .settings(majorVersion := 1)
  .settings(isPublicArtefact := true)
  .settings(ScoverageSettings())
  .settings(
    resolvers += Resolver.typesafeRepo("releases"),
    resolvers += "hmrc-releases" at "https://artefacts.tax.service.gov.uk/artifactory/hmrc-releases/"
  )

libraryDependencies ++= Seq(
  "org.threeten"            % "threeten-extra"    % "1.5.0",
  "com.vladsch.flexmark"    % "flexmark-all"      % "0.36.8"  % Test,
  "org.pegdown"             %  "pegdown"          % "1.6.0"   % Test
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor <= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus.play" %% "scalatestplus-play"        % "4.0.3"       % Test,
        "org.scalatestplus"      %% "scalatestplus-scalacheck"  % "3.1.0.0-RC2" % Test
      )
    case _ =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus.play" %% "scalatestplus-play"        % "5.1.0"       % Test,
        "org.scalatestplus"      %% "scalatestplus-scalacheck"  % "3.1.0.0-RC2" % Test
      )
  }
}