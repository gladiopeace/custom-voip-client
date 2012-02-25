package app;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CallInfo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField dialing = new JTextField(15);
	
	private String myNumber;
	
	public CallInfo(String number){
		myNumber = number;
		setLayoutToDefault();
		dialing.setEditable(false);
	}
	
	public void setLayoutToDefault(){
		this.add(dialing, BorderLayout.CENTER);
		this.add(new JLabel(myNumber), BorderLayout.WEST);
	}
	
	public void setLayoutToCalling(){
		
	}
	
	public boolean isDialBarEmpty(){
		return this.getNumber().equals("");
	}
	
	public String getNumber(){
		return dialing.getText().replaceAll("-", "");
	}
	
	public void clearDialBar(){
		dialing.setText("");
	}
	
	public void setDialBar(String message){
		dialing.setText(message);
	}
	
	public void appendToDialBar(String s){
		String dt = dialing.getText();
		dt = addHyphens(dt);
		this.setDialBar(dt+s);
	}
	
	public String addHyphens(String original){
		if (original.length()%3==2)original+="-";
		return original;
	}
	
	public void appendToDialBar(int i){
		this.appendToDialBar(i+"");
	}
	
}
