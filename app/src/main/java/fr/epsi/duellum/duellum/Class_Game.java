package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import fr.epsi.duellum.duellum.Games.ClickTest;
import fr.epsi.duellum.duellum.Games.De;

public class Class_Game {
    private String nom;
    private String description;
    private boolean enabled;
    private static ArrayList<Class_Game> jeux;
    private static Class_Game active_game;


    protected Class_Player j1;
    protected Class_Player j2;
    protected Class_Player winner;
    protected ArrayList<Integer> objets;
    protected Integer image;


    //constructeurs
    public Class_Game() {
    }

    public Class_Game(String _nom, String _description) {
        this.nom = _nom;
        this.description = _description;
        this.enabled = true;
    }

    //accesseurs

    public static ArrayList<Class_Game> getGames() {
        return jeux;
    }

    public String getName() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Integer getImage() {
        return image;
    }


    public void setMatchup(ArrayList<Class_Player> matchup) {
        j1 = matchup.get(0);
        j2 = matchup.get(1);
        winner = null;
    }

    public static Class_Game getRandomGame() {
        Random r = new Random();
        Class_Game game = jeux.get(r.nextInt(jeux.size()));
        if (game.isEnabled()) {
            return game;
        } else {
            return getRandomGame();
        }
    }

    public static Class_Game getActiveGame() {
        return active_game;
    }

    public static void setActiveGame(Class_Game jeu) {
        active_game = jeu;
    }

    public void EditLayout(Activity a) {
    }

    public void Start(Activity a, Context c) {
    }

    //static

    public static void LoadGames() {
        jeux = new ArrayList<>();

        jeux.add(new ClickTest());
        jeux.add(new De());



    }
}
