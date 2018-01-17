package View;

import Class.Case;

/**
 *
 * @author qdesbin
 */
public interface Observateur {

    public void avertirNouvellePartie();

    public void avertirFinPartie();

    public void avertirEnDeplacement(Case caseEnDeplacement);
}
