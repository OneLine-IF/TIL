
public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a1=new int[10];
		int a2 []=new int[10];
		//�迭�� ����
		System.out.println("a1�� ���� : " + a1.length);
		System.out.println("a2�� ���� : " + a2.length);
		
		int [] a3 = {1,2,3};
		System.out.println("a3�� ���� : " + a3.length);
		
		for(int i=0;i<a3.length;i++) {
			System.out.printf("a3[%d] = %d \n", i,a3[i]);
		}
		
		int [] a4 [] = new int[3][5];
		int a5 [][]=new int[3][5];
		
		System.out.println("a4�� ���� : " + a4.length);
		System.out.println("a4[0]�� ���� : " + a4[0].length);
		
		int [][] a6= {
				{1,2,3},{4,5,6},{7,8,9}
		};
		for(int i=0;i<a6.length;i++) {
			for(int k=0;k<a6[i].length;k++) {
				System.out.printf("a6[%d][%d] = %d\n ", + i,k,a6[i][k]);
			}
		}
	}

}
