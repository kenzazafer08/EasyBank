package interfaces;

import dto.Affectation;
import java.util.List;

public interface AffectationI {
    Affectation createNewAffectation(Affectation a);
    boolean deleteAffectation(int id);
    List<Affectation> affectationsList();
}
