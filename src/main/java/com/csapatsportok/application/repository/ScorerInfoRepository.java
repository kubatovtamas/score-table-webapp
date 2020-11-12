package com.csapatsportok.application.repository;

import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.info.ScorerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScorerInfoRepository {
    final private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public ScorerInfoRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ScorerInfo> getScorerInfoByLeagueName(String name) {
        String sql = """
                SELECT
                    PLAYER.NAME AS PLAYER_NAME,
                    TEAM.NAME AS TEAM_NAME,
                    COUNT(*) AS NUM_GOALS
                FROM GOAL
                LEFT JOIN PLAYER
                    ON GOAL.PLAYER_ID = PLAYER.ID
                LEFT JOIN TEAM
                    ON TEAM.ID = PLAYER.TEAM_ID
                LEFT JOIN LEAGUE
                    ON LEAGUE.ID = TEAM.LEAGUE_ID
                WHERE
                    LEAGUE.ID = (
                        SELECT L.id
                        FROM LEAGUE AS L
                        WHERE L.name LIKE :name
                    )
                GROUP BY player_id
                ORDER BY NUM_GOALS DESC
                LIMIT 10;
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        return jdbc.query(sql, namedParameters, (resultSet, i) -> new ScorerInfo(
                        resultSet.getString("PLAYER_NAME"),
                        resultSet.getString("TEAM_NAME"),
                        resultSet.getInt("NUM_GOALS")
                )
        );
    }
}
