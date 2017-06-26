import java.util.Scanner;
import java.util.ArrayList;

public class wordChain{

	public static void main(String[] args) {
		wordChain st = new wordChain();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter number of words (N)");
		int N=scanner.nextInt();
		String array[]=new String[N];
		System.out.println("Please enter the words");
		for(int i=0;i<N;i++){
			array[i]=scanner.next();
		}
		scanner.close();
		
		ArrayList<Integer> chain = new ArrayList<Integer>();
		chain.add(0);
		String end = array[0] + "/" + chainOfWords(chain, array);

		if(end.split("/").length != N){
			System.out.println("IMPOSSIBLE");
		}else{
			System.out.println(end.replace("/", "\n"));
		}
		scanner.close();
	}

	public static String chainOfWords(ArrayList<Integer> chain, String[] array){
		String origWord = array[chain.get(chain.size() - 1)];
		for(int i =0; i < array.length; i++){
			boolean inChain = false;
			for(int j =0; j < chain.size();j++){
				if(chain.get(j) == i){
					inChain = true;
				}
			}
			if(!inChain){
				int shortestLength = origWord.length();
				if(array[i].length() < shortestLength)
					shortestLength = array[i].length();
				for(int k = 3; k <= shortestLength; k ++){
					String prefix = origWord.substring(origWord.length() - k);
					if(array[i].startsWith(prefix)){
						ArrayList<Integer> tmp = (ArrayList<Integer>) chain.clone();
						tmp.add(i);
						String all = array[i] + "/" + chainOfWords(tmp, array);
						if(all.split("/").length == array.length - tmp.size() + 1)
							return all;
					}
				}
			}
		}
		return "";
	}
	
	
}








