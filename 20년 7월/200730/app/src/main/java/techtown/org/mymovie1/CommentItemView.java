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
    TextView UserId,Write_time,Comment,Rec,RecNum,Bar,Warning;
    RatingBar Rating;

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
        Rating = (RatingBar) findViewById(R.id.Rating);
        UserId = (TextView) findViewById(R.id.UserId);
        Write_time = (TextView) findViewById(R.id.Write_time);
        Comment = (TextView) findViewById(R.id.Comment);
        Rec = (TextView) findViewById(R.id.Rec);
        RecNum = (TextView) findViewById(R.id.RecNum);
        Bar = (TextView) findViewById(R.id.Bar);
        Warning = (TextView) findViewById(R.id.Warning);

    }

    public void setUserImage(int resId){
        UserImage.setImageResource(resId);
    }
    
    public void setRatings(){
        Rating.getRating();
    }

    public void setUserId(String Id){
        UserId.setText(Id);
    }

    public void SetWritetime(String time){
        Write_time.setText(time);
    }

    public void setComment(String com){
        Comment.setText(com);
    }

    public void setRec(String rec){
        Rec.setText(rec);
    }

    public void setRecNum(String recN){
        RecNum.setText(recN);
    }

    public void setBar(String bar){
        Bar.setText(bar);
    }

    public void setWarning(String war){
        Warning.setText(war);
    }
    
}
