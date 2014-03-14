import javax.swing.JOptionPane;


public class BoardManager {

	GridFun Me = new GridFun(100,100, "You");
	GridFun Opponent=new GridFun(100,100, "Opponent");
BackBoard MyBoard= new BackBoard();
BackBoard HisBoard= new BackBoard();


public void setShip(int x, int y){
	Me.setSquare(x,y, Protocol.SHIP);
	MyBoard.setValue(x,y,Protocol.SHIP);
}
public void setOpponent(int a, int b, char c){
	Opponent.setSquare(a,b,c);
	HisBoard.setValue(a, b, c);
}
public void setMe(int a, int b, char c){
	Me.setSquare(a, b, c);
	MyBoard.setValue(a, b, c);
}
public void setLastResult(char A){
	setOpponent(Protocol.SHOTX, Protocol.SHOTY, A);
}
public void checkOut()
{
	setOpponent(Protocol.SHOTX,Protocol.SHOTY , Protocol.RESULT);
	if(Protocol.RESULT==Protocol.LOSE){
	JOptionPane.showConfirmDialog(null, "You Win!", null, JOptionPane.OK_CANCEL_OPTION);
		//System.out.println("You Win!");
	System.out.println(Protocol.SHOTS);
	CommCenter.closeConnection();
	System.exit(0);
	}
	
	
}
public void checkIn(){
	if(MyBoard.getValue(Protocol.XIN, Protocol.YIN)== Protocol.SHIP){
		Protocol.CONTACT=Protocol.HIT;
	setMe(Protocol.XIN,Protocol.YIN, Protocol.HIT);
	Protocol.COUNTER+=1;
	}
	
	else{
		setMe(Protocol.XIN, Protocol.YIN, Protocol.MISS);
		Protocol.CONTACT=Protocol.MISS;
	}
	
	
}
public char checkThem(int y,int z){
	return HisBoard.getValue(y, z);
}

}
