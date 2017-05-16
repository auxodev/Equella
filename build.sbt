import java.util.Properties
import scala.collection.JavaConverters._

lazy val learningedge_config = project in file("Dev/learningedge-config")

lazy val allPlugins = LocalProject("allPlugins")

val legacyPaths = Seq(
  javaSource in Compile := baseDirectory.value / "src",
  javaSource in Test := baseDirectory.value / "test",
  unmanagedResourceDirectories in Compile := (baseDirectory.value / "resources") :: Nil,
  unmanagedSourceDirectories in Compile := (javaSource in Compile).value :: Nil,
  unmanagedSourceDirectories in Test := (javaSource in Test).value :: Nil
)

lazy val equellaserver = (project in file("Source/Server/equellaserver")).settings(legacyPaths).enablePlugins(JPFRunnerPlugin)

lazy val platformCommon = LocalProject("com_tle_platform_common")
lazy val platformSwing = LocalProject("com_tle_platform_swing")
lazy val platformEquella = LocalProject("com_tle_platform_equella")

lazy val adminTool = (project in file("Source/Server/adminTool")).settings(legacyPaths).dependsOn(
  platformSwing,
  platformEquella,
  LocalProject("com_tle_webstart_admin")
)

lazy val UpgradeInstallation = (project in file("Source/Tools/UpgradeInstallation")).settings(legacyPaths).dependsOn(
  platformCommon,
  platformEquella
)

lazy val UpgradeManager = (project in file("Source/Tools/UpgradeManager")).settings(legacyPaths).dependsOn(platformCommon, platformEquella)

lazy val Installer = (project in file("Installer")).settings(legacyPaths).dependsOn(platformCommon, platformSwing, platformEquella, UpgradeManager)

lazy val equella = (project in file(".")).enablePlugins(JPFScanPlugin).aggregate(equellaserver, allPlugins, adminTool, Installer)

name := "Equella"

versionProperties := {
  val props = new Properties
  props.putAll(
    Map("version.mm" -> "6.4",
        "version.mmr" -> "6.4.r43",
        "version.display" -> "6.4-Alpha",
        "version.commit" -> "3a75a23").asJava)
  val f = target.value / "version.properties"
  IO.write(props, "version", f)
  f
}