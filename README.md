# Errol

Swing'ing UI for specs2 (or one of Lady Sybil Ramkin's Swamp dragons).

## Requirements

* Scala 2.10
* Java 1.6+
* SBT 0.12.2

## Usage

Errol can be used on SBT projects add dependency `"fr.applicius" %% "errol" % "VERSION"` 
and having `"Applicius Snapshots" at "https://raw.github.com/applicius/mvn-repo/master/snapshots/"` in resolvers.

### Scala

You can instantiate Error SwingRunner as following:

```scala
object YourRunner extends SwingRunner {
  // Custom labels
  def runningMessage = "Running…"
  def endMessage = "That's all folks"
  def frameTitle = "Your title"
  def buttonLabel = "Close"

  def subReporter = new HtmlReporter {} // Any specs2 reporter

  def specification = {
    // Your SpecificationStructure to be runned
  }

  override def beforeClosing {} // Optional callback
}
```

## Build

Errol can be built from these sources using SBT (0.12.2+): `sbt publish`
