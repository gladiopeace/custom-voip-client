package data;

import java.net.*;
import java.io.*;

public class SoundReceiver implements Runnable {
    private DataInputStream in;
    private SoundManager sm;
    
    public SoundReceiver(Socket theSocket, SoundManager sm)  {
        try {
        	in = new DataInputStream(theSocket.getInputStream());
        	this.sm = sm;
        }catch (IOException ioe) {}
    }
    public void run() {
        try{
            while (true){
            	int len = in.readInt();
            	byte[] data = new byte[len];
                if (len > 0) {
                    in.readFully(data);
                }
            	sm.speakerOutput.playAudio(data);
            }
        }catch (IOException ioe) {} 
    } 
    public void closeInStream() {
        try {
            if(in !=null) in.close();
        }catch(IOException iE) {} 
    } 
}