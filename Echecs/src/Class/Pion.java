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
public class Pion extends Piece {

    public Pion(int couleur, EchecModel _echec_m) {
        super("Pion", couleur, 0, _echec_m);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            if (super.getCouleur() == 0) { //pour les pions blancs
                if (caseArrive.getRangee() == caseDepart.getRangee() - 1) {
                    if (!caseArrive.estOccupe()
                            && caseArrive.getColonne() == caseDepart.getColonne()) {
                        return true;
                    } else if (caseArrive.estOccupe()
                            && (caseArrive.getColonne() == caseDepart.getColonne() - 1
                            || caseArrive.getColonne() == caseDepart.getColonne() + 1)) {
                        return true;
                    }
                } else if (!caseArrive.estOccupe() && super.estEnPositionInitiale()
                        && caseArrive.getRangee() == caseDepart.getRangee() - 2
                        && caseArrive.getColonne() == caseDepart.getColonne()) {
                    return true;
                }
            } else if (super.getCouleur() == 1) { //pour les pions noirs
                if (caseArrive.getRangee() == caseDepart.getRangee() + 1) {
                    if (!caseArrive.estOccupe()
                            && caseArrive.getColonne() == caseDepart.getColonne()) {
                        return true;
                    } else if (caseArrive.estOccupe()
                            && (caseArrive.getColonne() == caseDepart.getColonne() - 1
                            || caseArrive.getColonne() == caseDepart.getColonne() + 1)) {
                        return true;
                    }
                } else if (!caseArrive.estOccupe() && super.estEnPositionInitiale()
                        && caseArrive.getRangee() == caseDepart.getRangee() + 2
                        && caseArrive.getColonne() == caseDepart.getColonne()) {
                    return true;
                }
            }
        }
        return false; // sinon on retourne false
    }

}
