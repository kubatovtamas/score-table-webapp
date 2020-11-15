package com.csapatsportok.application.repository;

import com.csapatsportok.application.info.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableInfoRepository {

    final private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public TableInfoRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<TableInfo> getTableInfoByLeagueName(String name) {
        String sql = """
                SELECT
                    TEAM,
                    COUNT(TEAM) AS P,
                    SUM(GOALS_FOR) AS GF,
                    SUM(GOALS_AGAINST) AS GA,
                    SUM(GOAL_DIFFERENCE) AS GD,
                    SUM(POINTS) AS PTS
                FROM (
                    SELECT
                        HOME_TEAM AS TEAM,
                        HOME_POINTS AS POINTS,
                        HOME_GOALS_FOR AS GOALS_FOR,
                        HOME_GOALS_AGAINST AS GOALS_AGAINST,
                        (HOME_GOALS_FOR - HOME_GOALS_AGAINST) AS GOAL_DIFFERENCE
                    FROM (
                        SELECT
                            home.name AS HOME_TEAM,
                            CASE
                                WHEN G.NUM_HOME_GOALS > G.NUM_AWAY_GOALS THEN 3
                                WHEN G.NUM_HOME_GOALS < G.NUM_AWAY_GOALS THEN 0
                                ELSE 1
                            END AS HOME_POINTS,
                            G.NUM_HOME_GOALS AS HOME_GOALS_FOR,
                            G.NUM_AWAY_GOALS AS HOME_GOALS_AGAINST
                        FROM GAME AS G
                        LEFT JOIN team AS AWAY
                            ON away.id = G.away_team_id
                        LEFT JOIN team AS HOME
                            ON home.id = G.home_team_id
                        WHERE HOME.id in (
                                SELECT T.id
                                FROM team AS T
                                WHERE T.league_id = (
                                    SELECT L.id
                                    FROM LEAGUE AS L
                                    WHERE L.name LIKE :name
                                )
                        )
                    ) as HOME_RESULTS
                              
                    UNION ALL
                              
                    SELECT
                        AWAY_TEAM AS TEAM,
                        AWAY_POINTS AS POINTS,
                        AWAY_GOALS_FOR AS GOALS_FOR,
                        AWAY_GOALS_AGAINST AS GOALS_AGAINST,
                        (AWAY_GOALS_FOR - AWAY_GOALS_AGAINST) AS GOAL_DIFFERENCE
                    FROM (
                        SELECT
                            away.name AS AWAY_TEAM,
                            CASE
                                WHEN G.NUM_HOME_GOALS < G.NUM_AWAY_GOALS THEN 3
                                WHEN G.NUM_HOME_GOALS > G.NUM_AWAY_GOALS THEN 0
                                ELSE 1
                            END AS AWAY_POINTS,
                            G.NUM_AWAY_GOALS AS AWAY_GOALS_FOR,
                            G.NUM_HOME_GOALS AS AWAY_GOALS_AGAINST
                        FROM GAME AS G
                        LEFT JOIN team AS AWAY
                            ON away.id = G.away_team_id
                        LEFT JOIN team AS HOME
                            ON home.id = G.home_team_id
                        WHERE HOME.id in (
                                SELECT T.id
                                FROM team AS T
                                WHERE T.league_id = (
                                    SELECT L.id
                                    FROM LEAGUE AS L
                                    WHERE L.name LIKE :name
                                )
                        )
                    ) AS AWAY_RESULTS
                ) AS TOTAL_RESULTS
                GROUP BY TEAM
                ORDER BY PTS, GD DESC;
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        return jdbc.query(sql, namedParameters, (resultSet, i) -> new TableInfo(
                resultSet.getString("TEAM"),
                resultSet.getInt("P"),
                resultSet.getInt("GF"),
                resultSet.getInt("GA"),
                resultSet.getInt("GD"),
                resultSet.getInt("PTS")
                )
        );
    }
}
