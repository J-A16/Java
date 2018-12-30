

class PolyMorphism{
	
	public static void main(String[] args){
		Object c = new Cat();
		System.out.println(c);
		
		Pet q = new Dog();
		System.out.println(q.call());
	}
}