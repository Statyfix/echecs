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
public class Fou extends Piece {

    public Fou(int couleur, EchecModel _echec_m) {
        super("Fou", couleur, 3, _echec_m);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            int rangee = caseDepart.getRangee();
            int colonne = caseDepart.getColonne();
            if (rangee < caseArrive.getRangee() && colonne < caseArrive.getColonne()) {
                while (colonne <= 8 && rangee <= 8) {
                    colonne++;
                    rangee++;
                    if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                        return true;
                    } else if (super.getEchec_m().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee > caseArrive.getRangee() && colonne > caseArrive.getColonne()) {
                while (colonne <= 8 && rangee <= 8) {
                    colonne--;
                    rangee--;
                    if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                        return true;
                    } else if (super.getEchec_m().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
