/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.EchecModel;
import View.EchecView;

/**
 *
 * @author UTILISATEUR
 */
public class Main {

    public static void main(String[] args) {
        
        EchecModel model = new EchecModel();
        EchecView view1 = new EchecView(model);
//        EchecView view2 = new EchecView(model);

        //ajout de la vue au modele comme observateur
        model.ajouterObservateur(view1);
//        model.ajouterObservateur(view2);

        view1.setVisible(true);
//        view2.setVisible(true);
    }
}
