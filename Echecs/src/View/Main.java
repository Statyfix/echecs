/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EchecController;
import View.EchecView;

/**
 *
 * @author vmachu
 */
public class Main {

    public static void main(String[] args) {
        
        EchecController echec_c = new EchecController();
        EchecView echec_v = new EchecView(echec_c);
        EchecTextual echec_t = new EchecTextual(echec_c);

        //ajout de la vue au modele comme observateur
        echec_c.ajouterObservateur(echec_v);
        echec_c.ajouterObservateur(echec_t);

        echec_v.setVisible(true);
    }
}
