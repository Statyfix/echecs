package View;

import Class.Case;
import Class.Echiquier;

/**
 *
 * @author qdesbin
 */
public interface Observateur {

    public void avertirNouvellePartie(Echiquier echiquier);

    public void avertirFinPartie();

    public void avertirEnDeplacement(Case caseEnDeplacement);

    public void avertir(Case caseReferente);
}
