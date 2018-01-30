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

    public boolean verifierPasEchecApresDeplacement(Case caseDepart, Case caseArrive) {
        Piece pieceInitial = caseArrive.estOccupe() ? caseArrive.getPiece() : null;
        caseDepart.setPiece(null);
        caseArrive.setPiece(this);//on simule le déplacement de la pièce
        for (int rangee = 0; rangee < 8; rangee++) {
            for (int colonne = 0; colonne < 8; colonne++) {//pour chaque case de l'echiquier
                Roi roi = null;// pour n'importe quelle pièce on met roi à null
                Case caseSusceptibleRoi = echiquier.chercherCase(rangee, colonne);
                if (caseSusceptibleRoi.estOccupe() //si la case cherchée est occupée
                        && caseSusceptibleRoi.getPiece().getType() == 5) {// si la pièce est un roi
                    roi = (Roi) caseSusceptibleRoi.getPiece();// on stock la pièce
                }
                if (roi != null //si la pièce est bien un roi
                        && roi.getCouleur() == this.getCouleur() //si la couleur du roi est la même
                        && roi.estEnEchec(caseSusceptibleRoi)) {//et que le roi sera en echec
                    caseDepart.setPiece(this);
                    caseArrive.setPiece(pieceInitial);//on remet la pièce comme avant
                    return false;
                }
            }
        }
        caseDepart.setPiece(this);
        caseArrive.setPiece(pieceInitial);//on remet la pièce comme avant
        return true;
    }

    public abstract boolean deplacementPossible(Case caseDepart, Case caseArrive);

}
