package com.example.androidhomework3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.example.androidhomework3.fragments.AnimFragment;
import com.example.androidhomework3.fragments.LottieFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // 2 initial fragment pages
        fragments.add(new AnimFragment());
        fragments.add(new LottieFragment());

        ViewPager pager = findViewById(R.id.vp_main);
        TabLayout tabLayout = findViewById(R.id.tl_main);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Tab " + (position + 1);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        tabLayout.setupWithViewPager(pager);

        // Set transform-page animation
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;

            @Override
            public void transformPage(@NonNull View page, float position) {
                //a slide to b, position changes are a: 0 -> -1 ; b: 1 -> 0.
                int pageWidth = page.getWidth();
                int pageHeight = page.getHeight();
                if (position < -1 || position > 1) {
                    // This page is way off-screen to the left.
                    page.setAlpha(0); // Not shown
                } else {
                    // -1 <= position <= 1
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        page.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        page.setTranslationX(-horzMargin + vertMargin / 2);
                    }
                    // Scale the page down (between MIN_SCALE and 1)
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);
                    // Fade the page relative to its size.
                    page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                            / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                }
            }
        });


        // Click to create a new page
        findViewById(R.id.fab).setOnClickListener(view -> {
            View chooseView = View.inflate(MainActivity.this, R.layout.layout_addfragment, null);

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.choose_frag)
                    .setView(chooseView)
                    .setNegativeButton("取消", (dialogInterface, i) -> {
                    })
                    .setPositiveButton("确认", (dialogInterface, i) -> {
                        Fragment fragment = ((RadioButton) chooseView.findViewById(R.id.rb_anim)).isChecked() ?
                                new AnimFragment() : new LottieFragment();
                        fragments.add(fragment);
                        ViewPager viewPager = findViewById(R.id.vp_main);
                        viewPager.getAdapter().notifyDataSetChanged();
                        viewPager.setCurrentItem(fragments.size() - 1); // Set to the new-created item
                    })
                    .create();

            alertDialog.show();
        });
    }
}
