import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Standardinput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//���ڿ� �Է¹ޱ�
			System.out.print("���ڸ� �Է��� �ּ���");
			String str= br.readLine();
			int number=Integer.parseInt(str);//���ڿ��� ���ڷ� �ٲ��ֱ�
			if(number%3==0) {
				System.out.println("�Է��Ͻ� ���ڴ� 3�� ��� �Դϴ�.");
			}else {
				System.out.println("�Է��Ͻ� ���ڴ� 3�� ����� �ƴմϴ�.");
			}
		}catch(Exception e) {}// bufferedreader�� try catch�� �ʿ��ϴٰ� �Ѵ�.
	}

}
