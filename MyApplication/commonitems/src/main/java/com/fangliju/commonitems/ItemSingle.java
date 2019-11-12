package com.fangliju.commonitems;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ItemSingle extends ConstraintLayout {

    private Context mContext;

    public ItemSingle(Context context) {
        super(context);
    }

    public ItemSingle(Builder builder) {
        super(builder.context);
        mContext = builder.context;
        this.mLayoutParams = builder.layoutParams;
        this.clickable = builder.clickable;
        this.drawable_bg = builder.drawable_bg;
        this.leftText = builder.leftText;
        this.leftColor = builder.leftColor;
        this.leftSize = builder.leftSize;
        this.rightText = builder.rightText;
        this.rightColor = builder.rightColor;
        this.rightSize = builder.rightSize;
        this.dividersType = builder.dividersType;
        this.drawable_right = builder.drawable_right;
        this.dividerHeight = builder.dividerHeight;
        this.dividerColor = builder.dividerColor;
        this.dividerTopMarginLeft = builder.dividerTopMarginLeft;
        this.dividerTopMarginRight = builder.dividerTopMarginRight;
        this.dividerBottomMarginLeft = builder.dividerBottomMarginLeft;
        this.dividerBottomMarginRight = builder.dividerBottomMarginRight;
        this.rightViewType = builder.rightViewType;
        this.rightViewChecked = builder.rightViewChecked;
        this.defaultMargin = Tools.dip2px(mContext, defaultMargin);
        initView();
    }

    public ItemSingle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(context, attrs);
        initView();
    }

    private LayoutParams mLayoutParams;
    private Drawable drawable_bg;
    private boolean clickable;

    private int defaultColor = 0x000000;
    private int defaultSize = 15;
    private int defaultMargin = 16;

    private String leftText;
    private int leftColor;
    private int leftSize;
    private String rightText;
    private int rightColor;
    private int rightSize;
    private String rightHint;

    private int dividerColor;
    private int dividerHeight;
    private int defaultDividerColor = 0xFFE5E5E5;//分割线默认颜色

    public static final int DIVIDERS_NONE = 0;
    public static final int DIVIDERS_TOP = 1;
    public static final int DIVIDERS_BOTTOM = 2;
    public static final int DIVIDERS_BOTH = 3;

    private int dividersType = DIVIDERS_BOTTOM;
    private int dividerTopMarginLeft;
    private int dividerTopMarginRight;
    private int dividerBottomMarginLeft;
    private int dividerBottomMarginRight;

    private Drawable drawable_right;

    private int rightViewType;
    private boolean rightViewChecked;

    public static final int SWITCH_TYPE = 0;
    public static final int CHECK_TYPE = 1;

    private void initAttrs(Context context, AttributeSet attrs) {
        defaultSize = Tools.sp2px(context, defaultSize);
        defaultMargin = Tools.dip2px(context, defaultMargin);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemSingle);
        drawable_bg = typedArray.getDrawable(R.styleable.ItemSingle_item_bg);
        clickable = typedArray.getBoolean(R.styleable.ItemSingle_item_clickable, true);
        leftText = typedArray.getString(R.styleable.ItemSingle_left_text);
        leftColor = typedArray.getColor(R.styleable.ItemSingle_left_text_color, defaultColor);
        leftSize = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_left_text_size, defaultSize);

        rightText = typedArray.getString(R.styleable.ItemSingle_right_text);
        rightHint = typedArray.getString(R.styleable.ItemSingle_right_hint);
        rightColor = typedArray.getColor(R.styleable.ItemSingle_right_text_color, defaultColor);
        rightSize = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_right_text_size, defaultSize);

        dividersType = typedArray.getInt(R.styleable.ItemSingle_dividers_type, dividersType);
        dividerColor = typedArray.getColor(R.styleable.ItemSingle_dividers_color, defaultDividerColor);
        dividerHeight = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_dividers_height, Tools.dip2px(context, 5f));

        dividerTopMarginLeft = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_dividers_top_margin_left, 0);
        dividerTopMarginRight = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_dividers_top_margin_right, 0);
        dividerBottomMarginLeft = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_dividers_bottom_margin_left, 0);
        dividerBottomMarginRight = typedArray.getDimensionPixelSize(R.styleable.ItemSingle_dividers_bottom_margin_right, 0);

        drawable_right = typedArray.getDrawable(R.styleable.ItemSingle_right_img);
        rightViewType = typedArray.getInt(R.styleable.ItemSingle_right_view_type, -1);
        rightViewChecked = typedArray.getBoolean(R.styleable.ItemSingle_right_view_checked, false);


    }


    private void initView() {

        initItemSingle();
        initLeftTextView();
        initRightTextView();
        initDividers();
        initRightView();
        initRightIcon();
        initConstraint();

    }


    private void initItemSingle() {
        this.setEnabled(clickable);
        this.setId(R.id.clItem);
        if (drawable_bg == null)
            drawable_bg = mContext.getResources().getDrawable(R.drawable.ripple_item_write);
        this.setBackground(drawable_bg);
        if (mLayoutParams != null)
            this.setLayoutParams(mLayoutParams);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClickListener(v);
            }
        });
    }

    private TextView tv_left, tv_right;

    private void initLeftTextView() {
        tv_left = setTextView(leftText, leftColor, leftSize, "", 0);
        tv_left.setId(R.id.tvLeft);
        addView(tv_left);
    }

    private void initRightTextView() {
        tv_right = setTextView(rightText, rightColor, rightSize, rightHint, 1);
        tv_right.setId(R.id.tvRight);
        addView(tv_right);
    }

    private TextView setTextView(String text, int color, int size, String hint, int textType) {
        TextView tv = new TextView(mContext);
        LayoutParams layoutParams = new LayoutParams(textType == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : 0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topToTop = getTop();
        layoutParams.bottomToBottom = getBottom();
        if (textType == 0) {
            layoutParams.leftToLeft = getLeft();
            layoutParams.setMargins(defaultMargin, 0, Tools.dip2px(mContext, 10), 0);
        } else {
            layoutParams.rightToRight = getRight();
            layoutParams.setMargins(0, 0, defaultMargin, 0);
            tv.setGravity(Gravity.RIGHT);
        }
        tv.setMaxLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setHint(hint);
        tv.setTextColor(color);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        return tv;
    }

    private TextView tv_top_line, tv_bottom_line;

    private void initDividers() {
        switch (dividersType) {
            case DIVIDERS_NONE:
                if (tv_top_line != null)
                    removeView(tv_top_line);
                if (tv_bottom_line != null)
                    removeView(tv_bottom_line);
                break;
            case DIVIDERS_TOP:
                tv_top_line = setDividers(dividerTopMarginLeft, dividerTopMarginRight, dividersType);
                break;
            case DIVIDERS_BOTTOM:
                tv_bottom_line = setDividers(dividerBottomMarginLeft, dividerBottomMarginRight, dividersType);
                break;
            case DIVIDERS_BOTH:
                tv_top_line = setDividers(dividerTopMarginLeft, dividerTopMarginRight, DIVIDERS_TOP);
                tv_bottom_line = setDividers(dividerBottomMarginLeft, dividerBottomMarginRight, DIVIDERS_BOTTOM);
                break;
        }
        if (tv_top_line != null) {
            tv_top_line.setId(R.id.tvTopLine);
            addView(tv_top_line);
        }

        if (tv_bottom_line != null) {
            tv_bottom_line.setId(R.id.tvBottomLine);
            addView(tv_bottom_line);
        }

    }


    private TextView setDividers(int dividerMarginLeft, int dividerMarginRight, int dividersType) {
        TextView tv_line = new TextView(mContext);
        tv_line.setBackgroundColor(dividerColor);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight);
        params.setMargins(dividerMarginLeft, 0, dividerMarginRight, 0);
        params.leftToLeft = getLeft();
        params.rightToRight = getRight();
        if (dividersType == DIVIDERS_TOP)
            params.topToTop = getTop();
        else
            params.bottomToBottom = getBottom();
        tv_line.setLayoutParams(params);
        return tv_line;
    }

    private void initRightView() {
        switch (rightViewType) {
            case SWITCH_TYPE:
                initSwitch();
                break;
            case CHECK_TYPE:
                initCheckBox();
                break;
        }


    }

    private SwitchCompat switchCompat;

    private void initSwitch() {
        if (switchCompat == null)
            switchCompat = new SwitchCompat(mContext);
        switchCompat.setId(R.id.switchCompat);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, defaultMargin, 0);
        params.topToTop = getTop();
        params.bottomToBottom = getBottom();
        params.rightToRight = getRight();
        switchCompat.setLayoutParams(params);
        switchCompat.setChecked(rightViewChecked);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchCheckedChangeListener.onCheckedChangeListener(buttonView, isChecked);
            }
        });
        addView(switchCompat);
    }

    private AppCompatCheckBox checkBox;

    private void initCheckBox() {
        if (checkBox == null)
            checkBox = new AppCompatCheckBox(mContext);
        checkBox.setId(R.id.checkBox);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, defaultMargin, 0);
        params.topToTop = getTop();
        params.bottomToBottom = getBottom();
        params.rightToRight = getRight();
        checkBox.setLayoutParams(params);
        checkBox.setChecked(rightViewChecked);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchCheckedChangeListener != null)
                    switchCheckedChangeListener.onCheckedChangeListener(buttonView, isChecked);
            }
        });
        addView(checkBox);
    }

    private ImageView iv_right;

    private void initRightIcon() {
        if (iv_right == null) {
            iv_right = new ImageView(mContext);
            iv_right.setId(R.id.ivRight);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, defaultMargin, 0);
            params.topToTop = getTop();
            params.bottomToBottom = getBottom();
            params.rightToRight = getRight();
            iv_right.setLayoutParams(params);
            addView(iv_right);
        }

        if (drawable_right != null)
            iv_right.setBackground(drawable_right);
    }

    private void initConstraint() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(R.id.tvLeft, ConstraintSet.RIGHT, R.id.tvRight, ConstraintSet.LEFT, Tools.dip2px(mContext, 10));
        constraintSet.connect(R.id.tvRight, ConstraintSet.LEFT, R.id.tvLeft, ConstraintSet.RIGHT);
        if (drawable_right != null)
            constraintSet.connect(R.id.tvRight, ConstraintSet.RIGHT, R.id.ivRight, ConstraintSet.LEFT);
        constraintSet.applyTo(this);
    }


    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClickListener(View view);
    }


    public interface OnSwitchCheckedChangeListener {
        void onCheckedChangeListener(CompoundButton buttonView, boolean isChecked);
    }

    private OnSwitchCheckedChangeListener switchCheckedChangeListener;

    public void setSwitchCheckedChangeListener(OnSwitchCheckedChangeListener switchCheckedChangeListener) {
        this.switchCheckedChangeListener = switchCheckedChangeListener;
    }


    public static class Builder {
        private Context context;
        private LayoutParams layoutParams;
        private Drawable drawable_bg;
        private boolean clickable;
        private int itemHeight = 50;
        private String leftText;
        private int leftColor;
        private int leftSize;
        private String rightText;
        private int rightColor;
        private int rightSize;
        private int dividerColor;
        private int dividerHeight;
        private int dividersType;
        private int dividerTopMarginLeft;
        private int dividerTopMarginRight;
        private int dividerBottomMarginLeft;
        private int dividerBottomMarginRight;

        private Drawable drawable_right;
        private int rightViewType;
        private boolean rightViewChecked;


        public Builder(@NonNull Context context) {
            this.context = context;
            itemHeight = Tools.dip2px(context, itemHeight);
            clickable = true;
            drawable_bg = context.getResources().getDrawable(R.drawable.ripple_item_write);
            leftColor = rightColor = context.getResources().getColor(R.color.text_color);
            leftSize = rightSize = Tools.sp2px(context, 15);
            dividersType = ItemSingle.DIVIDERS_BOTTOM;
            dividerHeight = Tools.dip2px(context, 1);
            dividerColor = 0xFFE5E5E5;
            rightViewType = -1;
        }

        public void setDrawable_bg(int bgId) {
            this.drawable_bg = context.getResources().getDrawable(bgId);
        }

        public void setClickable(boolean clickable) {
            this.clickable = clickable;
        }

        public Builder setItemHeight(int height) {
            this.itemHeight = Tools.dip2px(context, height);
            return this;
        }


        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Builder setLeftColor(int leftColor) {
            this.leftColor = context.getResources().getColor(leftColor);
            return this;
        }

        public Builder setLeftSize(int leftSize) {
            this.leftSize = leftSize;
            return this;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public Builder setRightColor(int rightColor) {
            this.rightColor = context.getResources().getColor(rightColor);
            return this;
        }

        public Builder setRightSize(int rightSize) {
            this.rightSize = rightSize;
            return this;
        }

        public Builder setDividersType(int dividersType) {
            this.dividersType = dividersType;
            return this;
        }

        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder setDividerHeight(int dividerHeight) {
            this.dividerHeight = Tools.dip2px(context, dividerHeight);
            return this;
        }

        public Builder setDividerTopMarginLeft(int dividerTopMarginLeft) {
            this.dividerTopMarginLeft = Tools.dip2px(context, dividerTopMarginLeft);
            return this;
        }

        public Builder setDividerTopMarginRight(int dividerTopMarginRight) {
            this.dividerTopMarginRight = Tools.dip2px(context, dividerTopMarginRight);
            return this;
        }

        public Builder setDividerBottomMarginLeft(int dividerBottomMarginLeft) {
            this.dividerBottomMarginLeft = Tools.dip2px(context, dividerBottomMarginLeft);
            return this;
        }

        public Builder setDividerBottomMarginRight(int dividerBottomMarginRight) {
            this.dividerBottomMarginRight = Tools.dip2px(context, dividerBottomMarginRight);
            return this;
        }

        public Builder setDrawable_right(int rightIcId) {
            this.drawable_right = context.getResources().getDrawable(rightIcId);
            return this;
        }

        public Builder setRightViewType(int rightViewType) {
            this.rightViewType = rightViewType;
            return this;
        }

        public Builder setRightViewChecked(boolean rightViewChecked) {
            this.rightViewChecked = rightViewChecked;
            return this;
        }

        public ItemSingle create() {
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
            return new ItemSingle(this);
        }


    }
}
