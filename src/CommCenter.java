
public class CommCenter {
	static boolean provider;
	Object o;
	static boolean bool=false;
public static void setProvider(boolean t){
	provider= t;
	
}
public static boolean connect(){
	
		bool=Provider.connect();
		provider=true;
		return bool;
}
public static boolean connect(String hostname, int port){
	bool=Requester.connect(hostname, port);
	return bool;
}
public static void sendShot(String s){
	if(provider==true){
		Provider.sendShot(s);
	}
	else{
		Requester.sendShot(s);
	}
	Protocol.SHOTS++;
}
public static void closeConnection(){
	if (provider == true){
		Provider.closeConnection();
	}
	else{
		Requester.closeConnection();
	}
}

}
