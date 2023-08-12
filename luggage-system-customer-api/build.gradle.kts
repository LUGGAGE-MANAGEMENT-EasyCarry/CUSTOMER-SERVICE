import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.8"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("kapt") version "1.8.22"
}

group = "com.luggagesystem"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven {
		url = uri("http://10.115.210.111:8080/repository/maven-public/")
		isAllowInsecureProtocol = true
	}
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.0.2")


	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.apache.kafka:kafka-streams")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-json
	implementation("org.springframework.boot:spring-boot-starter-json:3.1.0")
	testImplementation("io.mockk:mockk:1.12.4")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.kafka:spring-kafka")
	// https://mvnrepository.com/artifact/io.r2dbc/r2dbc-postgresql
	implementation("io.r2dbc:r2dbc-postgresql:0.8.10.RELEASE")
	implementation("io.r2dbc:r2dbc-postgresql")
	implementation ("io.projectreactor:reactor-core")
	implementation("org.postgresql:r2dbc-postgresql") {
		exclude("io.projectreactor.netty", "reactor-netty-http-brave")
	}
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
