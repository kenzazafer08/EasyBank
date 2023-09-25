package dto;

import java.util.Date;

public class Affectation {

    private Date startDate;
    private Date endDate;
    private Mission mission;
    private Employee employee;


    public Affectation() {
    }

    public Affectation(Date startDate, Date endDate, Mission mission, Employee employee) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.mission = mission;
        this.employee = employee;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
