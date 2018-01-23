/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Model.EchecModel;

/**
 *
 * @author UTILISATEUR
 */
public class Reine extends Piece {

    public Reine(int couleur, Echiquier _echuiquier) {
        super("Reine", couleur, 4, _echuiquier);
    }

    @Override
    public boolean deplacementPossible(Case caseDepart, Case caseArrive) {
        if (!caseArrive.estOccupePar(caseDepart.getPiece().getCouleur())) {
            int rangeeDepart = caseDepart.getRangee();
            int colonneDepart = caseDepart.getColonne();
            int rangeeArrive = caseArrive.getRangee();
            int colonneArrive = caseArrive.getColonne();

            if (colonneArrive == colonneDepart) {
                if (rangeeArrive > rangeeDepart) {
                    for (int rangee = rangeeDepart + 1; rangee < rangeeArrive; rangee++) {
                        if (super.getEchiquier().chercherCase(rangee, colonneDepart).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int rangee = rangeeDepart - 1; rangee > rangeeArrive; rangee--) {
                        if (super.getEchiquier().chercherCase(rangee, colonneDepart).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                }
            } else if (rangeeArrive == rangeeDepart) {
                if (colonneArrive > colonneDepart) {
                    for (int colonne = colonneDepart + 1; colonne < colonneArrive; colonne++) {
                        if (super.getEchiquier().chercherCase(rangeeDepart, colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int colonne = colonneDepart - 1; colonne > colonneArrive; colonne--) {
                        if (super.getEchiquier().chercherCase(rangeeDepart, colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                }

            } else {
                int rangee = caseDepart.getRangee();
                int colonne = caseDepart.getColonne();

                if (rangee < rangeeArrive && colonne < colonneArrive) {
                    while (colonne < 7 && rangee < 7) {
                        rangee++;
                        colonne++;
                        if (rangee == rangeeArrive && colonne == colonneArrive) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee > rangeeArrive && colonne > colonneArrive) {
                    while (colonne > 0 && rangee > 0) {
                        rangee--;
                        colonne--;
                        if (rangee == rangeeArrive && colonne == colonneArrive) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee > rangeeArrive && colonne < colonneArrive) {
                    while (colonne < 7 && rangee > 0) {
                        rangee--;
                        colonne++;
                        if (rangee == rangeeArrive && colonne == colonneArrive) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee < rangeeArrive && colonne > colonneArrive) {
                    while (colonne > 0 && rangee < 7) {
                        rangee++;
                        colonne--;
                        if (rangee == rangeeArrive && colonne == colonneArrive) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
}
