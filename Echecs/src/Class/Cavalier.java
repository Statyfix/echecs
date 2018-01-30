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
public class Cavalier extends Piece {

    public Cavalier(int couleur, Echiquier _echiquier) {
        super("Cavalier", couleur, 2, _echiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {//si la case n'est pas occupé par une pièce de la couleur de celle joué
            int rangeeDepart = caseDepart.getRangee();
            int rangeeArrive = caseArrive.getRangee();
            int colonneDepart = caseDepart.getColonne();
            int colonneArrive = caseArrive.getColonne();
            if (rangeeArrive == rangeeDepart - 2 //si le cavalier va vers le haut
                    && (colonneArrive == colonneDepart + 1 //et vers la droite
                    || colonneArrive == colonneDepart - 1)) {//ou vers la gauche

                return verifierPasEchecApresDeplacement(caseDepart, caseArrive); //il peut se déplacer
            } else if (rangeeArrive == rangeeDepart + 2 //si le cavalier va vers le bas
                    && (colonneArrive == colonneDepart + 1 //et vers la droite
                    || colonneArrive == colonneDepart - 1)) {//ou vers la gauche
                return verifierPasEchecApresDeplacement(caseDepart, caseArrive); //il peut se déplacer
            } else if (colonneArrive == colonneDepart - 2 //si le cavalier va vers la gauche
                    && (rangeeArrive == rangeeDepart + 1 //et vers le bas
                    || rangeeArrive == rangeeDepart - 1)) {//ou vers le haut
                return verifierPasEchecApresDeplacement(caseDepart, caseArrive); //il peut se déplacer
            } else if (colonneArrive == colonneDepart + 2 //si le cavalier va vers la droite
                    && (rangeeArrive == rangeeDepart + 1 //et vers le bas
                    || rangeeArrive == rangeeDepart - 1)) {//ou vers le haut
                return verifierPasEchecApresDeplacement(caseDepart, caseArrive); //il peut se déplacer
            }
        }
        return false; // sinon il ne peut pas
    }

}
