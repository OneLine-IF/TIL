package techtown.org.mymovie1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentItemView extends LinearLayout {
    CircleImageView UserImage;
    TextView UserId,WriteTime,Comment,Recommend,Warning;
    RatingBar Ratings;

    public CommentItemView(Context context) {
        super(context);
        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view,this,true);
        UserImage = (CircleImageView) findViewById(R.id.UserImage);
        UserId = (TextView) findViewById(R.id.UserId);
        WriteTime = (TextView) findViewById(R.id.WriteTime);
        Comment = (TextView) findViewById(R.id.Comment);
        Recommend = (TextView) findViewById(R.id.Recommend);
        Warning = (TextView) findViewById(R.id.Warning);
        Ratings = (RatingBar) findViewById(R.id.Ratings);
    }

    public void SetUserId(String Id) {UserId.setText(Id);}

    public void SetWriteTime(String Time) {WriteTime.setText(Time);}

    public void SetComment(String Comments) {Comment.setText(Comments);}

    public void SetRecommend(String Rec) {Recommend.setText(Rec);}

    public void SetWarning(String Warn) {Warning.setText(Warn);}

    public void SetUserImage(int resId) {UserImage.setImageResource(resId);}

    public void SetRatings(){Ratings.getRating();}
}
