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
public class Fou extends Piece{

    public Fou(int couleur) {
        super("Fou", couleur, 3);
    }

    @Override
    public boolean deplacementPossible(Case caseArrive, Case caseDepart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
