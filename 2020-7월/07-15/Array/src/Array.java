import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStreamReader ir=new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (ir);
			System.out.print("학생의 수를 입력하세요");
			String str = br.readLine();
			int num = Integer.parseInt(str);
			int meanK =0, sumK=0, meanM =0, sumM=0, meanE =0, sumE=0;
			int []student []=new int[num][3];
			for(int i=0;i<num;i++) {
				for(int k=0;k<3;k++) {
					student[i][k]=Integer.parseInt(br.readLine());
				}
			}
			for(int i=0;i<num;i++) {
					sumK+=student[i][0];
			}
			meanK=sumK/num;
			System.out.println(sumK);
			System.out.println(meanK);
		}catch(Exception e) {}
	}

}








