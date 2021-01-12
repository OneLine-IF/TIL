package com.example.project2;
//한명의 리뷰에 대한 데이터 담는 곳.
public class reViewitem {
    String reView;

    public reViewitem(String reView) {
        this.reView = reView;//Generate > Constructor > 생성자 형성
    }

    public String getReView() {
        return reView;
    }
    //Generate > getter and setter
    public void setReView(String reView) {
        this.reView = reView;
    }

    @Override
    public String toString() {
        return "reViewitem{" +
                "reView='" + reView + '\'' +
                '}';
    }//정보 확인용
}
