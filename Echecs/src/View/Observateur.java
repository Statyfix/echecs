package View;

import Class.Case;
import Class.Echiquier;

/**
 *
 * @author qdesbin
 */
public interface Observateur {

    public void avertirMajEchiquier(Echiquier echiquier);

    public void avertirFinPartie();

    public void avertirEnDeplacement(Case caseEnDeplacement);

    public void avertirDeplacementsPossible(boolean[][] deplacementsPossible);
    
    public void avertirEffacerDeplacementsPossible();

    public void avertir(Case caseReferente);
}
