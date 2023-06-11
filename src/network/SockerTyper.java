package network;

import helper.StreamCopier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

/*
* Before data is sent across the Internet from one host to another, it is split into packets of
varying but finite size called datagrams. Datagrams range in size from a few dozen bytes to
about 60,000 bytes. Anything larger, and often things smaller, must be split into smaller
Java I/O
66
pieces before it can be transmitted. The advantage of this scheme is that if one packet is lost,
it can be retransmitted without requiring redelivery of all other packets. Furthermore, if
packets arrive out of order, they can be reordered at the receiving end of the connection.
Fortunately, packets are invisible to the Java programmer. The host's native networking
software splits data into packets on the sending end and reassembles packets on the receiving
end. Instead, the Java programmer is presented with a higher-level abstraction called a socket.
* */
public class SockerTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java SocketTyper url1 url2 ...");
            return;
        }
        for (String arg : args) {
            if (args.length > 1) {
                System.out.println(arg + ":");
            }
            try {
                URL u = new URL(arg);
                if (!u.getProtocol().equalsIgnoreCase("http")) {
                    System.err.println("Sorry, " + u.getProtocol());
                    break;
                }

                String host = u.getHost();
                int port = u.getPort();
                String file = u.getFile();
                // default port
                if (port <= 0) port = 80;

                Socket s = new Socket(host, port);
                String request = "GET " + file + " HTTP/1.0\r\n"
                        + "User-Agent: MechaMozilla\r\nAccept: text/*\r\n\r\n";
                // This next line is problematic on non-ASCII systems
                byte[] b = request.getBytes();

                OutputStream out = s.getOutputStream();
                InputStream in = s.getInputStream();
                out.write(b);
                out.flush();

                StreamCopier.copy(in, System.out);
                /*
                When you use the URL class to
                 download a web page, the associated protocol handler never shows you the HTTP header.
                 */
                in.close();
                out.close();
                s.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
