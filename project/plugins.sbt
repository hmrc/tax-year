resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("uk.gov.hmrc"   % "sbt-auto-build" % "3.15.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage"  % "1.9.3")
addSbtPlugin("org.scalameta" % "sbt-scalafmt"   % "2.4.6")
