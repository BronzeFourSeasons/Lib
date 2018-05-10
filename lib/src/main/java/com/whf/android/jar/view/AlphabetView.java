package com.whf.android.jar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Custom control on the right of address book
 * Description: Right alphabet control in imitation WeChat address book
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class AlphabetView extends View {

    // Listener
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private final static String[] ABC =
            {"@", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                    "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                    "W", "X", "Y", "Z", "#"};

    private int choose = -1;

    private Paint paint = new Paint();

    private TextView mTextDialog;

    /**
     * @param mTextDialog:view
     */
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }


    public AlphabetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AlphabetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphabetView(Context context) {
        super(context);
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();// Get the corresponding height
        int width = getWidth(); // Get the corresponding width
        int singleHeight = height / ABC.length;// Get the height of each letter

        for (int i = 0; i < ABC.length; i++) {
            paint.setColor(Color.rgb(33, 65, 98));
            // paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(20);
            // Selected state
            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            // The X coordinate is half the width of the middle string
            float xPos = width / 2f - paint.measureText(ABC[i]) / 2f;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(ABC[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        // The proportion of the Y coordinates to the total height
        // is the length of the *b array, which is equal to the number of clicks in B
        final int c = (int) (y / getHeight() * ABC.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                //setBackgroundDrawable(new ColorDrawable(0x00000000));
                //setBackgroundResource(android.R.color.black);
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                //setBackgroundResource(android.R.color.white);
                if (oldChoose != c) {
                    if (c >= 0 && c < ABC.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(ABC[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(ABC[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    /**
     * @param onTouchingLetterChangedListener:onClick
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * interface
     * @author coder
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }

}

