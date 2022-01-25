import org.springframework.boot.gradle.tasks.bundling.BootJar

apply(plugin = "org.springframework.boot")

tasks.getByName<BootJar>("bootJar") {
	mainClass.set("com.chigirh.bc.notice.BcNoticeApplicationKt")
}

dependencies {
	implementation(project(":common"))
	implementation(project(":web"))
	implementation(project(":batch"))
	implementation(project(":application"))
	implementation(project(":domain"))
	implementation(project(":persistence"))
	implementation(project(":infra:mysql"))
	implementation(project(":infra:api"))
	implementation(project(":infra:mail"))
}
