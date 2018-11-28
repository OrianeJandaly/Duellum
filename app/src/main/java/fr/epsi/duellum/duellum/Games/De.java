package fr.epsi.duellum.duellum.Games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import fr.epsi.duellum.duellum.Class_Game;
import fr.epsi.duellum.duellum.Class_Player;
import fr.epsi.duellum.duellum.Load_EndManche;
import fr.epsi.duellum.duellum.R;

public class De extends Class_Game {


    //attributs
    private ArrayList<Integer> des = new ArrayList<>();
    private int de_un;
    private int de_deux;
    private Class_Player joueur;
    private boolean running;

    //constructeur
    public De() {
        super("Jeu des dés", "Le premier joueur faisant un double gagne la manche");
        this.objets = new ArrayList<>();
        this.objets.add(R.id.de_groupede);
        this.objets.add(R.id.de_premierde);
        this.objets.add(R.id.de_secondde);
        this.objets.add(R.id.de_joueur);
        this.image = R.drawable.jeu_de_icone;
        de_un = 0;
        de_deux = 0;
        running = false;
        joueur = null;
        des.add(R.drawable.jeu_de_un);
        des.add(R.drawable.jeu_de_deux);
        des.add(R.drawable.jeu_de_trois);
        des.add(R.drawable.jeu_de_quatre);
        des.add(R.drawable.jeu_de_cinq);
        des.add(R.drawable.jeu_de_six);
    }

    //utils

    public void startActivity(Activity a, Context c, Class<?> c2) {
        Intent i = new Intent(c, c2);
        a.startActivityForResult(i, 1);
    }

    public void Start(final Activity a, final Context c) {
        winner = null;
        joueur = j1;
        de_un = 0;
        de_deux = 0;
        running = false;

        final ImageView image_de_un = a.findViewById(R.id.de_premierde);
        final TextView joueurr = a.findViewById(R.id.de_joueur);
        joueurr.setText(joueur.getName());
        a.findViewById(R.id.layout_manche).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    running = true;
                    Roulette(a, c, image_de_un, 5);
                    //check roulette
                }
            }
        });

    }

    public void Roulette(final Activity a, final Context c, final ImageView image, final int i) {
        final Random r = new Random();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i > 0) {
                    image.setImageResource(des.get(r.nextInt(des.size())));
                    Roulette(a, c, image, (i - 1));
                } else {
                    int lancer = (r.nextInt(6)) + 1;
                    image.setImageResource(getImage(lancer));
                    if (de_un == 0) {
                        de_un = lancer;
                        Roulette(a, c, ((ImageView) a.findViewById(R.id.de_secondde)), 5);
                    } else {
                        de_deux = lancer;

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (de_un == de_deux) {
                                    winner = joueur;
                                    startActivity(a, c, Load_EndManche.class);
                                } else {
                                    final TextView joueurr = a.findViewById(R.id.de_joueur);
                                    if (joueur == j1) {
                                        joueur = j2;
                                    } else {
                                        joueur = j1;
                                    }
                                    joueurr.setText(joueur.getName());
                                    ((ImageView) a.findViewById(R.id.de_premierde)).setImageResource(R.drawable.jeu_de_zero);
                                    ((ImageView) a.findViewById(R.id.de_secondde)).setImageResource(R.drawable.jeu_de_zero);
                                    de_un = 0;
                                    de_deux = 0;
                                    running = false;
                                }
                            }
                        }, 2000);
                    }
                }
            }
        }, 250);
    }

    public int getImage(int i) {
        if (i == 1) {
            return R.drawable.jeu_de_un;
        } else if (i == 2) {
            return R.drawable.jeu_de_deux;
        } else if (i == 3) {
            return R.drawable.jeu_de_trois;
        } else if (i == 4) {
            return R.drawable.jeu_de_quatre;
        } else if (i == 5) {
            return R.drawable.jeu_de_cinq;
        } else {
            return R.drawable.jeu_de_six;
        }
    }

    public void EditLayout(Activity a) {
        for (int i = 0; i < this.objets.size(); i++) {
            a.findViewById(this.objets.get(i)).setVisibility(View.VISIBLE);
        }
        ((ImageView) a.findViewById(R.id.de_premierde)).setImageResource(R.drawable.jeu_de_zero);
        ((ImageView) a.findViewById(R.id.de_secondde)).setImageResource(R.drawable.jeu_de_zero);


    }
}
