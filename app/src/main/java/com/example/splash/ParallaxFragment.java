package com.example.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ParallaxFragment extends Fragment {

    private List<View> mViews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null) {
            int layoutId = bundle.getInt(ParallaxContainer.LAYOUT_ID);
            if(layoutId != 0){
//                ParallaxLayoutInflater layoutInflater =
//                        new ParallaxLayoutInflater(inflater,getActivity(),this);
                return inflater.inflate(layoutId,null);
            }
        }
        return null;
    }

    public void addView(View view){
        mViews.add(view);
    }

    public List<View> getViews(){
        return mViews;
    }
}
