plugins {
    java
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {

        implementation("org.projectlombok", "lombok", "1.18.22")
        annotationProcessor("org.projectlombok", "lombok", "1.18.22")

        compileOnly("org.mapstruct", "mapstruct", "1.4.1.Final")
        annotationProcessor("org.mapstruct", "mapstruct-processor", "1.4.1.Final")

        testImplementation("org.projectlombok", "lombok", "1.18.22")
        testAnnotationProcessor("org.projectlombok", "lombok", "1.18.22")
        testAnnotationProcessor("org.mapstruct", "mapstruct", "1.4.2.Final")
    }

    tasks.withType<JavaCompile>() {
        sourceCompatibility = JavaVersion.VERSION_11.toString();
        targetCompatibility = JavaVersion.VERSION_11.toString();
    }
}
