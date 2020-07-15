
public class ConTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConTest();
		new ConTest(10);
		new ConTest(100,200);
	}
	//매개변수가 없는 생성자 = default 생성자
	public ConTest() {
		System.out.println("Default 생성자");
	}
	//매개변수가 있는 생성자
	public ConTest(int a) {
		System.out.println("넘겨 받은 정수 값 : " +a);
	}
	public ConTest(int a, int b) {
		System.out.println("두 수의 합 : " +(a+b));
	}
	
}









