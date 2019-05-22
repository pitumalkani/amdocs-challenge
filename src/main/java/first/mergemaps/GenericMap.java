package first.mergemaps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericMap.
 *
 * @param <T> the generic type
 */
public class GenericMap<T> {

    /**
     * Merge.
     *
     * @param m1 the m 1
     * @param m2 the m 2
     * @param func the func
     * @return the map< integer,? extends object>
     */
    public Map<Integer, ? extends Object> merge( Map<Integer, T> m1, Map<Integer, T> m2, BiFunction<T, T, T> func ) {
        Map<Integer, T> re = new HashMap<>( m1 );
        m2.forEach( ( k, v ) -> {
            if ( re.containsKey( k ) ) {
                re.put( k, func.apply( re.get( k ), v ) );
            } else {
                re.put( k, v );
            }
        } );
        return re;
    }

    /**
     * Merge map list.
     *
     * @param list the list
     * @param func the func
     * @return the map< integer,? extends object>
     */
    public Map<Integer, ? extends Object> mergeMapList( List<Map<Integer, T>> list, BiFunction<T, T, T> func ) {
        Map<Integer, T> result = new HashMap<>();
        list.parallelStream().forEach( map -> {
            map.forEach( ( k, v ) -> {
                if ( result.containsKey( k ) ) {
                    result.put( k, func.apply( result.get( k ), v ) );
                } else {
                    result.put( k, v );
                }
            } );
        } );
        return result;
    }
}
