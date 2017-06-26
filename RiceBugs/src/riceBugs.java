import java.util.Scanner;

public class riceBugs {

	public static int row, col, bugs, time;
	public static int[][] paddyField = new int[row][col];
	public static int[][]bugsArray = null;
	public static char[]directionArray = null;
	public static void main(String[] args) {
		
		System.out.print("Please enter the size of the paddy field (M N): ");
		Scanner scanner = new Scanner(System.in);
		row = scanner.nextInt();
		col = scanner.nextInt();
		System.out.print("Please enter the period of simulation: ");
		time = scanner.nextInt();
		System.out.print("Please enter amount of rice bugs: ");
		bugs = scanner.nextInt();
		System.out.print("Please enter position of the rice bug in the field follwed by their strting time and their directional movement: ");

		if(bugs < 15){
			bugsArray = new int[bugs][4];
			directionArray = new char[bugs];
			for(int i=0; i < bugs; i++){
				bugsArray[i][0] = scanner.nextInt();  //row
				bugsArray[i][1] = scanner.nextInt(); //col
				bugsArray[i][2] = scanner.nextInt(); //time
				bugsArray[i][3] = 0; 						//strength
				directionArray[i] = scanner.next().charAt(0); //direction
			}
		}

		if(row <= 6 && col <= 6){
			paddyField = new int[row][col];
			createMatrix();
		}
		boolean infect = true;
		for(int i =1; i <= time; i++){
			for(int j=0; j< bugs; j++){
				if(notDead(bugsArray[j][3])){
					if(checkStartingTime(i, bugsArray[j][2])){
						if(collision(j)){
							killBug(j);
						}
						else if(edgeCollision(bugsArray[j][0],bugsArray[j][1])){
							bugsArray[j][3] = -1;
							infect = false;
						}
						if(infect == true){
							infectPlant(bugsArray[j][0],bugsArray[j][1]);
							bugsArray[j][3]++;
							moveBug(j, directionArray[j]);	
						}
						infect = true;
					}
				}
			}
		}
		print();
		scanner.close();
	}

	public static boolean checkStartingTime(int i, int bugTime){
		if(bugTime <= i){
			return true;
		}
		return false;
	}

	public static void createMatrix(){
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				paddyField[i][j] = 0;
			}
		}
	}

	public static void moveBug(int j, char letter){
		switch(letter){
		case 'A':
			bugsArray[j][0] = bugsArray[j][0] - 1;
			bugsArray[j][1] = bugsArray[j][1] - 1;
			break;
		case 'B':
			bugsArray[j][0] = bugsArray[j][0] - 1;
			break;
		case 'C':
			bugsArray[j][0] = bugsArray[j][0] - 1;
			bugsArray[j][1] = bugsArray[j][1] + 1;
			break;
		case 'D':
			bugsArray[j][1] = bugsArray[j][1] - 1;
			break;
		case 'E':
			bugsArray[j][1] = bugsArray[j][1] + 1;
			break;
		case 'F':
			bugsArray[j][0] = bugsArray[j][0] + 1;
			bugsArray[j][1] = bugsArray[j][1] - 1;
			break;
		case 'G':
			bugsArray[j][0] = bugsArray[j][0] + 1;
			break;
		case 'H':
			bugsArray[j][0] = bugsArray[j][0] + 1;
			bugsArray[j][1] = bugsArray[j][1] + 1;
			break;
		default:
			break;
		}
	}


	public static void killBug(int j){
		for(int i=0; i<bugs;i++){
			if(bugsArray[j][0] == bugsArray[i][0] && bugsArray[j][1] == bugsArray[i][1] && j != i){
				if(bugsArray[j][3]>= bugsArray[i][3]){
					bugsArray[i][3]=-1;
				}else{
					bugsArray[j][3]=-1;
				}
			}
		}

	}

	public static boolean notDead(int strn){
		if(strn != -1){
			return true;
		}
		return false;
	}

	public static boolean collision(int j){
		for(int i=0; i<bugs; i++){
			if(checkStartingTime(time, bugsArray[i][2])){
				if(bugsArray[j][0] == bugsArray[i][0] && bugsArray[j][1] == bugsArray[i][1] && j != i){
					return true;
				}
			}
		}
		return false;
	}

	public static void infectPlant(int bugRow, int bugCol){
		paddyField[bugRow][bugCol] = 1;
	}

	public static boolean edgeCollision(int bugRow, int bugCol){
		if(bugRow >= row || bugCol >= col || bugCol <= -1 || bugRow <= -1){
			return true;
		}
		return false;
	}

	public static void print(){
		int notInfected = 0;
		int notDead = 0;
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				if(paddyField[i][j] == 0){
					notInfected++;
				}
			}
		}
		for(int k=0; k < bugsArray.length; k++){
			if(bugsArray[k][3]!= -1){
				notDead++;
			}
		}
		System.out.println("Number of plants not infected: " + notInfected);
		System.out.println("Number of bugs alive: " + notDead);
	}
}
