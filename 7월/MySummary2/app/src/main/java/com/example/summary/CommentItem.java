package com.example.summary;

public class CommentItem {

    String Id;
    String Time;
    String Comment;
    int resId;

    public CommentItem(String Id, String Time, String Comment, int resId) {
        this.Id = Id;
        this.Time = Time;
        this.Comment = Comment;
        this.resId =resId;
    }

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

    @Override
    public String toString() {
        return "CommentItem{" +
                "Id='" + Id + '\'' +
                ", Time='" + Time + '\'' +
                ", Comment='" + Comment + '\'' +
                ", resId=" + resId +
                '}';
    }
}
