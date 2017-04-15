package com.primudesigns.androidjokedisplay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(null);
        }

        jokeText = (TextView) findViewById(R.id.tv_joke);

        if (getIntent() != null && getIntent().hasExtra("joke")) {

            String joke = getIntent().getStringExtra("joke");
            showJoke(joke);

        } else {
            jokeText.setText(R.string.error_intent_empty);
        }

    }

    @SuppressLint("SetTextI18n")
    public void showJoke(String joke) {
        if (!TextUtils.isEmpty(joke)) {
            jokeText.setText(joke);
        } else {
            jokeText.setText(R.string.error_joke_empty);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
