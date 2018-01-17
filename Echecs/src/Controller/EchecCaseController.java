/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Class.Case;
import Class.Piece;
import Model.EchecModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author vmachu
 */
public class EchecCaseController extends MouseAdapter {

    private Case caseReferente;

    private EchecModel echec;

    public EchecCaseController(Case _caseReferente, EchecModel _echec) {
        this.caseReferente = _caseReferente;
        this.echec = _echec;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!caseReferente.estEnDeolacement() && echec.aucunePieceEnDeplacement()) {
            caseReferente.setEtat(1);
            echec.avertirEnDeplacementObservateurs(caseReferente);
        }

    }
}
