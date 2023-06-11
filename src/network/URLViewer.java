package network;

import helper.StreamCopier;
import helper.StreamedTextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLViewer extends Frame implements WindowListener, ActionListener {
    TextField theURL = new TextField();
    Button loadButton = new Button("Load");
    StreamedTextArea theDisplay = new StreamedTextArea();

    public URLViewer() {
        super("URL Viewer");
    }
    public void init() {

        this.add("North", theURL);
        this.add("Center", theDisplay);
        Panel south = new Panel();
        south.add(loadButton);
        this.add("South", south);
        theURL.addActionListener(this);
        loadButton.addActionListener(this);
        this.addWindowListener(this);
        this.setLocation(50, 50);
        this.pack();
        this.show();
    }
    public void actionPerformed(ActionEvent evt) {

        try {
            URL u = new URL(theURL.getText());
            InputStream in = u.openStream();
            OutputStream out = theDisplay.getOutputStream();
            StreamCopier.copy(in, out);
            in.close();
            out.close();
        }
        catch (MalformedURLException ex) {theDisplay.setText("Invalid URL");}
        catch (IOException ex) {theDisplay.setText("Invalid URL");}
    }

    public void windowClosing(WindowEvent e) {

        this.setVisible(false);
        this.dispose();
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public static void main(String args[]) {
        URLViewer me = new URLViewer();
        me.init();
    }
}
