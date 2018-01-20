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

    public Roi(int couleur, EchecModel _echec_m) {
        super("Roi", couleur, 5, _echec_m);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            if (caseDepart.getRangee() - caseArrive.getRangee() == 1
                    || caseArrive.getRangee() - caseDepart.getRangee() == 1) {
                if (caseDepart.getColonne() == caseArrive.getColonne()) {
                    return true;
                } else if (caseDepart.getColonne() - caseArrive.getColonne() == 1
                        || caseArrive.getColonne() - caseDepart.getColonne() == 1) {
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
