package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Load_Classement extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        ArrayList<Class_Player> listejoueurs = Class_Player.getJoueurs();
        HashMap<Class_Player, Integer> classe = new HashMap<>();
        for(Class_Player player : listejoueurs) {
            classe.put(player, player.getVictoires());
        }
        if(listejoueurs.size() > 4) {
            findViewById(R.id.barrebas_classement).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.barrebas_classement).setVisibility(View.INVISIBLE);
        }
Class_Game.setActiveGame(null);
          final ListView classement =  findViewById(R.id.list_classement);

        //afficher les jeux (isolé du reste du code)
        {
            final ClassementAdapter adapter = new ClassementAdapter(Load_Classement.this, listejoueurs);
            classement.setAdapter(adapter);
        }
        final Button button = findViewById(R.id.classement_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(Load_Classement.this, Load_Main.class);
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
        Intent in = new Intent(c, c2);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
}
