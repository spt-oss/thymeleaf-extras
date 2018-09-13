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

package org.thymeleaf.extras.minify.postprocessor;

import org.thymeleaf.engine.ITemplateHandler;
import org.thymeleaf.extras.minify.engine.AbstractMinifierTemplateHandler;
import org.thymeleaf.postprocessor.IPostProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * {@link IPostProcessor}: Minifier
 */
public class MinifierPostProcessor implements IPostProcessor {
	
	/**
	 * Handler class
	 */
	private final Class<? extends AbstractMinifierTemplateHandler> handlerClass;
	
	/**
	 * Constructor
	 * 
	 * @param handlerClass {@link #handlerClass}
	 */
	public MinifierPostProcessor(Class<? extends AbstractMinifierTemplateHandler> handlerClass) {
		
		this.handlerClass = handlerClass;
	}
	
	@Override
	public TemplateMode getTemplateMode() {
		
		return TemplateMode.HTML;
	}
	
	@Override
	public int getPrecedence() {
		
		return Integer.MAX_VALUE;
	}
	
	@Override
	public Class<? extends ITemplateHandler> getHandlerClass() {
		
		return this.handlerClass;
	}
}
