
public class Class1 {
	int a;
	
	public void method1() {
		int a =20;
		System.out.println("메서드의 지역변수 a = " +a);
		System.out.println("클래스 멤버 변수 a = " + this.a);
	}
	public void method2() {
		int a=30;
		System.out.println("메서드 2의 지역변수 a = " + a);
	}
}
