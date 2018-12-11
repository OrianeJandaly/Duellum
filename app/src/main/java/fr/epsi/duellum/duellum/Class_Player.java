package fr.epsi.duellum.duellum;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Class_Player {


    //attributs

    private String nom;
    private int victoires;
    private int defaites;

    private static ArrayList<Class_Player> joueurs = new ArrayList<Class_Player>();
    private static int manches_par_joueur;

    //constructeur

    public Class_Player(String _nom) {
        nom = _nom;
        victoires = 0;
        defaites = 0;
    }


    //accesseurs

    public String getName() {
        return nom;
    }

    public int getVictoires() {
        return victoires;
    }

    public int getDefaites() {
        return defaites;
    }

    public void setName(String _nom) {
        nom = _nom;
    }

    public void setVictoires(int _victoires) {
        victoires = _victoires;
    }

    public void setDefaites(int _defaites) {
        defaites = _defaites;
    }

    public boolean canPlay() {
        return (manches_par_joueur > (victoires + defaites));
    }

    public static ArrayList<Class_Player> getRandomMatchup() {
        ArrayList<Class_Player> matchup = new ArrayList<>();
        Random r = new Random();
        while (matchup.size() < 2) {
            Class_Player random_player = joueurs.get(r.nextInt(joueurs.size()));
            if (random_player.canPlay() && (!matchup.contains(random_player))) {
                matchup.add(random_player);
            }
        }
        return matchup;
    }

    public static int getCountManches() {
        int count = 0;
        for (Class_Player player : joueurs) {
            count = count + player.getDefaites();
            count = count + player.getVictoires();
        }
        if (count > 0) {
            count = count / 2;
        }
        count++;
        return count;
    }

    public static ArrayList<Class_Player> getJoueurs() {
        ArrayList<Class_Player> joueurs_tries = new ArrayList<>();
        for (int i = 0; i < joueurs.size(); i++) {
            Class_Player better = getBetterNotInside(joueurs_tries);
            joueurs_tries.add(better);
        }
        return joueurs_tries;
    }


    public static Class_Player getBetterNotInside(ArrayList<Class_Player> not) {
        Class_Player better = null;
        Log.e("oui", "0");
        for (Class_Player player : joueurs) {
            Log.e("oui", "1");
            if (!not.contains(player)) {
                if (better == null) {
                    better = player;

                } else {
                    if (player.getVictoires() > better.getVictoires() && !not.contains(player)) {
                        better = player;
                    }
                }
            }
        }
        return better;
    }

    public static boolean isOver() {
        boolean player1_canplay = false;
        boolean player2_canplay = false;
        for (Class_Player player : joueurs) {
            if (player.canPlay()) {
                if (player1_canplay) {
                    player2_canplay = true;
                } else {
                    player1_canplay = true;
                }
            }
        }
        return (!player1_canplay && !player2_canplay);
    }

    public static void resetJoueurs() {
        joueurs.clear();
    }

    public static void setMPP(int m) {
        manches_par_joueur = m;
    }

    public static void addJoueur(Class_Player j) {
        joueurs.add(j);
    }

}