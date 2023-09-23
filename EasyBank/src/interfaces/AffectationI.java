package interfaces;

import dto.Affectation;

public interface AffectationI {
    Affectation createNewAffectation(Affectation a);
    boolean deleteAffectation(int id);
}
