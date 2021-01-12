package com.example.project2;

import android.os.Parcel;
import android.os.Parcelable;

//한명의 리뷰에 대한 데이터 담는 곳.
public class reViewitem implements Parcelable {
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

    public reViewitem(Parcel src){
        reView = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public reViewitem createFromParcel(Parcel src){
            return new reViewitem(src);
        }

        public reViewitem[] newArray(int Size){
            return new reViewitem[Size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reView);
    }
}
