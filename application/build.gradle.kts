dependencies {

	// coroutine
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

	// @Transactional用
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")

	implementation(project(":common"))
	implementation(project(":domain"))
}
