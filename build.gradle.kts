plugins {
	java
	war
	id("org.springframework.boot") version "2.7.18"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "org.hdivsamples"
version = "0.0.1-SNAPSHOT"

sourceSets {
	main {
		resources.srcDir("src/main/webapp")
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.ldap:spring-ldap-core")
	implementation("com.unboundid:unboundid-ldapsdk")
	implementation("commons-fileupload:commons-fileupload:1.5")
	implementation("commons-io:commons-io:2.13.0")
	implementation("com.google.guava:guava:30.0-jre")

	runtimeOnly("javax.servlet:jstl")
	runtimeOnly("org.apache.tomcat.embed:tomcat-embed-jasper")
	runtimeOnly("org.hsqldb:hsqldb")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
