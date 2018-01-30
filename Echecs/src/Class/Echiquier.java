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

    public boolean roquePossible(Case caseDepart, Case caseArrive) {
        int rangeeDepart = caseDepart.getRangee();
        int colonneDepart = caseDepart.getColonne();
        int colonneArrive = caseArrive.getColonne();
        if (verifierEchec()
                && !caseArrive.estOccupe()//si la case d'arrivée du roi est libre
                && caseDepart.getPiece().estEnPositionInitiale()// que le roi est en position initiale
                && caseArrive.getRangee() == rangeeDepart) {
            if (colonneArrive == colonneDepart + 2
                    && chercherCase(rangeeDepart, colonneArrive + 1).getPiece().estEnPositionInitiale()
                    && chercherCase(rangeeDepart, colonneArrive - 1).estOccupe()) {
                return true;
            } else if (colonneArrive == colonneDepart - 3
                    && chercherCase(rangeeDepart, colonneArrive - 1).getPiece().estEnPositionInitiale()
                    && chercherCase(rangeeDepart, colonneArrive + 1).estOccupe()
                    && chercherCase(rangeeDepart, colonneArrive + 2).estOccupe()) {
                return true;
            }
        }
        return false;
    }

    public boolean verifierEchec() {
        for (int rangee = 0; rangee < TAILLE; rangee++) {
            for (int colonne = 0; colonne < TAILLE; colonne++) {//pour chaque case de l'echiquier
                Case caseTest = chercherCase(rangee, colonne);
                if (caseTest.estOccupe() && caseTest.getPiece().getType() == 5) {// si la case est occupée et possède un roi
                    Roi roiTest = (Roi) caseTest.getPiece();
                    System.out.println("ok");
                    if (roiTest.estEnEchec(caseTest)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean[][] deplacementsPossible(Case caseReferente) {//retourne un tableau à double entrées avec tous les déplacements possible d'une pièce
        boolean[][] deplacementsPossible = new boolean[TAILLE][TAILLE];
        for (int rangee = 0; rangee < TAILLE; rangee++) {
            for (int colonne = 0; colonne < TAILLE; colonne++) {//pour chaque case de l'echiquier
                deplacementsPossible[rangee][colonne] = caseReferente.getPiece().deplacementPossible(caseReferente, chercherCase(rangee, colonne));//on stock le resultat du test
                if (caseReferente.getPiece().getType() == 5
                        && roquePossible(caseReferente, chercherCase(rangee, colonne))) {
                    deplacementsPossible[rangee][colonne] = true;
                }
            }
        }
        return deplacementsPossible;
    }

}
