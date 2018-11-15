package fr.epsi.duellum.duellum;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.ArrayList;

import fr.epsi.duellum.duellum.Games.ClickTest;

public class Class_Game {
    protected String nom;
    protected String description;
    protected Bitmap image;
    protected ArrayList<Integer> objets;

    private static ArrayList<Class_Game> jeux;
private static Class_Game active_game;
    //constructeurs
    public Class_Game() {
    }

    public Class_Game(String _nom, String _description) {
        nom = _nom;
        description = _description;
        //image = _image;
    }

    //accesseurs

    public static ArrayList<Class_Game> GetGames() {
        return jeux;
    }

    public String GetName() {
        return nom;
    }

    public String GetDescription() {
        return description;
    }

    public Bitmap GetImage() {
        return image;
    }

    public static Class_Game getActiveGame() {
        return active_game;
    }

    public static void setActiveGame(Class_Game jeu) {
        active_game = jeu;
    }
    public void EditLayout(Activity a) {}
    public void Start(Activity a, Class_Player j1, Class_Player j2) {}

    //static

    public static void LoadGames() {
        jeux = new ArrayList<Class_Game>();
        jeux.add(new ClickTest("Click Test", "Test de rapidité de clic sur 10 secondes"));
    }
}
