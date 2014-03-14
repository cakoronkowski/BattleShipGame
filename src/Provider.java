
import java.io.*;
import java.net.*;

public class Provider {

    static ServerSocket        providerSocket;
    static Socket              connection = null;
    static ObjectOutputStream  out;
    static ObjectInputStream   in;
    String              message;
    static boolean check=false;
 
    Provider(){}
	
  public static boolean connect(){
        try
        {
                //
                //Create socket
		providerSocket = new ServerSocket(Protocol.PORT, 10);
			
               
		
		connection = providerSocket.accept();
		System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			
                //
                // Input and Output streams
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
                
                // in stream
		in = new ObjectInputStream(connection.getInputStream());
		String response = null;
		try {
			response = (String)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Protocol.decode(response);
	
		check=false;
		}
        catch(IOException I){
        	I.printStackTrace();
        	check=true;
        }
        return 	check;
        }
			
                
		
       
           public static void closeConnection() {    //Close connection
                try
                {
                    in.close();
                    out.close();
                    providerSocket.close();
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }}
	
        void run()
        {}
	static void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			//System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
			closeConnection();
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
	
	}

