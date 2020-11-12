package com.csapatsportok.application.info;

public class TableInfo {
    private String teamName;
    private int played;
    private int goalsFor;
    private int goalsAgainst;
    private int goalsGoalDifference;
    private int points;


    public TableInfo(String teamName, int played, int goalsFor, int goalsAgainst, int goalsGoalDifference, int points) {
        this.teamName = teamName;
        this.played = played;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalsGoalDifference = goalsGoalDifference;
        this.points = points;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getPlayed() {
        return this.played;
    }

    public int getGoalsFor() {
        return this.goalsFor;
    }

    public int getGoalsAgainst() {
        return this.goalsAgainst;
    }

    public int getGoalsGoalDifference() {
        return this.goalsGoalDifference;
    }

    public int getPoints() {
        return this.points;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setGoalsGoalDifference(int goalsGoalDifference) {
        this.goalsGoalDifference = goalsGoalDifference;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TableInfo)) return false;
        final TableInfo other = (TableInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$teamName = this.getTeamName();
        final Object other$teamName = other.getTeamName();
        if (this$teamName == null ? other$teamName != null : !this$teamName.equals(other$teamName)) return false;
        if (this.getPlayed() != other.getPlayed()) return false;
        if (this.getGoalsFor() != other.getGoalsFor()) return false;
        if (this.getGoalsAgainst() != other.getGoalsAgainst()) return false;
        if (this.getGoalsGoalDifference() != other.getGoalsGoalDifference()) return false;
        if (this.getPoints() != other.getPoints()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TableInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $teamName = this.getTeamName();
        result = result * PRIME + ($teamName == null ? 43 : $teamName.hashCode());
        result = result * PRIME + this.getPlayed();
        result = result * PRIME + this.getGoalsFor();
        result = result * PRIME + this.getGoalsAgainst();
        result = result * PRIME + this.getGoalsGoalDifference();
        result = result * PRIME + this.getPoints();
        return result;
    }

    public String toString() {
        return "Table(teamName=" + this.getTeamName() + ", played=" + this.getPlayed() + ", goalsFor=" + this.getGoalsFor() + ", goalsAgainst=" + this.getGoalsAgainst() + ", goalsGoalDifference=" + this.getGoalsGoalDifference() + ", points=" + this.getPoints() + ")";
    }
}
