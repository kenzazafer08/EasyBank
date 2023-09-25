package dto;

import java.util.List;

public class Mission {

    private String code;
    private String name;
    private String description;
    private List<Affectation> affectationList;
    public Mission() {
    }

    public Mission(String code, String name, String description, List<Affectation> affectationList) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.affectationList = affectationList;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Affectation> getAffectationList() {
        return affectationList;
    }

    public void setAffectationList(List<Affectation> affectationList) {
        this.affectationList = affectationList;
    }
}
