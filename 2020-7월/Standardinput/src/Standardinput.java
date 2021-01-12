import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Standardinput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//문자열 입력받기
			System.out.print("숫자를 입력해 주세요");
			String str= br.readLine();
			int number=Integer.parseInt(str);//문자열을 숫자로 바꿔주기
			if(number%3==0) {
				System.out.println("입력하신 숫자는 3의 배수 입니다.");
			}else {
				System.out.println("입력하신 숫자는 3의 배수가 아닙니다.");
			}
		}catch(Exception e) {}// bufferedreader는 try catch가 필요하다고 한다.
	}

}
