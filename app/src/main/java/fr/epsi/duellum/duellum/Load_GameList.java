package fr.epsi.duellum.duellum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class Load_GameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        Class_Game.LoadGames();
       ArrayList<Class_Game> listejeux = Class_Game.GetGames();
        ListView jeux = (ListView) findViewById(R.id.jeux);
        GameAdapter adapter = new GameAdapter(Load_GameList.this, listejeux);
        jeux.setAdapter(adapter);
    }
}
