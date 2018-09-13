/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.thymeleaf.extras.minify.dialect;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;
import org.thymeleaf.extras.minify.engine.SimpleMinifierTemplateHandler;

/**
 * {@link Test}: {@link MinifierDialect}
 */
public class MinifierDialectTests {
	
	/**
	 * Execute
	 */
	@Test
	public void execute() {
		
		// TODO more tests
		assertThat(processTemplate("html/source.html")).isEqualTo(readFromClassPath("html/remove-all.html"));
	}
	
	/**
	 * Process template
	 * 
	 * @param path path
	 * @return processed text
	 */
	private static String processTemplate(String path) {
		
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new MinifierDialect(SimpleMinifierTemplateHandler.class));
		
		try {
			
			return templateEngine.process(readFromClassPath(path), new Context());
		}
		catch (TemplateEngineException e) {
			
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Read from class path
	 * 
	 * @param path path
	 * @return text
	 */
	private static String readFromClassPath(String path) {
		
		try {
			
			return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			
			throw new IllegalStateException(e);
		}
	}
}
