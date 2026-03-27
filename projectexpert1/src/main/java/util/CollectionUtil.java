package util;

import java.util.List;

public class CollectionUtil {

    public static <E> List<E> subList(List<E> list, int fromIndex, int toIndex){
        if (toIndex > list.size()) toIndex = list.size();
        return list.subList(fromIndex, toIndex);
    }
}
