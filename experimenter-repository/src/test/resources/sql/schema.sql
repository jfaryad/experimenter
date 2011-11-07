
-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'USER'
-- 
-- ---

CREATE SEQUENCE SEQ_USER AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_USERGROUP AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_COMPUTER AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_CONNECTION AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_EXPERIMENT AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PROJECT AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_INPUT AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_INPUT_SET AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_APPLICATION AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PROGRAM AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PROBLEM AS INTEGER START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE SEQ_FARM AS INTEGER START WITH 100 INCREMENT BY 1;

DROP TABLE IF EXISTS USER;
		
CREATE TABLE USER (
  user_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  surname VARCHAR(60) NOT NULL,
  login VARCHAR(60) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(60) DEFAULT NULL,
);

-- ---
-- Table 'COMPUTER'
-- 
-- ---

DROP TABLE IF EXISTS COMPUTER;
		
CREATE TABLE COMPUTER (
  computer_id INTEGER NOT NULL PRIMARY KEY,
  address VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
);

-- ---
-- Table 'CONNECTION'
-- 
-- ---

DROP TABLE IF EXISTS CONNECTION;
		
CREATE TABLE CONNECTION (
  connection_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  login VARCHAR(60) NOT NULL,
  password VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  computer_id INTEGER NOT NULL,
  port INTEGER DEFAULT NULL,
  farm_id INTEGER NOT NULL,
);

-- ---
-- Table 'GROUP'
-- 
-- ---

DROP TABLE IF EXISTS USERGROUP;
		
CREATE TABLE USERGROUP (
  usergroup_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
);

-- ---
-- Table 'USER_USERGROUP'
-- 
-- ---

DROP TABLE IF EXISTS USER_USERGROUP;
		
CREATE TABLE USER_USERGROUP (
  user_id INTEGER NOT NULL,
  usergroup_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, usergroup_id),
);

-- ---
-- Table 'FARM'
-- 
-- ---

DROP TABLE IF EXISTS FARM;
		
CREATE TABLE FARM (
  farm_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  usergroup_id INTEGER NOT NULL,
);

-- ---
-- Table 'PROGRAM'
-- 
-- ---

DROP TABLE IF EXISTS PROGRAM;
		
CREATE TABLE PROGRAM (
  program_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) DEFAULT NULL,
  description VARCHAR(100) DEFAULT NULL,
  command VARCHAR(60) NOT NULL,
  project_id INTEGER NOT NULL,
);

-- ---
-- Table 'APPLICATION'
-- 
-- ---

DROP TABLE IF EXISTS APPLICATION;
		
CREATE TABLE APPLICATION (
  application_id INTEGER NOT NULL PRIMARY KEY,
  version_number VARCHAR(60) NOT NULL,
  executable VARCHAR(255) NOT NULL,
  program_id INTEGER NOT NULL,
);

-- ---
-- Table 'PROBLEM'
-- 
-- ---

DROP TABLE IF EXISTS PROBLEM;
		
CREATE TABLE PROBLEM (
  problem_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
);

-- ---
-- Table 'INPUT'
-- 
-- ---

DROP TABLE IF EXISTS INPUT;
		
CREATE TABLE INPUT (
  input_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  data VARCHAR(255) NOT NULL,
  problem_id INTEGER NOT NULL,
);

-- ---
-- Table 'EXPERIMENT'
-- 
-- ---

DROP TABLE IF EXISTS EXPERIMENT;
		
CREATE TABLE EXPERIMENT (
  experiment_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  project_id INTEGER NOT NULL,
  application_id INTEGER NOT NULL,
);

-- ---
-- Table 'PROJECT'
-- 
-- ---

DROP TABLE IF EXISTS PROJECT;
		
CREATE TABLE PROJECT (
  project_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  problem_id INTEGER NOT NULL,
  usergroup_id INTEGER NOT NULL,
);

-- ---
-- Table 'EXPERIMENT_INPUT_SET'
-- 
-- ---

DROP TABLE IF EXISTS EXPERIMENT_INPUT_SET;
		
CREATE TABLE EXPERIMENT_INPUT_SET (
  input_set_id INTEGER NOT NULL,
  experiment_id INTEGER NOT NULL,
  PRIMARY KEY(input_set_id, experiment_id),
);

-- ---
-- Table 'PROJECT_INPUT_SET'
-- 
-- ---

DROP TABLE IF EXISTS PROJECT_INPUT_SET;
		
CREATE TABLE PROJECT_INPUT_SET (
  input_set_id INTEGER NOT NULL,
  project_id INTEGER NOT NULL,
  PRIMARY KEY(input_set_id, project_id),
);

-- ---
-- Table 'INPUT_SET'
-- 
-- ---

DROP TABLE IF EXISTS INPUT_SET;
		
CREATE TABLE INPUT_SET (
  input_set_id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  description VARCHAR(100) DEFAULT NULL,
  problem_id INTEGER NOT NULL,
);

-- ---
-- Table 'INPUT_INPUT_SET'
-- 
-- ---

DROP TABLE IF EXISTS INPUT_INPUT_SET;
		
CREATE TABLE INPUT_INPUT_SET (
  input_id INTEGER NOT NULL,
  input_set_id INTEGER NOT NULL,
  PRIMARY KEY(input_id, input_set_id),
);

-- ---
-- Table 'EXPERIMENT_FARM'
-- 
-- ---

DROP TABLE IF EXISTS EXPERIMENT_FARM;
		
CREATE TABLE EXPERIMENT_FARM (
  experiment_id INTEGER NOT NULL,
  farm_id INTEGER NOT NULL,
  PRIMARY KEY(experiment_id, farm_id)
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE CONNECTION ADD FOREIGN KEY (computer_id) REFERENCES COMPUTER (computer_id);
ALTER TABLE CONNECTION ADD FOREIGN KEY (farm_id) REFERENCES FARM (farm_id);
ALTER TABLE USER_USERGROUP ADD FOREIGN KEY (user_id) REFERENCES USER (user_id);
ALTER TABLE USER_USERGROUP ADD FOREIGN KEY (usergroup_id) REFERENCES USERGROUP (usergroup_id);
ALTER TABLE FARM ADD FOREIGN KEY (usergroup_id) REFERENCES USERGROUP (usergroup_id);
ALTER TABLE PROGRAM ADD FOREIGN KEY (project_id) REFERENCES PROJECT (project_id);
ALTER TABLE APPLICATION ADD FOREIGN KEY (program_id) REFERENCES PROGRAM (program_id);
ALTER TABLE EXPERIMENT ADD FOREIGN KEY (project_id) REFERENCES PROJECT (project_id);
ALTER TABLE EXPERIMENT ADD FOREIGN KEY (application_id) REFERENCES APPLICATION (application_id);
ALTER TABLE PROJECT ADD FOREIGN KEY (problem_id) REFERENCES PROBLEM (problem_id);
ALTER TABLE PROJECT ADD FOREIGN KEY (usergroup_id) REFERENCES USERGROUP (usergroup_id);
ALTER TABLE EXPERIMENT_INPUT_SET ADD FOREIGN KEY (input_set_id) REFERENCES INPUT_SET (input_set_id);
ALTER TABLE EXPERIMENT_INPUT_SET ADD FOREIGN KEY (experiment_id) REFERENCES EXPERIMENT (experiment_id);
ALTER TABLE PROJECT_INPUT_SET ADD FOREIGN KEY (input_set_id) REFERENCES INPUT_SET (input_set_id);
ALTER TABLE PROJECT_INPUT_SET ADD FOREIGN KEY (project_id) REFERENCES PROJECT (project_id);
ALTER TABLE INPUT_INPUT_SET ADD FOREIGN KEY (input_id) REFERENCES INPUT (input_id);
ALTER TABLE INPUT_INPUT_SET ADD FOREIGN KEY (input_set_id) REFERENCES INPUT_SET (input_set_id);
ALTER TABLE INPUT ADD FOREIGN KEY (problem_id) REFERENCES PROBLEM (problem_id);
ALTER TABLE INPUT_SET ADD FOREIGN KEY (problem_id) REFERENCES PROBLEM (problem_id);
ALTER TABLE EXPERIMENT_FARM ADD FOREIGN KEY (experiment_id) REFERENCES EXPERIMENT (experiment_id);
ALTER TABLE EXPERIMENT_FARM ADD FOREIGN KEY (farm_id) REFERENCES FARM (farm_id);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE USER ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE COMPUTER ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE CONNECTION ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE USERGROUP ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE USER_USERGROUP ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE FARM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE PROGRAM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE APPLICATION ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE PROBLEM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE INPUT ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE EXPERIMENT ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE PROJECT ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE EXPERIMENT_INPUT_SET ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE PROJECT_INPUT_SET ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE INPUT_SET ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE INPUT_INPUT_SET ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE EXPERIMENT_FARM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO USER (user_id,name,surname,login,password,email) VALUES
-- ('','','','','','');
-- INSERT INTO COMPUTER (computer_id,address,description) VALUES
-- ('','','');
-- INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES
-- ('','','','','','','','');
-- INSERT INTO USERGROUP (group_id,name) VALUES
-- ('','');
-- INSERT INTO USER_USERGROUP (USER_USERGROUP_id,user_id,group_id) VALUES
-- ('','','');
-- INSERT INTO FARM (farm_id,name,description,group_id) VALUES
-- ('','','','');
-- INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES
-- ('','','','','');
-- INSERT INTO APPLICATION (application_id,version_number,executable,program_id) VALUES
-- ('','','','');
-- INSERT INTO PROBLEM (problem_id,name,description) VALUES
-- ('','','');
-- INSERT INTO INPUT (input_id,name,data) VALUES
-- ('','','');
-- INSERT INTO EXPERIMENT (experiment_id,name,description,project_id,application_id) VALUES
-- ('','','','','');
-- INSERT INTO PROJECT (project_id,name,description,problem_id,group_id) VALUES
-- ('','','','','');
-- INSERT INTO EXPERIMENT_INPUT_SET (experiment_input_set_id,input_set_id,experiment_id) VALUES
-- ('','','');
-- INSERT INTO PROJECT_INPUT_SET (project_input_id,input_set_id,project_id) VALUES
-- ('','','');
-- INSERT INTO INPUT_SET (input_set_id,name,description) VALUES
-- ('','','');
-- INSERT INTO INPUT_INPUT_SET (input_input_set_id,input_id,input_set_id,problem_id) VALUES
-- ('','','','');
-- INSERT INTO EXPERIMENT_FARM (experiment_farm_id,experiment_id,farm_id) VALUES
-- ('','','');
