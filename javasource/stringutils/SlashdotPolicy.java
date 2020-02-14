package stringutils;

//Copyright (c) 2011, Mike Samuel
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without
//modification, are permitted provided that the following conditions
//are met:
//
//Redistributions of source code must retain the above copyright
//notice, this list of conditions and the following disclaimer.
//Redistributions in binary form must reproduce the above copyright
//notice, this list of conditions and the following disclaimer in the
//documentation and/or other materials provided with the distribution.
//Neither the name of the OWASP nor the names of its contributors may
//be used to endorse or promote products derived from this software
//without specific prior written permission.
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
//"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
//LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
//FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
//COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
//BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
//LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
//LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
//ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
//POSSIBILITY OF SUCH DAMAGE.
import java.util.regex.Pattern;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class SlashdotPolicy {
	public static final PolicyFactory POLICY_DEFINITION = new HtmlPolicyBuilder()
	          .allowStandardUrlProtocols()
	          // Allow title="..." on any element.
	          .allowAttributes("title").globally()
	          // Allow href="..." on <a> elements.
	          .allowAttributes("href").onElements("a")
	          // Defeat link spammers.
	          .requireRelNofollowOnLinks()
	          // Allow lang= with an alphabetic value on any element.
	          .allowAttributes("lang").matching(Pattern.compile("[a-zA-Z]{2,20}"))
	              .globally()
	          // The align attribute on <p> elements can have any value below.
	          .allowAttributes("align")
	              .matching(true, "center", "left", "right", "justify", "char")
	              .onElements("p")
	          // These elements are allowed.
	          .allowElements(
	              "a", "p", "div", "i", "b", "em", "blockquote", "tt", "strong",
	              "br", "ul", "ol", "li")
	          // Custom slashdot tags.
	          // These could be rewritten in the sanitizer using an ElementPolicy.
	          .allowElements("quote", "ecode")
	          .toFactory();
	
	 public static final String Sanitize(String html) {
		// Use the policy defined above to sanitize the HTML.
	    return POLICY_DEFINITION.sanitize(html);
	  }

}
