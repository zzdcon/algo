package algo.jdk.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Sort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(10, 13, 5, 9));
        Collections.sort(list);
        System.out.println(list);
        ConcurrentHashMap map = new ConcurrentHashMap();
    }
}
