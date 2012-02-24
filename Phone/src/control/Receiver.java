package control;

import java.net.*;
import java.io.*;

public class Receiver implements Runnable {
    private BufferedReader in;
    private CentralizedDataManager cdm;
    public Receiver(Socket theSocket, CentralizedDataManager cdm)  {
        try {
        	in = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
        	this.cdm = cdm;
        }catch (IOException ioe) {}
    }
    public void run() {
        try{
            String inStream;
            while ((inStream = in.readLine()) != null) cdm.accept(inStream);
        }catch (IOException ioe) {} 
    } 
    public void closeInStream() {
        try {
            if(in !=null) in.close();
        }catch(IOException iE) {} 
    } 
}