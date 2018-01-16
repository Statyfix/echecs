package View;

/**
 *
 * @author qdesbin
 */
public interface Observateur {
    void avertir(int i, int j);
    void avertirNouvellePartie();
    void avertirFinPartie();
}
