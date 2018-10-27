package com.lucemanb.lisolo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucemanb.lisolo.utils.PreferencesHelper;
import com.lucemanb.onboard.OnBoard;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final boolean isFirstTime = new PreferencesHelper(this).isFirstTimeLaunch();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstTime){
                    int[] layouts = {
                            OnBoard.getSampleLayout(),
                            OnBoard.getSampleLayout(),
                            OnBoard.getSampleLayout()
                    };
                    OnBoard board = new OnBoard(layouts,  R.color.colorAccent, R.color.colorPrimary, Main.class);
                    board.startOnBoard(Splash.this);
                }
                else startActivity(new Intent(Splash.this, Main.class));
                finish();
            }
        }, 4000);
    }

}
