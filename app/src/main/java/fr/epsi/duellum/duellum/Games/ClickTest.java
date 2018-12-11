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

public class ClickTest extends Class_Game {


    //attributs
    private int score1;
    private int score2;
    private double compteur;
private boolean can_click;
    //constructeur
    public ClickTest() {
        super("Click Test", "Test de rapidité de clic sur 10 secondes");
        //   "Juste Prix", "Deviner le prix d'un article aléatoire sur internet"
        this.objets = new ArrayList<>();
        this.objets.add(R.id.clicktest_click1);
        this.objets.add(R.id.clicktest_click2);
        this.objets.add(R.id.clicktest_count1);
        this.objets.add(R.id.clicktest_count2);
        this.objets.add(R.id.clicktest_j1);
        this.objets.add(R.id.clicktest_j2);
        this.objets.add(R.id.clicktest_middle);
        this.image = R.drawable.jeu_gameclick_icone;
    can_click = false;
    }

    //utils
    public void Count(final Activity a, final Context c) {
        final TextView count1 = a.findViewById(R.id.clicktest_count1);
        final TextView count2 = a.findViewById(R.id.clicktest_count2);
        final Button click1 = a.findViewById(R.id.clicktest_click1);
        final Button click2 = a.findViewById(R.id.clicktest_click2);
        DecimalFormat numberFormat = new DecimalFormat("#.#");
        count1.setText(numberFormat.format(compteur) + "");
        count2.setText(numberFormat.format(compteur) + "");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                compteur = compteur - 0.1;
                if (compteur > 0) {
                    Count(a, c);
                } else {
                    can_click = false;
                    count1.setText(score1 + "");
                    count2.setText(score2 + "");

                    if (score1 > score2) {
                        winner = j1;
                        final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) click1.getLayoutParams();
                        params.width = params.width + 100;
                        params.height = params.height + 100;
                        click1.setLayoutParams(params);
                    } else {
                        winner = j2;
                        final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) click2.getLayoutParams();
                        params.width = params.width + 100;
                        params.height = params.height + 100;
                        click2.setLayoutParams(params);
                    }
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(a, c, Load_EndManche.class);
                        }
                    }, 5000);
                }
            }
        }, 100);
    }

    public void startActivity(Activity a, Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        a.startActivityForResult(i, 1);
    }

    public void Start(final Activity a, final Context c) {
        score1 = 0;
        winner = null;
        score2 = 0;
        compteur = 10.00;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                can_click = true;
                Count(a, c);
            }
        }, 2000);
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
        final Button click1 = a.findViewById(R.id.clicktest_click1);
        final TextView count1 = a.findViewById(R.id.clicktest_count1);
        final TextView t_j1 = a.findViewById(R.id.clicktest_j1);
        final TextView t_j2 = a.findViewById(R.id.clicktest_j2);
        t_j1.setText(j1.getName());
        t_j2.setText(j2.getName());
        count1.setRotation(180);
        click1.setRotation(180);
        t_j1.setRotation(180);

        click1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (winner == null && can_click) {
                    ClickOn(click1);
                    score1++;
                }
            }
        });

        final Button click2 = a.findViewById(R.id.clicktest_click2);
        click2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (winner == null && can_click) {
                    score2++;
                    ClickOn(click2);
                }
            }
        });
    }
}
