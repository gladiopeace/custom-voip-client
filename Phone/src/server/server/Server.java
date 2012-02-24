package server.server;
/**
*Author:  Ted Meyer
*/
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Server extends JFrame{
	private static final long serialVersionUID = -3080570434439692328L;
	private JTextArea  messageArea=new JTextArea(); // Displays messages from the server
    private JScrollPane jsp=new JScrollPane(messageArea); // scroll area for the message area
    private ClientHandler ch;
    
    int usersonline = 0;
    
    public Server(int port, String SN) throws IOException {
        ch = new ClientHandler(this, port, SN); //Creates a ClientHandler object
        messageArea=new JTextArea();    
        messageArea.setEditable(false);
        jsp=new JScrollPane(messageArea); 
        Container con=getContentPane();
        con.setLayout(new BorderLayout());
        con.add(jsp,BorderLayout.CENTER);
        con.add(jsp,"Center");
        setSize(600,300);
        Thread th = new Thread(ch);
        th.start();
        setVisible(true);
    }
    
    public void showMessage(String msg) {
        messageArea.append(msg+"\n");
    }
    
    public static void main(String [] args){ 
        int port = 6969;
        String servName = "";
        try {
            new Server(port, servName);
        }
        catch (IOException dsf){}
    }
    
    public void clearScreen() {
        messageArea.setText("");
    }
    
    
    public String getResultOfChatting(){
        return (messageArea.getText());
    }
} 