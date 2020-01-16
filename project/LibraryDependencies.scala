import sbt._

object LibraryDependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.github.nscala-time" %% "nscala-time" % "2.22.0"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = ???
  }

  object Test {
    def apply(): Seq[ModuleID] =
      new TestDependencies {
        override lazy val test: Seq[ModuleID] = Seq(
          "org.scalatest" %% "scalatest" % "3.0.1" % scope,
          "org.pegdown" % "pegdown" % "1.6.0" % scope
        )
      }.test
  }
  def apply(): Seq[ModuleID] = compile ++ Test()
}
