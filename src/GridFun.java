import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.*;
public class GridFun {
JFrame frame ;
JButton[][] grid;

	 GridFun(int x, int y, String z){
		 Clip clip;
		 
		 ActionListener quitlisten= new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			Requester.closeConnection();
			System.exit(0);
				
			}
		 };
		 frame=new JFrame(z);
		 frame.setLayout(new GridLayout(x,y));
		 
		 grid=new JButton[x][y];
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setBackground(Color.RED);
		 frame.setSize(600,600);
		 JMenuBar menuBar= new JMenuBar();
		 JMenu menu =new JMenu("menu");
		 menuBar.add(menu);
		 JMenuItem quit=new JMenuItem("KILL");
		 quit.addActionListener(quitlisten);
		menu.add(quit);
		frame.setJMenuBar(menuBar);
		 //frame.pack();
		
		for(int y2=0;y2<x ; y2++){
			 for(int x2=0; x2 < y; x2++){
				 grid[x2][y2]=new JButton();
				
				 frame.add(grid[x2][y2]);
				 grid[x2][y2].setBackground(Color.BLUE);
				// grid[x2][y2].setBorderPainted(false);
				 
			 }
		 }
		
		frame.setVisible(true);
		if(z.equals("You")){
			
			
			 File Sound=new File("march.au");
			
			 try{
				AudioInputStream in =AudioSystem.getAudioInputStream(Sound);
			
			
			
			 clip =AudioSystem.getClip();
			
		
			 clip.open(in);
			 clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			catch(Exception c){
				c.printStackTrace();
			}
			 }
		
	 }
	public void setSquare(int a, int b, char c)
	{
		if(c==Protocol.MISS){
			grid[a][b].setBackground(Color.WHITE);
		}
		else if(c==Protocol.HIT){
			grid[a][b].setBackground(Color.RED);
		}
		else if(c== Protocol.SHIP)	{
			grid[a][b].setBackground(Color.YELLOW);
		}
	
	}
	
	public void setHit(int d, int e){
		setSquare(d,e,Protocol.HIT);
		}
	
		
	

}
