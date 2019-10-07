package com.example.splash;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ParallaxLayoutInflater extends LayoutInflater {

    private ParallaxFragment fragment;

    public ParallaxLayoutInflater(LayoutInflater original, Context newContext,ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new MyFactory(this));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return null;
    }

    private class MyFactory implements Factory2 {

        private LayoutInflater inflater;

        private final String[] PREFIX = new String[]{
                "android.widget.",
                "android.view."
        };

        private MyFactory(LayoutInflater inflater){
            this.inflater = inflater;
        }

        private final int[] attrsID = new int[]{
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out,
                R.attr.a_in,
                R.attr.a_out
        };

        @Nullable
        @Override
        public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            //name是控件名，系统控件如：ImageView、TextView
            //自定义控件如:xx.xx.xx.MyView
            View view = createMyView(name,attrs);
            if(view != null){
                TypedArray array = context.obtainStyledAttributes(attrs,attrsID);
                if(array.length() != 0) {
                    ParallaxTag tag = new ParallaxTag();
                    tag.xIn = array.getFloat(0, 0f);
                    tag.xOut = array.getFloat(1, 0f);
                    tag.yIn = array.getFloat(2, 0f);
                    tag.yOut = array.getFloat(3, 0f);
                    tag.aIn = array.getFloat(4, 0f);
                    tag.aOut = array.getFloat(5, 0f);
                    view.setTag(tag);
                }
                fragment.addView(view);
                array.recycle();
            }
            return view;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            return null;
        }

        public View createMyView(String name,AttributeSet attributeSet){
            try {
                if (name.contains(".")) {   //自定义控件
                    return inflater.createView(name, null, attributeSet);
                } else {
                    for (String prefix : PREFIX) {
                        View view = inflater.createView(name,prefix,attributeSet);
                        if(view != null){
                            return view;
                        }
                    }
                }
            }catch (ClassNotFoundException e){}
            return null;
        }
    }
}
