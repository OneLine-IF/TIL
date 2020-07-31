package techtown.org.mymovie1;

public class CommentItem {
    String UserId,WriteTime,Comment,Recommend,Warning;
    int resId,Ratings;

    public CommentItem(String userId, String writeTime, String comment, String recommend, String warning, int resId,int Ratings) {
        this.UserId = userId;
        this.WriteTime = writeTime;
        this.Comment = comment;
        this.Recommend = recommend;
        this.Warning = warning;
        this.resId = resId;
        this.Ratings = Ratings;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getWriteTime() {
        return WriteTime;
    }

    public void setWriteTime(String writeTime) {
        this.WriteTime = writeTime;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public String getRecommend() {
        return Recommend;
    }

    public void setRecommend(String recommend) {
        this.Recommend = recommend;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        this.Warning = warning;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getRatings() {
        return Ratings;
    }

    public void setRatings(int ratings) {
        this.Ratings = ratings;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "UserId='" + UserId + '\'' +
                ", WriteTime='" + WriteTime + '\'' +
                ", Comment='" + Comment + '\'' +
                ", Recommend='" + Recommend + '\'' +
                ", Warning='" + Warning + '\'' +
                ", resId=" + resId +
                '}';
    }
}
