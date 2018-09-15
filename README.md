# Thymeleaf Extras

[![circleci](https://img.shields.io/badge/circleci-thymeleaf--extras-brightgreen.svg)](https://circleci.com/gh/spt-oss/thymeleaf-extras)
[![maven central](https://img.shields.io/badge/maven_central-thymeleaf--extras-blue.svg)](https://mvnrepository.com/artifact/com.github.spt-oss/thymeleaf-extras)
[![javadoc](https://img.shields.io/badge/javadoc-thymeleaf--extras-blue.svg)](https://www.javadoc.io/doc/com.github.spt-oss/thymeleaf-extras)

* [Thymeleaf](https://github.com/thymeleaf/thymeleaf) extra modules for customizing templates
* **Note: This project is unofficial and experimental.**

## TOC

* [thymeleaf-extras-minify](#thymeleaf-extras-minify)
* [License](#license)

## thymeleaf-extras-minify

### Example

* Thymeleaf template
	```html
	<!DOCTYPE html>
	<html xmlns:th="http://www.thymeleaf.org">
	
	<!-- head -->
	<head>
	    <meta charset="utf-8" />
	    <title th:text="bar">foo</title>
	    <style>
	    body {
	      font-size: 1rem;
	    }
	    </style>
	......
	```
* Processed HTML
	```html
	<!DOCTYPE html><html><head><meta charset="utf-8" /><title>bar</title><style>body { font-size: 1rem; }</style>......
	```

### Usage

1. Add a dependency in your project.

	```xml
	<dependency>
	    <groupId>com.github.spt-oss</groupId>
	    <artifactId>thymeleaf-extras-minify</artifactId>
	    <version>3.0.11.0</version>
	</dependency>
	```

1. Add the `MinifierDialect` to the `TemplateEngine` instance.

	```java
	import org.thymeleaf.TemplateEngine;
	import org.thymeleaf.extras.minify.dialect.MinifierDialect;
	
	TemplateEngine engine = new TemplateEngine();
	engine.addDialect(new MinifierDialect());
	```

1. Or setup Spring configurations if your project is based on Spring Boot.

	```yml
	spring.thymeleaf:
	    prefix: classpath:/templates/
	```
	```java
	import org.springframework.context.annotation.Bean;
	import org.thymeleaf.extras.minify.dialect.MinifierDialect;
	
	@Bean
	public MinifierDialect minifierDialect() {
	    return new MinifierDialect();
	}
	```

### Customization

1. Create a subclass of 
[AbstractMinifierTemplateHandler](./thymeleaf-extras-minify/src/main/java/org/thymeleaf/extras/minify/engine/AbstractMinifierTemplateHandler.java) 
 or 
[SimpleMinifierTemplateHandler](./thymeleaf-extras-minify/src/main/java/org/thymeleaf/extras/minify/engine/SimpleMinifierTemplateHandler.java).

	```java
	package my.project;
	
	import org.thymeleaf.extras.minify.engine.AbstractMinifierTemplateHandler;
	
	public class MyMinifierTemplateHandler extends AbstractMinifierTemplateHandler {
	    ......
	}
	```

1. Set the class to the first argument of the `MinifierDialect`. Note that only class type is accepted because of Thymeleaf processor's restriction.

	```java
	import my.project.MyMinifierTemplateHandler;
	
	TemplateEngine engine = new TemplateEngine();
	engine.addDialect(new MinifierDialect(MyMinifierTemplateHandler.class));
	```
	```java
	import my.project.MyMinifierTemplateHandler;
	
	@Bean
	public MinifierDialect minifierDialect() {
	    return new MinifierDialect(MyMinifierTemplateHandler.class);
	}
	```

### References

* https://stackoverflow.com/questions/39098106/how-to-minify-thymeleaf-generated-html
  * The answer is very helpful.

## License

* This software is released under the Apache License 2.0.
