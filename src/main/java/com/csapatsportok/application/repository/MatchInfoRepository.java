package com.csapatsportok.application.repository;

import com.csapatsportok.application.info.MatchInfo;
import com.csapatsportok.application.info.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchInfoRepository {

    final private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public MatchInfoRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<MatchInfo> getMatchinfoByLeagueName(String name) {
        String sql = """
                SELECT
                    home.name AS HOME_TEAM,
                    away.name AS AWAY_TEAM,
                    CONCAT(G.NUM_HOME_GOALS, '-', G.NUM_AWAY_GOALS) AS SCORE,
                    G.date as DATE
                FROM game AS G
                LEFT JOIN team AS AWAY
                    ON away.id = G.away_team_id
                LEFT JOIN team AS HOME
                    ON home.id = G.home_team_id
                WHERE
                    home_team_id IN (
                        SELECT T.id
                        FROM team AS T
                        WHERE T.league_id = (
                            SELECT L.id
                            FROM LEAGUE AS L
                            WHERE L.name LIKE :name
                        )
                    )
                ORDER BY G.date;
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        return jdbc.query(sql, namedParameters, (resultSet, i) -> new MatchInfo(
                        resultSet.getString("HOME_TEAM"),
                        resultSet.getString("AWAY_TEAM"),
                        resultSet.getString("SCORE"),
                        resultSet.getDate("DATE")
                )
        );
    }


}
