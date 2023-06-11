package files;

import helper.StreamCopier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopier {
    public static void main(String[] args) {
        try {
            copy("/Users/yassiracaf/Desktop/hey.txt", "test_file_copier.txt");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void copy(String inFile, String outFile)
            throws IOException {
        try (FileInputStream fin = new FileInputStream(inFile); FileOutputStream fout = new FileOutputStream(outFile)) {
            StreamCopier.copy(fin, fout);
        }
    }
}