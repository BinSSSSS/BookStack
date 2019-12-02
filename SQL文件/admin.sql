DROP TABLE ADMINS PURGE;

DROP SEQUENCE admin_id_seq;

DROP TRIGGER admin_insert_trigger;

CREATE SEQUENCE admin_id_seq;


CREATE TABLE ADMINS(
  admin_id NUMBER not null,
  admin_name VARCHAR2(20) not null,
  authorizer VARCHAR2(20) not null,
  authorizer_time TIMESTAMP(6) default SYSTIMESTAMP,
  CONSTRAINT ADMIN_ID_PK PRIMARY KEY(admin_id),
  CONSTRAINT USER_ID_FK FOREIGN KEY(admin_name) REFERENCES BookUsers(userName)
  ON DELETE CASCADE
);
	
CREATE TRIGGER admin_insert_trigger 
BEFORE INSERT ON admins
FOR EACH ROW
BEGIN
	:new.admin_id :=  admin_id_seq.nextVal();
	:new.authorizer := ORA_LOGIN_USER;
END;
/


INSERT INTO ADMINS(admin_name) VALUES('Black');
COMMIT;

