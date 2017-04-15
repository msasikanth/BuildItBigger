package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.primudesigns.androidjokedisplay.DisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointAsync.EndpoinrResponseInterface {

    private ProgressBar progressBar;
    private Button button;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        button = (Button) root.findViewById(R.id.button);
        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorPrimary)));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointAsync(MainActivityFragment.this).execute();
                button.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.pb_loading_joke);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onResponse(boolean isSuccess, String result) {
        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if (isSuccess) {
            Intent displayIntent = new Intent(getActivity(), DisplayActivity.class);
            displayIntent.putExtra("joke", result);
            startActivity(displayIntent);
        } else {
            Toast.makeText(getContext(), getString(R.string.error) + result, Toast.LENGTH_SHORT).show();
        }
    }
}
