package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Load_Manche extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manche);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

  for (int i = 0; i < ((ConstraintLayout) findViewById(R.id.layout_manche)).getChildCount(); i++) {
            ((ConstraintLayout) findViewById(R.id.layout_manche)).getChildAt(i).setVisibility(View.INVISIBLE);
        }
        findViewById(R.id.layout_manche).setBackgroundColor(getResources().getColor(R.color.Couleur1));
        final TextView timer = findViewById(R.id.start_timer_manche);
        timer.setVisibility(View.VISIBLE);
        Timer(timer, 3, this, this);

    }
    public void Timer(final TextView timer, final int i, final Activity a, final Context c) {
        timer.setText(i + "");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i > 1) {
                    Timer(timer, i - 1, a ,c);
                }
                else {
                    timer.setVisibility(View.INVISIBLE);
                    findViewById(R.id.layout_manche).setBackgroundColor(Color.TRANSPARENT);
                    Class_Game.getActiveGame().EditLayout(a);
                    Class_Game.getActiveGame().Start(a, c);
                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
    }

    public void startActivity(Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        startActivityForResult(i, 1);
    }
}
