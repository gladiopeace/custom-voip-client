package app;

import java.awt.BorderLayout;
import java.net.Socket;

import javax.swing.JFrame;

import control.CentralizedDataManager;

public class Window extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private CallInfo ci;
	private DialPad dp;
	private CentralizedDataManager cdm;
	
	private boolean inCall = false;
	
	public Window() throws Exception{
		ci = new CallInfo();
		dp = new DialPad(ci, this);
		
		Socket centralServerDatabase = new Socket("localhost", 6969);
		cdm = new CentralizedDataManager(centralServerDatabase, this);
		
		initWindow();
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
			e.printStackTrace();
		}
	}
	
	public void makeConnection(Socket socket){
		
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
	
	
}
