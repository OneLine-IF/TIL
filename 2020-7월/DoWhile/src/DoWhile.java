import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DoWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//문자열 입력받기
		int jm=0;
		do {
			System.out.print("숫자를 입력해 주세요");
			String pk= br.readLine();
			jm=Integer.parseInt(pk);//문자열을 숫자로 바꿔주기
		}while(jm<100);
		System.out.println("시스템 종료");
		}catch(Exception e) {}
	}

}
