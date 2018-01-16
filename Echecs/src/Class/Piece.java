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
    private String couleur;

    public Piece(String _nom, String _couleur) {
        this.nom = _nom;
        this.couleur = _couleur;
    }

    public String getNom() {
        return nom;
    }

    public String getCouleur() {
        return couleur;
    }

    
}
