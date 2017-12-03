import java.util.Arrays;

public class Train {
	public static final String SECRET = "8351";
	public static final int BULL_WEIGHT = 2;
	public static final int COW_WEIGHT = 1;
	
	Guess[] generation;
	int num_generation;
	boolean win;
	int times;
	
	public Train(int upperBound, int count){
		this.times = upperBound;
		initialize(count);
	}
	
	void initialize(int count){
		System.out.println("===========Initialized ============");
		generation = new Guess[count];
		for(int i = 0; i < count; i++){
			generation[i] = new Guess();//create generation 0
		}
		num_generation = 0;
		win = false;
		System.out.println("===========================-Generation 0-==================");
		for(Guess g: generation){
			System.out.print(g.sequence + "  " );
		}
		System.out.println();
	}
	
	void checkCurrentGeneration(){
		for(Guess g: generation){
			g.calFitness(SECRET);
		}
		Arrays.sort(generation, (a,b) -> b.fitness - a.fitness);
		//System.out.println("Best fittness of Generation " + num_generation + " is: " + generation[0].fitness);
		if(generation[0].bulls == 4) {
			win = true;
			System.out.println("The correct guess is "+ generation[0].sequence + ", bulls is " + generation[0].bulls);
		}
	}
	
	void evoleToNext(){
		while(times > 0 && !win){
			int half = generation.length/2;
			for(int i = 0; i < half; i++){
				Guess new_guess =  generation[i].evolve();
				generation[i+half] = new_guess;
			}
			num_generation++;//new generation created
			times--;
			System.out.println("========= new genration evolved: ========" + num_generation + "=========");
			for(Guess g: generation){
				System.out.print(g.sequence + "  ");
			}
			System.out.println();
			checkCurrentGeneration();
		}
	}
	
	
	public static void main(String[] args) {
		Train test = new Train(100,16);
	    test.checkCurrentGeneration();
	    test.evoleToNext();  
	}
}