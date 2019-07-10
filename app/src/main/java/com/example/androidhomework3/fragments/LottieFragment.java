package com.example.androidhomework3.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidhomework3.R;

public class LottieFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_lottie, container, false);

        LottieAnimationView lottieAnimationView = view.findViewById(R.id.lav);
        SeekBar seekBar = view.findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int max = seekBar.getMax();
                // Use the progress of SeekBar to change the progress of animation
                LottieAnimationView lottieAnimationView = getView().findViewById(R.id.lav);
                lottieAnimationView.setProgress(((float)i) / max);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                getView().findViewById(R.id.sb).setEnabled(true);
                getView().findViewById(R.id.ib_play).setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
        view.findViewById(R.id.ib_play).setOnClickListener(v -> {
            LottieAnimationView lottieAnimationView1 = getView().findViewById(R.id.lav);
            lottieAnimationView1.playAnimation();
            SeekBar seekBar1 = getView().findViewById(R.id.sb);
            seekBar1.setEnabled(false);
            v.setEnabled(false);
        });

        return view;
    }
}
