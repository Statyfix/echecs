/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author UTILISATEUR
 */
public class Echiquier {

    private Case[][] echiquier;
    private static final int TAILLE = 8;

    public Echiquier() {
        this.echiquier = new Case[TAILLE][TAILLE];
        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                addCase(rangee, colonne);
            }
        }
    }

    public Case[][] getEchiquier() {
        return echiquier;
    }

    public void addCase(int rangee, int colonne) {
        echiquier[rangee][colonne] = new Case(rangee, colonne);
    }

    public Case chercherCase(int rangee, int colonne) {
        return echiquier[rangee][colonne];
    }

    public Case rechercherPieceEnDeplacement() {
        for (int rangee = 0; rangee <= TAILLE - 1; rangee++) {
            for (int colonne = 0; colonne <= TAILLE - 1; colonne++) {
                if (echiquier[rangee][colonne].estEnDeplacement()) {
                    return echiquier[rangee][colonne];
                }
            }
        }
        return null;
    }

}
