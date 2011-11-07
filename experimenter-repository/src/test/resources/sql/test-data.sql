
-- ---
-- Test Data
-- ---

INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (1, 'Tester1','Exists','tester1','heslo','tester1@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (2, 'Tester2','Delete','tester2','heslo','tester2@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (3, 'Tester3','Update','tester3','heslo','tester3@experimenter.org');

INSERT INTO USERGROUP (usergroup_id,name) VALUES (1,'students');
INSERT INTO USERGROUP (usergroup_id,name) VALUES (2,'teachers');

INSERT INTO COMPUTER (computer_id,address,description) VALUES (1,'u-pl20','computer u-l20, test exists');
INSERT INTO COMPUTER (computer_id,address,description) VALUES (2,'u-pl21','computer u-l21, test delete');
INSERT INTO COMPUTER (computer_id,address,description) VALUES (3,'u-pl22','computer u-l22');

INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (1,'testFarm1','farm to test find',1);
INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (2,'testFarm2','farm to test delete',1);

INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (1,'myConn1exists','test','test123','my test connection 1',1,221,1);
INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (2,'myConn2delete','comp','comp123','my test connection 2',2,221,1);

-- INSERT INTO USER_USERGROUP (USER_USERGROUP_id,user_id,usergroup_id) VALUES
-- ('','','');

INSERT INTO PROBLEM (problem_id,name,description) VALUES (1,'3-SAT','you know what it means');
INSERT INTO PROBLEM (problem_id,name,description) VALUES (2,'4-SAT','yeah, right...');

INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (1,'testProject1','first project',1,1);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (2,'testProject2','project to test delete',2,2);

INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (1, 'solver1','program to test find','solver1.sh run','1');
INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (2, 'solver2','program to test delete','solver2.sh run','1');

INSERT INTO APPLICATION (application_id,version_number,executable,program_id) VALUES (1,'1.3','solver1_1.3.sh run','1');
INSERT INTO APPLICATION (application_id,version_number,executable,program_id) VALUES (2,'1.4','solver1_1.4.sh run','1');

INSERT INTO INPUT (input_id,name,data,problem_id) VALUES (1,'testInput1','data1',1);
INSERT INTO INPUT (input_id,name,data,problem_id) VALUES (2,'testInput2','data2', 1);
INSERT INTO INPUT (input_id,name,data,problem_id) VALUES (3,'testInput3','data3', 2);

INSERT INTO EXPERIMENT (experiment_id,name,description,project_id,application_id) VALUES (1,'experiment1','experiment to test find',1,1);
INSERT INTO EXPERIMENT (experiment_id,name,description,project_id,application_id) VALUES (2,'experiment2','experiment to test delete',1,2);

-- INSERT INTO EXPERIMENT_INPUT_SET (experiment_input_set_id,input_set_id,experiment_id) VALUES
-- ('','','');
-- INSERT INTO PROJECT_INPUT_SET (project_input_id,input_set_id,project_id) VALUES
-- ('','','');

INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (1,'testInputSet1','basic set of inputs', 1);
INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (2,'testInputSet2','set to test delete', 2);

-- INSERT INTO INPUT_INPUT_SET (input_input_set_id,input_id,input_set_id,problem_id) VALUES
-- ('','','','');
-- INSERT INTO EXPERIMENT_FARM (experiment_farm_id,experiment_id,farm_id) VALUES
-- ('','','');
