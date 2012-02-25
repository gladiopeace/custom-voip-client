package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SoundManager {
	
	private PrintWriter out;
	SpeakerOutput speakerOutput;
	
	public SoundManager(Socket s){
		try {
			out = new PrintWriter(s.getOutputStream(), true);
            
			Thread soundReceiver = new Thread(new SoundReceiver(s, this));
			soundReceiver.start();
			
			Thread micIn = new Thread(new MicrophoneInput(this));
			micIn.start();
			
			speakerOutput = new SpeakerOutput();
		}catch(IOException iE) {}
	}
	
	public void closeOutStream() {
        try{out.close();}catch(Exception s){}
    }
    public void sendAway(String s)  {
        try{out.println(s);}catch(Exception e){e.printStackTrace();}
    }
}
