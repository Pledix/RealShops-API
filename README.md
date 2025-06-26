# RealShops-API
API for RealShops plugin

Gradle
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("dev.pledix.realshops:realshops-api:1.0.0")
}
```

Maven
```xml
<dependencies>
    <dependency>
        <groupId>dev.pledix.realshops</groupId>
        <artifactId>realshops-api</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>central</id>
        <name>Maven Central</name>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
</repositories>
```