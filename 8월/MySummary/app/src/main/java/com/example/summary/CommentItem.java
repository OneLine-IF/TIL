package com.example.summary;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {

    String Id;
    String Time;
    String Comment;
    int resId;
    float rating;

    public CommentItem(String Id, String Time, String Comment, int resId, float rating) {
        this.Id = Id;
        this.Time = Time;
        this.Comment = Comment;
        this.resId = resId;
        this.rating = rating;
    }

    public CommentItem(Parcel src) {
        Id = src.readString();
        Time = src.readString();
        Comment = src.readString();
        resId = src.readInt();
        rating = src.readFloat();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        // 바로 객체를 만들면서 할당해주는 형태
        public CommentItem createFromParcel (Parcel src) {
            return new CommentItem(src);
        }

        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "Id='" + Id + '\'' +
                ", Time='" + Time + '\'' +
                ", Comment='" + Comment + '\'' +
                ", resId=" + resId +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(Time);
        parcel.writeString(Comment);
        parcel.writeInt(resId);
        parcel.writeFloat(rating);
    }
}
