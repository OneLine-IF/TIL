
public class ConTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConTest();
		new ConTest(10);
		new ConTest(100,200);
	}
	//�Ű������� ���� ������ = default ������
	public ConTest() {
		System.out.println("Default ������");
	}
	//�Ű������� �ִ� ������
	public ConTest(int a) {
		System.out.println("�Ѱ� ���� ���� �� : " +a);
	}
	public ConTest(int a, int b) {
		System.out.println("�� ���� �� : " +(a+b));
	}
	
}









