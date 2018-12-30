public class WatchtowerEvent {
	static BoringCompany boringCompany = new BoringCompany();
	
	private Castle castle;

	public static void main(String[] args) {
		
		WatchtowerEvent event = boringCompany.organizeWatchtowerEvent();
		boringCompany.startEvent(event);
	}
	
	WatchtowerEvent(Castle castle) {

		this.castle = castle;
	}

	void start() {
		castle.frenchman.hurlInsults();
	}
}

class BoringCompany {
	WatchtowerEvent organizeWatchtowerEvent() {

		Castle castle = buildCastle();
		InsultsStarterPack insultsStarterPack = createInsults();
		Frenchman frenchman = hireFrenchman();

		frenchman.rehearse(insultsStarterPack);
		castle.add(frenchman);

		return new WatchtowerEvent(castle);
	}

	void startEvent(WatchtowerEvent event) {
		event.start();
	}
	
	Castle buildCastle(){
		return new Castle();
	}
	
	InsultsStarterPack createInsults(){
		return new InsultsStarterPack();
	}
	
	Frenchman hireFrenchman(){
		return new Frenchman();
	}
	
	void print(String string){
		System.out.println(string);
	}
}

class Castle {
	
	int bricks = 0;
	
	Frenchman frenchman;
	
	Castle(){
		
		int numberOfBricksInACaslte = 20000;
		
		while(bricks < numberOfBricksInACaslte){
			bricks++;
		}
		
		print("The castle is built!");
	}
	
	void print(String string){
		System.out.println(string);
	}
	
	void add(Frenchman frenchman) {
		this.frenchman = frenchman;
		
		print("The frenchman has climbed the tower!");
	}
}

class InsultsStarterPack{
	private String[] insultsStarterPack;
	
	InsultsStarterPack(){
		
		// Insults from scoopwhoop.com/best-smartass-insults/#.4csffa1zf
		
		insultsStarterPack = new String[]
				{
						"You're as useless as the 'ueue' in 'queue'!",
						"Mirrors can't talk. Lucky for you, they can't laugh either!",
						"Hey, you have something on your chin...no the 3rd one down.",
						"You're the reason the gene pool needs a lifeguard.",
						"If I had a face like yours, I'd sue my parents.",
						"Your only chance of getting laid is to crawl up a chicken's butt and wait.",
						"Some day you'll go far...and I hope you stay there.",
						"Aha! I see the fuck-up fairy has visited us again!",
						"You must have been born on a highway cos' that's where the most accidents happen.",
						"If laughter is the best medicine, your face must be curing the world.",
						"I'm glad to see you're not letting your education get in the way of your ignorance.",
						"So, a thought crossed your mind? Must have been a long and lonely journey.",
						"When you were born the doctor threw you out the window and the window threw you back."
				};
		
		print("The insults are written!");
	}
	
	void print(String string){
		System.out.println(string);
	}
	
	String[] getInsults(){
		return insultsStarterPack;
	}
}

class Frenchman {
	private String[] insults;
	
	Frenchman(){
		print("The frenchman is hired!");
	}
	
	void rehearse(InsultsStarterPack insultsStarterPack) {
		print("The frenchman is rehearsing his insults!");
		insults = insultsStarterPack.getInsults();
	}

	void hurlInsults() {
		int numberOfInsultsToShout = 5, numberShouted = 0, insult;

		while (numberShouted < numberOfInsultsToShout) {
			insult = pickAnInsult();
			shoutInsult(insult);
			numberShouted++;
		}
	}

	int pickAnInsult() {
		print("The frenchman is eyeballing his victim!");

		return (int) Math.floor(Math.random() * insults.length);
	}

	void shoutInsult(int insult) {
		print("\"" + insults[insult] + "\"");
	}
	
	void print(String string){
		System.out.println(string);
	}
}