create table country (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table game (id bigint not null auto_increment, date datetime(6) not null, num_away_goals integer not null, num_home_goals integer not null, away_team_id bigint not null, home_team_id bigint not null, primary key (id)) engine=InnoDB
create table goal (id bigint not null auto_increment, game_id bigint not null, player_id bigint not null, team_id bigint not null, primary key (id)) engine=InnoDB
create table league (id bigint not null auto_increment, name varchar(255) not null, country_id bigint not null, primary key (id)) engine=InnoDB
create table player (id bigint not null auto_increment, name varchar(255), team_id bigint, primary key (id)) engine=InnoDB
create table team (id bigint not null auto_increment, name varchar(255) not null, league_id bigint not null, primary key (id)) engine=InnoDB
alter table country add constraint UK_llidyp77h6xkeokpbmoy710d4 unique (name)
alter table game add constraint UK5s43nppb2bv3x3pop2db39tre unique (away_team_id, home_team_id)
alter table league add constraint UK_n8qcbpi2pjf8bbenfm9le3v36 unique (name)
alter table team add constraint UK_g2l9qqsoeuynt4r5ofdt1x2td unique (name)
alter table game add constraint FKaowpughmnfa6ifikwfy6wf4g2 foreign key (away_team_id) references team (id) on delete cascade
alter table game add constraint FK51yuyu3ukyq5e8nsvywbof2ws foreign key (home_team_id) references team (id) on delete cascade
alter table goal add constraint FK3xcybek61cdmo4enyyyten6l6 foreign key (game_id) references game (id) on delete cascade
alter table goal add constraint FKnxibcbjg2e3dqfo4l77bvp8cc foreign key (player_id) references player (id) on delete cascade
alter table goal add constraint FK5h3n8a7x2jfhrcqom6c3xtduf foreign key (team_id) references team (id) on delete cascade
alter table league add constraint FKtdt2rqg50rlqti7yubvppq25e foreign key (country_id) references country (id) on delete cascade
alter table player add constraint FKdvd6ljes11r44igawmpm1mc5s foreign key (team_id) references team (id)
alter table team add constraint FK9rk8716asfr76xkn99aa3uvp foreign key (league_id) references league (id) on delete cascade
create table country (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table game (id bigint not null auto_increment, date datetime(6) not null, num_away_goals integer not null, num_home_goals integer not null, away_team_id bigint not null, home_team_id bigint not null, primary key (id)) engine=InnoDB
create table goal (id bigint not null auto_increment, game_id bigint not null, player_id bigint not null, team_id bigint not null, primary key (id)) engine=InnoDB
create table league (id bigint not null auto_increment, name varchar(255) not null, country_id bigint not null, primary key (id)) engine=InnoDB
create table player (id bigint not null auto_increment, name varchar(255), team_id bigint, primary key (id)) engine=InnoDB
create table team (id bigint not null auto_increment, name varchar(255) not null, league_id bigint not null, primary key (id)) engine=InnoDB
alter table country add constraint UK_llidyp77h6xkeokpbmoy710d4 unique (name)
alter table game add constraint UK5s43nppb2bv3x3pop2db39tre unique (away_team_id, home_team_id)
alter table league add constraint UK_n8qcbpi2pjf8bbenfm9le3v36 unique (name)
alter table team add constraint UK_g2l9qqsoeuynt4r5ofdt1x2td unique (name)
alter table game add constraint FKaowpughmnfa6ifikwfy6wf4g2 foreign key (away_team_id) references team (id) on delete cascade
alter table game add constraint FK51yuyu3ukyq5e8nsvywbof2ws foreign key (home_team_id) references team (id) on delete cascade
alter table goal add constraint FK3xcybek61cdmo4enyyyten6l6 foreign key (game_id) references game (id) on delete cascade
alter table goal add constraint FKnxibcbjg2e3dqfo4l77bvp8cc foreign key (player_id) references player (id) on delete cascade
alter table goal add constraint FK5h3n8a7x2jfhrcqom6c3xtduf foreign key (team_id) references team (id) on delete cascade
alter table league add constraint FKtdt2rqg50rlqti7yubvppq25e foreign key (country_id) references country (id) on delete cascade
alter table player add constraint FKdvd6ljes11r44igawmpm1mc5s foreign key (team_id) references team (id)
alter table team add constraint FK9rk8716asfr76xkn99aa3uvp foreign key (league_id) references league (id) on delete cascade
