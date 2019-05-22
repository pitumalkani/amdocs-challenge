package third.markdown.parser;
import java.util.HashMap;
import java.util.Map;

public class Challenge {

    private static final String PATTERN = "^#{1,6}\\s.*";

    private static final String SPACE = "\\s";

    private static final char HASH = '#';

    private final static Map<Integer, String> headerMap = new HashMap<Integer, String>() {

        private static final long serialVersionUID = -4589553565995497397L;
        {
            put( 6, "\\s?######\\s(.*)" );
            put( 5, "\\s?#####\\s(.*)" );
            put( 4, "\\s?####\\s(.*)" );
            put( 3, "\\s?###\\s(.*)" );
            put( 2, "\\s?##\\s(.*)" );
            put( 1, "\\s?#\\s(.*)" );

        }
    };

    public static void main( String args[] ) {
        System.out.println( "basicValidCases" );

        markdownParser( "# Big" );
        markdownParser( "## Not As Big" );
        markdownParser( "### Smaller Still" );
        markdownParser( "###### So Tiny For a Header" );

        System.out.println( "basicInvalidCases" );
        markdownParser( "#NoSpace" );
        markdownParser( "Behind # The Scenes" );
        markdownParser( "Wizard# Behind The Curtain" );

        System.out.println( "edgeCases" );
        markdownParser( "### ### Double Triple Header" );
        markdownParser( "####### Snow White and the Seven Hashtags" );
        markdownParser( " #### Space Jam" );
        markdownParser( " ## Lost In Space " );
        markdownParser( "# Far Out, Dude" );

    }

    public static String markdownParser( String input ) {
        String result = null;
        input = input.trim();
        if ( input.matches( PATTERN ) ) {
            int count = getHashCount( input );
            result = input.replaceFirst( headerMap.get( count ), getReplace( count ) );
            return result;
        } else {
            return input;
        }

    }

    private static int getHashCount( String input ) {
        String[] text = input.split( SPACE );
        int count = (int)text[0].chars().filter( ch -> ch == HASH ).count();
        return count;
    }

    private static String getReplace( int count ) {
        String replace = "<h" + count + ">$1</h" + count + ">";
        return replace;
    }


}
