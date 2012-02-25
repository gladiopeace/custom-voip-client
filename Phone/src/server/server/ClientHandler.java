package server.server;

import java.net.*;
import java.util.*;

import server.database.Database;



public class ClientHandler implements Runnable {
    
    public  ServerSocket serverSocket;
    public int port;
    public int clientCount;
    public Server chatServer; 
    private ArrayList<Client> clientArr;
    private Thread thread;
    public String servName;
    
    public Database db = new Database();
    
        
    
    public ClientHandler(Server chatServer, int port, String SN) {
        this.chatServer = chatServer;
        this.port = port;
        servName = SN;
        
        try{
            serverSocket=new ServerSocket(port);
        }
        catch(Exception e) {
            System.err.println("Can't make a socketconnection " + e);
            System.exit(0);
        } 
        clientCount = 0;
        clientArr=new ArrayList<Client>();
        thread=new Thread(this);
        thread.start();
        
        
        //db.addSet("000000", "localhost");
    }
        
    public void run() {
        while(true) {
            try {
                Socket socket=serverSocket.accept();
                clientArr.add(new Client(socket, this, servName));
            }catch(Exception e){}
        } 
    } 
    
    public synchronized void broadcast(String msg) {
        chatServer.showMessage(msg);
        for(Client c: clientArr)
            c.sendMessage(msg);
    }
    
    public synchronized void killClient(Client c) {
        try{
            c.in.close();
            c.out.close();
            c.socket.close();
            for(int i=0;i<clientArr.size();i++)
                if((clientArr.get(i)).equals(c))clientArr.remove(i);
            clientArr.trimToSize();
            clientCount--;
        }
        catch(Exception e) {
        }
    }
} 