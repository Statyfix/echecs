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

    public Pion(int couleur, Echiquier _echiquier) {
        super("Pion", couleur, 0, _echiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            int rangeeDepart = caseDepart.getRangee();
            int rangeeArrive = caseArrive.getRangee();
            int colonneDepart = caseDepart.getColonne();
            int colonneArrive = caseArrive.getColonne();
            if (super.getCouleur() == 0) { //pour les pions blancs
                if (rangeeArrive == rangeeDepart - 1) {
                    if (!caseArrive.estOccupe()
                            && colonneArrive == colonneDepart) {
                        return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                    } else if (caseArrive.estOccupe()
                            && (colonneArrive == colonneDepart - 1
                            || colonneArrive == colonneDepart + 1)) {
                        return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                    }
                } else if (!caseArrive.estOccupe() && super.estEnPositionInitiale()
                        && rangeeArrive == rangeeDepart - 2
                        && colonneArrive == colonneDepart
                        && !super.getEchiquier().chercherCase(rangeeArrive + 1, colonneArrive).estOccupe()) {
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                }
            } else if (super.getCouleur() == 1) { //pour les pions noirs
                if (rangeeArrive == rangeeDepart + 1) {
                    if (!caseArrive.estOccupe()
                            && colonneArrive == colonneDepart) {
                        return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                    } else if (caseArrive.estOccupe()
                            && (colonneArrive == colonneDepart - 1
                            || colonneArrive == colonneDepart + 1)) {
                        return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                    }
                } else if (!caseArrive.estOccupe() && super.estEnPositionInitiale()
                        && rangeeArrive == rangeeDepart + 2
                        && colonneArrive == colonneDepart
                        && !super.getEchiquier().chercherCase(rangeeArrive - 1, colonneArrive).estOccupe()) {
                    return verifierPasEchecApresDeplacement(caseDepart, caseArrive);
                }
            }
        }
        return false; // sinon on retourne false
    }

}
