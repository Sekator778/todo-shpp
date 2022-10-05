package com.example.todoshpp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        HashMap<Object, String> map = new HashMap<>();
        map.put(null, "one");
        map.put(null, "two");
        map.put(1, "one");
        map.forEach((integer, s) -> System.out.println(integer + " : " + s));
        Map<Object, String> objectStringMap = Collections.unmodifiableMap(map);
        objectStringMap.put(1, "one1");
    }
}
