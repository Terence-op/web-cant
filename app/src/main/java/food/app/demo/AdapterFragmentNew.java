package food.app.demo;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;


public class AdapterFragmentNew extends FragmentPagerAdapter {
    private int numOfTabs;
    public AdapterFragmentNew(FragmentManager fm,int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainMealFragment();
            case 1:
                return new BreakfastFragment();
            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
