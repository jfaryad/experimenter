
-- ---
-- Test Data
-- ---

INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (1, 'Tester','Exists','tester1','heslo','tester1@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (2, 'Tester','Delete','tester1','heslo','tester2@experimenter.org');

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