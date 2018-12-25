ALTER TABLE task ADD email VARCHAR(200);
alter table job_timertask add remark varchar(200);
ALTER TABLE  case_result ADD updateTime DATETIME;
ALTER TABLE  case_result ADD exeTime DOUBLE;
ALTER TABLE interface_result    ADD exeTime DOUBLE;
ALTER TABLE  case_result ADD successRate DOUBLE;
ALTER TABLE  job_timertaskconfig ADD endtime DATETIME;


ALTER TABLE  case_result ADD UUID VARCHAR(100);
ALTER TABLE case_result ADD UNIQUE (
UUID
);

ALTER TABLE  interface_result ADD UUID VARCHAR(100);

ALTER TABLE interface_result ADD UNIQUE (
UUID
);

ALTER TABLE  interface_result ADD updateTime DATETIME;

ALTER TABLE  task_result ADD remark VARCHAR(500);
ALTER TABLE   case_result ADD remark VARCHAR(500);



ALTER TABLE  interface  ADD preconditionCheckPoint VARCHAR(1000);
ALTER TABLE  interface_result  ADD preconditionCheckPoint VARCHAR(1000);
ALTER TABLE interface MODIFY COLUMN url VARCHAR(500);
ALTER TABLE interface_collection MODIFY COLUMN url VARCHAR(500);
ALTER TABLE interface_history MODIFY COLUMN url VARCHAR(500);
ALTER TABLE interface_result MODIFY COLUMN url VARCHAR(500);



ALTER TABLE interface ADD    sysId VARCHAR(30)   COMMENT '系统编号' ;
ALTER TABLE interface ADD    sysName VARCHAR(30)   COMMENT '系统名字' ;


ALTER TABLE interface_result ADD    sysId VARCHAR(30)  COMMENT '系统编号' ;
ALTER TABLE interface_result ADD    sysName  VARCHAR(30)   COMMENT '系统名字' ;
