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
public class Case {

    private Piece piece;
    private int rangee;
    private int colonne;
    private int etat; //0=la pièce est posé || 1 = la pièce est entrain d'être déplacé

    public Case() {

    }

    public Case(int _rangee, int _colonne) {
        this.rangee = _rangee;
        this.colonne = _colonne;
        this.etat = 0;
    }

    //GET ET SET
    public Piece getPiece() {
        return piece;
    }

    public int getRangee() {
        return rangee;
    }

    public int getColonne() {
        return colonne;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean estOccupe() {
        return (piece != null);
    }

    public boolean estOccupePar(int couleur) {
        if (piece == null) {
            return false;
        } else {
            return (piece.getCouleur() == couleur);
        }
    }

    public boolean estEnDeolacement() {
        return etat == 1;
    }
}
