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
    private boolean enDeplacement;

    public Case() {

    }

    public Case(int _rangee, int _colonne) {
        this.rangee = _rangee;
        this.colonne = _colonne;
        this.enDeplacement = false;
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

    public boolean estEnDeplacement() {
        return enDeplacement;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setEnDeplacement(boolean enDeplacement) {
        this.enDeplacement = enDeplacement;
    }

    // FONCTIONS
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
}
