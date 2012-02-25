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
        }catch(IOException iE) {}
		
		Thread soundReceiver = new Thread(new SoundReceiver(s, this));
		soundReceiver.start();
		
		Thread micIn = new Thread(new MicrophoneInput(this));
		micIn.start();
		
		speakerOutput = new SpeakerOutput();
	}
	
	public void closeOutStream() {
        if(out != null)out.close();
    }
    public void sendAway(String s)  {
        if(out !=null)out.println(s);
    }
}
