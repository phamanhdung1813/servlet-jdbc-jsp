INSERT INTO role(role,code) values('ADMIN','ADMIN');
INSERT INTO role(role,code) values('USER','USER');

INSERT INTO user(username,password,fullname,status, roleid) values('admin','admin','admin',1,1);
INSERT INTO user(username,password,fullname,status, roleid) values('abc','abc','ABC',1,2);
INSERT INTO user(username,password,fullname,status, roleid) values('def','def','DEF',0,2);