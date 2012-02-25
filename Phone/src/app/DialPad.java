package app;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DialPad extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	CallInfo callInfo;
	Window window;
	
	JButton[] buttons = new JButton[12];

	public DialPad(CallInfo callInfo, Window window){
		this.window = window;
		this.callInfo = callInfo;
		setLayout(new GridLayout(4,3));
		for (int i = 0; i < 12; i++){
			if (i<9) buttons[i] = new JButton((i+1)+"");
			if (i==9) buttons[i] = new JButton("end");
			if (i==10) buttons[i] = new JButton("0");
			if (i==11) buttons[i] = new JButton("talk");
			buttons[i].addActionListener(this);
			this.add(buttons[i]);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		JButton jb = (JButton)(ae.getSource());
		
		try{
			int i = Integer.parseInt(jb.getText());
			callInfo.appendToDialBar(i);
		}
		catch(Exception e){
			if (jb.getText().equals("end")){
				if (window.isAvailable()){
					if(callInfo.isDialBarEmpty())System.exit(0);
					callInfo.clearDialBar();
				}
				else window.closeConnection();
			}
			if (jb.getText().equals("talk"))window.makeConnection(callInfo.getNumber());
		}
	}
}
