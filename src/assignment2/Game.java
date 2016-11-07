package assignment2;
import java.util.*;
public class Game {
	//a 2D array of GameItem is defined below
	GameItem[][] Board = new GameItem [4][4]; 
	Scanner reader = new Scanner(System.in);
	char [][] array = new char[4][4];
	int choice = 0;
	String input;
	public int Gold = 0;
	// setBoard method instantiates GameItems on board 
	private  void setBoard() {
			Random randomGenerator = new Random();
			//3 random pit items are defined on board as p
			int Pit = 0;
			while (Pit <3){
				int num1 = randomGenerator.nextInt(4);
				int num2 = randomGenerator.nextInt(4);
				if (Board[num1][num2] == null )
					{
						Board[num1][num2] = new Pit('p');
						Pit ++;
					}
				}
			// random Gold item are defined on board range from 1 to 3 as g
			int num = randomGenerator.nextInt(3) + 1;
			int Gold = 0;
			while (Gold<num){
				int num1 = randomGenerator.nextInt(4);
				int num2 = randomGenerator.nextInt(4);
				if (Board[num1][num2] == null)
					{
						Board[num1][num2] = new Gold('g');
						Gold++;
					}
				}
			//1 random wumpus is defined on board as W
			while (true){
				int num1 = randomGenerator.nextInt(4);
				int num2 = randomGenerator.nextInt(4);
				if (Board[num1][num2] == null)
					{
					Board[num1][num2] = new Wumpus('W');
					break;
					}
				}
			//left over positions are defined as clear ground as .
			for (int i=0; i<4;i++){
				for(int j=0;j<4;j++){
					if (Board[i][j] == null)
						{
							Board[i][j] = new ClearGround('.');
						}
					}
				}
			//random current position of player is defined on board as *
			for (int i=0; i<4;i++){
				for(int j=0;j<4;j++){
					array[i][j] = Board[i][j].display();
				}
			}		
			while(true)	
				{
					int num1 = randomGenerator.nextInt(4);
					int num2 = randomGenerator.nextInt(4);
					if (array[num1][num2]=='.')
						{
							array[num1][num2] = '*';
							break;
						}
				}			
	} //end of setBoard()
	// display method to display items that instantiated in setBoard method
	private void display() {
		for (int i=0; i<4;i++){
			for(int j=0;j<4;j++){
						System.out.print(array[i][j]+"\t");
					}System.out.println();
				}
	}
	// senseNearby method to display messages related to elements surrounding player
	private void senseNearby(){
		char Up='0';
		char Down='0';
		char Right='0';
		char Left='0';
		for (int i=0; i<4;i++){
			for(int j=0;j<4;j++){
				if (array[i][j] =='*'){
					int row =i ;
					int col =j;
					//up
					if (row==0){
					 row =3;
					 Up =array[row][col];
					 row =i;
					 }
					else {
					 Up =array[row-1][col];
					 }
					//down
					Down =array[(row+1)%4][col];
					//right
					Right =array[row][(col+1)%4];
					//left
					if (col==0){
					 col =3;
					 Left =array[row][col];
					 col = j;
					 }
					 else {
					  Left =array[row][col-1];
					 }		
				}
			}
		}
		System.out.println("Up: "+check(Up));
		System.out.println("Down: "+check(Down));
		System.out.println("Right: "+check(Right));
		System.out.println("Left: "+check(Left));
	}
	// menu method to display option of movement for player
	private void menu() {
		System.out.println("1. Move player left");
		System.out.println("2. Move player right");
		System.out.println("3. Move player up");
		System.out.println("4. Move player down");
		System.out.println("5. Quit");
		System.out.println();
		int done = 0;
		while (done == 0){
			try{
				System.out.println("Enter your move :");	
				input = reader.nextLine();
				choice = Integer.parseInt(input);
			}
			catch (NumberFormatException e1){	
				System.out.println("Invalid input");
				continue;
			}
			done =1;
		}
	}
	// movePlayer is defined for the movement of player related to user input and changing previous element to '.' 
	public void movePlayer() {
	 switch(choice) {
	  case 1:
			int row = 0;
			int col = 0;
			for (int i=0; i<4;i++){
			  for (int j=0; j<4;j++){				
				if (array[i][j] =='*') {
					row =i;
					col = j;
					array[row][col] ='.';
					if (col==0){
						col = 3;
						checkValue(array[row][col]);
						array[row][col] = '*'; 
						return;   
						 }
					else {
						checkValue(array[row][col-1]);
						array[row][col-1] = '*';
					   return;
						 }
					}
				}
			}
			break;
	case 2:
		for (int i=0; i<4;i++){
		  for (int j=0; j<4;j++){
			if (array[i][j] =='*'){
				array[i][j] = '.';
				checkValue(array[i][(j+1)%4]);
				array[i][(j+1)%4] = '*';
				return;
			  }
			}
		}
		break;
	case 3:		
		for (int i=0; i<4;i++){
		  for (int j=0; j<4;j++){
			if (array[i][j] =='*') {
				int row1 = i;
				array[i][j] = '.';
				
				if (row1==0){
					row1 =3;
					checkValue(array[row1-1][j]);
					array[row1][j] = '*'; 
					return;
					}
				else {
					checkValue(array[row1][j]);
					array[row1-1][j] = '*';
					return;
					 }	
				}
			}
		}
		break;
	case 4: 
		for (int i=0; i<4;i++){
		  for (int j=0; j<4;j++){
			if (array[i][j] =='*'){
				array[i][j] = '.';
				checkValue(array[(i+1)%4][j]);
				array[(i+1)%4][j] = '*';
				return;	
				}
			}
		}
		break;
	case 5:
		System.out.println("you have quit");	
		System.exit(0);
	default:
		System.out.println("Invalid input: try again");
	 }	
	}
	// check value of surrounding element as return value to senseNearby
	public String check(char c) {
	  switch(c){
		case 'p': 
			return "Breeze";
		case 'W':
			return "Vile smell";
		case 'g':
			return "faint glitter";
		default:
			return "clear Ground";
			}
		}
	//check the surrounding values of the player for movePlayer and give the desired output 	
	public void checkValue(char l){
	  switch(l){
		case 'p': 
			System.out.println("player dies");
			System.exit(0);
		case 'W':
			System.out.println("player dies");
			System.exit(0);
		case 'g':
			System.out.println("got gold");
			Gold++;
			System.out.println("Credit :"+Gold);
		default:
			return;
			}
		}
		// method which calls all other methods to execute game 
	public void runGame() {
		setBoard();
		while(true){
			System.out.println("****************Wumpus******************");
			display();
			System.out.println("----------------NearBy Objects__________");
			senseNearby();
			System.out.println("................Menu....................");
			menu();
			movePlayer();
			}
		}
		
}

