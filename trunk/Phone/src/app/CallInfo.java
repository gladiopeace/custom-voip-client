package app;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CallInfo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField dialing = new JTextField(15);
	
	private int myNumber;
	
	public CallInfo(){
		setLayoutToDefault();
		dialing.setEditable(false);
	}
	
	public void setLayoutToDefault(){
		this.add(dialing, BorderLayout.CENTER);
		this.add(new JLabel(myNumber+""), BorderLayout.WEST);
	}
	
	public void setLayoutToCalling(){
		
	}
	
	public boolean isDialBarEmpty(){
		return this.getNumber().equals("");
	}
	
	public String getNumber(){
		return dialing.getText();
	}
	
	public void clearDialBar(){
		dialing.setText("");
	}
	
	public void setDialBar(String message){
		dialing.setText(message);
	}
	
	public void appendToDialBar(String s){
		String dt = dialing.getText();
		this.setDialBar(dt+s);
	}
	
	public void appendToDialBar(int i){
		this.appendToDialBar(i+"");
	}
	
}
