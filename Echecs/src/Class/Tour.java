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
public class Tour extends Piece {

    public Tour(int couleur, Echiquier _echiquier) {
        super("Tour", couleur, 1, _echiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {

            int rangeeDepart = caseDepart.getRangee();
            int colonneDepart = caseDepart.getColonne();
            int rangeeArrive = caseArrive.getRangee();
            int colonneArrive = caseArrive.getColonne();

            if (colonneArrive == colonneDepart) {
                if (rangeeArrive > rangeeDepart) {
                    for (int rangee = rangeeDepart + 1; rangee < rangeeArrive; rangee++) {
                        if (super.getEchiquier().chercherCase(rangee, colonneDepart).estOccupe()) {
                            return false;
                        }
                    }
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                } else {
                    for (int rangee = rangeeDepart - 1; rangee > rangeeArrive; rangee--) {
                        if (super.getEchiquier().chercherCase(rangee, colonneDepart).estOccupe()) {
                            return false;
                        }
                    }
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                }
            } else if (rangeeArrive == rangeeDepart) {
                if (colonneArrive > colonneDepart) {
                    for (int colonne = colonneDepart + 1; colonne < colonneArrive; colonne++) {
                        if (super.getEchiquier().chercherCase(rangeeDepart, colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                } else {
                    for (int colonne = colonneDepart - 1; colonne > colonneArrive; colonne--) {
                        if (super.getEchiquier().chercherCase(rangeeDepart, colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                }

            }
        }
        return false;
    }
}
