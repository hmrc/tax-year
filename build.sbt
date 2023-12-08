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
  .settings(scalaVersion := "2.13.12")
  .settings(majorVersion := 4)
  .settings(isPublicArtefact := true)
  .settings(ScoverageSettings())
  .settings(
    resolvers += Resolver.typesafeRepo("releases"),
    scalafmtOnCompile := true
  )

libraryDependencies ++= Seq(
  "org.threeten"         % "threeten-extra"  % "1.7.2",
  "com.vladsch.flexmark" % "flexmark-all"    % "0.64.6"   % Test,
  "org.pegdown"          % "pegdown"         % "1.6.0"    % Test,
  "org.scalatest"       %% "scalatest"       % "3.2.17"   % Test,
  "org.scalatestplus"   %% "scalacheck-1-17" % "3.2.17.0" % Test
)
