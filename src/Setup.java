import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Setup {
public static void start(){
	boolean check;
	ComputerPlay p;
	int c=JOptionPane.showConfirmDialog(null, "Would you like to Host the game?", "Mode Selection", JOptionPane.YES_NO_OPTION );
	
	if(c== JOptionPane.NO_OPTION){
	do{
	String hostname="";
	String port="80";
	hostname=JOptionPane.showInputDialog(null, "enter the hostname you wish to connect to");
	port=JOptionPane.showInputDialog(null,"enter the port number");
	Scanner scn =new Scanner(port);
	int portNo=scn.nextInt();
	
	check=CommCenter.connect(hostname, portNo);
	p=new ComputerPlay();
	p.run();
	
	if (!check){
		int n=JOptionPane.showConfirmDialog(null, "error connecting, please retry", "Error", JOptionPane.OK_CANCEL_OPTION);
	if (n==JOptionPane.CANCEL_OPTION)
		System.exit(0);
}
	
		
		
	
	
}while(!check);
}
	else{
		JFrame frame=new JFrame("Info");
		frame.setContentPane(new ServerInfo());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(190, 75);
		frame.setVisible(true);
		CommCenter.connect();
		frame.setVisible(false);
		p=new ComputerPlay();
		p.runSecond();
		}
}
}
