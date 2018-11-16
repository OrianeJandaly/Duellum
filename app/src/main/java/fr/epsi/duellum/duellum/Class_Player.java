package fr.epsi.duellum.duellum;

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

    public static boolean isOver() {
        boolean over = true;
        boolean over_2 = true;
        for (Class_Player player : joueurs) {
            if (player.canPlay()) {
                if (over) {
                    over_2 = false;
                } else {
                    over = false;
                }
            }
        }
        return (!over && !over_2);
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

    public static Class_Player getJoueur(int i) {
        return joueurs.get(i - 1);
    }
}
