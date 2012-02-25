package data;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class MicrophoneInput implements Runnable{
	public void run(){
		try{
			AudioFormat audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo = new DataLine.Info( TargetDataLine.class, audioFormat);
			TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			targetDataLine.open(audioFormat);
			targetDataLine.start();
			
			byte[] tempBuffer = new byte[3000];
			int i = 0;
			while(true){
				int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
				if(cnt > 0){
					sm.sendAway(tempBuffer);
				}
			}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	SoundManager sm;
	
	public MicrophoneInput(SoundManager sm){
		this.sm = sm;
	}
	
	
	private AudioFormat getAudioFormat(){
	    float sampleRate = 8000.0F;
	    int sampleSizeInBits = 16;
	    int channels = 1;
	    boolean signed = true;
	    boolean bigEndian = false;
	    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
	}
}
