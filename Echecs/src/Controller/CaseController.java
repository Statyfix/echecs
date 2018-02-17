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
    private final EchecModel echec_m;

    public CaseController(Case _caseReferente, EchecController _echec_c) {
        this.caseReferente = _caseReferente;
        this.echec_c = _echec_c;
        this.echec_m = echec_c.getEchec_m();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Case caseEnDeplacement = echec_m.getEchiquier().rechercherPieceEnDeplacement();
        if (caseEnDeplacement == null //si aucune piece n'est en déplacement
                && caseReferente.estOccupe()//et que la case possède une pièce
                && echec_m.getJoueurEnJeu() == caseReferente.getPiece().getCouleur()//et que la couleur de la pièce est celle du joueur qui doit jouer
                ) {
            caseReferente.setEnDeplacement(true);
            echec_c.avertirEnDeplacementObservateurs(caseReferente);
            echec_c.avertirDeplacementsPossibleObservateurs(echec_m.getEchiquier().deplacementsPossible(caseReferente));
        } else if (caseReferente.estEnDeplacement()) {
            caseReferente.setEnDeplacement(false);
            echec_c.avertirObservateurs(caseReferente);
            echec_c.avertirEffacerDeplacementsPossibleObservateurs();
        } else if (!caseReferente.estEnDeplacement()
                && caseEnDeplacement != null) {
            echec_m.jouer(caseEnDeplacement, caseReferente);
            echec_c.avertirEffacerDeplacementsPossibleObservateurs();
        }

    }
}
