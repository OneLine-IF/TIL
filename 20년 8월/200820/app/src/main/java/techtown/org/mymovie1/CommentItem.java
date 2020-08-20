package techtown.org.mymovie1;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    String useridTextview,writetimeTextview,commentTextview,recommendnumberTextview,sueTextview;
    int resId;
    float ratings;

    public CommentItem(String userId, String writeTime, String comment, String recommend, String warning, int resId, float ratings) {
        this.useridTextview = userId;
        this.writetimeTextview = writeTime;
        this.commentTextview = comment;
        this.recommendnumberTextview = recommend;
        this.sueTextview = warning;
        this.resId = resId;
        this.ratings = ratings;
    }

    protected CommentItem(Parcel in) {
        useridTextview = in.readString();
        writetimeTextview = in.readString();
        commentTextview = in.readString();
        recommendnumberTextview = in.readString();
        sueTextview = in.readString();
        resId = in.readInt();
        ratings = in.readFloat();
    }

    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };

    public String getUserId() {
        return useridTextview;
    }

    public void setUserId(String userId) {
        this.useridTextview = userId;
    }

    public String getWriteTime() {
        return writetimeTextview;
    }

    public void setWriteTime(String writeTime) {
        this.writetimeTextview = writeTime;
    }

    public String getComment() {
        return commentTextview;
    }

    public void setComment(String comment) {
        this.commentTextview = comment;
    }

    public String getRecommend() {
        return recommendnumberTextview;
    }

    public void setRecommend(String recommendnumber) {
        this.recommendnumberTextview = recommendnumber;
    }

    public String getWarning() {
        return sueTextview;
    }

    public void setWarning(String sue) {
        this.sueTextview = sue;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "userId_textview='" + useridTextview + '\'' +
                ", writeTime_textview='" + writetimeTextview + '\'' +
                ", comment_textview='" + commentTextview + '\'' +
                ", recommendnumber_textview='" + recommendnumberTextview + '\'' +
                ", sue_textview='" + sueTextview + '\'' +
                ", resId=" + resId +
                ", ratings=" + ratings +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(useridTextview);
        parcel.writeString(writetimeTextview);
        parcel.writeString(commentTextview);
        parcel.writeString(recommendnumberTextview);
        parcel.writeString(sueTextview);
        parcel.writeInt(resId);
        parcel.writeFloat(ratings);
    }
}
