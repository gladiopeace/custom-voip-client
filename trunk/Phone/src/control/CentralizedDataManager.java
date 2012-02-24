package control;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import app.Window;

public class CentralizedDataManager{
	
	private Sender sender;
	private Receiver receiver;
	private ArrayList<String> messagesFromServer = new ArrayList<String>();
	private boolean received = false;
	private Window window;
	
	
	public CentralizedDataManager() throws UnknownHostException, IOException{
		this(new Socket("url", 6969), null);
	}
	
	public CentralizedDataManager(Socket socket, Window w){
		window = w;
		sender = new Sender(socket);
		receiver = new Receiver(socket, this);
		Thread receiverThread = new Thread(receiver);
		receiverThread.start();
	}

	public Socket getConnectionInfo(String numberDialed) throws Exception{
		sender.sendAway("get-info-on-number-#"+numberDialed);
		while(!received){
			window.getCallInfo().setDialBar("waiting...");
		}
		received = false;
		String response = this.messagesFromServer.get(this.messagesFromServer.size()-1);
		if (response.equals("nonexistant-number"))
			throw new Exception("number does not exist");
		try{
			return new Socket(response, 6969);
		}
		catch(Exception s){
			throw s;
		}
	}
	
	public void accept(String s){
		this.messagesFromServer.add(s.replace("incoming-message-", ""));
		received = true;
	}

}
