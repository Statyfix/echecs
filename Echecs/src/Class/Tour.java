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
public class Tour extends Piece {

    public Tour(int couleur, EchecModel _echec_m) {
        super("Tour", couleur, 1, _echec_m);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        if (caseArrive.getColonne() == caseDepart.getColonne()) {
            if (caseArrive.getRangee() > caseDepart.getRangee()) {
                for (int rangee = caseDepart.getRangee() + 1; rangee < caseArrive.getRangee(); rangee++) {
                    if (super.getEchec_m().chercherCase(rangee, caseDepart.getColonne()).estOccupe()) {
                        System.out.println("colonne");
                        return false;
                    }
                }
                if (!caseArrive.estOccupe()
                        || (caseArrive.estOccupe()
                        && !caseArrive.estOccupePar(caseDepart.getPiece().getCouleur()))) {
                    return true;
                }
            } else {
                for (int rangee = caseDepart.getRangee() - 1; rangee > caseArrive.getRangee(); rangee--) {
                    if (super.getEchec_m().chercherCase(rangee, caseDepart.getColonne()).estOccupe()) {
                        return false;
                    }
                }
                if (!caseArrive.estOccupe()
                        || caseArrive.estOccupe()
                        && !caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
                    return true;
                }
            }
        } else if (caseArrive.getRangee() == caseDepart.getRangee()) {
            System.out.println("rangee");
            if (caseArrive.getColonne() > caseDepart.getColonne()) {
                for (int colonne = caseDepart.getColonne() + 1; colonne < caseArrive.getColonne(); colonne++) {
                    if (super.getEchec_m().chercherCase(caseDepart.getRangee(), colonne).estOccupe()) {
                        return false;
                    }
                }
                if (!caseArrive.estOccupe()
                        || caseArrive.estOccupe()
                        && !caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
                    return true;
                }
            } else {
                for (int colonne = caseDepart.getColonne() - 1; colonne > caseArrive.getColonne(); colonne--) {
                    if (super.getEchec_m().chercherCase(caseDepart.getRangee(), colonne).estOccupe()) {
                        return false;
                    }
                }
                if (!caseArrive.estOccupe()
                        || caseArrive.estOccupe()
                        && !caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
                    return true;
                }
            }
        }
        return false;
    }
}
