package teste.com.fixtures;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import teste.com.fixtures.View.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static teste.com.fixtures.Utils.OrientationChangeAction.orientationLandscape;
import static teste.com.fixtures.Utils.WaitUtils.cleanupWaitTime;
import static teste.com.fixtures.Utils.WaitUtils.waitTime;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FixtureScreensInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private void startActivityWithIntent() {
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
    }

    @Test
    public void testFixtureFilterByCompetition() {

        startActivityWithIntent();

        waitTime();

        onView(allOf(withId(R.id.searchEditText),isDisplayed()))
                .perform(typeText("premier"));

        waitTime();

        onView(allOf(withId(R.id.recyclerView),isDisplayed()))
                .check(matches(hasDescendant(withText(containsString("Premier")))));

        cleanupWaitTime();
    }

    @Test
    public void testResultFilterByCompetition() {

        startActivityWithIntent();

        waitTime();

        onView(allOf(withId(R.id.viewPager),isDisplayed())).perform(swipeLeft());

        waitTime();

        onView(allOf(withId(R.id.searchEditText),isDisplayed()))
                .perform(typeText("premier"));

        waitTime();

        onView(allOf(withId(R.id.recyclerView),isDisplayed()))
                .check(matches(hasDescendant(withText(containsString("Premier")))));

        cleanupWaitTime();
    }
    @Test
    public void testFixtureRecyclerContentWithSwitchingToLandscape() {

        startActivityWithIntent();

        waitTime();

        onView(allOf(withId(R.id.searchEditText),isDisplayed()))
                .perform(typeText("uefa"));

        waitTime();

        onView(isRoot()).perform(orientationLandscape());

        waitTime();

        onView(allOf(withId(R.id.recyclerView),isDisplayed()))
                .check(matches(hasDescendant(withText(containsString("UEFA")))));

        cleanupWaitTime();
    }

    @Test
    public void testResultRecyclerContentWithSwitchingToLandscape() {

        startActivityWithIntent();

        waitTime();

        onView(allOf(withId(R.id.viewPager),isDisplayed())).perform(swipeLeft());

        waitTime();

        onView(allOf(withId(R.id.searchEditText),isDisplayed()))
                .perform(typeText("uefa"));

        waitTime();

        onView(isRoot()).perform(orientationLandscape());

        waitTime();

        onView(allOf(withId(R.id.recyclerView),isDisplayed()))
                .check(matches(hasDescendant(withText(containsString("UEFA")))));

        cleanupWaitTime();
    }

}
