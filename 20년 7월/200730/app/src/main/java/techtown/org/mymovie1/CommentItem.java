package techtown.org.mymovie1;

public class CommentItem {

    String UserId,Write_time,Comment,Rec,RecNum,Bar,Warning;
    int Rating, UserImage;

    public CommentItem(String userId, String write_time, String comment, String rec, String recNum, String bar, String warning, int rating, int userImage) {
        this.UserId = userId;
        this.Write_time = write_time;
        this.Comment = comment;
        this.Rec = rec;
        this.RecNum = recNum;
        this.Bar = bar;
        this.Warning = warning;
        this.Rating = rating;
        this.UserImage = userImage;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getWrite_time() {
        return Write_time;
    }

    public void setWrite_time(String write_time) {
        this.Write_time = write_time;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        this.Comment = comment;
    }

    public String getRec() {
        return Rec;
    }

    public void setRec(String rec) {
        this.Rec = rec;
    }

    public String getRecNum() {
        return RecNum;
    }

    public void setRecNum(String recNum) {
        this.RecNum = recNum;
    }

    public String getBar() {
        return Bar;
    }

    public void setBar(String bar) {
        this.Bar = bar;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String warning) {
        this.Warning = warning;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        this.Rating = rating;
    }

    public int getUserImage() {
        return UserImage;
    }

    public void setUserImage(int userImage) {
        this.UserImage = userImage;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "UserId='" + UserId + '\'' +
                ", Write_time='" + Write_time + '\'' +
                ", Comment='" + Comment + '\'' +
                ", Rec='" + Rec + '\'' +
                ", RecNum='" + RecNum + '\'' +
                ", Bar='" + Bar + '\'' +
                ", Warning='" + Warning + '\'' +
                ", Rating=" + Rating +
                ", UserImage=" + UserImage +
                '}';
    }
}
