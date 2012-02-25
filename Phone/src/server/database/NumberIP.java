package server.database;

public class NumberIP {
	
	String number;
	String ip;
	
	public NumberIP(String number, String ip){
		this.number = number;
		this.ip = ip;
	}
	
	public String getNumber(){
		return number;
	}
	
	public String getIP(){
		return ip;
	}
	
	public String toString(){
		return number+"-"+ip;
	}
}
