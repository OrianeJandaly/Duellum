package fr.epsi.duellum.duellum.Games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.epsi.duellum.duellum.Class_Game;
import fr.epsi.duellum.duellum.Load_EndManche;
import fr.epsi.duellum.duellum.R;

public class De extends Class_Game {


    //attributs


    //constructeur
    public De() {
        super("Click Test", "Le premier joueur à faire un double gagne la manche");
        this.objets = new ArrayList<>();
        this.objets.add(R.id.de_premierde);
        this.objets.add(R.id.de_secondde);
        this.image = R.drawable.de_un;
    }

    //utils

    public void startActivity(Activity a, Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        a.startActivityForResult(i, 1);
    }

    public void Start(Activity a, Context c) {
        winner = null;

    }

    public void ClickOn(final Button button) {
        final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) button.getLayoutParams();
        params.width = params.width + 50;
        params.height = params.height + 50;
        button.setLayoutParams(params);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                params.width = params.width - 50;
                params.height = params.height - 50;
                button.setLayoutParams(params);
            }
        }, 50);
    }

    public void EditLayout(Activity a) {
        for (int i = 0; i < this.objets.size(); i++) {
            a.findViewById(this.objets.get(i)).setVisibility(View.VISIBLE);
        }


    }
}
