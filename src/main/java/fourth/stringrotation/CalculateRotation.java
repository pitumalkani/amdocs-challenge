package fourth.stringrotation;

public class CalculateRotation {

    private static String shiftRight( String given ) {
        return given.charAt( given.length() - 1 ) + given.substring( 0, given.length() - 1 );

    }

    public static int shiftedDiff( String given, String required ) {
        int count = 0;
        if ( !given.equals( required ) ) {
            for ( int i = 0; i < given.length(); i++ ) {
                given = shiftRight( given );
                if ( required.equals( given ) ) {
                    count = i + 1;
                    break;
                } else {
                    count = -1;
                }
            }
        }
        return count;
    }
}
