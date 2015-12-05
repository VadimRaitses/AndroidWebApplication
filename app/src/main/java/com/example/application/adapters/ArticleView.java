package com.example.application.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application.watchandlike.R;

/**
 * Created by Generator on 12/4/2015.
 */
public class ArticleView extends GridLayout {

    private TextView mHomeScore;
    private ImageView mImageView;

    public ArticleView(Context context) {
        super(context);
    }

    public ArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mHomeScore =(TextView) findViewById(R.id.text_team_away);
        mImageView = (ImageView) findViewById(R.id.logo_team_away);
    }

    @Override
    public String toString() {
        return  mHomeScore.getText()
                + ": " + getLeft() + "," + getTop()
                + ": " + getMeasuredWidth() + "x" + getMeasuredHeight();
    }
}
