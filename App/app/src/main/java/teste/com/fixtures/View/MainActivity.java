package teste.com.fixtures.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import teste.com.fixtures.View.Adapter.ViewPagerAdapter;
import teste.com.fixtures.R;

public class MainActivity extends AppCompatActivity {


    private ViewPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.viewPager)
    public ViewPager viewPager;

    @BindView(R.id.tablayout)
    public TabLayout tablayout;

    public static String sortTerm = "";
    public static String FRAGMENT = "fragment";
    public Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(
                    savedInstanceState, FRAGMENT);
        }

        mSectionsPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);

        tablayout.setupWithViewPager(viewPager);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, FRAGMENT, getSupportFragmentManager().findFragmentById(R.id.viewPager));
    }

}
