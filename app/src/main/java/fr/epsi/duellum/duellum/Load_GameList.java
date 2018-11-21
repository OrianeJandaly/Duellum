package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Load_GameList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);

        //charger jeux
        Class_Game.LoadGames();
        ArrayList<Class_Game> listejeux = Class_Game.getGames();
        final ListView jeux = findViewById(R.id.listejeux);

        //afficher les jeux (isolé du reste du code)
        {
            final GameAdapter adapter = new GameAdapter(Load_GameList.this, listejeux);
            jeux.setAdapter(adapter);
        }

        final Button button = findViewById(R.id.gamelist_jouer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListAdapter adapter = jeux.getAdapter();
                boolean enabled = false;
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (((Class_Game) adapter.getItem(i)).isEnabled()) {
                        enabled = true;
                    }
                }
                if (enabled) {
                    startActivity(Load_GameList.this, Load_NewManche.class);
                }
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
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasBackPressed", true);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void startActivity(Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        startActivityForResult(i, 1);
    }
}
