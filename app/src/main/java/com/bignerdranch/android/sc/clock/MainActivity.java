package com.bignerdranch.android.sc.clock;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import static com.bignerdranch.android.sc.StatusBar.makeStatusBarTransparent;

public class MainActivity extends StatusBar {

    public TextView ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main_page);

        ticker = (TextView) findViewById(R.id.tv_scroll);
        ticker.setSelected(true);

    }

}
