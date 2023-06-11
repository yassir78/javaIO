package files;

import helper.StreamCopier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileTyper {
    public static void main(String[] args) throws IOException {
        String result = new String(getFileInput(), UTF_8);
        try {
            typeFile(result);
            System.out.println();
            System.out.println("------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void typeFile(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        StreamCopier.copy(fin, System.out);
        fin.close();
    }

    private static byte[] getFileInput() throws IOException {
        InputStream in = System.in;
        byte[] buffer = new byte[256];
        in.read(buffer);
        return buffer;

    }
}
