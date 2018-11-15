package fr.epsi.duellum.duellum.Games;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.epsi.duellum.duellum.Class_Game;
import fr.epsi.duellum.duellum.Class_Player;
import fr.epsi.duellum.duellum.R;

public class ClickTest extends Class_Game {

    private int score1;
    private int score2;
    private double compteur;
    private Class_Player j1;
    private Class_Player j2;
    private Class_Player winner;

    public ClickTest(String _nom, String _description) {
        this.nom = _nom;
        this.description = _description;

        this.objets = new ArrayList<>();
        this.objets.add(R.id.clicktest_titrejeu);
        this.objets.add(R.id.clicktest_click1);
        this.objets.add(R.id.clicktest_click2);
        this.objets.add(R.id.clicktest_count1);
        this.objets.add(R.id.clicktest_count2);

    }

    public void Count(final Activity a) {
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
                    Count(a);
                } else {
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
                }
            }
        }, 100);
    }

    public void Start(Activity a, Class_Player _j1, Class_Player _j2) {
        score1 = 0;
        score2 = 0;
        compteur = 10.00;
        j1 = _j1;
        j2 = _j2;
        Count(a);
    }

    public void EditLayout(Activity a) {
        for (int i = 0; i < this.objets.size(); i++) {
            a.findViewById(this.objets.get(i)).setVisibility(View.VISIBLE);
        }
        ((TextView) a.findViewById(R.id.clicktest_titrejeu)).setText("Juste Prix");

        final Button click1 = a.findViewById(R.id.clicktest_click1);
        final TextView count1 = a.findViewById(R.id.clicktest_count1);
        count1.setRotation(180);
        click1.setRotation(180);
        click1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (winner == null) {
                    final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) click1.getLayoutParams();
                    params.width = params.width + 50;
                    params.height = params.height + 50;
                    click1.setLayoutParams(params);

                    score1++;

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            params.width = params.width - 50;
                            params.height = params.height - 50;
                            click1.setLayoutParams(params);
                        }
                    }, 50);
                }
            }
        });

        final Button click2 = a.findViewById(R.id.clicktest_click2);
        click2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (winner == null) {
                    final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) click2.getLayoutParams();
                    params.width = params.width + 50;
                    params.height = params.height + 50;
                    click2.setLayoutParams(params);

                    score2++;

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            params.width = params.width - 50;
                            params.height = params.height - 50;
                            click2.setLayoutParams(params);
                        }
                    }, 50);
                }
            }
        });
    }
}
