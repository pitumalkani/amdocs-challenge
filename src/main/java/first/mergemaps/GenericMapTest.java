package first.mergemaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericMapTest {

    
    public static void main( String[] args ) {
        testMapMergeForInteger();
        testMapMergeForString();
        testMapMergeForListOfMaps();
    }
    

    /**
     * Test map merge for integer.
     */
    public static void testMapMergeForInteger() {
        GenericMap<Integer> genericMap = new GenericMap<>();
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        map1.put( 1, 10 );
        map1.put( 2, 20 );
        map2.put( 1, 20 );
        map2.put( 3, 30 );
        map2.put( 4, 40 );
        System.out.println( "Merging of Maps for Integer as value-->\n" + genericMap.merge( map1, map2, (k, v)-> k+v ));
    }

    /**
     * Test map merge for integer.
     */
    public static void testMapMergeForString() {
        GenericMap<String> genericMap = new GenericMap<>();
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        Map<Integer, String> map2 = new HashMap<Integer, String>();
        map1.put( 1, "Hello" );
        map2.put( 2, "Hi" );
        map2.put( 1, "World" );
        map2.put( 3, "Test" );
        System.out.println( "\nMerging of Maps for String as value-->\n" +genericMap.merge( map1, map2, (k, v)-> k.concat( v ) ));
    }
    
    /**
     * Test map merge for integer.
     */
    public static void testMapMergeForListOfMaps() {
        List<Map<Integer,String>> mapList = new  ArrayList<>();
        GenericMap<String> genericMap = new GenericMap<>();
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        Map<Integer, String> map2 = new HashMap<Integer, String>();
        Map<Integer, String> map3 = new HashMap<Integer, String>();
        map1.put( 1, "Hello" );
        map2.put( 2, "Hi" );
        map2.put( 1, "World" );
        map2.put( 3, " We " );
        map3.put( 3, " are " );
        map2.put( 4, " testing " );
        map3.put( 4, " merging " );
        map2.put( 5, " of " );
        map3.put( 5, " list " );
        map2.put( 6, " of " );
        map3.put( 6, " maps " );
        mapList.add( map1 );
        mapList.add( map2 );
        mapList.add( map3 );
        System.out.println( "\nMerging of list of Maps-->\n" +genericMap.mergeMapList( mapList, (k,v)-> k.concat( v ) ));
    }

   
}
