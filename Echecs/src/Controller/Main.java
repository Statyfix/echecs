/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EchecModel;
import View.EchecView;

/**
 *
 * @author UTILISATEUR
 */
public class Main {

    public static void main(String[] args) {
        
        EchecModel model = new EchecModel();
        EchecView view = new EchecView(model);

        //ajout de la vue au modele comme observateur
        model.ajouterObservateur(view);

        view.setVisible(true);
    }
}
