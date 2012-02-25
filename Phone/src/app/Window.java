package app;

import java.awt.BorderLayout;
import java.net.Socket;

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
		Socket centralServerDatabase = new Socket("ozymandias.servequake.com", 6666);
		cdm = new CentralizedDataManager(centralServerDatabase, this, false);
		
		myNumber = initPhoneNumber();
		cdm.sender.sendAway("number-is-"+getMyNumber());
		
		ci = new CallInfo(getMyNumber());
		dp = new DialPad(ci, this);
		
		initWindow();
	}
	
	public String initPhoneNumber(){
		return "000001";
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
