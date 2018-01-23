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
public class Fou extends Piece {

    public Fou(int couleur, Echiquier _echiquier) {
        super("Fou", couleur, 3, _echiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {//si la case n'est pas occupé par une pièce de la couleur de celle joué
            int rangee = caseDepart.getRangee();
            int colonne = caseDepart.getColonne();
            int rangeeArrive = caseArrive.getRangee();
            int colonneArrive = caseArrive.getColonne();

            if (rangee < rangeeArrive && colonne < colonneArrive) {
                while (colonne < 7 && rangee < 7) {
                    rangee++;
                    colonne++;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee > rangeeArrive && colonne > colonneArrive) {
                while (colonne > 0 && rangee > 0) {
                    rangee--;
                    colonne--;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee > rangeeArrive && colonne < colonneArrive) {
                while (colonne < 7 && rangee > 0) {
                    rangee--;
                    colonne++;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                        return false;
                    }
                }
            } else if (rangee < rangeeArrive && colonne > colonneArrive) {
                while (colonne > 0 && rangee < 7) {
                    rangee++;
                    colonne--;
                    if (rangee == rangeeArrive && colonne == colonneArrive) {
                        return true;
                    } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
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
