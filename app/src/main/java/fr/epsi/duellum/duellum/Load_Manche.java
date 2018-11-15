package fr.epsi.duellum.duellum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Load_Manche extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manche);
        Class_Game.getActiveGame().EditLayout(this);
        Class_Game.getActiveGame().Start(this, Class_Player.getJoueur(1), Class_Player.getJoueur(2));


    }
}
