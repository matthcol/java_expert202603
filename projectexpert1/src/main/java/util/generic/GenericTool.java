package util.generic;

import java.util.List;

public class GenericTool {

    public static <T> List<T> toList(Object data, Class<T> elementClass){
        // NB: extra arg elementClass captures type T which will be erased after compilation
        //   => allow dynamic instanceOf, cast and newInstance with this type
        if (data instanceof List<?> unsafeList){
            boolean elementTypesOk = unsafeList.stream()
                    .allMatch(elementClass::isInstance);
            if (elementTypesOk) {
                @SuppressWarnings("unchecked") // all is dynamically verified (1 - list ; 2 - all elements of type T)
                List<T> safeList = (List<T>) unsafeList;
                return safeList;
            } else {
                throw new ClassCastException("at least one element is not a " + elementClass.getName());
            }
        } else {
            throw new ClassCastException("data is not a list");
        }
    }

    public static <T> List<T> toNewList(Object data, Class<T> elementClass){
        // NB: extra arg elementClass captures type T which will be erased after compilation
        //   => allow dynamic instanceOf, cast and newInstance with this type
        if (data instanceof List<?> unsafeList){
           return unsafeList.stream()
                   .map(e -> {
                       if (!elementClass.isInstance(e)){
                           throw new ClassCastException("at least one element is not a " + elementClass.getName());
                       }
                       return elementClass.cast(e);
                   })
                   .toList();
        } else {
            throw new ClassCastException("data is not a list");
        }
    }

}
