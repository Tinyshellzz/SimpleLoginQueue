import java.text.SimpleDateFormat
version = "1.0.1"

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("http://repo.crypticlib.com:8081/repository/maven-public/") {
        isAllowInsecureProtocol = true
    }
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://jitpack.io")
    maven("https://repo.rosewooddev.io/repository/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven("https://r.irepo.space/maven/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://nexus.phoenixdevt.fr/repository/maven-public/")
    //EcoEnchants
    maven("https://repo.auxilor.io/repository/maven-public/")
    //NBT-API
    maven("https://repo.codemc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
}

group = "com.tinyshellzz.permissionManager"
var pluginVersion: String = version.toString() + "-" + SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {
    val props = HashMap<String, String>()
    props["version"] = pluginVersion
    processResources {
        filesMatching("plugin.yml") {
            expand(props)
        }
        filesMatching("config.yml") {
            expand(props)
        }
    }
    compileJava {
        dependsOn(clean)
        options.encoding = "UTF-8"
    }
}