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
public class Roi extends Piece {

    public Roi(int couleur) {
        super("Roi", couleur, 5);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
