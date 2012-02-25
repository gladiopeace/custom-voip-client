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
			
			byte[] tempBuffer = new byte[7000];
			while(true){
				int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
				if(cnt > 0){
					sm.sendAway(getArrayAsString(tempBuffer));
				}
			}
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public String getArrayAsString(byte[] a){
		String q = "";
		for (byte b: a)q+=b+"/";
		return q;
	}
	
	SoundManager sm;
	
	public MicrophoneInput(SoundManager sm){
		this.sm = sm;
	}
	
	
	public static AudioFormat getAudioFormat(){
	    float sampleRate = 4500.0F;
	    int sampleSizeInBits = 16;
	    int channels = 1;
	    boolean signed = true;
	    boolean bigEndian = true;
	    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
	}
}
