package techtown.org.mymovie1;

public class CommentItem {
    String userId_textview,writeTime_textview,comment_textview,recommendnumber_textview,sue_textview;
    int resId;
    float ratings;

    public CommentItem(String userId, String writeTime, String comment, String recommend, String warning, int resId, float ratings) {
        this.userId_textview = userId;
        this.writeTime_textview = writeTime;
        this.comment_textview = comment;
        this.recommendnumber_textview = recommend;
        this.sue_textview = warning;
        this.resId = resId;
        this.ratings = ratings;
    }

    public String getUserId() {
        return userId_textview;
    }

    public void setUserId(String userId) {
        this.userId_textview = userId;
    }

    public String getWriteTime() {
        return writeTime_textview;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime_textview = writeTime;
    }

    public String getComment() {
        return comment_textview;
    }

    public void setComment(String comment) {
        this.comment_textview = comment;
    }

    public String getRecommend() {
        return recommendnumber_textview;
    }

    public void setRecommend(String recommendnumber) {
        this.recommendnumber_textview = recommendnumber;
    }

    public String getWarning() {
        return sue_textview;
    }

    public void setWarning(String sue) {
        this.sue_textview = sue;
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
                "userId_textview='" + userId_textview + '\'' +
                ", writeTime_textview='" + writeTime_textview + '\'' +
                ", comment_textview='" + comment_textview + '\'' +
                ", recommendnumber_textview='" + recommendnumber_textview + '\'' +
                ", sue_textview='" + sue_textview + '\'' +
                ", resId=" + resId +
                ", ratings=" + ratings +
                '}';
    }
}
