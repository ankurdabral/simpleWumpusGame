package assignment2;
//all the Items are instantiated in this class
public abstract class GameItem {
	private char type;
	GameItem(char d)
	{
		type = d;
	}
	
	public char display(){
		return type;
	}
	
	public void currentpos(char c) {
		type =c;
	}
}
