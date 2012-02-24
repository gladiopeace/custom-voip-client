package control;

import java.net.*;
import java.io.*;

public class Sender {
    private PrintWriter out;
    public Sender(Socket theSocket) {
        try {
            out = new PrintWriter(theSocket.getOutputStream(), true);
        }catch(IOException iE) {}
    }
    public void closeOutStream() {
        if(out != null)
            out.close();
    }
    public void sendAway(String s)  {
        if(out !=null)
            out.println(s);
    }
}