package ru.dimorinny.floatingtextbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ru.dimorinny.floatingtextbutton.util.DimensionUtils;

public class FloatingTextButton extends FrameLayout {

    private CardView container;
    private ImageView leftIconView;
    private ImageView rightIconView;
    private ImageView topIconView;
    private ImageView bottomIconView;
    private TextView titleView;

    private String title;
    private int titleColor;
    private int selectedTitleColor;
    private Drawable leftIcon;
    private Drawable rightIcon;
    private Drawable topIcon;
    private Drawable bottomIcon;
    private int backgroundColor;
    private int selectedBackgroundColor;
    private int selectedImageColor;

    Integer minHeight;
    Integer minWidth;

    boolean isSelected = false;

    public FloatingTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
        initAttributes(attrs);
        initView();
    }

    public void setTitle(String newTitle) {
        title = newTitle;

        if (newTitle == null || newTitle.isEmpty()) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
        }

        titleView.setText(newTitle);
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean selected)
    {
        if(isSelected != selected) {
            isSelected = selected;
            setSelectedTitleColor(selected ? selectedTitleColor : titleColor);
            setSelectedBackgroundColor(selected ? selectedBackgroundColor : backgroundColor);
            setSelectedIconColor(topIconView, selected ? selectedImageColor : null);
            setSelectedIconColor(leftIconView, selected ? selectedImageColor : null);
            setSelectedIconColor(bottomIconView, selected ? selectedImageColor : null);
            setSelectedIconColor(rightIconView, selected ? selectedImageColor : null);
        }
    }

    private void setSelectedIconColor(ImageView icon,Integer color)
    {
        if(icon != null) {
            if(color != null)
                icon.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_ATOP);
            else
                icon.clearColorFilter();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitleColor(@ColorInt int color) {
        titleColor = color;
        titleView.setTextColor(color);
    }

    private void setSelectedTitleColor(@ColorInt int color) {
        titleView.setTextColor(color);
    }

    public @ColorInt int getTitleColor() {
        return titleColor;
    }

    public void setBackgroundColor(@ColorInt int color) {
        backgroundColor = color;
        container.setCardBackgroundColor(color);
    }

    public void setSelectedBackgroundColor(@ColorInt int color) {
        container.setCardBackgroundColor(color);
    }


    public @ColorInt int getBackgroundColor() {
        return backgroundColor;
    }

    public void setLeftIconDrawable(Drawable drawable) {
        leftIcon = drawable;
        if (drawable != null) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setImageDrawable(drawable);
        } else {
            leftIconView.setVisibility(GONE);
        }
    }

    public void setTopIconDrawable(Drawable drawable) {
        topIcon = drawable;
        if (drawable != null) {
            topIconView.setVisibility(VISIBLE);
            topIconView.setImageDrawable(drawable);
        } else {
            topIconView.setVisibility(GONE);
        }
    }

    public void setBottomIconDrawable(Drawable drawable) {
        bottomIcon = drawable;
        if (drawable != null) {
            bottomIconView.setVisibility(VISIBLE);
            bottomIconView.setImageDrawable(drawable);
        } else {
            bottomIconView.setVisibility(GONE);
        }
    }


    public void setRightIconDrawable(Drawable drawable) {
        rightIcon = drawable;
        if (drawable != null) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setImageDrawable(drawable);
        } else {
            rightIconView.setVisibility(GONE);
        }
    }

    public void setMinimumHeight(Integer dpHeight){
        if(container != null && dpHeight != null && dpHeight != 0)
            container.setMinimumHeight(dpHeight);
    }

    public void setMinimumWidth(Integer dpWidth){
        if(container != null && dpWidth != null && dpWidth != 0)
            container.setMinimumWidth(dpWidth);
    }


    public Drawable getLeftIconDrawable() {
        return leftIcon;
    }

    public Drawable getRightIconDrawable() {
        return rightIcon;
    }

    public Drawable getTopIconDrawable(){ return topIcon;}

    public Drawable getBottomIconDrawable(){return bottomIcon;}


    @Override
    public void setOnClickListener(OnClickListener listener) {
        container.setOnClickListener(listener);
    }

    @Override
    public boolean hasOnClickListeners() {
        return container.hasOnClickListeners();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener listener) {
        container.setOnLongClickListener(listener);
    }

    private void inflateLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_floating_text_button, this, true);

        container = view.findViewById(R.id.layout_button_container);

        leftIconView = view.findViewById(R.id.layout_button_image_left);
        rightIconView = view.findViewById(R.id.layout_button_image_right);
        topIconView = view.findViewById(R.id.layout_button_image_top);
        bottomIconView = view.findViewById(R.id.layout_button_image_bottom);

        titleView = view.findViewById(R.id.layout_button_text);
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray styleable = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.FloatingTextButton,
                0,
                0
        );

        title = styleable.getString(R.styleable.FloatingTextButton_floating_title);
        titleColor = styleable.getColor(R.styleable.FloatingTextButton_floating_title_color, Color.BLACK);
        bottomIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_bottom_icon);
        topIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_top_icon);
        leftIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_left_icon);
        rightIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_right_icon);
        backgroundColor = styleable.getColor(R.styleable.FloatingTextButton_floating_background_color, Color.WHITE);

        selectedImageColor = styleable.getColor(R.styleable.FloatingTextButton_floating_selected_image_color, Color.WHITE);
        selectedBackgroundColor = styleable.getColor(R.styleable.FloatingTextButton_floating_selected_background_color, Color.WHITE);
        selectedTitleColor = styleable.getColor(R.styleable.FloatingTextButton_floating_selected_title_color, Color.BLACK);
        minHeight =(int) styleable.getDimension(R.styleable.FloatingTextButton_floating_minHeight,0);
        minWidth= (int) styleable.getDimension(R.styleable.FloatingTextButton_floating_minWidth,0);
        styleable.recycle();
    }

    private void initView() {
        setTitle(title);
        setTitleColor(titleColor);
        setBottomIconDrawable(bottomIcon);
        setLeftIconDrawable(leftIcon);
        setRightIconDrawable(rightIcon);
        setTopIconDrawable(topIcon);
        setBackgroundColor(backgroundColor);
        setMinimumHeight(minHeight);
        setMinimumWidth(minWidth);

        container.setContentPadding(
                getHorizontalPaddingValue(6),
                getVerticalPaddingValue(6),
                getHorizontalPaddingValue(6),
                getVerticalPaddingValue(6)
        );
        initViewRadius();
    }

    private void initViewRadius() {
        container.post(new Runnable() {
            @Override
            public void run() {
                container.setRadius(container.getHeight() / 2);
            }
        });
    }

    @SuppressWarnings("SameParameterValue")
    private int getVerticalPaddingValue(int dp) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return DimensionUtils.dpToPx(getContext(), dp) / 4;
        } else {
            return DimensionUtils.dpToPx(getContext(), dp);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private int getHorizontalPaddingValue(int dp) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return DimensionUtils.dpToPx(getContext(), dp) / 2;
        } else {
            return DimensionUtils.dpToPx(getContext(), dp);
        }
    }
}
