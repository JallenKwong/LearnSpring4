# Spring Boot的数据访问 #


## JPA/Hibernate/Spring Date JPA概念 ##

ORM的全称是Object/Relation Mapping

当ORM框架完成映射后，程序员即可以利用OOP的简单易用性，又可以利用RDB的技术优势。

![](image/01.png)

基本映射方式

1. 数据表映射类
2. 数据表的行映射对象（即实例）
3. 数据表的列（字段）映射对象的属性

流行的ORM框架简介

1. Hibernate
2. JPA
3. Spring Data JPA

## Spring Data JPA ##

### Spring Data 核心数据访问接口 ###

Spring Data JPA是Spring Data 下的一个模块，想了解一下Spring Data核心数据访问接口。

	package org.springframework.data.repository;

	public interface Repository<T, ID> {
	
	}

---

	package org.springframework.data.repository;

	public interface CrudRepository<T, ID> extends Repository<T, ID> {
	
		<S extends T> S save(S entity);
	
		<S extends T> Iterable<S> saveAll(Iterable<S> entities);
	
		Optional<T> findById(ID id);
	
		boolean existsById(ID id);
	
		Iterable<T> findAll();
	
		Iterable<T> findAllById(Iterable<ID> ids);
	
		long count();
	
		void deleteById(ID id);
	
		void delete(T entity);
	
		void deleteAll(Iterable<? extends T> entities);
	
		void deleteAll();
	}

### 示例：CrudRepository接口访问数据 ###

增加依赖

	<!-- 添加MySQL依赖 -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>

	<!-- 添加Spring Data JPA依赖 -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>

---

[数据库连接等配置信息](crudrepositorytest/src/main/resources/application.properties)

---

[创建持久化类User](crudrepositorytest/src/main/java/org/fkit/crudrepositorytest/bean/User.java)

---

[定义数据访问层接口](crudrepositorytest/src/main/java/org/fkit/crudrepositorytest/repository/UserRepository.java)


	public interface UserRepository extends CrudRepository<User, Integer>{
		//这里故意留白
	}

>PS.好精简啊

---

[定义业务层类](crudrepositorytest/src/main/java/org/fkit/crudrepositorytest/service/UserService.java)

---

[定义控制类](crudrepositorytest/src/main/java/org/fkit/crudrepositorytest/controller/UserController.java)

---

创建数据库

	CREATE DATABASE springdatajpa;

数据库表会由应用创建

---

运行应用

1. http://127.0.0.1:8080/user/save 增
2. http://127.0.0.1:8080/user/update 改
3. http://127.0.0.1:8080/user/getAll 查
4. http://127.0.0.1:8080/user/delete 删

### 示例：PagingAndSortingRepository接口访问数据 ###

PagingAndSortingRepository继承CrudRepository接口，并另外新增了排序和分页查询功能

	package org.springframework.data.repository;

	public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
	
		Iterable<T> findAll(Sort sort);
	
		Page<T> findAll(Pageable pageable);
	}

[pom.xml](pagingandsortingrepositorytest/pom.xml)同上例基本雷同

[application.properties](pagingandsortingrepositorytest/src/main/resources/application.properties)同上例基本雷同

[持久化类Article](pagingandsortingrepositorytest/src/main/java/org/fkit/pagingandsortingrepositorytest/bean/Article.java)

---

[定义数据访问层接口](pagingandsortingrepositorytest/src/main/java/org/fkit/pagingandsortingrepositorytest/repository/ArticleRepository.java)

---

[定义业务层类](pagingandsortingrepositorytest/src/main/java/org/fkit/pagingandsortingrepositorytest/service/ArticleService.java)

---

[定义控制器类](pagingandsortingrepositorytest/src/main/java/org/fkit/pagingandsortingrepositorytest/controller/ArticleController.java)

---

[为数据库插入数据](pagingandsortingrepositorytest/scripts/db.sql)

---

运行应用

1. http://127.0.0.1:8080/article/sort 查询全部
2. http://127.0.0.1:8080/article/page/1 查询分页


### Spring Data JPA开发 ###

