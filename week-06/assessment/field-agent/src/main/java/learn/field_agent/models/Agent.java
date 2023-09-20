package learn.field_agent.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agent {

    private int agentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private int heightInInches;
    private List<AgentAgency> agencies = new ArrayList<>();
    private List<AgentAlias> aliases = new ArrayList<>();   // Aliases are attached to an agent.

    public Agent() {

    }

    public Agent(int agentId, String firstName, String middleName, String lastName, LocalDate dob, int heightInInches, List<AgentAgency> agencies, List<AgentAlias> aliases) {
        this.agentId = agentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.heightInInches = heightInInches;
        this.agencies = agencies;
        this.aliases = aliases;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public List<AgentAgency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public void setAgencies(List<AgentAgency> agencies) {
        this.agencies = agencies;
    }

    public List<AgentAlias> getAliases() {
        return aliases;
    }

    public void setAliases(List<AgentAlias> aliases) {
        this.aliases = aliases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return agentId == agent.agentId && heightInInches == agent.heightInInches && Objects.equals(firstName, agent.firstName) && Objects.equals(middleName, agent.middleName) && Objects.equals(lastName, agent.lastName) && Objects.equals(dob, agent.dob) && Objects.equals(agencies, agent.agencies) && Objects.equals(aliases, agent.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, firstName, middleName, lastName, dob, heightInInches, agencies, aliases);
    }
}
