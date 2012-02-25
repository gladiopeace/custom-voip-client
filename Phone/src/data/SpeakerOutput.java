package data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SpeakerOutput {
	
	public void playAudio(byte[] soundByte){
		try{
			InputStream byteArrayInputStream = new ByteArrayInputStream(soundByte);
			AudioFormat audioFormat = this.getAudioFormat();
			AudioInputStream audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat, soundByte.length/audioFormat.getFrameSize());
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
		    sourceDataLine.start();
			int cnt;
	    	while((cnt = audioInputStream. read(soundByte, 0, soundByte.length)) != -1)
	    		if(cnt > 0)sourceDataLine.write(soundByte, 0, cnt);
	    	sourceDataLine.drain();
	    	sourceDataLine.close();
		}
		catch(Exception s){}
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
