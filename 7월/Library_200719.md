### 도서관 프로그램 완성본

1. Main 클래스

```Java
public class Main {

	public static void main(String[] args) {
		Library Lb = new Library();
		Librarianlmpl Lblmpl = new Librarianlmpl();
		User user = new User();
		
		int check = 0;
		
		while(true) {
			switch(user.input()) {
			case "제목으로 찾기" :
				String Title = user.inputTitle();
				Book bk = Lblmpl.findBookByTitle(Title,Lb.getBookList());
				if(bk != null){
					Lblmpl.printBookInfo(bk);
					System.out.println("출력되었습니다");
				}
				else {
					System.out.println("일치하는 책이 없습니다");
				}
				break;
			case "책 추가" :
				Lblmpl.addBook(user.createBook(),Lb.getBookList());
				System.out.println("추가되었습니다");
				break;
			case "책 제거" :
				String Title1 = user.inputTitle();
				Book bk2 = Lblmpl.findBookByTitle(Title1,Lb.getBookList());
				if(bk2 != null){
					Lblmpl.removeBook(bk2, Lb.getBookList());
					System.out.println("제거되었습니다");
				}
				else {
					System.out.println("제거할 책이 존재하지 않습니다");
				}
				break;
			case "책 수정" :
				String Title2 = user.inputTitle();
				Book bk3 = Lblmpl.findBookByTitle(Title2,Lb.getBookList());
				if(bk3 != null){
					Lblmpl.reviseBook(bk3, user.createBook());
					System.out.println("수정되었습니다");
				}
				else {
					System.out.println("수정할 책이 존재하지 않습니다");
				}
				break;
			case "종료" :
				check = 1;
				System.out.println("시스템이 종료되었습니다");
				break;
			}
			if(check == 1) break;
		}	
	}

}
```

1. Librarianlmpl 클래스

```Java
import java.util.ArrayList;

public class Librarianlmpl implements Librarian {

	@Override
	public Book findBookByTitle(String title, ArrayList<Book> bookList) {
		for(Book number: bookList) { 
			if(title.equals(number.getTitle())) {
				return number;
			}
		}
		return null;
	}

	@Override
	public void printBookInfo(Book book) {
		// TODO Auto-generated method stub
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getDate());
	}

	@Override
	public void addBook(Book book, ArrayList<Book> bookList) {
		bookList.add(book);
	}

	@Override
	public void removeBook(Book book, ArrayList<Book> bookList) {
		bookList.remove(book);
	}

	@Override
	public void reviseBook(Book book, Book userBook) {
		book.setTitle(userBook.getTitle());
		book.setAuthor(userBook.getAuthor());
		book.setDate(userBook.getDate());
	}
	
	

}

```