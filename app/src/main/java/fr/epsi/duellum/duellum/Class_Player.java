package fr.epsi.duellum.duellum;

import java.util.ArrayList;

public class Class_Player {


    //attributs

    private String nom;
    private int victoires;
    private int defaites;

    private static ArrayList<Class_Player> joueurs = new ArrayList<Class_Player>();


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

    public static void addJoueur(Class_Player j) {
        joueurs.add(j);
    }
    public static Class_Player getJoueur(int i) {
        return joueurs.get(i-1);
    }
}
