package app;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import control.CentralizedDataManager;
import data.SoundManager;

public class Window extends JFrame{
	
	private final String myNumber;
	
	private static final long serialVersionUID = 1L;
	
	private CallInfo ci;
	private DialPad dp;
	private CentralizedDataManager cdm;
	
	private boolean inCall = false;
	
	public Window() throws Exception{
		Socket centralServerDatabase = new Socket("99.159.103.70", 6969);
		cdm = new CentralizedDataManager(centralServerDatabase, this, false);
		
		myNumber = initPhoneNumber()+"";
		cdm.sender.sendAway("number-is-"+getMyNumber());
		
		ci = new CallInfo(getMyNumber());
		dp = new DialPad(ci, this);
		
		initWindow();
	}
	
	public int initPhoneNumber(){
		try{
			Scanner reader = new Scanner(new File("c:/.microcall/number.phone"));
			return reader.nextInt();
		}
		catch(Exception sa){
			int i = (int)(Math.random()*1000000);
			File file = new File("c:/.microcall/number.phone");
			try {
				file.createNewFile();
				FileWriter fstream = new FileWriter("out.txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(i+"");
				out.close();
			}catch (IOException e){
				System.err.println("oh meh gawd");
			}
			return i;
		}
	}
	
	public CallInfo getCallInfo(){
		return ci;
	}
	
	public void initWindow(){
		this.setTitle("microCall");
		this.setSize(220,270);
		this.setVisible(true);
		this.add(dp);
		this.add(ci, BorderLayout.NORTH);
		this.setResizable(false);
	}
	
	public void makeConnection(String number){
		try {
			Socket connectionSocket = cdm.getConnectionInfo(number);
			this.ci.setDialBar(connectionSocket.toString());
			makeConnection(connectionSocket);
		}
		catch (Exception e) {
			this.ci.setDialBar("NUMBER DOES NOT EXIST");
		}
	}
	
	public void makeConnection(Socket socket){
		new SoundManager(socket);
	}
	
	public void closeConnection(){
		
	}
	
	
	public static void main(String[] args){
		try{
			new Window();
		}
		catch(Exception s){
			s.printStackTrace();
		}
	}

	public boolean isInCall() {
		return inCall;
	}
	
	public boolean isAvailable(){
		return !isInCall();
	}

	public void setInCall(boolean inCall) {
		this.inCall = inCall;
	}

	public String getMyNumber() {
		return myNumber;
	}
	
	
}
