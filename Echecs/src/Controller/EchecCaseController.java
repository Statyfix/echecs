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

    private EchecModel echec_m;

    public EchecCaseController(Case _caseReferente, EchecModel _echec_m) {
        this.caseReferente = _caseReferente;
        this.echec_m = _echec_m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Case caseEnDeplacement = echec_m.rechercherPieceEnDeplacement();
        if (caseReferente.estOccupe()
                && echec_m.getJoueurEnJeu() == caseReferente.getPiece().getCouleur()
                && !caseReferente.estEnDeplacement()
                && caseEnDeplacement == null) { // et si aucune piece n'est en déplacement
            caseReferente.setEtat(1);
            echec_m.avertirEnDeplacementObservateurs(caseReferente);
        } else if (caseReferente.estEnDeplacement()) {
            caseReferente.setEtat(0);
            echec_m.avertirObservateurs(caseReferente);
        } else if (!caseReferente.estEnDeplacement()
                && caseEnDeplacement != null) {
            echec_m.jouer(caseReferente, caseEnDeplacement);
        }

    }
}
