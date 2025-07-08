import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    // Java keywords (from Java 8)
    private static final String[] keywordArray = {
        "abstract", "assert", "boolean",   "break",      "byte",
        "case",     "catch",  "char",      "class",      "const",
        "continue", "default","do",        "double",     "else",
        "enum",     "extends","final",     "finally",    "float",
        "for",      "goto",   "if",        "implements", "import",
        "instanceof","int",    "interface", "long",       "native",
        "new",      "package","private",   "protected",  "public",
        "return",   "short",  "static",    "strictfp",   "super",
        "switch",   "synchronized","this","throw",      "throws",
        "transient","try",    "void",      "volatile",   "while",
        "true",     "false",  "null"
    };
    private static final Set<String> keywords =
        new HashSet<>(Arrays.asList(keywordArray));

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java CountKeywords <JavaSourceFile>");
            System.exit(1);
        }
        String content = new String(
            Files.readAllBytes(Paths.get(args[0])),
            "UTF-8"
        );

        // 1) Remove block comments: /* ... */
        content = content.replaceAll("/\\*(?:.|\\R)*?\\*/", " ");

        // 2) Remove line comments: // ...
        content = content.replaceAll("//.*", " ");

        // 3) Remove string literals: " ... "
        content = content.replaceAll("\"(?:\\\\.|[^\"\\\\])*\"", " ");

        int count = countKeywords(content);
        System.out.println("The number of keywords in " + args[0] + " is " + count);
    }

    private static int countKeywords(String text) {
        int cnt = 0;
        // Split on any character that is not a letter
        String[] tokens = text.split("[^A-Za-z]+");
        for (String token : tokens) {
            if (keywords.contains(token)) {
                cnt++;
            }
        }
        return cnt;
    }
}
