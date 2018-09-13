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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.IPostProcessorDialect;
import org.thymeleaf.extras.minify.engine.AbstractMinifierTemplateHandler;
import org.thymeleaf.extras.minify.engine.SimpleMinifierTemplateHandler;
import org.thymeleaf.extras.minify.postprocessor.MinifierPostProcessor;
import org.thymeleaf.postprocessor.IPostProcessor;

/**
 * Minifier dialect
 */
public class MinifierDialect implements IPostProcessorDialect {
	
	/**
	 * {@link Class}: {@link AbstractMinifierTemplateHandler}
	 */
	private final Class<? extends AbstractMinifierTemplateHandler> handlerClass;
	
	/**
	 * Constructor
	 */
	public MinifierDialect() {
		
		this(SimpleMinifierTemplateHandler.class);
	}
	
	/**
	 * Constructor
	 * 
	 * @param handlerClass {@link #handlerClass}
	 */
	public MinifierDialect(Class<? extends AbstractMinifierTemplateHandler> handlerClass) {
		
		this.handlerClass = handlerClass;
	}
	
	@Override
	public String getName() {
		
		return "html-minifier";
	}
	
	@Override
	public int getDialectPostProcessorPrecedence() {
		
		return Integer.MAX_VALUE;
	}
	
	@Override
	public Set<IPostProcessor> getPostProcessors() {
		
		return new HashSet<>(Arrays.asList(new MinifierPostProcessor(this.handlerClass)));
	}
}
