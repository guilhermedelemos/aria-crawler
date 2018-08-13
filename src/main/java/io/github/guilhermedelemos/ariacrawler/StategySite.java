package io.github.guilhermedelemos.ariacrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class StategySite {
    protected BufferedReader in;

    public StategySite(String fileName) throws IOException {
        this.in = Files.newBufferedReader(Paths.get(fileName));
    }
}
