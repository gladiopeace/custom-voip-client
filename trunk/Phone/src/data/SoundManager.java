package data;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SoundManager {
	
	private DataOutputStream out;
	SpeakerOutput speakerOutput;
	
	public SoundManager(Socket s){
		
		try {
            out = new DataOutputStream(s.getOutputStream());
        }catch(IOException iE) {}
		
		Thread soundReceiver = new Thread(new SoundReceiver(s, this));
		soundReceiver.start();
		
		Thread micIn = new Thread(new MicrophoneInput(this));
		micIn.start();
		
		speakerOutput = new SpeakerOutput();
	}
	
	public void closeOutStream() {
        try{out.close();}catch(Exception s){}
    }
    public void sendAway(byte[] s)  {
        try{out.write(s, 0, 3000);}catch(Exception e){}
    }
}
