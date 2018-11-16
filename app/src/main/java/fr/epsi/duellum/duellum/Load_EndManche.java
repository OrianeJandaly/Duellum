package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Load_EndManche extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endmanche);

//cacher la barre d'action système en bas du téléphone
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Class_Player winner = Class_Game.getActiveGame().winner;
        Class_Player j1 = Class_Game.getActiveGame().j1;
        Class_Player j2 = Class_Game.getActiveGame().j2;
        ((TextView) findViewById(R.id.manche_endmanche)).setText("FIN MANCHE " + Class_Player.getCountManches());

        if (j1 == winner) {
            j1.setVictoires(j1.getVictoires() + 1);
            j2.setDefaites(j2.getDefaites() + 1);
            ((TextView) findViewById(R.id.winner_endmanche)).setText(j1.getName());
            ((TextView) findViewById(R.id.looser_endmanche)).setText(j2.getName());
        } else {
            j2.setVictoires(j2.getVictoires() + 1);
            j1.setDefaites(j1.getDefaites() + 1);
            ((TextView) findViewById(R.id.winner_endmanche)).setText(j2.getName());
            ((TextView) findViewById(R.id.looser_endmanche)).setText(j1.getName());
        }
        findViewById(R.id.continue_endmanche).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Load_EndManche.this, Load_NewManche.class);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                boolean hasBackPressed = data.getBooleanExtra("hasBackPressed", false);
                if (hasBackPressed) {
                    finish();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void startActivity(Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        startActivityForResult(i, 1);
    }
}
