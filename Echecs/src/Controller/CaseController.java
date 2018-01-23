/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Class.Case;
import Model.EchecModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author vmachu
 */
public class CaseController extends MouseAdapter {

    private final Case caseReferente;

    private final EchecController echec_c;

    public CaseController(Case _caseReferente, EchecController _echec_c) {
        this.caseReferente = _caseReferente;
        this.echec_c = _echec_c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        EchecModel echec_m = echec_c.getEchec_m();
        Case caseEnDeplacement = echec_m.getEchiquier().rechercherPieceEnDeplacement();
        if (caseEnDeplacement == null //si aucune piece n'est en déplacement
                && caseReferente.estOccupe()
                && echec_m.getJoueurEnJeu() == caseReferente.getPiece().getCouleur()
                && !caseReferente.estEnDeplacement()) {
            caseReferente.setEnDeplacement(true);
            echec_c.avertirEnDeplacementObservateurs(caseReferente);
        } else if (caseReferente.estEnDeplacement()) {
            caseReferente.setEnDeplacement(false);
            echec_c.avertirObservateurs(caseReferente);
        } else if (!caseReferente.estEnDeplacement()
                && caseEnDeplacement != null) {
            echec_m.jouer(caseEnDeplacement, caseReferente);
        }

    }
}
