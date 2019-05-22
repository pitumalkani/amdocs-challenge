package third.markdown.parser;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarkdownParserTests {
    @Test
    public void basicValidCases() {
        String expected = "<h1>Big</h1>";
        String actual = Challenge.markdownParser( "# Big" );
        assertEquals( expected, actual );
        String expected1 = "<h2>Not As Big</h2>";
        String actual1 = Challenge.markdownParser( "## Not As Big" );
        assertEquals( expected1, actual1 );
        String expected2 = "<h3>Smaller Still</h3>";
        String actual2 = Challenge.markdownParser( "### Smaller Still" );
        assertEquals( expected2, actual2 );
        String expected3 = "<h6>So Tiny For a Header</h6>";
        String actual3 = Challenge.markdownParser( "###### So Tiny For a Header" );
        assertEquals( expected3, actual3 );
    }

    @Test
    public void basicInvalidCases() {
        String expected = "#NoSpace";
        String actual = Challenge.markdownParser( "#NoSpace" );
        assertEquals( expected, actual );
        String expected1 = "Behind # The Scenes";
        String actual1 = Challenge.markdownParser( "Behind # The Scenes" );
        assertEquals( expected1, actual1 );
        String expected2 = "Wizard# Behind The Curtain";
        String actual2 = Challenge.markdownParser( "Wizard# Behind The Curtain" );
        assertEquals( expected2, actual2 );
    }

    @Test
    public void edgeCases() {
        String expected = "<h3>### Double Triple Header</h3>";
        String actual = Challenge.markdownParser( "### ### Double Triple Header" );
        assertEquals( expected, actual );
        String expected1 = "####### Snow White and the Seven Hashtags";
        String actual1 = Challenge.markdownParser( "####### Snow White and the Seven Hashtags" );
        assertEquals( expected1, actual1 );
        String expected2 = "<h4>Space Jam</h4>";
        String actual2 = Challenge.markdownParser( " #### Space Jam" );
        assertEquals( expected2, actual2 );
        String expected3 = "<h2>Lost In Space</h2>";
        String actual3 = Challenge.markdownParser( " ## Lost In Space " );
        assertEquals( expected3, actual3 );
        String expected4 = "<h1>Far Out, Dude</h1>";
        String actual4 = Challenge.markdownParser( "# Far Out, Dude" );
        assertEquals( expected4, actual4 );
    }
}
