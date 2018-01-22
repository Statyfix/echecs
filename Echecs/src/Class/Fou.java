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
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {//si la case n'est pas occupé par une pièce de la couleur de celle joué
            int rangee = caseDepart.getRangee();
            int colonne = caseDepart.getColonne();
            int rangeeArrive = caseArrive.getRangee();
            int colonneArrive = caseArrive.getColonne();

            if (rangee < rangeeArrive && colonne < colonneArrive) {
                while (colonne <= 8 && rangee <= 8) {
                    rangee++;
                    colonne++;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchec_m().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee > rangeeArrive && colonne > colonneArrive) {
                while (colonne <= 8 && rangee <= 8) {
                    rangee--;
                    colonne--;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchec_m().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee > rangeeArrive && colonne < colonneArrive) {
                while (colonne <= 8 && rangee <= 8) {
                    rangee--;
                    colonne++;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchec_m().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee < rangeeArrive && colonne > colonneArrive) {
                while (colonne <= 8 && rangee <= 8) {
                    rangee++;
                    colonne--;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
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
