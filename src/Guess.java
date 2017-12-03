import java.util.Random;

public class Guess {
	int fitness;
	int bulls;
	int cows;
	String sequence;
	Random rn = new Random();
	
	public Guess(){
		this.fitness = 0;
		this.bulls = 0;
		this.cows = 0;
		this.createRandom();
	}
	
	public Guess evolve(){
		Guess nextGeneration = new Guess();
		this.mutate();
		nextGeneration.sequence = this.sequence;
		return nextGeneration;
	}
	
	public void createRandom(){
		StringBuilder sb  = new StringBuilder();
		for(int i = 0; i < 4; i++){
			sb.append(rn.nextInt(Integer.MAX_VALUE)%10);
		}
		this.sequence = sb.toString();
	}
	
	void mutate(){
		int idx  = rn.nextInt(Integer.MAX_VALUE)%4;
		int new_number = rn.nextInt(Integer.MAX_VALUE)%10;
		char[] tmp = sequence.toCharArray();
		tmp[idx] = (char) (new_number + '0');
		sequence = new String(tmp);
	}
	
	private void calBullsCows(String secret){
		int[] numbers = new int[10];
		this.bulls = 0;
		this.cows = 0;
		for(int i = 0; i < secret.length(); i++){
			char s = secret.charAt(i);
			char c = sequence.charAt(i);
			if(s == c) {
				bulls++;
			}else{
				if(numbers[s-'0'] < 0) cows++;
				numbers[s-'0']++;//if one character occurs in secret, increment numbers[s-'0]
				if(numbers[c-'0'] > 0) cows++;
				numbers[c-'0']--;
			}
		}
	}
	
	public void calFitness(String secret){
		this.calBullsCows(secret);
		this.fitness = this.bulls*Train.BULL_WEIGHT + this.cows*Train.COW_WEIGHT;
	}
}
