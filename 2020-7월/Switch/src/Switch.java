import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//문자열 입력받기
			System.out.print("숫자를 입력해 주세요");
			String str1= br.readLine();
			System.out.print("숫자를 입력해 주세요");
			String str2= br.readLine();
			int number1=Integer.parseInt(str1);//문자열을 숫자로 바꿔주기
			int number2=Integer.parseInt(str2);
			System.out.print("연산자를 입력해 주세요");//연산자 입력은 하나라서 System.in.read로 받기
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
		}}catch(Exception e) {} // buffered는 try catch가 필요하다고 한다.
	}
}
