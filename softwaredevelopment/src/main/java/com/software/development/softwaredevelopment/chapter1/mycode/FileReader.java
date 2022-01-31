package com.software.development.softwaredevelopment.chapter1.mycode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public class FileReader {

    public List<String> readFile(String resouresPath, String fileName) {
        ClassPathResource resource = new ClassPathResource(resouresPath + fileName);
        List<String> content = Collections.emptyList();
        try {
            Path path = Paths.get(resource.getURI());
            content = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
