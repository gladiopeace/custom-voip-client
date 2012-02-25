package control;

import java.net.ServerSocket;
import java.net.Socket;

import app.Window;

public class IncomingCallReceiver implements Runnable{
	
	private ServerSocket serverSocket;
	private Window window;
	
	public IncomingCallReceiver(Window window) throws Exception{
		serverSocket = new ServerSocket(6969);
		this.window = window;
	}
	
	public void run() {
        while(true) {
            try {
                Socket socket=serverSocket.accept();
                if (window.isAvailable()){
                	System.out.println("receiving call");
                	window.makeConnection(socket);
                	window.setInCall(true);
                }
            }catch(Exception e){}
        } 
    } 
}
