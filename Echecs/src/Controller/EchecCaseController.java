/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Class.Piece;
import Model.EchecModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author vmachu
 */
public class EchecCaseController extends MouseAdapter {

    private int rangee;
    private int colonne;
    private Piece piece;

    private EchecModel echec;

    public EchecCaseController(int _i, int _j, EchecModel _echec) {
        this.rangee = _i;
        this.colonne = _j;
        this.echec = _echec;
    }

    public EchecCaseController(int _i, int _j, EchecModel _echec, Piece _piece) {
        this(_i, _j, _echec);
        this.piece = _piece;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(rangee + " " + colonne);
    }
}
