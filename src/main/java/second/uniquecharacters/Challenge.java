package second.uniquecharacters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {

    public static final String pattern = "([a-zA-Z]).*?\\1";

    public static final String space = "\\s";

    public static boolean hasUniqueChars( String input ) {
        boolean isUnique = false;
        Pattern p = Pattern.compile( pattern );
        Matcher m = p.matcher( input );
        if ( input.matches( space ) || m.find() ) {
            isUnique = false;
        } else {
            isUnique = true;
        }
        return isUnique;
    }
}
