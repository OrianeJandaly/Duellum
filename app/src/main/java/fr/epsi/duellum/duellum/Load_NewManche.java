package fr.epsi.duellum.duellum;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Load_NewManche extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmanche);
        TextView timer = findViewById(R.id.start_timer);

//cacher la barre d'action système en bas du téléphone
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (Class_Game.getActiveGame() == null) {
            for (int i = 0; i < ((ConstraintLayout) findViewById(R.id.layout_newmanche)).getChildCount(); i++) {
                ((ConstraintLayout) findViewById(R.id.layout_newmanche)).getChildAt(i).setVisibility(View.INVISIBLE);
            }
            findViewById(R.id.layout_newmanche).setBackgroundColor(getResources().getColor(R.color.Couleur1));
            timer.setVisibility(View.VISIBLE);
            Timer(timer, 3);
        } else {
            LoadNewManche(findViewById(R.id.layout_newmanche));
        }

    }

    public void LoadNewManche(View v) {
        if (Class_Player.isOver()) {
//partie terminée
        } else {
            ArrayList<Class_Player> matchup = Class_Player.getRandomMatchup();
            final Class_Game jeu = Class_Game.getRandomGame();
            jeu.setMatchup(matchup);
            ((TextView) v.findViewById(R.id.joueur1_newmanche)).setText(matchup.get(0).getName());
            ((TextView) v.findViewById(R.id.joueur2_newmanche)).setText(matchup.get(1).getName());
            ((ImageView) v.findViewById(R.id.image_newmanche)).setImageResource(jeu.getImage());

            ((TextView) v.findViewById(R.id.count_newmanche)).setText("Manche " + Class_Player.getCountManches());
            ((TextView) v.findViewById(R.id.titrejeu_newmanche)).setText(jeu.getName());
            ((TextView) v.findViewById(R.id.descriptionjeu_newmanche)).setText(jeu.getDescription());
            v.findViewById(R.id.continue_endmanche).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class_Game.setActiveGame(jeu);
                    startActivity(Load_NewManche.this, Load_Manche.class);
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
    }

    public void startActivity(Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        startActivityForResult(i, 1);
    }

    public void Timer(final TextView timer, final int i) {
        timer.setText("Début de la partie dans\n" + i);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i > 1) {
                    Timer(timer, i - 1);
                } else {
                    LoadNewManche(findViewById(R.id.layout_newmanche));
                    findViewById(R.id.layout_newmanche).setBackgroundColor(Color.TRANSPARENT);
                    for (int i = 0; i < ((ConstraintLayout) findViewById(R.id.layout_newmanche)).getChildCount(); i++) {
                        ((ConstraintLayout) findViewById(R.id.layout_newmanche)).getChildAt(i).setVisibility(View.VISIBLE);
                    }
                    timer.setVisibility(View.INVISIBLE);
                }
            }
        }, 1000);
    }
}
