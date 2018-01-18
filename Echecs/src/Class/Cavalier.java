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
public class Cavalier extends Piece {

    public Cavalier(int couleur, EchecModel _echec_m) {
        super("Cavalier", couleur, 2, _echec_m);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (!caseArrive.estOccupe()
                || !caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            if (caseArrive.getRangee() == caseDepart.getRangee() - 2
                    && (caseArrive.getColonne() == caseDepart.getColonne() + 1
                    || caseArrive.getColonne() == caseDepart.getColonne() - 1)) {
                return true;
            } else if (caseArrive.getRangee() == caseDepart.getRangee() + 2
                    && (caseArrive.getColonne() == caseDepart.getColonne() + 1
                    || caseArrive.getColonne() == caseDepart.getColonne() - 1)) {
                return true;
            } else if (caseArrive.getColonne() == caseDepart.getColonne() - 2
                    && (caseArrive.getRangee() == caseDepart.getRangee() + 1
                    || caseArrive.getRangee() == caseDepart.getRangee() - 1)) {
                return true;
            } else if (caseArrive.getColonne() == caseDepart.getColonne() + 2
                    && (caseArrive.getRangee() == caseDepart.getRangee() + 1
                    || caseArrive.getRangee() == caseDepart.getRangee() - 1)) {
                return true;
            }
        }

        return false; // sinon on retourn false
    }
}
