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

    public Case() {

    }

    public Case(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean estOccupe() {
        return (piece != null);
    }

    public boolean estOccupePar(String couleur) {
        if (piece == null) {
            return false;
        } else {
            return (piece.getCouleur().equals(couleur));
        }
    }
}
