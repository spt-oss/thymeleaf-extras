# Thymeleaf Extras

[![circleci](https://img.shields.io/badge/circleci-thymeleaf--extras-brightgreen.svg)](https://circleci.com/gh/spt-oss/thymeleaf-extras)
[![maven central](https://img.shields.io/badge/maven_central-thymeleaf--extras-blue.svg)](https://mvnrepository.com/artifact/com.github.spt-oss/thymeleaf-extras)
[![javadoc](https://img.shields.io/badge/javadoc-thymeleaf--extras-blue.svg)](https://www.javadoc.io/doc/com.github.spt-oss/thymeleaf-extras)

* Thymeleaf extra modules for customizing templates
* ⚠️**This project is unofficial and experimental.**

## TOC

* [thymeleaf-extras-minify](#thymeleaf-extras-minify)
	* [Example](#example)
	* [Usage](#usage)
	* [Customization](#customization)
	* [References](#references)
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
	...
	```
* Processed HTML
	```html
	<!DOCTYPE html><html><head><meta charset="utf-8" /><title>bar</title><style>body { font-size: 1rem; }</style>...
	```

### Usage

1. Add a dependency in your project.

	```xml
	<dependency>
		<groupId>com.github.spt-oss</groupId>
		<artifactId>thymeleaf-extras-minify</artifactId>
		<version>0.1.0</version>
	</dependency>
	```

2. Add the `MinifierDialect` to the `TemplateEngine` instance.

	```java
	import org.thymeleaf.TemplateEngine;
	import org.thymeleaf.extras.minify.dialect.MinifierDialect;
	
	TemplateEngine engine = new TemplateEngine();
	engine.addDialect(new MinifierDialect());
	```

3. Or create Spring Bean if your project is based on Spring Boot.

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
[SimpleMinifierTemplateHandler](./thymeleaf-extras-minify/src/main/java/org/thymeleaf/extras/minify/engine/SimpleMinifierTemplateHandler.java) 
or 
[AbstractMinifierTemplateHandler](./thymeleaf-extras-minify/src/main/java/org/thymeleaf/extras/minify/engine/AbstractMinifierTemplateHandler.java).

	```java
	package my.project;
	
	import org.thymeleaf.extras.minify.engine.SimpleMinifierTemplateHandler;
	
	public class CustomMinifierTemplateHandler extends SimpleMinifierTemplateHandler {
		......
	}
	```

2. Set the class to the first argument of the dialect.

	```java
	import org.thymeleaf.TemplateEngine;
	import org.thymeleaf.extras.minify.dialect.MinifierDialect;
	import my.project.CustomMinifierTemplateHandler;
	
	TemplateEngine engine = new TemplateEngine();
	engine.addDialect(new MinifierDialect(CustomMinifierTemplateHandler.class));
	```

### References

* https://stackoverflow.com/questions/39098106/how-to-minify-thymeleaf-generated-html
  * The answer is very helpful.

## License

* This software is released under the Apache License 2.0.
