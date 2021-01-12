
public class WhileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=1;
		while(a<=100) {
			if(!(a%2==0||a%3==0||a%5==0)) {
				System.out.printf("%d ", a);
			}a++;
		}
	}

}
