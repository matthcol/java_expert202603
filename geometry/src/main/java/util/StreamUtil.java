package util;

import java.util.stream.Stream;

public class StreamUtil {
    // filterByType
    public static <T, R> Stream<R> filterByType(Stream<T> stream, Class<R> typeR){
//        return stream.filter(t -> typeR.isInstance(t)) //t instanceof R)
//                .map(t -> typeR.cast(t));// (R) t);
        return stream.filter(typeR::isInstance) //t instanceof R)
                .map(typeR::cast);// (R) t);
    }
}
