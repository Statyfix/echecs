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
public class Pion extends Piece {

    private boolean positionInitiale;

    public Pion(int couleur) {
        super("Pion", couleur, 0);
        this.positionInitiale = true;
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (super.getCouleur() == 1
                && caseArrive.getRangee() == caseDepart.getRangee() + 1
                && caseArrive.getColonne() == caseDepart.getColonne()) {
            return true;
        } else if (super.getCouleur() == 0
                && caseArrive.getRangee() == caseDepart.getRangee() - 1
                && caseArrive.getColonne() == caseDepart.getColonne()) {
            return true;
        } else {
            return false;
        }
    }

}
