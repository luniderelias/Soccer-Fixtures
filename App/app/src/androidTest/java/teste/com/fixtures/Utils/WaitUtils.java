package teste.com.fixtures.Utils;

import android.support.test.espresso.IdlingResource;

import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;

public class WaitUtils {

    private static final int DEFAULT_WAIT_TIME = 3000;

    private static IdlingResource idlingResource;

    public static void waitTime() {
        waitTime(DEFAULT_WAIT_TIME);
    }

    public static void waitTime(int waitingTime) {
        cleanupWaitTime();

        idlingResource = new ElapsedTimeIdlingResource(waitingTime);
        registerIdlingResources(idlingResource);
    }

    public static void cleanupWaitTime() {
        if (idlingResource == null) return;

        unregisterIdlingResources(idlingResource);
        idlingResource = null;
    }
}
