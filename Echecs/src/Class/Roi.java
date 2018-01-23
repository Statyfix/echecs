/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Model.EchecModel;

/**
 *
 * @author UTILISATEUR
 */
public class Roi extends Piece {

    public Roi(int couleur, Echiquier _echiquier) {
        super("Roi", couleur, 5, _echiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {//si la case n'est pas occupé par une pièce de la couleur de celle joué
            if (caseDepart.getRangee() - caseArrive.getRangee() == 1 // si le roi va vers le haut
                    || caseArrive.getRangee() - caseDepart.getRangee() == 1) {// ou verrs le bas
                if (caseDepart.getColonne() == caseArrive.getColonne()) {// si le roi reste sur la même colonne
                    return true;
                } else if (caseDepart.getColonne() - caseArrive.getColonne() == 1   // si le roi va en haut ou en bas à gauche
                        || caseArrive.getColonne() - caseDepart.getColonne() == 1) {// si le roi va en haut ou en bas à droite
                    return true;
                }
            } else if (caseDepart.getRangee() == caseArrive.getRangee()
                    && caseDepart.getColonne() - caseArrive.getColonne() == 1
                    || caseArrive.getColonne() - caseDepart.getColonne() == 1) {
                return true;
            }
        }
        return false;
    }
}
