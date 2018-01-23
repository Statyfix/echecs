/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Class.Case;
import Model.EchecModel;
import View.Observateur;
import java.util.ArrayList;

/**
 *
 * @author vmachu
 */
public class EchecController {

    private final ArrayList<Observateur> observateurs;
    private final EchecModel echec_m;

    public EchecController() {
        this.observateurs = new ArrayList<>();
        this.echec_m = new EchecModel(this);
    }

    public EchecModel getEchec_m() {
        return echec_m;
    }

    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public void retirerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    public void avertirObservateurs(Case caseReferente) {
        for (Observateur o : observateurs) {
            o.avertir(caseReferente);
        }
    }

    public void avertirEnDeplacementObservateurs(Case caseReferente) {
        for (Observateur o : observateurs) {
            o.avertirEnDeplacement(caseReferente);
        }
    }

    public void avertirNouvellePartieObservateurs() {
        for (Observateur o : observateurs) {
            o.avertirNouvellePartie(echec_m.getEchiquier());
        }
    }

    public void avertirFinPartieAllObservateurs() {
        for (Observateur o : observateurs) {
            o.avertirFinPartie();
        }
    }

    public void nouvellePartie() {
        echec_m.nouvellePartie();
    }
}
