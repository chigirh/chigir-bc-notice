import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension


// swagger
val spec = "$rootDir/openapi/bc-notice-api.yaml"
val generatedOpenApiSourcesDir = "$buildDir/generated/openapi"
val swaggerVersion = "2.9.2"

apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(project(":common"))
    implementation(project(":application"))
    implementation(project(":domain"))
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.openapitools:openapi-generator-gradle-plugin:5.3.0")
        classpath("gradle.plugin.org.hidetake:gradle-swagger-generator-plugin:2.18.2")
    }
}

sourceSets {
    main {
        kotlin {
            sourceSets["main"].apply {
                kotlin.srcDir("$generatedOpenApiSourcesDir/src/main/kotlin")
                kotlin.srcDir("$rootDir/src/gen/kotlin")
            }
        }
    }
}
apply(plugin = "org.openapi.generator")

val Project.`openApiGenerate`: OpenApiGeneratorGenerateExtension
    get() =
        (this as ExtensionAware).extensions.getByName("openApiGenerate") as OpenApiGeneratorGenerateExtension


fun Project.`openApiGenerate`(configure: OpenApiGeneratorGenerateExtension.() -> Unit): Unit =
    (this as ExtensionAware).extensions.configure("openApiGenerate", configure)

openApiGenerate {
    //config docs
    //https://openapi-generator.tech/docs/generators/kotlin-spring/
    //https://openapi-generator.tech/docs/configuration/
    generatorName.set("kotlin-spring")
    inputSpec.set(spec)
    outputDir.set(generatedOpenApiSourcesDir)
    apiPackage.set("com.chigirh.bc.notice.web.oas3.controller")
    invokerPackage.set("com.chigirh.bc.notice.web.oas3")
    modelPackage.set("com.chigirh.bc.notice.web.oas3.model")
    modelNameSuffix.set("Dto")

    configOptions.set(
        mapOf(
            "dateLibrary" to "java8"
        )
    )
    additionalProperties.set(
        mapOf(
            "delegatePattern" to "true",
            "unhandledException" to "true"
        )
    )
}