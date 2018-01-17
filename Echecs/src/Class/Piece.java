/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author vmachu
 */
public abstract class Piece {

    private String nom;
    private int couleur;
    private int type;

    public Piece(String _nom, int _couleur, int _type) {
        this.nom = _nom;
        this.couleur = _couleur;
        this.type = _type;
    }

    public String getNom() {
        return nom;
    }

    public int getCouleur() {
        return couleur;
    }

    public int getType() {
        return type;
    }

}
