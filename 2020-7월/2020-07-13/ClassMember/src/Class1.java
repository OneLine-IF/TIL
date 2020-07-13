
public class Class1 {
	public void method1() {
		System.out.println("메서드 1번 입니다.");
		method2();
	}
	public void method2() {
		System.out.println("메서드 2번입니다.");
	}
	public void add(int a, int b) {
		int result = a+b;
		System.out.println("정수 연산 : a + b = " +result);
	}
	public void add(int a, int b,int c) {
		int result = a+b+c;
		System.out.println("정수 연산 : a + b + c = " +result);
	}
	public int resultAdd(int a, int b) {
		int result=a+b;
		return result;
	}
}
