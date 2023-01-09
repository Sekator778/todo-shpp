package com.example.todoshpp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Find {
    public static void main(String[] args) throws IOException {
        System.out.println("first arg - path, second depth");
        String path;
        int maxDepth;
        if (args.length < 2) {
            path = "";
            maxDepth = 1;
        } else {
            path = args[0];
            maxDepth = Integer.parseInt(args[1]);
        }
        List<String> result;
        try (Stream<Path> walk = Files.walk(Paths.get(path), maxDepth)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))   // not a directory
                    .map(p -> p.getFileName().toString().toLowerCase()) // convert path to string
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());        // collect all matched to a List
        }
        System.out.println(result);
    }
}