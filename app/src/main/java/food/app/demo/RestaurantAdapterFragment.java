package food.app.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class RestaurantAdapterFragment extends FragmentPagerAdapter {
    private int numOfTabs;
    public RestaurantAdapterFragment (FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RestaurantFragment();
            case 1:
                return new PromotionsFragment();
            case 2:
                return new MarketFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
