package com.csapatsportok.application.info;

public class ScorerInfo {
    private String playerName;
    private String teamName;
    private int numGoals;

    public ScorerInfo(String playerName, String teamName, int numGoals) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.numGoals = numGoals;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getNumGoals() {
        return this.numGoals;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ScorerInfo)) return false;
        final ScorerInfo other = (ScorerInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$playerName = this.getPlayerName();
        final Object other$playerName = other.getPlayerName();
        if (this$playerName == null ? other$playerName != null : !this$playerName.equals(other$playerName))
            return false;
        final Object this$teamName = this.getTeamName();
        final Object other$teamName = other.getTeamName();
        if (this$teamName == null ? other$teamName != null : !this$teamName.equals(other$teamName)) return false;
        if (this.getNumGoals() != other.getNumGoals()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScorerInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $playerName = this.getPlayerName();
        result = result * PRIME + ($playerName == null ? 43 : $playerName.hashCode());
        final Object $teamName = this.getTeamName();
        result = result * PRIME + ($teamName == null ? 43 : $teamName.hashCode());
        result = result * PRIME + this.getNumGoals();
        return result;
    }

    public String toString() {
        return "ScorerInfo(playerName=" + this.getPlayerName() + ", teamName=" + this.getTeamName() + ", numGoals=" + this.getNumGoals() + ")";
    }
}
