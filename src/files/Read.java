package files;

import java.io.FileInputStream;
import java.io.IOException;

public class Read {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("/Users/yassiracaf/Desktop/hey.txt")) {
            int n;
            while ((n = fis.available()) > 0) {
                byte[] b = new byte[n];
                int result = fis.read(b);
                if (result == -1) break;
                System.out.println(new String(b));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
