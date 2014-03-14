

import java.io.*;
import java.net.*;


public class Requester {

	static Socket              requestSocket;
	static ObjectOutputStream  out;
 	static ObjectInputStream   in;
 	String              message;
 	static String ServerIP;
 	static int ServerPort;
        public static boolean connect(String b, int a){
        	ServerIP=b;
        	ServerPort=a;
        	try{
        		requestSocket = new Socket(InetAddress.getByName(ServerIP),ServerPort );
        	}
        	catch(UnknownHostException unknownHost){
    			System.err.println("You are trying to connect to an unknown host!");
    		return false;
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FileWriter fstream;
				
				//this is an example of catching an exception, and reading to a file
				try {
					fstream = new FileWriter("exception.txt");
				
			         BufferedWriter printout=new BufferedWriter(fstream);
			         printout.write(e.toString());
			        
			        printout.close();
			        return false;
				} catch (IOException b1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
        	run();
        	return true;
        }
      //  Requester(String a, int b)
        //
        // Constructor

	public static void run()
	{
		try{
			//socket to server
			
	
                        
			//Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
                        
                        //Input and Output streams
			in = new ObjectInputStream(requestSocket.getInputStream());
                        
			
			
		}
		
		catch(IOException ioException){
			ioException.printStackTrace();
			  FileWriter fstream;
			try {
				fstream = new FileWriter("exception.txt");
			
		         BufferedWriter printout=new BufferedWriter(fstream);
		         printout.write(ioException.toString());
		        printout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(Exception a){
			FileWriter fstream;
			try {
				fstream = new FileWriter("exception.txt");
			
		         BufferedWriter printout=new BufferedWriter(fstream);
		         printout.write(a.toString());
		        printout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}
		finally{
		
		}}
	}
        
        //
        //
	static void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			//System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
      
		public static void sendShot(String SHOT){
        	try{
        	sendMessage(SHOT);
        	Thread.sleep(5);
        	String response=(String)in.readObject();
        	Protocol.decode(response);
        	
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		
        	}
        	finally{
        		
        	}
        	
        }
      public static void closeConnection(){
    	  try
          {
	in.close();
	out.close();
	requestSocket.close();
}
catch(IOException ioException){
	ioException.printStackTrace();
}
      	
      }
        
}
