package com.example.project2;

import android.os.Parcel;
import android.os.Parcelable;

//한명의 리뷰에 대한 데이터 담는 곳.
public class ReviewItem implements Parcelable {
    String review;

    public ReviewItem(String reView) {
        this.review = reView;//Generate > Constructor > 생성자 형성
    }


    public String getReView() {
        return review;
    }

    //Generate > getter and setter
    public void setReView(String reView) {
        this.review = reView;
    }

    @Override
    public String toString() {
        return "reViewitem{" +
                "reView='" + review + '\'' +
                '}';
    }//정보 확인용

    public ReviewItem(Parcel src){
        review = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ReviewItem createFromParcel(Parcel src){
            return new ReviewItem(src);
        }

        public ReviewItem[] newArray(int Size){
            return new ReviewItem[Size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(review);
    }
}
