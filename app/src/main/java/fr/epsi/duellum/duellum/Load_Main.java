package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Load_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final EditText j1 = findViewById(R.id.joueur1);
        final EditText j2 = findViewById(R.id.joueur2);
        final EditText j3 = findViewById(R.id.joueur3);
        final EditText j4 = findViewById(R.id.joueur4);
        final EditText mpp = findViewById(R.id.pp_joueur);
        final Button button = findViewById(R.id.jouer);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //ajout joueurs

                if (!j1.getText().toString().isEmpty() && !j2.getText().toString().isEmpty()) {
                    Class_Player.setMPP(Integer.parseInt(mpp.getText().toString()));
                    Class_Player.addJoueur(new Class_Player(j1.getText().toString()));
                    Class_Player.addJoueur(new Class_Player(j2.getText().toString()));

                    if (!j3.getText().toString().isEmpty()) {
                        Class_Player.addJoueur(new Class_Player(j3.getText().toString()));
                    }
                    if (!j4.getText().toString().isEmpty()) {
                        Class_Player.addJoueur(new Class_Player(j4.getText().toString()));
                    }
                    //go to gamelist
                    startActivity(Load_Main.this, Load_GameList.class);

                } else if (j1.getText().toString().isEmpty()) {
                    j1.requestFocus();
                    Vibe(j1);
                } else {
                    j2.requestFocus();
                    Vibe(j2);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Class_Player.resetJoueurs();
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void startActivity(Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        startActivityForResult(i, 1);
    }

    public void Vibe(final View v) {
        v.setX(v.getX() - 20);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setX(v.getX() + 40);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setX(v.getX() - 40);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                v.setX(v.getX() + 40);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        v.setX(v.getX() - 20);
                                    }
                                }, 50);
                            }
                        }, 50);
                    }
                }, 50);
            }
        }, 50);
    }
}
