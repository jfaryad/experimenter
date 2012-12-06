
-- ---
-- Test Data
-- ---

INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (1, 'Tester1','Exists','tester1','heslo','tester1@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (2, 'Tester2','Delete','tester2','heslo','tester2@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (3, 'Tester3','Update','tester3','heslo','tester3@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (4, 'Tester4','AddToGroup','tester4','heslo','tester4@experimenter.org');
INSERT INTO USER (user_id, name,surname,login,password,email) VALUES (5, 'Tester5','RemoveFromGroup','tester5','heslo','tester5@experimenter.org');

INSERT INTO USERGROUP (usergroup_id,name) VALUES (1,'students');
INSERT INTO USERGROUP (usergroup_id,name) VALUES (2,'teachers');
INSERT INTO USERGROUP (usergroup_id,name) VALUES (3,'public');
INSERT INTO USERGROUP (usergroup_id,name) VALUES (4,'delete-group');
INSERT INTO USERGROUP (usergroup_id,name) VALUES (5,'remove-group');

INSERT INTO COMPUTER (computer_id,address,description, running_jobs) VALUES (1,'u-pl20','computer u-pl20, test exists', 3);
INSERT INTO COMPUTER (computer_id,address,description, running_jobs) VALUES (2,'u-pl21','computer u-pl21, test delete', 0);
INSERT INTO COMPUTER (computer_id,address,description, running_jobs) VALUES (3,'u-pl22','computer u-pl22', 2);
INSERT INTO COMPUTER (computer_id,address,description, running_jobs) VALUES (4,'u-pl22','computer u-pl23 has dependent connection', 1);

INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (1,'testFarm1','farm to test find',1);
INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (2,'testFarm2','farm to test delete',1);
INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (3,'testFarm3','farm to test update',3);
INSERT INTO FARM (farm_id,name,description,usergroup_id) VALUES (4,'testFarm4','dependent on user group, has dependent connection',5);


INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (1,'myConn1exists','test','test123','my test connection 1',1,221,1);
INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (2,'myConn2delete','comp','comp123','my test connection 2',3,221,1);
INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (3,'myConn3update','upd','upd123','my test connection 3',1,221,1);
INSERT INTO CONNECTION (connection_id,name,login,password,description,computer_id,port,farm_id) VALUES (4,'myConn4del','upd','upd123','dependent on farm and computer',4,221,4);

INSERT INTO USER_USERGROUP (user_id,usergroup_id) VALUES ('1','1');
INSERT INTO USER_USERGROUP (user_id,usergroup_id) VALUES ('5','5');
INSERT INTO USER_USERGROUP (user_id,usergroup_id) VALUES ('2','4');

INSERT INTO PROBLEM (problem_id,name,description) VALUES (1,'3-SAT','you know what it means');
INSERT INTO PROBLEM (problem_id,name,description) VALUES (2,'4-SAT','yeah, right...');
INSERT INTO PROBLEM (problem_id,name,description) VALUES (3,'testDelete','i will be deleted');
INSERT INTO PROBLEM (problem_id,name,description) VALUES (4,'testDep','i have dependencies');

INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (1,'testProject1','first project',1,1);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (2,'testProject2','project to test delete',2,5);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (3,'testProject3','project to test update',1,1);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (4,'testProject4','project to add input set',2,1);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (5,'testProject5','project to remove input set',2,1);
INSERT INTO PROJECT (project_id,name,description,problem_id,usergroup_id) VALUES (6,'testProject6','dependent on user group and problem',4,5);

INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (1, 'solver1','program to test find','solver1.sh run',1);
INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (2, 'solver2','program to test delete','solver2.sh run',1);
INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (3, 'solver3','program to test update','solver3.sh run',1);
INSERT INTO PROGRAM (program_id,name,description,command,project_id) VALUES (4, 'solver4','dependent on project','solver4sh run',6);

INSERT INTO APPLICATION (application_id,version,executable,program_id) VALUES (1,'1.3','solver1_1.3.sh run',1);
INSERT INTO APPLICATION (application_id,version,executable,program_id) VALUES (2,'1.4','solver1_1.4.sh run',1);
INSERT INTO APPLICATION (application_id,version,executable,program_id) VALUES (3,'1.5','solver1_1.5.sh run',1);
INSERT INTO APPLICATION (application_id,version,executable,program_id) VALUES (4,'1.2','dependent on program',4);

INSERT INTO INPUT (input_id,name,data,checksum, problem_id) VALUES (1,'testInput1','data1','abcd1',1);
INSERT INTO INPUT (input_id,name,data,checksum, problem_id) VALUES (2,'testInput2','data2','abcd2', 1);
INSERT INTO INPUT (input_id,name,data,checksum, problem_id) VALUES (3,'testInput3','data3','abcd3', 2);
INSERT INTO INPUT (input_id,name,data,checksum, problem_id) VALUES (4,'testInput4','dependent on problem','abcd4', 4);

INSERT INTO EXPERIMENT (experiment_id,name,description, cron_expression, scheduled_time, application_id) VALUES (1,'experiment1','experiment to test find','0/10 * * * * ?', '2008-08-08 20:08:00',  1);
INSERT INTO EXPERIMENT (experiment_id,name,description, cron_expression, application_id) VALUES (2,'experiment2','experiment to test delete','0/10 * * * * ?', 3);
INSERT INTO EXPERIMENT (experiment_id,name,description, cron_expression, application_id) VALUES (3,'experiment3','experiment to test update','0/10 * * * * ?', 3);
INSERT INTO EXPERIMENT (experiment_id,name,description, cron_expression, application_id) VALUES (4,'experiment4','experiment to remove input set from','0/10 * * * * ?', 3);
INSERT INTO EXPERIMENT (experiment_id,name,description, cron_expression, application_id) VALUES (5,'experiment5','dependent on application','0/10 * * * * ?', 4);

INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (1,'testInputSet1','basic set of inputs', 1);
INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (2,'testInputSet2','set to test delete', 2);
INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (3,'testInputSet3','set to test update', 2);
INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (4,'testInputSet4','set to add inputs', 2);
INSERT INTO INPUT_SET (input_set_id,name,description,problem_id) VALUES (5,'testInputSet5','dependent on problem', 4);

INSERT INTO EXPERIMENT_INPUT_SET (input_set_id,experiment_id) VALUES (1,1);
INSERT INTO EXPERIMENT_INPUT_SET (input_set_id,experiment_id) VALUES (4,4);

INSERT INTO INPUT_INPUT_SET (input_id,input_set_id) VALUES (1, 1);
INSERT INTO INPUT_INPUT_SET (input_id,input_set_id) VALUES (2, 3);

INSERT INTO PROJECT_INPUT_SET (input_set_id,project_id) VALUES (1, 1);
INSERT INTO PROJECT_INPUT_SET (input_set_id,project_id) VALUES (3, 5);


INSERT INTO EXPERIMENT_FARM (experiment_id,farm_id) VALUES (1, 1);
INSERT INTO EXPERIMENT_FARM (experiment_id,farm_id) VALUES (2, 4);

