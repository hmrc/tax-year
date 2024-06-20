lazy val taxYear = (project in file("."))
  .settings(
    name := "tax-year",
    crossSbtVersions := List("0.13.18", "1.3.4", "1.6.2", "1.7.2", "1.9.7", "1.9.9"),
    crossScalaVersions := List("2.13.8", "2.13.14", "3.3.3"),
    developers := List(
      Developer(
        "andy-gray",
        "Andrew Gray",
        "andrew.gray@digital.hmrc.gov.uk",
        new URL("http://www.hmrc.gov.uk")
      )
    )
  )
  .settings(scalaVersion := "2.13.14")
  .settings(majorVersion := 5)
  .settings(isPublicArtefact := true)
  .settings(ScoverageSettings())
  .settings(
    resolvers += Resolver.typesafeRepo("releases"),
    scalafmtOnCompile := true
  )

libraryDependencies ++= Seq(
  "org.threeten"         % "threeten-extra"  % "1.8.0",
  "com.vladsch.flexmark" % "flexmark-all"    % "0.64.6"   % Test,
  "org.pegdown"          % "pegdown"         % "1.6.0"    % Test,
  "org.scalatest"       %% "scalatest"       % "3.2.18"   % Test,
  "org.scalatestplus"   %% "scalacheck-1-17" % "3.2.18.0" % Test
)
