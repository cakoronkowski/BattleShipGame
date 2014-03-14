import java.net.InetAddress;

import javax.swing.*;

//this provides an example of inheritance
public class ServerInfo extends JPanel {
	ServerInfo(){
	String FE="";
	try{
		FE=InetAddress.getLocalHost().getHostAddress().toString();
	}
	catch(Exception e){
		
	}
	
JLabel theLabel=new JLabel("IP: "+FE+" PORT: "+Protocol.PORT);
add(theLabel);
}
}