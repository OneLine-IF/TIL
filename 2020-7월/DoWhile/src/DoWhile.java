import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DoWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir = new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ir);//���ڿ� �Է¹ޱ�
		int jm=0;
		do {
			System.out.print("���ڸ� �Է��� �ּ���");
			String pk= br.readLine();
			jm=Integer.parseInt(pk);//���ڿ��� ���ڷ� �ٲ��ֱ�
		}while(jm<100);
		System.out.println("�ý��� ����");
		}catch(Exception e) {}
	}

}
