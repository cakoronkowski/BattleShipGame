//this is an example of an array
public class BackBoard {
	int x,y;
	private char[][] theBoard=new char[100][100];
	BackBoard(){

for(x=0;x<100;x++){
	for(y=0;y<100;y++){
		theBoard[x][y]='e';
	}
}
	}
	public void setValue(int a, int b, char c)
	{
		theBoard[a][b]=c;
	}
	public char getValue(int a, int b)
	{
		return theBoard[a][b];
	}
}


