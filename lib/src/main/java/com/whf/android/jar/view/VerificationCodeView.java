package com.whf.android.jar.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.Random;

/**
 * Real Code
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class VerificationCodeView extends ImageView {

    public VerificationCodeView(Context context) {
        super(context);
        onView();
    }

    public VerificationCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onView();
    }

    /**
     * init
     */
    private void onView() {
        this.setImageBitmap(Code.getInstance().createBitmap());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAfresh();
            }
        });
    }

    /**
     * Refresh
     */
    public void onAfresh() {
        this.setImageBitmap(Code.getInstance().createBitmap());
    }

    /**
     * Set the number of Code
     *
     * @param mDefaultCodeLength:CodeLength
     */
    public void onDefaultCodeLength(int mDefaultCodeLength) {
        Code.getInstance().setDefaultCodeLength(mDefaultCodeLength);
    }

    /**
     * getCode
     *
     * @return String
     */
    @NonNull
    public String onRealCode() {
        return Code.getInstance().getCode().toLowerCase();
    }

    /**
     * A class of tools for generating verification codes
     *
     * @author qf
     * @author wang.hai.fang
     * @since 2.5.0
     */
    public static class Code {

        /**
         * Random number array
         */
        private static final char[] CHARS = {
                '3', '4', '5', '6', '7', '8',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
                'n', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        private static Code bmpCode;

        public static Code getInstance() {
            if (bmpCode == null) {
                bmpCode = new Code();
            }
            return bmpCode;
        }

        //default settings
        private static int DEFAULT_CODE_LENGTH = 6;
        //Default font size
        private static final int DEFAULT_FONT_SIZE = 25;
        //The number of default lines
        private static final int DEFAULT_LINE_NUMBER = 5;
        //padding
        private static final int BASE_PADDING_LEFT = 10, RANGE_PADDING_LEFT = 15, BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;

        //width and height
        private static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 40;

        //settings decided by the layout xml
        //canvas width and height
        private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

        //random word space and pading_top
        private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,
                base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;

        //number of chars, lines; font size
        private int codeLength = DEFAULT_CODE_LENGTH, line_number = DEFAULT_LINE_NUMBER, font_size = DEFAULT_FONT_SIZE;

        //variables
        private String code;
        private int padding_left, padding_top;
        private Random random = new Random();

        //create Bitmap
        public Bitmap createBitmap() {
            padding_left = 0;

            Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas c = new Canvas(bp);

            code = createCode();

            c.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setTextSize(font_size);
            //paint Code
            for (int i = 0; i < code.length(); i++) {
                randomTextStyle(paint);
                randomPadding();
                c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
            }
            //paint
            for (int i = 0; i < line_number; i++) {
                drawLine(c, paint);
            }

            c.save(Canvas.ALL_SAVE_FLAG);
            c.restore();
            return bp;
        }

        public String getCode() {
            return code;
        }

        public void setDefaultCodeLength(int mDefaultCodeLength) {
            DEFAULT_CODE_LENGTH = mDefaultCodeLength;
        }

        /**
         * Generating verification code
         */
        @NonNull
        private String createCode() {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < codeLength; i++) {
                buffer.append(CHARS[random.nextInt(CHARS.length)]);
            }
            return buffer.toString();
        }

        /**
         * Draw interference line
         */
        private void drawLine(Canvas canvas, Paint paint) {
            int color = randomColor();
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int stopX = random.nextInt(width);
            int stopY = random.nextInt(height);
            paint.setStrokeWidth(1);
            paint.setColor(color);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }

        /**
         * Generate random colors
         */
        private int randomColor() {
            return randomColor(1);
        }

        /**
         * Generate random colors
         */
        private int randomColor(int rate) {
            int red = random.nextInt(256) / rate;
            int green = random.nextInt(256) / rate;
            int blue = random.nextInt(256) / rate;
            return Color.rgb(red, green, blue);
        }

        /**
         * Generate random Text Style
         */
        private void randomTextStyle(Paint paint) {
            int color = randomColor();
            paint.setColor(color);
            paint.setFakeBoldText(random.nextBoolean());
            float skewX = random.nextInt(11) / 10f;
            skewX = random.nextBoolean() ? skewX : -skewX;
            paint.setTextSkewX(skewX);
        }

        /**
         * Generate random Padding
         */
        private void randomPadding() {
            padding_left += base_padding_left + random.nextInt(range_padding_left);
            padding_top = base_padding_top + random.nextInt(range_padding_top);
        }
    }
}
