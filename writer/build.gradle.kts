import org.gradle.kotlin.dsl.implementation

plugins {
	java
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.cart"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.slf4j:slf4j-api")
	implementation("ch.qos.logback:logback-classic")

	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:4.0.0"))

	implementation("io.awspring.cloud:spring-cloud-aws-starter-dynamodb")
	implementation("io.awspring.cloud:spring-cloud-aws-starter-sqs")
	implementation("io.awspring.cloud:spring-cloud-aws-starter-sns")

	implementation("software.amazon.awssdk:sqs")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
