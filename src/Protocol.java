
import java.util.*;
public class Protocol {
	private static String Transmission;
	
	
	public static char MISS = 'M';
	public static char HIT = 'H';
	public static char LOSE = 'L';
	public static char SHIP= 'S';
	public static int SHOTX;
	public static int SHOTY;
	public static char RESULT;
	public static int XIN;
	public static int YIN;
	public static char CONTACT=MISS;
	public static int COUNTER=0;
	public static int SHIPCOUNT=0;
	public static int SHOTS=0;
	public static int PORT=13000;
	
		public static void exit(){
			Requester.closeConnection();
			System.exit(0);
		}
public static String encode(int x, int y )
{
	if(COUNTER>=SHIPCOUNT){
		CONTACT=LOSE;
	}

	Transmission="";
	Transmission+=CONTACT+ ","+x+","+y;
	SHOTX=x;
	SHOTY=y;
	return Transmission;
	
	
}
public static void decode(String s){
	StringTokenizer scn=new StringTokenizer(s, ",");

	char C= scn.nextToken().charAt(0);
	int X=Integer.parseInt( scn.nextToken());
	int Y= Integer.parseInt(scn.nextToken());
	RESULT=C;
	XIN=X;
	YIN=Y;

}
}
