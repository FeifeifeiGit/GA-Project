import java.util.Random;
/**
 * @author fei
 * Guess : one guess for SECRET word
 * sequence : the word 
 * bulls : how many same character same position exist in this guess
 * cows : how many same character but different position exist in this guess
 * fitness : the fitness value calculated from bulls and cows
 */
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
	
	/*
	 * evolve method: this guess evolves to a new guess by mutation of sequence
	 */
	public Guess evolve(){
		Guess nextGeneration = new Guess();
		this.mutate();
		nextGeneration.sequence = this.sequence;
		return nextGeneration;
	}
	
	/*
	 * createRandom method: create a random guess as a member in initial generation
	 */
	public void createRandom(){
		StringBuilder sb  = new StringBuilder();
		for(int i = 0; i < 4; i++){
			sb.append(rn.nextInt(Integer.MAX_VALUE)%10);
		}
		this.sequence = sb.toString();
	}
	
	/*
	 * mutate method: mutate a random character in the sequence into a random character
	 */
	void mutate(){
		int idx  = rn.nextInt(Integer.MAX_VALUE)%4;
		int new_number = rn.nextInt(Integer.MAX_VALUE)%10;
		char[] tmp = sequence.toCharArray();
		tmp[idx] = (char) (new_number + '0');
		sequence = new String(tmp);
	}
	
	/* calBullsCows method
	 * calculate the bulls and cows for this guess
	 */
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
	
	/* calFitness() method
	 * calculate the fitness for this guess
	 * argument: secret: the SECRET word need to be guessed
	 */ 
	public void calFitness(String secret){
		this.calBullsCows(secret);
		this.fitness = this.bulls*Train.BULL_WEIGHT + this.cows*Train.COW_WEIGHT;
	}
}
