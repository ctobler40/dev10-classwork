package learn.field_agent.models;

import java.util.Objects;

public class AgentAlias
{
    private int aliasId;
    private String name;
    private String persona;
    private Agent agent;

    public AgentAlias() {

    }

    public AgentAlias(int aliasId, String name, String persona, Agent agent) {
        this.aliasId = aliasId;
        this.name = name;
        this.persona = persona;
        this.agent = agent;
    }

    public int getAliasId() {
        return aliasId;
    }

    public void setAliasId(int aliasId) {
        this.aliasId = aliasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentAlias alias = (AgentAlias) o;
        return aliasId == alias.aliasId && Objects.equals(name, alias.name) && Objects.equals(persona, alias.persona) && Objects.equals(agent, alias.agent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliasId, name, persona, agent);
    }
}
