package file.html;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @Title: HtmlTagReplace.java
 * @Description: <br>
 *               <br>html 标签替换
 * @Created on 2016年3月12日 下午4:07:14
 * @author yangkai
 * @version 1.0
 */
public class HtmlTagReplace {


      private static final char[] HEX_CHARS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
      };

      private static final Set<Character> JS_ESCAPE_CHARS;
      private static final Set<Character> HTML_ESCAPE_CHARS;

      static {
        Set<Character> mandatoryEscapeSet = new HashSet<Character>();
        mandatoryEscapeSet.add('"');
        mandatoryEscapeSet.add('\\');
        JS_ESCAPE_CHARS = Collections.unmodifiableSet(mandatoryEscapeSet);

        Set<Character> htmlEscapeSet = new HashSet<Character>();
        htmlEscapeSet.add('<');
        htmlEscapeSet.add('>');
        htmlEscapeSet.add('&');
        htmlEscapeSet.add('=');
        htmlEscapeSet.add('\''); 
        HTML_ESCAPE_CHARS = Collections.unmodifiableSet(htmlEscapeSet);
      }

      private final boolean escapeHtmlCharacters;
      
      HtmlTagReplace(boolean escapeHtmlCharacters) {
        this.escapeHtmlCharacters = escapeHtmlCharacters;
      }
 

      /**
       * json处理
       * @param plainText
       * @param out
       * @throws IOException
       */
      public String escapeJsonString(CharSequence plainText) {
        StringBuffer escapedString = new StringBuffer(plainText.length() + 20);
        try {
          escapeJsonString(plainText, escapedString);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return escapedString.toString();
      }

      /**
       * json处理实现
       * @param plainText
       * @param out
       * @throws IOException
       */
      private void escapeJsonString(CharSequence plainText, StringBuffer out) throws IOException {
        int pos = 0;  // Index just past the last char in plainText written to out.
        int len = plainText.length();
        
        for (int charCount, i = 0; i < len; i += charCount) {
          int codePoint = Character.codePointAt(plainText, i);
          charCount = Character.charCount(codePoint);
          
           if (!isControlCharacter(codePoint) && !mustEscapeCharInJsString(codePoint)) {
              continue;
           }

           out.append(plainText, pos, i);
           pos = i + charCount;
           switch (codePoint) {
             case '\b':
//               out.append("\\b");
               out.append("");
               break;
             case '\t':
//               out.append("\\t");
               out.append("");
               break;
             case '\n':
//               out.append("\\n");
               out.append("");
               break;
             case '\f':
//               out.append("\\f");
               out.append("");
               break;
             case '\r':
//               out.append("\\r");
               out.append("");
               break;
             /*case '\\':
               out.append("\\\\");
               break;*/
             case '/':
               out.append("\\/");
               break;
             case '"':
//               out.append("\"");
               out.append("\\\"");
               break;
             default:
               appendHexJavaScriptRepresentation(codePoint, out);
               break;
           }
         }
         out.append(plainText, pos, len);
      }
      
      /**
       * html处理
       * @param codepoint
       * @return
       */
      private boolean mustEscapeCharInJsString(int codepoint) {
        if (!Character.isSupplementaryCodePoint(codepoint)) {
          char c = (char) codepoint;
          return JS_ESCAPE_CHARS.contains(c)
              || (escapeHtmlCharacters && HTML_ESCAPE_CHARS.contains(c));
        } else {
          return false;
        }
      }

      private static boolean isControlCharacter(int codePoint) {
        // JSON spec defines these code points as control characters, so they must be escaped
        return codePoint < 0x20
            || codePoint == 0x2028  // Line separator
            || codePoint == 0x2029  // Paragraph separator
            || (codePoint >= 0x7f && codePoint <= 0x9f);
      }

      private static void appendHexJavaScriptRepresentation(int codePoint, Appendable out)
          throws IOException {
        if (Character.isSupplementaryCodePoint(codePoint)) {
          char[] surrogates = Character.toChars(codePoint);
          appendHexJavaScriptRepresentation(surrogates[0], out);
          appendHexJavaScriptRepresentation(surrogates[1], out);
          return;
        }
        out.append("\\u")
            .append(HEX_CHARS[(codePoint >>> 12) & 0xf])
            .append(HEX_CHARS[(codePoint >>> 8) & 0xf])
            .append(HEX_CHARS[(codePoint >>> 4) & 0xf])
            .append(HEX_CHARS[codePoint & 0xf]);
      }
}
