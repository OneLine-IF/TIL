
public class ForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 1��° ����
		 	for(int i=0;i<3;i++) {
				for(int k=0;k<3;k++) {
					System.out.printf("%d ",i*3+k+1);
				}System.out.println("");
			} */
		/* 2��° ����
		for(int i=0;i<3;i++) {
			for(int k=0;k<3;k++) {
				System.out.printf("%d ", 3*(i+1)-k);
				}System.out.println();
			}
			*/
		/*3��° ����
		for(int i=0;i<3;i++) {
			for(int k=0;k<3;k++) {
				System.out.printf("%d ", i+3*k+1);
			}System.out.println();
			}
		*/
		/*4��° ����
		for(int i=0;i<3;i++) {
			for(int k=0;k<=i;k++) {
					System.out.printf("O ");
				}
			}
		*/
		/* 5��° ����
		for(int i=0;i<3;i++) {
			for(int k=4;k>i*2;k--) {
					System.out.printf(" ");
				}
			for(int k=0;k<=i;k++) {
				System.out.printf("O ");
			}
			System.out.println();
			}
		*/
		/*6��° ����
		for(int i=0;i<3;i++) {
			for(int k=0;k<3;k++) {
					if(i%2==1) {
						if(k%2==1) System.out.printf("O ");
						else System.out.printf("  ");
						if(k==2) System.out.println();
					}
					else {
						if(k%2==1)System.out.printf("  ");
						else System.out.printf("O ");
						if(k==2) System.out.println("");
					}
				}
			}
		*/
		/* 6��° ���� �ٸ� Ǯ��
		 for(int i=0;i<3;i++) {
			for(int k=0;k<3;k++) {
					if(i%2==k%2) {
						if(k%2==1) System.out.printf("O ");
					else{
					System.out.printf("  ");
					}
					System.out.println(); 
						}
		 */
	}

}
