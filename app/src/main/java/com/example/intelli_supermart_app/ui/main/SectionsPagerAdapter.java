package com.example.intelli_supermart_app.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.intelli_supermart_app.product.activities.ProductCategory1;
import com.example.intelli_supermart_app.product.activities.ProductCategory2;
import com.example.intelli_supermart_app.product.activities.ProductCategory3;
import com.example.intelli_supermart_app.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ProductCategory1 Tab1=new ProductCategory1();
                return Tab1;
            case 1:
                ProductCategory2 Tab2=new ProductCategory2();
                return Tab2;
            case 2:
                ProductCategory3 Tab3= new ProductCategory3();
                return Tab3;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Juices";
            case 1:
                return "Concentrates";
            case 2:
                return "Squash and Sharbat";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}