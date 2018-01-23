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
            if (caseArrive.getColonne() == caseDepart.getColonne()) {
                if (caseArrive.getRangee() > caseDepart.getRangee()) {
                    for (int rangee = caseDepart.getRangee() + 1; rangee < caseArrive.getRangee(); rangee++) {
                        if (super.getEchiquier().chercherCase(rangee, caseDepart.getColonne()).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int rangee = caseDepart.getRangee() - 1; rangee > caseArrive.getRangee(); rangee--) {
                        if (super.getEchiquier().chercherCase(rangee, caseDepart.getColonne()).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                }
            } else if (caseArrive.getRangee() == caseDepart.getRangee()) {
                if (caseArrive.getColonne() > caseDepart.getColonne()) {
                    for (int colonne = caseDepart.getColonne() + 1; colonne < caseArrive.getColonne(); colonne++) {
                        if (super.getEchiquier().chercherCase(caseDepart.getRangee(), colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int colonne = caseDepart.getColonne() - 1; colonne > caseArrive.getColonne(); colonne--) {
                        if (super.getEchiquier().chercherCase(caseDepart.getRangee(), colonne).estOccupe()) {
                            return false;
                        }
                    }
                    return true;
                }

            } else {
                int rangee = caseDepart.getRangee();
                int colonne = caseDepart.getColonne();
                if (rangee < caseArrive.getRangee() && colonne < caseArrive.getColonne()) {
                    while (colonne <= 8 && rangee <= 8) {
                        rangee++;
                        colonne++;
                        if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee > caseArrive.getRangee() && colonne > caseArrive.getColonne()) {
                    while (colonne <= 8 && rangee <= 8) {
                        rangee--;
                        colonne--;
                        if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee > caseArrive.getRangee() && colonne < caseArrive.getColonne()) {
                    while (colonne <= 8 && rangee <= 8) {
                        rangee--;
                        colonne++;
                        if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                } else if (rangee < caseArrive.getRangee() && colonne > caseArrive.getColonne()) {
                    while (colonne <= 8 && rangee <= 8) {
                        rangee++;
                        colonne--;
                        if (rangee == caseArrive.getRangee() && colonne == caseArrive.getColonne()) {
                            return true;
                        } else if (super.getEchiquier().chercherCase(rangee, colonne).estOccupe()) {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
