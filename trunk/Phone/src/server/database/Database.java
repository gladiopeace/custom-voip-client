package server.database;

import java.util.ArrayList;

public class Database {
	private ArrayList<NumberIP> list = new ArrayList<NumberIP>();
	
	public Database(){
		
	}
	
	public void addSet(String number, String ip){
		list.add(new NumberIP(number, ip));
	}
	
	public String getIP(String number){
		for (NumberIP nip: list) if (nip.getNumber().equals(number)) return nip.getIP();
		return "nonexistant-number";
	}
	
	public String toString(){
		String result = "";
		for(NumberIP nip: list){
			result+=nip.toString()+" / ";
		}
		return result;
	}
}
