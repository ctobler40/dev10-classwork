package learn.field_agent.models;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Mission
{
    private int missionId;
    private String codeName;
    private String notes;
    private LocalDate startDate;
    private LocalDate endDate_Projected;
    private LocalDate endDate_Actual;
    private BigDecimal operationalCost;
    private Agency agency;

    public Mission() {

    }

    public Mission(int missionId, String codeName, String notes, LocalDate startDate, LocalDate endDate_Projected, LocalDate endDate_Actual, BigDecimal operationalCost, Agency agency) {
        this.missionId = missionId;
        this.codeName = codeName;
        this.notes = notes;
        this.startDate = startDate;
        this.endDate_Projected = endDate_Projected;
        this.endDate_Actual = endDate_Actual;
        this.operationalCost = operationalCost;
        this.agency = agency;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate_Projected() {
        return endDate_Projected;
    }

    public void setEndDate_Projected(LocalDate endDate_Projected) {
        this.endDate_Projected = endDate_Projected;
    }

    public LocalDate getEndDate_Actual() {
        return endDate_Actual;
    }

    public void setEndDate_Actual(LocalDate endDate_Actual) {
        this.endDate_Actual = endDate_Actual;
    }

    public BigDecimal getOperationalCost() {
        return operationalCost;
    }

    public void setOperationalCost(BigDecimal operationalCost) {
        this.operationalCost = operationalCost;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return missionId == mission.missionId && Objects.equals(codeName, mission.codeName) && Objects.equals(notes, mission.notes) && Objects.equals(startDate, mission.startDate) && Objects.equals(endDate_Projected, mission.endDate_Projected) && Objects.equals(endDate_Actual, mission.endDate_Actual) && Objects.equals(operationalCost, mission.operationalCost) && Objects.equals(agency, mission.agency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, codeName, notes, startDate, endDate_Projected, endDate_Actual, operationalCost, agency);
    }
}
