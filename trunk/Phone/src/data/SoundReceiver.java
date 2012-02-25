package data;

import java.net.*;
import java.io.*;

public class SoundReceiver implements Runnable {
    private BufferedReader in;
    private SoundManager sm;
    
    public SoundReceiver(Socket theSocket, SoundManager sm)  {
        try {
        	in = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
        	this.sm = sm;
        }catch (IOException ioe) {}
    }
    public void run() {
        try{
            byte[] inStream;
            while ((inStream = in.readLine().getBytes()) != null)sm.speakerOutput.playAudio(inStream);
        }catch (IOException ioe) {} 
    } 
    public void closeInStream() {
        try {
            if(in !=null) in.close();
        }catch(IOException iE) {} 
    } 
}