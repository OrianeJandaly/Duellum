package fr.epsi.duellum.duellum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final Button button = findViewById(R.id.jouer);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              EditText e = ((EditText)findViewById(R.id.joueur1));
                Class_Player.addJoueur(new Class_Player(e.getText().toString()));
                EditText ee = ((EditText)findViewById(R.id.joueur2));
                Class_Player.addJoueur(new Class_Player(ee.getText().toString()));
                Class_Game.LoadGames();
                ArrayList<Class_Game> listejeux = Class_Game.GetGames();
                Class_Game.setActiveGame(listejeux.get(0));
                startActivity(new Intent(Load_Main.this, Load_Manche.class));
            }
        });
    }
}
