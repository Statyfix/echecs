/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Model.EchecModel;

/**
 *
 * @author vmachu
 */
public abstract class Piece {

    private String nom;
    private int couleur;
    private int type;
    private int nbDeplacement;
    private EchecModel echec_m;

    public Piece(String _nom, int _couleur, int _type, EchecModel _echec_m) {
        this.nom = _nom;
        this.couleur = _couleur;
        this.type = _type;
        this.echec_m = _echec_m;
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

    public EchecModel getEchec_m() {
        return echec_m;
    }

    public int getNbDeplacement() {
        return nbDeplacement;
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

    public abstract boolean deplacementPossible(Case caseArrive, Case caseDepart);

}
