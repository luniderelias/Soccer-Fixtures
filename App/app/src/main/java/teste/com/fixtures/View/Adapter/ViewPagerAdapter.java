package teste.com.fixtures.View.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import teste.com.fixtures.View.Fixtures.FixturesFragment;
import teste.com.fixtures.View.Results.ResultsFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {


    private String[] tabTitles = new String[]{"Fixtures", "Results"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FixturesFragment();
            case 1:
                return new ResultsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}