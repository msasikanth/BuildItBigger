package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class NonEmptyString extends AndroidTestCase implements EndpointAsync.EndpoinrResponseInterface {

    private static final String LOG_TAG = "NonEmptyStringTest";
    private String mResult = null;
    private CountDownLatch signal;


    @SuppressWarnings("unchecked")
    public void test() {

        // Testing that Async task successfully retrieves a non-empty string
        // You can test this from androidTest -> Run 'All Tests'
        Log.v("NonEmptyStringTest", "Running NonEmptyStringTest test");

        try {
            signal = new CountDownLatch(1);
            new EndpointAsync(this).execute();
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("joke is not null", mResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(boolean isSuccess, String result) {
        mResult = result;
        signal.countDown();
    }
}
