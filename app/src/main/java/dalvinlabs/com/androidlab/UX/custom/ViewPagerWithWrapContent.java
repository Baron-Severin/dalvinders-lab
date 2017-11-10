package dalvinlabs.com.androidlab.UX.custom;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ViewPagerWithWrapContent extends ViewPager {

    public ViewPagerWithWrapContent(Context context) {
        super(context);
    }

    public ViewPagerWithWrapContent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int measureViewHeight(View view, int widthMeasureSpec) {
        view.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        return view.getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight = 0;
        int tabHeight = 0;
        int currentHeight;
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (TabLayout.class.isInstance(child) && child.getVisibility() == VISIBLE) {
                tabHeight = measureViewHeight(child, widthMeasureSpec);
            } else {
                currentHeight = measureViewHeight(child, widthMeasureSpec) + tabHeight;
                maxHeight = currentHeight > maxHeight ? currentHeight : maxHeight;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
