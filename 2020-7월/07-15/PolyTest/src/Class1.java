
public class Class1 extends SuperClass {
	int class1V1;
	public void class1Method() {
		System.out.println("Class1의 매서드 - class1v1 : " + class1V1);
	}
	
	public void superMethod2() {
		System.out.println("자식 클래스에서 overriding한 메서드");
		super.superMethod2();
	}
}
