/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Observateur;
import java.util.ArrayList;

/**
 *
 * @author vmachu
 */
public class EchecModel {

    private int[][] echiquier;
    private static final int taille = 8;
    private boolean finPartie;
    private ArrayList<Observateur> observateurs;

    public EchecModel() {
        // Initialisation de la liste des observateurs
        this.observateurs = new ArrayList<Observateur>();// Initialisation de la liste des observateurs

        // Initialisation
        nouvellePartie();
    }

    public void jouer(int rangee, int colonne) {

    }

    public void ajouterObservateur(Observateur observateur) {
        this.observateurs.add(observateur);
    }

    public void retirerObservateur(Observateur observateur) {
        this.observateurs.remove(observateur);
    }

    public void avertirObservateurs(int i, int j) {
        for (Observateur o : this.observateurs) {
            o.avertir(i, j);
        }
    }

    public void avertirNouvellePartieObservateurs() {
        for (Observateur o : this.observateurs) {
            o.avertirNouvellePartie();
        }
    }

    public void avertirFinPartieAllObservateurs() {
        for (Observateur o : this.observateurs) {
            o.avertirFinPartie();
        }
    }
    
    public void nouvellePartie() {
        this.echiquier = new int[taille][taille];
        
        for(int i = 0; i < taille; i++) {
            for(int j = 0; j< taille; j++) {
                echiquier[i][j] = 0;
            }
        }
        
        finPartie = false; // Ce n'est pas la fin de la partie
        
        avertirNouvellePartieObservateurs();
    }
}
