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
    private int nbDeplacement;
    private Echiquier echiquier;

    public Piece(String _nom, int _couleur, int _type, Echiquier _echiquier) {
        this.nom = _nom;
        this.couleur = _couleur;
        this.type = _type;
        this.echiquier = _echiquier;
        this.nbDeplacement = 0;
    }

    //GET
    public String getNom() {
        return nom;
    }

    public int getCouleur() {
        return couleur;
    }

    public int getType() {
        return type;
    }

    public int getNbDeplacement() {
        return nbDeplacement;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }

    public void setNbDeplacement(int nbDeplacement) {
        this.nbDeplacement = nbDeplacement;
    }

    public boolean estEnPositionInitiale() {
        return nbDeplacement == 0;
    }

    public void incrementeNbDeplacement() {
        nbDeplacement++;
    }

    public abstract boolean deplacementPossible(Case caseDepart, Case caseArrive);

}
