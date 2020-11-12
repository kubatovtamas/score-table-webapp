package com.csapatsportok.application.repository;

import com.csapatsportok.application.info.GoalDifferenceInfo;
import com.csapatsportok.application.info.ScorerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalDifferenceInfoRepository {
    final private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public GoalDifferenceInfoRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<GoalDifferenceInfo> getGoalDifferenceInfoByCountryName(String name) {
        String sql = """
                SELECT
                    L.NAME AS LEAGUE_NAME,
                    HOME.NAME AS HOME_TEAM,
                    AWAY.NAME AS AWAY_TEAM,
                    CONCAT(G.NUM_HOME_GOALS, '-', G.NUM_AWAY_GOALS) AS SCORE,
                    ABS(G.NUM_AWAY_GOALS - G.NUM_HOME_GOALS)  AS DIFFERENCE,
                    G.DATE AS DATE
                FROM GAME AS G
                LEFT JOIN TEAM AS AWAY
                    ON away.id = G.away_team_id
                LEFT JOIN TEAM AS HOME
                    ON home.id = G.home_team_id
                LEFT JOIN LEAGUE AS L
                    ON home.league_id = L.id
                WHERE L.country_id = (
                    SELECT C.id
                    FROM COUNTRY AS C
                    WHERE LOWER(name) LIKE :name
                )
                ORDER BY DIFFERENCE DESC
                LIMIT 10;
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        return jdbc.query(sql, namedParameters, (resultSet, i) -> new GoalDifferenceInfo(
                        resultSet.getString("LEAGUE_NAME"),
                        resultSet.getString("AWAY_TEAM"),
                        resultSet.getString("SCORE"),
                        resultSet.getInt("DIFFERENCE"),
                        resultSet.getDate("DATE")
                )
        );
    }
}
