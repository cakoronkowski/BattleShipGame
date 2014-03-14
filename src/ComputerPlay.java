import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class ComputerPlay extends GameLogic {
	static String theShot;
	static int xshot=50, yshot=50;
	static char result='m';
	static int xmark=0;
	static int ymark=0;
	
	ComputerPlay(){
		
	}
	static BoardManager Board = new BoardManager();	
	static BackBoard set=new BackBoard();
public static void run(){
	
	//hard setting the ships, this area was commented in favor of a configfile system
//	int y=1;
//	for(int x=6; x>0;x--){
//		
//			Board.setShip(98, y)	;
//		y++;
//	}
//	int z=86;
//	for(int x=12;x>0;x--){
//		Board.setShip(1,z);
//		z++;
//	}
//	int n=92;
//	for(int x=6;x>0; x--){
//		Board.setShip(98, n);
//		n++;
//	}
	
	
	
	//Board.setShip(0, 0);
	
	
//	CommCenter.sendShot(Protocol.encode(0, 0));
//	
//	
//	Board.checkOut();
//	Board.checkIn();
//	if(Protocol.RESULT==Protocol.HIT){
//		kill();
//	}
//	
//CommCenter.sendShot(Protocol.encode(99, 0));
//	
//	
//	Board.checkOut();
//	Board.checkIn();
//	if(Protocol.RESULT==Protocol.HIT){
//		kill();
//	}
	

	/*while(!lose){
		
	
	while(result != 'e'){
		xshot=ran.nextInt(100);
		yshot=ran.nextInt(100);
	result=Board.checkThem(xshot, yshot);
	
	}
	
	CommCenter.sendShot(Protocol.encode(xshot, yshot));
	Board.checkOut();
	Board.checkIn();
	check();
	if(Protocol.RESULT==Protocol.HIT){
		kill();
	}
	result='m';	
	}*/
	
	
	setShips();
	search();
}
public void runSecond(){
Board.checkIn();
Board.checkOut();
run();
}
static void kill(){
	final int stx=Protocol.SHOTX;
	final int sty=Protocol.SHOTY;
	
	int cx;
	int cy;
	
	
		cx=stx;
		cy=sty;
		while(true){
			//checking north cardinal point
			cy++;
			if(cy>99){
				break;
			}
			else if(Board.checkThem(cx, cy)==Protocol.MISS){
				break;
			}
			else if(Board.checkThem(cx, cy)==Protocol.HIT){
				continue;
				
			}
			CommCenter.sendShot(Protocol.encode(cx, cy));
			Board.checkOut();
			Board.checkIn();
			check();
			if(Protocol.RESULT==Protocol.MISS){
			break;
				
			}
		}
	
		cy=sty;
	while(true){
		//checking south
		cy--;
		
		if(cy<0){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.MISS){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.HIT){
			continue;
			
		}
		
		CommCenter.sendShot(Protocol.encode(cx, cy));
		Board.checkOut();
		Board.checkIn();
		check();
		if(Protocol.RESULT==Protocol.MISS){
			break;
		}
	}
	//resetting start
	cy=sty;
	while(true){
		cx++;
		//checking east
		if(cx>99){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.MISS){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.HIT){
			continue;
			
		}
		CommCenter.sendShot(Protocol.encode(cx, cy));
		Board.checkOut();
		Board.checkIn();
		check();
		if(Protocol.RESULT==Protocol.MISS){
			break;
		}
	}
	cx=stx;
   while(true){
	   //checking west
	   cx--;
	   if(cx<0){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.MISS){
			break;
		}
		else if(Board.checkThem(cx, cy)==Protocol.HIT){
			continue;
			
		}
		CommCenter.sendShot(Protocol.encode(cx, cy));
		Board.checkOut();
		Board.checkIn();
		check();
		if(Protocol.RESULT==Protocol.MISS){
			break;
		} 
   }
	
	
}
static void check()
{
	if(Protocol.COUNTER>=Protocol.SHIPCOUNT){
		JOptionPane.showConfirmDialog(null, "You Lose!", null, JOptionPane.OK_CANCEL_OPTION);
		//System.out.println("You Lose!");
		Protocol.CONTACT=Protocol.LOSE;
		CommCenter.sendShot(Protocol.encode(0, 0));
		CommCenter.closeConnection();
		System.exit(0);
	}
}
static void search(){
	xshot=0;
	yshot=0;
	Random ran=new Random();
	boolean alt=ran.nextBoolean();
	set();
//boolean alt=true;
//if(alt){
//	xshot=99;
//}
	while(true){
		
		
		while(result != 'e'){
			//The code below was my previous method of finding ships, the new method grants the whole 
			//board equal chance while still using the minimum number of shots
			
			/*
			if(alt){
				xshot--;
			}
			else{xshot++;}
			yshot++;
			if(!alt){
			if(xshot==100&&yshot==100){
			
			xshot=0;
			yshot=0;
			xmark=6;
			xshot=xmark;
			}
			else if(xshot>99){
				ymark+=6;
				xshot=0;
				yshot=ymark;
			}
			else if(yshot>99){
				yshot=0;
				xmark+=6;
				xshot=xmark;
			}
			}
			else{
				if(xshot<0&&yshot>99){
					
					xshot=0;
					yshot=0;
					xmark=93;
					xshot=xmark;
					}
					else if(xshot<0){
						ymark+=6;
						xshot=99;
						yshot=ymark;
					}
					else if(yshot>99){
						yshot=0;
						xmark-=6;
						xshot=xmark;
					}	
			}
			*/
			char result2='e';
			while(result2!=Protocol.SHIP){
				xshot=ran.nextInt(100);
				yshot=ran.nextInt(100);
				result2=set.getValue(xshot, yshot);
				
			}
			result=Board.checkThem(xshot, yshot);
		}
		
		CommCenter.sendShot(Protocol.encode(xshot, yshot));
		Board.checkOut();
		Board.checkIn();
		check();
		if(Protocol.RESULT==Protocol.HIT){
			kill();
		}
		result='m';	
		}	
}
static void set(){//this method generates a series of diagonals that cover the board in the least number of shots
	xshot=0;
	yshot=0;

boolean alt=false;
//boolean alt=true;
set.setValue(0, 0, Protocol.SHIP);
while(true){
	
	
	
		//xshot=ran.nextInt(100);
		//yshot=ran.nextInt(100);
		
		//if(alt){
		//	xshot--;
		//}
		xshot++;
		yshot++;
		if(!alt){
		if(xshot==100&&yshot==100){
		
		xshot=0;
		yshot=0;
		xmark=6;
		xshot=xmark;
		}
		else if(xshot>99){
			ymark+=6;
			xshot=0;
			yshot=ymark;
		}
		else if(yshot>99){
			yshot=0;
			xmark+=6;
			xshot=xmark;
		}
		}
		
		if(xmark>99||ymark>99){
			break;
		}
		
		set.setValue(xshot, yshot, Protocol.SHIP);
	
	
	
	
	}	
	
	
}
public static void setShips(){//this method reads ship locations from a config file and places them on the board
	char C;
	int X;
	int Y;
try{
FileReader reader=new FileReader("ConfigFile.txt");
Scanner scn=new Scanner(reader);
StringTokenizer t;

String s;
while(scn.hasNext()){
	s=scn.nextLine();
	if(s.charAt(0)=='/'){
		continue;
	}
	t=new StringTokenizer(s, ",");
	 C= t.nextToken().charAt(0);
	
	 X=Integer.parseInt( t.nextToken());
	 Y= Integer.parseInt(t.nextToken());
	 System.out.println(C+X+Y);
	 for(int i=6; i>0;i--){
		 Board.setShip(X, Y);
		 if(C=='V'){
			 X++;
		 }
		 else{
			 Y++;
		 }
		 Protocol.SHIPCOUNT++;
		 
	 }
}

}
catch(Exception e)
{
e.printStackTrace();
CommCenter.closeConnection();
System.exit(0);
}
}
}
