package com.sberbank.task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFileService {

    private List<String> links = new ArrayList<>();

    public List<String> readTextFile(String pathToFile) throws IOException {

        File textWithLinks = new File(pathToFile);
        FileReader readerTextFile = new FileReader(textWithLinks);
        BufferedReader bufferedReaderTextFile = new BufferedReader(readerTextFile);

        String line = null;
        while ((line = bufferedReaderTextFile.readLine()) != null) {
            links.add(line);
        }
        bufferedReaderTextFile.close();

        return links;
    }
}
