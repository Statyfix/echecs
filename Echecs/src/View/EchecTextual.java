/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Class.Case;
import Class.Echiquier;
import Class.Piece;
import Controller.EchecController;

/**
 *
 * @author UTILISATEUR
 */
public class EchecTextual implements Observateur {

    private final EchecController echec_c;
    private final char rangee[] = {'8', '7', '6', '5', '4', '3', '2', '1'};
    private final char colonne[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public EchecTextual(EchecController _echec_c) {
        this.echec_c = _echec_c;
    }

    @Override
    public void avertirMajEchiquier(Echiquier echiquier) {
    }

    @Override
    public void avertirFinPartie() {
        System.out.println("Echec et Mat !");
    }

    @Override
    public void avertirEnDeplacement(Case caseEnDeplacement) {
    }

    @Override
    public void avertirDeplacementsPossible(boolean[][] deplacementsPossible) {
    }

    @Override
    public void avertirEffacerDeplacementsPossible() {
    }

    @Override
    public void avertir(Case caseReferente) {
        Piece piece = caseReferente.getPiece();
        System.out.println(piece.getNom() + " "
                + (piece.getCouleur() == 0 ? "blanc" : "noir") + " en "
                + colonne[caseReferente.getColonne()]
                + rangee[caseReferente.getRangee()]);
    }

    @Override
    public void avertirEchec() {
        System.out.println("Echec !");
    }

}
