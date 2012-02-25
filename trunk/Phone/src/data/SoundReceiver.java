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
        	String inStream;
            while (( inStream = in.readLine()) != null){
            	System.out.println("messageReceived");
            	this.sm.speakerOutput.playAudio(this.getByteArray(inStream));
            }
        }catch (IOException ioe) {
        	ioe.printStackTrace();
        } 
    } 
    
    public byte[] getByteArray(String s){
    	String[] ss = s.split("/");
    	byte[] result = new byte[ss.length];
    	for (int i=0; i<ss.length; i++){
    		result[i] = (byte)(Byte.parseByte(ss[i]));
    	}
    	return result;
    }
    
    public void closeInStream() {
        try {
            if(in !=null) in.close();
        }catch(IOException iE) {} 
    } 
}