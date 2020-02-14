package stringutils;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.base.Function;
import com.mendix.systemwideinterfaces.MendixRuntimeException;

import stringutils.proxies.XSSPolicy;

public class StringUtils
{

	public static final String	HASH_ALGORITHM	= "SHA-256";
	
	public static String hash(String value, int length) throws NoSuchAlgorithmException, DigestException
	{
		byte[] inBytes = value.getBytes(StandardCharsets.UTF_8);
		byte[] outBytes = new byte[length];

	    MessageDigest alg=MessageDigest.getInstance(HASH_ALGORITHM);
	    alg.update(inBytes);

	    alg.digest(outBytes, 0, length);

	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < outBytes.length; i++) {
	    String hex = Integer.toHexString(0xff & outBytes[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    
	    return hexString.toString();
	}

	public static String regexReplaceAll(String haystack, String needleRegex,
			String replacement)
	{
		Pattern pattern = Pattern.compile(needleRegex);
		Matcher matcher = pattern.matcher(haystack);
		return matcher.replaceAll(replacement);
	}

	public static boolean regexTest(String value, String regex)
	{
		return Pattern.matches(regex, value);
	}

	public static String leftPad(String value, Long amount, String fillCharacter)
	{
		if (fillCharacter == null || fillCharacter.length() == 0) {
			return org.apache.commons.lang3.StringUtils.leftPad(value, amount.intValue(), " ");
		}
		return org.apache.commons.lang3.StringUtils.leftPad(value, amount.intValue(), fillCharacter);
	}
	
	public static String rightPad(String value, Long amount, String fillCharacter)
	{
		if (fillCharacter == null || fillCharacter.length() == 0) {
			return org.apache.commons.lang3.StringUtils.rightPad(value, amount.intValue(), " ");
		}
		return org.apache.commons.lang3.StringUtils.rightPad(value, amount.intValue(), fillCharacter);
	}

	public static String randomString(int length)
	{
		return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(length);
	}

//  Move to Templates module	
//	
//	public static String substituteTemplate(final IContext context, String template,
//			final IMendixObject substitute, final boolean HTMLEncode, final String datetimeformat) {
//		return regexReplaceAll(template, "\\{(@)?([\\w./]+)\\}", new Function<MatchResult, String>() {
//
//			@Override
//			public String apply(MatchResult match)
//			{
//				String value;
//				String path = match.group(2);
//				if (match.group(1) != null)
//					value = String.valueOf(Core.getConfiguration().getConstantValue(path));
//				else {
//					try
//					{
//						value = ORM.getValueOfPath(context, substitute, path,	datetimeformat);
//					}
//					catch (Exception e)
//					{
//						throw new RuntimeException(e);
//					}
//				}
//				return HTMLEncode ? HTMLEncode(value) : value;
//			}
//			
//		});
//	}
	
	public static String regexReplaceAll(String source, String regexString, Function<MatchResult, String> replaceFunction)  {
		if (source == null || source.trim().isEmpty()) // avoid NPE's, save CPU
			return "";
	
		StringBuffer resultString = new StringBuffer();
		Pattern regex = Pattern.compile(regexString);
		Matcher regexMatcher = regex.matcher(source);
		
		while (regexMatcher.find()) {
			MatchResult match = regexMatcher.toMatchResult();
			String value = replaceFunction.apply(match); 
			regexMatcher.appendReplacement(resultString, Matcher.quoteReplacement(value));
		}
		regexMatcher.appendTail(resultString);
	
		return resultString.toString();
	}

	public static String HTMLEncode(String value)
	{
		return StringEscapeUtils.escapeHtml4(value);
	}

	public static String URLEncode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8");
	}
	
	public static String randomHash()
	{
		return UUID.randomUUID().toString();
	}

	public static String base64Decode(String encoded)
	{
		if (encoded == null)
			return null;
		return new String(Base64.decodeBase64(encoded.getBytes()));
	}

	public static String base64Encode(String value)
	{
		if (value == null)
			return null;
		return new String(Base64.encodeBase64(value.getBytes()));
	}

	public static String HTMLToPlainText(String html) throws IOException
	{
		if (html == null)
			return "";
		final StringBuffer result = new StringBuffer();
		
    HTMLEditorKit.ParserCallback callback = 
      new HTMLEditorKit.ParserCallback () {
        @Override
				public void handleText(char[] data, int pos) {
            result.append(data); //TODO: needds to be html entity decode?
        }
        
        @Override
        public void handleComment(char[] data, int pos) {
        	//Do nothing
        }
        
        @Override
				public void handleError(String errorMsg, int pos) {
        	//Do nothing
        }
        
        @Override
        public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet a, int pos) {
        		 if (tag == HTML.Tag.BR)
        			 result.append("\r\n");
        }
        
        @Override
        public void handleEndTag(HTML.Tag tag, int pos){
     		 if (tag == HTML.Tag.P)
    			 result.append("\r\n");
        }
    };
    
    new ParserDelegator().parse(new StringReader(html), callback, true);
		
    return result.toString();
	}

	public static String XSSSanitize(String html, XSSPolicy policy)
			throws Exception {
		if (html == null)
			return "";
		// return HtmlSanitizer.sanitize(html);
		String policyString = policy == null ? "ebay" : policy.toString()
				.toLowerCase();
		return XSSSanitize(html, policyString);
	}
	
	public static String XSSSanitize(String html, String policyString)
			throws Exception {
		
		String sanitizedHTML = null;
		
		if (html == null)
			return "";
		if (policyString == null)
			throw new Exception("Unable to perform XSS sanitization: policyString is null");
		
		if("ebay".equals(policyString)) {
			sanitizedHTML = EbayPolicy.Sanitize(html);  
		}
		else if("slashdot".equals(policyString)) {
			sanitizedHTML = SlashdotPolicy.Sanitize(html);
		}

		return sanitizedHTML;
		
	}

	private static final String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM     = "0123456789";
    private static final String SPL_CHARS   = "!@#$%^&*_=+-/";
	/**
	 * Returns a random strong password containing at least one number, lowercase character, uppercase character and strange character
	 * @param length
	 * @return
	 */
	public static String randomStrongPassword(int minLen, int maxLen, int noOfCAPSAlpha, 
            int noOfDigits, int noOfSplChars) {
        if(minLen > maxLen)
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        if( (noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen )
            throw new IllegalArgumentException
            ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        Random rnd = new Random();
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for(int i = 0; i < len; i++) {
            if(pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return String.valueOf(pswd);
    }
	private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while(pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }

	public static String encryptString(String key, String valueToEncrypt) throws Exception
	{
		if (valueToEncrypt == null) 
			return null;
		if (key == null)
			throw new MendixRuntimeException("Key should not be empty");
		if (key.length() != 16)
			throw new MendixRuntimeException("Key length should be 16");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		SecretKeySpec k = new SecretKeySpec(key.getBytes(), "AES");
		c.init(Cipher.ENCRYPT_MODE, k);
		byte[] encryptedData = c.doFinal(valueToEncrypt.getBytes());
		byte[] iv = c.getIV();
		
		return new String(Base64.encodeBase64(iv)) + ";" + new String(Base64.encodeBase64(encryptedData));
	}

	public static String decryptString(String key, String valueToDecrypt) throws Exception
	{
		if (valueToDecrypt == null)
			return null;
		if (key == null)
			throw new MendixRuntimeException("Key should not be empty");
		if (key.length() != 16)
			throw new MendixRuntimeException("Key length should be 16");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		SecretKeySpec k = new SecretKeySpec(key.getBytes(), "AES");
		String[] s = valueToDecrypt.split(";");
		if (s.length < 2) //Not an encrypted string, just return the original value.
			return valueToDecrypt;
		byte[] iv = Base64.decodeBase64(s[0].getBytes());
		byte[] encryptedData = Base64.decodeBase64(s[1].getBytes());
		c.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
		return new String(c.doFinal(encryptedData));
	}

	
	private static byte[] generateHmacSha256ByteArray(String key, String value) throws InvalidKeyException, NoSuchAlgorithmException {
		
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);

		byte[] rawHmac = (sha256_HMAC.doFinal(value.getBytes()));
		
		return rawHmac; 
	}
	
	
	public static String generateHmacSha256Hash(String key, String valueToEncrypt)
	{
		try {
            return new String(Base64.encodeBase64(generateHmacSha256ByteArray(key,valueToEncrypt)));
		}
		catch (Exception e) {
			throw new RuntimeException("StringUtils::EncodeHmacSha256::Unable to encode: " + e.getMessage(), e);
		}
	}
	
	public static String generateHmacSha256HexDigest(String key, String valueToEncrypt)
	{
		try {
            return Hex.encodeHexString(generateHmacSha256ByteArray(key,valueToEncrypt));
		}
		catch (Exception e) {
			throw new RuntimeException("StringUtils::EncodeHmacSha256::Unable to encode: " + e.getMessage(), e);
		}
	}
	

	public static String regexQuote(String unquotedLiteral) {
		return Pattern.quote(unquotedLiteral);
	}
}
