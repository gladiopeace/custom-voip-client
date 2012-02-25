package server.server;

import java.io.*;
import java.net.*;

public class Client extends Thread {
	
    public Socket socket;
    public ClientHandler ch;    
    public BufferedReader in;
    public PrintWriter out;
    
    public Client(Socket socket, ClientHandler ch, String servName) {
        this.socket = socket;
        this.ch = ch;
        try {
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } 
        catch(Exception e) {}
        
        this.start();
    }
    
    public void sendMessage(String msg) {
        try {
            out.println(msg);
        }catch(Exception e) {}
    }
    
    public void run() {
        String inStream=" ";
        try {
            while(((inStream = in.readLine()) != null)){
            	ch.chatServer.showMessage(inStream);
                if (inStream.startsWith("get-info-on-number-#")){
                	inStream = inStream.replace("get-info-on-number-#", "");
                	String ip = ch.db.getIP(inStream.trim());
                	sendMessage(ip);
                }
                if (inStream.startsWith("number-is-")){
                	inStream = inStream.replace("number-is-", "");
                	this.ch.db.addSet(inStream, socket.getInetAddress().toString());
                }
            }
                
            out.close();
            in.close();
            socket.close();
            
        }catch(Exception e){}
        
        ch.killClient(this);
    }
}