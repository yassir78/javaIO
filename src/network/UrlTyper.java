package network;

import helper.StreamCopier;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java URLTyper url1 url2 ...");
            return;
        }
        for (String arg : args) {
            if (args.length > 1) {
                System.out.println(arg + ":");
            }
            try {
                URL u = new URL(arg);
                InputStream in = u.openStream();
                StreamCopier.copy(in, System.out);
                in.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
