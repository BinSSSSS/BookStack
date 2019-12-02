-- ɾ�����ݱ�
DROP TABLE grade ;
DROP TABLE sporter ;
DROP TABLE item ;
PURGE RECYCLEBIN ;
-- ������
CREATE TABLE sporter(
	sporterid		NUMBER(4) ,
	name			VARCHAR2(30)	NOT NULL ,
	sex			VARCHAR2(10)	NOT NULL ,
	department		VARCHAR2(30)	NOT NULL ,
	CONSTRAINT pk_sporterid PRIMARY KEY(sporterid) ,
	CONSTRAINT ck_sex CHECK(sex IN ('��' ,'Ů'))
) ;
CREATE TABLE item(
	itemid			VARCHAR2(4) ,
	itemname		VARCHAR2(30)	NOT NULL ,
	location		VARCHAR2(30)	NOT NULL ,
	CONSTRAINT pk_itemid PRIMARY KEY(itemid)
) ;
CREATE TABLE grade(
	sporterid		NUMBER(4) ,
	itemid			VARCHAR2(4) ,
	mark			NUMBER(1) ,
	CONSTRAINT fk_sporterid FOREIGN KEY(sporterid) REFERENCES sporter(sporterid) ON DELETE CASCADE ,
	CONSTRAINT fk_itemid FOREIGN KEY(itemid) REFERENCES item(itemid) ON DELETE CASCADE ,
	CONSTRAINT ck_mark CHECK (mark IN (6,4,2,0))
) ;
-- ��������
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1001,'����','��','�����ϵ') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1002,'����','��','��ѧϵ') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1003,'����','��','�����ϵ') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1004,'����','��','����ϵ') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1005,'����','Ů','����ϵ') ;
INSERT INTO sporter(sporterid,name,sex,department) VALUES (1006,'����','Ů','��ѧϵ') ;

INSERT INTO item(itemid,itemname,location) VALUES ('x001','������ǧ��','һ�ٳ�') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x002','���ӱ�ǹ','һ�ٳ�') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x003','������Զ','���ٳ�') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x004','Ů������','���ٳ�') ;
INSERT INTO item(itemid,itemname,location) VALUES ('x005','Ů����ǧ��','���ٳ�') ;

INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x001', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x001', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x001', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1004,  'x001', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x003', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x003', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1004,  'x003', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x003', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1005,  'x004', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1006,  'x004', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x004', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1002,  'x004', 0) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1003,  'x002', 6) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1005,  'x002', 4) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1006,  'x002', 2) ;
INSERT INTO grade(sporterid,itemid,mark) VALUES (1001,  'x002', 0) ;
-- �����ύ
COMMIT ;
