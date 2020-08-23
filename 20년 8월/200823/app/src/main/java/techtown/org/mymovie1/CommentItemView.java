package techtown.org.mymovie1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentItemView extends LinearLayout {
    CircleImageView userImage_circleimage;
    TextView userId_textview,writeTime_textview,comment_textview,recommendnumber_textview,sue_textview;
    RatingBar ratings_ratingbar;

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
        userImage_circleimage = (CircleImageView) findViewById(R.id.userImage_circleimage);
        userId_textview = (TextView) findViewById(R.id.userId_textview);
        writeTime_textview = (TextView) findViewById(R.id.writeTime_textview);
        comment_textview = (TextView) findViewById(R.id.comment_textview);
        recommendnumber_textview = (TextView) findViewById(R.id.recommendnumber_textview);
        sue_textview = (TextView) findViewById(R.id.sue_textview);
        ratings_ratingbar = (RatingBar) findViewById(R.id.ratings_ratingbar);
    }

    public void SetUserId(String id) {userId_textview.setText(id);}

    public void SetWriteTime(String time) {writeTime_textview.setText(time);}

    public void SetComment(String comments) {
        comment_textview.setText(comments);}

    public void SetRecommend(String recommendNumber) {recommendnumber_textview.setText(recommendNumber);}

    public void SetWarning(String sue) {sue_textview.setText(sue);}

    public void SetUserImage(int resId) {userImage_circleimage.setImageResource(resId);}

    public void SetRatings(float ratings){ratings_ratingbar.setRating(ratings);}
}
