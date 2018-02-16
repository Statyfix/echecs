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
                    return !estEnEchec(caseArrive); // si le roi ne sera pas en echec après s'être déplacer on renvoit true
                } else if (caseDepart.getColonne() - caseArrive.getColonne() == 1 // si le roi va en haut ou en bas à gauche
                        || caseArrive.getColonne() - caseDepart.getColonne() == 1) {// si le roi va en haut ou en bas à droite
                    return !estEnEchec(caseArrive); // si le roi ne sera pas en echec après s'être déplacer on renvoit true
                }
            } else if (caseDepart.getRangee() == caseArrive.getRangee()// si le roi reste sur la même rangé
                    && (caseDepart.getColonne() - caseArrive.getColonne() == 1 // et si le roi va vers la gauche
                    || caseArrive.getColonne() - caseDepart.getColonne() == 1)) {// ou vers la droite 
                return !estEnEchec(caseArrive); // si le roi ne sera pas en echec après s'être déplacer on renvoit true
            }
        }
        return false;
    }

    public boolean estEnEchec(Case caseTest) {// vérifie si la case est en échec
        Piece pieceCaseTest = null;
        if (caseTest.estOccupe()) {
            pieceCaseTest = caseTest.getPiece();
            caseTest.setPiece(null);//on retire la pièce pour effectuer des test
        }
        for (int rangee = 0; rangee < 8; rangee++) {
            for (int colonne = 0; colonne < 8; colonne++) {//pour chaque case de l'echiquier
                Piece pieceSusceptibleAttaquer = getEchiquier().chercherCase(rangee, colonne).getPiece();
                if (pieceSusceptibleAttaquer != null // si la case suceptible d'attaquer la case à tester est occupé
                        && pieceSusceptibleAttaquer.getCouleur() != this.getCouleur() //si la couleur de la pièce suceptible d'attaquer la case à tester est différente
                        && pieceSusceptibleAttaquer.deplacementPossible(getEchiquier().chercherCase(rangee, colonne), caseTest)) {// et que la pièce peut s'y déplacer
                    caseTest.setPiece(pieceCaseTest);//on remet la pièce de base
                    return true;
                }
            }
        }
        caseTest.setPiece(pieceCaseTest);//on remet la pièce de base
        return false;
    }
}
