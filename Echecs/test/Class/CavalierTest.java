/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Controller.EchecController;
import Model.EchecModel;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vmachu
 */
public class CavalierTest {

    private Cavalier cavalier;
    private Case caseDepart;
    private Case caseArrive;
    private EchecModel echec_m;
    private EchecController echec_c;

    public CavalierTest() {
    }

    @Before
    public void setUp() {
        this.caseDepart = new Case(4, 4);
        this.echec_c = new EchecController();
        this.cavalier = new Cavalier(0, echec_c.getEchec_m().getEchiquier());
        caseDepart.setPiece(new Cavalier(0, echec_c.getEchec_m().getEchiquier()));
    }

    @After
    public void tearDown() {
        this.cavalier = null;
        this.caseDepart = null;
        this.caseArrive = null;
        this.echec_m = null;
    }

    /**
     * Test of deplacementPossible method, of class Cavalier.
     */
    @Test
    public void testDeplacementPossible() {// test si le déplacement est valide et que la case est vide
        boolean expResult = true;//on veut que la fonction retourne true.

        ArrayList<Boolean> results = new ArrayList<>();//on cré un tableau pour stocker les test
        for (int rangee = 0; rangee < 8; rangee++) {
            for (int colonne = 0; colonne < 8; colonne++) {//pour chaque case de l'echiquier
                this.caseArrive = new Case(rangee, colonne);//on indique les coordonnées de la case d'arrivé
                if ((rangee == 6 && (colonne == 5 || colonne == 3))//si il se déplace vers le bas et vers la droite ou la gauche
                        || (rangee == 2 && (colonne == 5 || colonne == 3))//ou si il se déplace vers le haut et vers la droite ou la gauche
                        || (colonne == 6 && (rangee == 5 || rangee == 3))//ou si il se déplace vers la droite et vers le bas ou le haut
                        || (colonne == 2 && (rangee == 5 || rangee == 3))) {//ou si il se déplace vers la gauche et vers le bas ou le haut
                    results.add(cavalier.deplacementPossible(caseDepart, caseArrive));//on stock le resultat du test
                } else {//si il se deplace vers une case non valide
                    results.add(!cavalier.deplacementPossible(caseDepart, caseArrive));//on stock le resultat du test
                }
            }
        }

        results.forEach((result) -> {
            assertEquals(expResult, result);//on regarde si les tests sont concluants
        });
    }

}
