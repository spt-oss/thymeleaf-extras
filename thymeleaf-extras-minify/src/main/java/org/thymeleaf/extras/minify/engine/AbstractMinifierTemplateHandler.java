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

package org.thymeleaf.extras.minify.engine;

import java.util.Objects;

import org.thymeleaf.engine.AbstractTemplateHandler;
import org.thymeleaf.engine.TextUtils;
import org.thymeleaf.model.ICloseElementTag;
import org.thymeleaf.model.IComment;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IText;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * {@link AbstractTemplateHandler}: Minifier
 */
public abstract class AbstractMinifierTemplateHandler extends AbstractTemplateHandler {
	
	/**
	 * Style
	 */
	protected static final String STYLE = "style";
	
	/**
	 * Script
	 */
	protected static final String SCRIPT = "script";
	
	/**
	 * Remove comments
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean removeComments = true;
	
	/**
	 * Minify inline style
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean minifyInlineStyle = true;
	
	/**
	 * Minify inline script
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean minifyInlineScript = true;
	
	/**
	 * Inside style elements
	 */
	@Getter(AccessLevel.PROTECTED)
	private boolean insideStyleElements;
	
	/**
	 * Inside script elements
	 */
	@Getter(AccessLevel.PROTECTED)
	private boolean insideScriptElements;
	
	@Override
	public void handleComment(IComment comment) {
		
		if (!this.removeComments) {
			
			super.handleComment(comment);
		}
	}
	
	@Override
	public void handleText(@NonNull IText text) {
		
		String value = text.getText();
		
		if (this.minifyInlineStyle && this.insideStyleElements) {
			
			value = this.minifyInlineStyle(value);
		}
		else if (this.minifyInlineScript && this.insideScriptElements) {
			
			value = this.minifyInlineScript(value);
		}
		else {
			
			value = this.minifyText(value);
		}
		
		super.handleText(TextUtils.copy(text, value));
	}
	
	@Override
	public void handleOpenElement(@NonNull IOpenElementTag openElementTag) {
		
		String name = openElementTag.getElementCompleteName();
		
		if (!this.insideStyleElements && Objects.equals(name, STYLE)) {
			
			this.insideStyleElements = true;
		}
		else if (!this.insideScriptElements && Objects.equals(name, SCRIPT)) {
			
			this.insideScriptElements = true;
		}
		
		super.handleOpenElement(openElementTag);
	}
	
	@Override
	public void handleCloseElement(@NonNull ICloseElementTag closeElementTag) {
		
		String name = closeElementTag.getElementCompleteName();
		
		if (this.insideStyleElements && Objects.equals(name, STYLE)) {
			
			this.insideStyleElements = false;
		}
		else if (this.insideScriptElements && Objects.equals(name, SCRIPT)) {
			
			this.insideScriptElements = false;
		}
		
		super.handleCloseElement(closeElementTag);
	}
	
	/**
	 * Minify inline style
	 * 
	 * @param value value
	 * @return minified value
	 */
	protected abstract String minifyInlineStyle(String value);
	
	/**
	 * Minify inline script
	 * 
	 * @param value value
	 * @return minified script
	 */
	protected abstract String minifyInlineScript(String value);
	
	/**
	 * Minify text
	 * 
	 * @param value value
	 * @return minified script
	 */
	protected abstract String minifyText(String value);
}
