# MBJCommons

Most repetitive Java variables, operations and utilities.

Installation with Maven:

```xml
<repositories>
    <repository>
        <id>oss-ternary-software</id>
        <url>https://oss.ternary.software/repository/maven-public</url>
    </repository>
</repositories>

<dependency>
    <groupId>net.bedra.maciej</groupId>
    <artifactId>mbjcommons</artifactId>
    <version>1.0.0</version>
</dependency>
```

Installation with Gradle:

```groovy
repositories {
    maven {
        url('https://oss.ternary.software/repository/maven-public')
    }
}

dependencies {
    compile('net.bedra.maciej:mbjcommons:1.0.0')
}
```

## About library

**MBJCommons** is strictly utilisation library. It contains the most repetitive Java variables and operations. Library is all time under development because new operations are being added during other (bigger) projects development. Every single method has it's unit test for various scenarios. Detailed Java Doc Strings are also provided.

## How to use the library

Library can be imported with usage of building tools like Maven or Gradle. In the repository there is directory named releases. Every release of library packed to **.jar** can be found here with full documentation generated from Java Doc Strings. In **CHANGELOG.md** file all implemented operations are described in really simple words.

## Requirements

Minimal requirements:

- Java 8
- Gradle 4.10.3

## Releases

Every release of the library is described in **CHANGELOG.md** file. 

List of releases:

- 1.0.0