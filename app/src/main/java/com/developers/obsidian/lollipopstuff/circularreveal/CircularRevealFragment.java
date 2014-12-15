package com.developers.obsidian.lollipopstuff.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.developers.obsidian.lollipopstuff.R;

import java.util.Random;

public class CircularRevealFragment extends Fragment {

    int[] colors = {Color.GREEN, Color.RED, Color.BLUE, Color.CYAN, Color.MAGENTA,
            R.color.material_deep_teal_200, R.color.material_deep_teal_500};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.circular_reveal, container, false);

        final View v = view.findViewById(R.id.revealView);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                toggleView(v, colors);
            }
        });


        return view;
    }

    public void toggleView(final View v, int[] colorsArray) {

        Animator reveal;

        int cx = (v.getLeft() + v.getRight()) / 2;
        int cy = (v.getTop() + v.getBottom()) / 2;
        float radius = Math.max(v.getWidth(), v.getHeight()) * 2.0f;

        if (v.getVisibility() == View.INVISIBLE || v.getVisibility() == View.GONE) {
            v.setBackgroundColor(colorsArray[new Random().nextInt(colorsArray.length - 1)]);
            v.setVisibility(View.VISIBLE);
            reveal = ViewAnimationUtils.createCircularReveal(
                    v, cx, cy, 0, radius);
            reveal.setInterpolator(new AccelerateInterpolator(2.0f));
        } else {
            reveal = ViewAnimationUtils.createCircularReveal(
                    v, cx, cy, radius, 0);
            reveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    v.setVisibility(View.INVISIBLE);
                }
            });

            reveal.setInterpolator(new DecelerateInterpolator(2.0f));
        }

        reveal.setDuration(700);
        reveal.start();
    }


}