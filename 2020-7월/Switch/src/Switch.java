import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//���ڿ� �Է¹ޱ�
			System.out.print("���ڸ� �Է��� �ּ���");
			String str1= br.readLine();
			System.out.print("���ڸ� �Է��� �ּ���");
			String str2= br.readLine();
			int number1=Integer.parseInt(str1);//���ڿ��� ���ڷ� �ٲ��ֱ�
			int number2=Integer.parseInt(str2);
			System.out.print("�����ڸ� �Է��� �ּ���");//������ �Է��� �ϳ��� System.in.read�� �ޱ�
			char calculate= (char)System.in.read();
			System.in.read();
			System.in.read();
			switch(calculate) {
			case '+':
				System.out.printf("%d %c %d = %d",number1,calculate,number2,number1+number2);break;
			case '-':
				System.out.printf("%d %c %d = %d",number1,calculate,number2,number1-number2);break;
			case '*':
				System.out.printf("%d %c %d = %d",number1,calculate,number2,number1*number2);break;
			case '/':
				System.out.printf("%d %c %d = %d",number1,calculate,number2,number1/number2);break;
		}}catch(Exception e) {} // buffered�� try catch�� �ʿ��ϴٰ� �Ѵ�.
	}
}
