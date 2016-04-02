package basicJava;

enum  EM{
	aa,bb
}
public class Test {
	public static void main(String[] args) {
		Grant grant = new Son();
		grant.f();
		int i = 90;
		long l =33;
		String s = "d";
		EM []em = EM.values();
		
		switch(em[i]){
		case aa:;
		case bb:;
		}
	}
} 

class Grant{
	void f(){
		System.out.println("Grant");
	}
}
class Father extends Grant{
	void f(){
		System.out.println("Father");
	}
}
class Son extends Father{
	void f(){
		super.f();
	}
}