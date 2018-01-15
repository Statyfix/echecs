/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EchecModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author vmachu
 */
public class EchecCaseController extends MouseAdapter {

    int i;
    int j;

    private EchecModel echec;

    public EchecCaseController(int _i, int _j, EchecModel _echec) {
        i = _i;
        j = _j;
        this.echec = _echec;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        
    }
}
