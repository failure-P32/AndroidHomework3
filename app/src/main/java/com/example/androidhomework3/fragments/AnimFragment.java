package com.example.androidhomework3.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidhomework3.R;

public class AnimFragment extends Fragment {

    AnimatorSet mAnimatorSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_anim, container, false);

        view.findViewById(R.id.b_scale).setOnClickListener(v -> {
            ScaleAnimation mScaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            mScaleAnimation.setDuration(1000);
            mScaleAnimation.setRepeatCount(1);
            mScaleAnimation.setRepeatMode(Animation.REVERSE);
            ImageView image = getView().findViewById(R.id.iv_android);
            image.startAnimation(mScaleAnimation);
        });

        view.findViewById(R.id.b_alpha).setOnClickListener(v -> {
            AlphaAnimation mAlphaAnimation = new AlphaAnimation(1f, 0.1f);
            mAlphaAnimation.setDuration(1000);
            mAlphaAnimation.setRepeatCount(1);
            mAlphaAnimation.setRepeatMode(Animation.REVERSE);
            ImageView image = getView().findViewById(R.id.iv_android);
            image.startAnimation(mAlphaAnimation);
        });

        view.findViewById(R.id.b_both).setOnClickListener(v -> {
            ScaleAnimation mScaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            mScaleAnimation.setRepeatCount(1);
            mScaleAnimation.setRepeatMode(Animation.REVERSE);
            AlphaAnimation mAlphaAnimation = new AlphaAnimation(1f, 0.1f);
            mAlphaAnimation.setRepeatCount(1);
            mAlphaAnimation.setRepeatMode(Animation.REVERSE);
            AnimationSet mAnimationSet = new AnimationSet(false);
            mAnimationSet.addAnimation(mScaleAnimation);
            mAnimationSet.addAnimation(mAlphaAnimation);
            mAnimationSet.setDuration(1000);
            ImageView image = getView().findViewById(R.id.iv_android);
            image.startAnimation(mAnimationSet);
        });

        Button btn = view.findViewById(R.id.b_anim);
        final ObjectAnimator mScaleXAnimator = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 2f);
        ObjectAnimator mScaleYAnimator = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 2f);
        ObjectAnimator mAlphaAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0.1f);
        mScaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mScaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mScaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mScaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAlphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAlphaAnimator.setRepeatCount(ValueAnimator.INFINITE);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(2000);
        mAnimatorSet.playTogether(mScaleXAnimator, mScaleYAnimator, mAlphaAnimator);

        btn.setOnClickListener(v -> {
            if (!mAnimatorSet.isStarted())
                mAnimatorSet.start();
            else {
                if (mAnimatorSet.isPaused())
                    mAnimatorSet.resume();
                else
                    mAnimatorSet.pause();
            }
        });

        return view;
    }
}
