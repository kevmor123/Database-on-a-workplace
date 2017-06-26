
import java.util.Scanner;

public class Sokoban {

	public static int R, C, destR, destC;
	public static boolean[][] isObstacle, alreadyFilled;
	public static int one = 1;

	public static void main(String[] args){
		System.out.println("Please put in the input");
		Scanner scanner = new Scanner(System.in);
		R = scanner.nextInt();
		C = scanner.nextInt();
		int destBoxR = 0;
		int destBoxC = 0;
		alreadyFilled = new boolean[R][C];
		isObstacle = new boolean[R][C];
		scanner.nextLine();
        for (int i = 0; i <= R-1; i++) {
            String tmp = scanner.nextLine();
            for (int j = 0; j <= C-1; j++) {                
                char ch = tmp.charAt(j);
                switch (ch) {
                    case '#':
                        isObstacle[i][j] = true;
                        break;
                    case 'B':
                    	destBoxR = i;
    					destBoxC = j;
                        break;
                    case 'D':
                        destR = i;
                        destC = j;
                        break;
                    default:
                        isObstacle[i][j]= false;
                }
            }
        }
		if(possibleMove(destBoxR,destBoxC,0,one)){
			int count = moveBox(destBoxR,destBoxC,0,one);
			if(count != 0)
				print(count);
		}if(possibleMove(destBoxR,destBoxC,one,0)){
			int count= moveBox(destBoxR,destBoxC,one,0);
			if(count != 0 )
				print(count);
		}if(possibleMove(destBoxR,destBoxC,-one,0)){
			int count = moveBox(destBoxR,destBoxC,-one,0);
			if(count != 0)
				print(count);
		}if(possibleMove(destBoxR,destBoxC,0,-one)){
			int count = moveBox(destBoxR,destBoxC,0,-one);
			if(count != 0)
				print(count);
		}
		scanner.close();
	}
	
	public static void print(int num){
		System.out.println("The number of moves taken was: " + num);
	}
	public static boolean possibleMove(int boxR,int boxC,int newR,int newC){
		if(boxR + newR >= R || boxR - newR < 0 || boxC + newC >= C || boxC - newC < 0
		|| boxR + newR < 0 || boxC + newC <0 || boxR - newR >= R || boxC - newC >= C)
			return false;
		
		else if(!isObstacle[boxR+newR][boxC+newC]){
			if(!alreadyFilled[boxR + newR][boxC + newC])
				if(!isObstacle[boxR - newR][boxC - newC])
					return true;
		}
		return false;
	}
	
	public static int moveBox(int boxR,int boxC,int newR,int newC){
		boxR += newR;
		boxC += newC;
		alreadyFilled[boxR][boxC] = true;
		if(boxR == destR && boxC == destC)
			return 1;
		if(possibleMove(boxR, boxC, 0, one)){
			int count = moveBox(boxR, boxC, 0, one);
			if(count != 0)
				return 1 + count;
		}if(possibleMove(boxR, boxC, 0, -one)){
			int count = moveBox(boxR, boxC, 0, -one);
			if(count != 0)
				return 1 + count;
		}if(possibleMove(boxR, boxC, one, 0)){
			int count = moveBox(boxR, boxC, one, 0);
			if(count != 0)
				return 1 + count;
		}if(possibleMove(boxR, boxC, -one, 0)){
			int count = moveBox(boxR, boxC, -one, 0);
			if(count != 0)
				return 1 + count;
		}return 0;
	}
}