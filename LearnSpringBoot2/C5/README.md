# Spring Boot的热部署与单元测试 #

[1.使用spring-boot-devtools进行热部署](#使用spring-boot-devtools进行热部署)

[1.1.示例：使用spring-boot-devtools实现热部署](#示例使用spring-boot-devtools实现热部署)

[2.Spring Boot的单元测试](#spring-boot的单元测试)

[2.1.示例：使用Spring Boot的单元测试](#示例使用spring-boot的单元测试)

## 使用spring-boot-devtools进行热部署 ##

### 示例：使用spring-boot-devtools实现热部署 ###

引入依赖

	<!-- Spring Boot spring-boot-devtools 依赖 -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
		<scope>true</scope>
	</dependency>

另外还需加入插件

	<!-- 添加spring-boot-maven-plugin -->
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<!-- 如果没有该项配置，devtools不会起作用，即应用不会restart -->
					<fork>true</fork>
				</configuration>
			</plugin>
		</plugins>
	</build>

这样启动应用就可以热部署。每次修改并保存源码，系统自动更新启动，大大方便开发工作。

注意：Eclipse上菜单栏Project下拉选项中的Build Automatically自动编译必须被勾选。

## Spring Boot的单元测试 ##

### 示例：使用Spring Boot的单元测试 ###

创建数据库

	CREATE DATABASE springboottest;

引入依赖

	<!-- spring-boot-starter-test 依赖.... -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>

[Controller单元测试](springboottest/src/test/java/org/fkit/springboottest/StudentControllerTest.java)

[Service单元测试](springboottest/src/test/java/org/fkit/springboottest/StudentServiceTest.java)

