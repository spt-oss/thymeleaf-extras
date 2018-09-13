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

import java.util.regex.Pattern;

import org.thymeleaf.util.StringUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * {@link AbstractMinifierTemplateHandler}: Simple
 */
public class SimpleMinifierTemplateHandler extends AbstractMinifierTemplateHandler {
	
	/**
	 * Line break pattern
	 */
	protected static final Pattern LINE_BREAK_PATTERN = Pattern.compile("[\r\n]");
	
	/**
	 * Left spaces pattern
	 */
	protected static final Pattern LEFT_SPACES_PATTERN = Pattern.compile("^[ \\t]+");
	
	/**
	 * Right spaces pattern
	 */
	protected static final Pattern RIGHT_SPACES_PATTERN = Pattern.compile("[ \\t]+$");
	
	/**
	 * Middle spaces pattern
	 */
	protected static final Pattern MIDDLE_SPACES_PATTERN = Pattern.compile("[ \\t]+");
	
	/**
	 * Remove line break
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean removeLineBreak = true;
	
	/**
	 * Line break replacement
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private String lineBreakReplacement = "";
	
	/**
	 * Remove left spaces
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean removeLeftSpaces = true;
	
	/**
	 * Left spaces replacement
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private String leftSpacesReplacement = "";
	
	/**
	 * Remove right spaces
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean removeRightSpaces = true;
	
	/**
	 * Right spaces replacement
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private String rightSpacesReplacement = "";
	
	/**
	 * Remove middle spaces
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private boolean removeMiddleSpaces = true;
	
	/**
	 * Middle spaces replacement
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter
	@Accessors(chain = true)
	private String middleSpacesReplacement = " ";
	
	@Override
	protected String minifyInlineStyle(String value) {
		
		return this.minifyTextInternal(value);
	}
	
	@Override
	protected String minifyInlineScript(String value) {
		
		return this.minifyTextInternal(value);
	}
	
	@Override
	protected String minifyText(String value) {
		
		return this.minifyTextInternal(value);
	}
	
	/**
	 * {@link #minifyText(String)} internal
	 * 
	 * @param value value
	 * @return minified value
	 */
	protected String minifyTextInternal(String value) {
		
		if (StringUtils.isEmpty(value)) {
			
			return value;
		}
		
		String minified = value;
		
		if (this.removeLineBreak) {
			
			minified = LINE_BREAK_PATTERN.matcher(minified).replaceAll(this.lineBreakReplacement);
		}
		
		if (this.removeLeftSpaces) {
			
			minified = LEFT_SPACES_PATTERN.matcher(minified).replaceAll(this.leftSpacesReplacement);
		}
		
		if (this.removeRightSpaces) {
			
			minified = RIGHT_SPACES_PATTERN.matcher(minified).replaceAll(this.rightSpacesReplacement);
		}
		
		if (this.removeMiddleSpaces) {
			
			minified = MIDDLE_SPACES_PATTERN.matcher(minified).replaceAll(this.middleSpacesReplacement);
		}
		
		return minified;
	}
}
