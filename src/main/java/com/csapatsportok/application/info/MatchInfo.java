package com.csapatsportok.application.info;

import java.sql.Date;

public class MatchInfo {
    private String homeTeamName;
    private String awayTeamName;
    private String score;
    private Date date;


    public MatchInfo(String homeTeamName, String awayTeamName, String score, Date date) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.score = score;
        this.date = date;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }

    public String getScore() {
        return this.score;
    }

    public Date getDate() {
        return this.date;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MatchInfo)) return false;
        final MatchInfo other = (MatchInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$homeTeamName = this.getHomeTeamName();
        final Object other$homeTeamName = other.getHomeTeamName();
        if (this$homeTeamName == null ? other$homeTeamName != null : !this$homeTeamName.equals(other$homeTeamName))
            return false;
        final Object this$awayTeamName = this.getAwayTeamName();
        final Object other$awayTeamName = other.getAwayTeamName();
        if (this$awayTeamName == null ? other$awayTeamName != null : !this$awayTeamName.equals(other$awayTeamName))
            return false;
        final Object this$score = this.getScore();
        final Object other$score = other.getScore();
        if (this$score == null ? other$score != null : !this$score.equals(other$score)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MatchInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $homeTeamName = this.getHomeTeamName();
        result = result * PRIME + ($homeTeamName == null ? 43 : $homeTeamName.hashCode());
        final Object $awayTeamName = this.getAwayTeamName();
        result = result * PRIME + ($awayTeamName == null ? 43 : $awayTeamName.hashCode());
        final Object $score = this.getScore();
        result = result * PRIME + ($score == null ? 43 : $score.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        return result;
    }

    public String toString() {
        return "MatchInfo(homeTeamName=" + this.getHomeTeamName() + ", awayTeamName=" + this.getAwayTeamName() + ", score=" + this.getScore() + ", date=" + this.getDate() + ")";
    }
}
