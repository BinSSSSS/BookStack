DROP TRIGGER BookUserIdAutoIncr_trigger;
DROP SEQUENCE BookUserSeq;
DROP TABLE BookUsers PURGE;


CREATE SEQUENCE BookUserSeq;


create table BookUsers
(
   userName           VARCHAR2(20)         not null,
   userId             NUMBER               not null,
   account            NUMBER(11)           not null,
   password           VARCHAR2(25)         not null,
   gender             VARCHAR2(10)         default 'Male',
   brithday           DATE                 default TO_DATE('1990/1/1','yyyy/mm/dd'),
   phoneNum           NUMBER(11)           not null,
   homeLand           VARCHAR2(10)         default '中国',
   age                NUMBER(3)            default MONTHS_BETWEEN(SYSDATE，TO_DATE('1990/1/1','yyyy/mm/dd')) / 12,
   PersonalSignature  VARCHAR2(200)       default 'I have all of the world',
   job                VARCHAR2(20),
   email              VARCHAR2(30),
   constraint PK_ACCOUNT primary key (account),
   constraint UK_USERID  unique(userId),
   constraint UK_USERNAME unique(userName)
);

CREATE OR REPLACE TRIGGER BookUserIdAutoIncr_trigger 
BEFORE INSERT ON BookUsers
FOR EACH ROW
BEGIN
  :new.userId := BookUserSeq.nextVal();
END;
/


INSERT INTO BookUsers(userName,account, password,phoneNum) 
VALUES('TD唐登','88888888','88888888',18070500090);

INSERT INTO BookUsers(userName,account, password,phoneNum) 
VALUES('Black','12345678','12345678',17605089995);

INSERT INTO BookUsers(userName,account, password,phoneNum,gender) 
VALUES('Jan','87654321','12345678',1760807213,'Female');

INSERT INTO BookUsers(userName,account, password,phoneNum,gender) 
VALUES('Alice','1395926989','12345678',13467128763,'Female');

COMMIT;
