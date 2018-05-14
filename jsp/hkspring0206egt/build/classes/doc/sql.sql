--drop table HKMOBILE_board cascade constraint;

--drop sequence seq_HKMOBILE_board;

--drop table hkmobile_member cascade constraint;

//---------------------------------------
create table hkmobile_member(
id varchar2(50) primary key,
name varchar2(50)  not null,
email varchar2(50) unique ,
pwd varchar2(50)  not null,
delflag number(1) default 0,
auth number(1) default 3
);
INSERT INTO HKMOBILE_MEMBER (ID,NAME,EMAIL,PWD)
VALUES('sn001','홍길동','aa@naver.com','1234');

INSERT INTO HKMOBILE_MEMBER (ID,NAME,EMAIL,PWD,AUTH)
VALUES('admin','admin','admin@naver.com','1234',1);


create table HKMOBILE_board(
seq number primary key,
id varchar2(50) not null,
title varchar2(100) not null,
content varchar2(4000) not null,
wdate date not null
);
ALTER TABLE HKMOBILE_board
	ADD CONSTRAINT FK_HKMOBILE_board_member
FOREIGN KEY (id)
		 REFERENCES hkmobile_member (id);

create sequence seq_HKMOBILE_board
start with 1 increment by 1;

--2015 1 6추가
--drop table hkm_mboard cascade constraint;
--drop sequence seq_hkm_mboard;

create table hkm_mboard(
seq number primary key,
id varchar2(50) not null,
title varchar2(200) not null,
content varchar2(4000) not null,
wdate date not null,
ref number not null,
step number not null,
depth number not null,
parent number not null,
delflag number(1) default 0
);  
create sequence seq_hkm_mboard 
start with 1 increment by 1;

alter table hkm_mboard ADD CONSTRAINT 
fk_hkm_mboard_member
FOREIGN KEY (id)
		 REFERENCES hkmobile_member (id);


-----TEST
insert into hkm_mboard(seq ,id ,title,content,
wdate,ref,step,depth,parent,delflag)
values(seq_hkm_mboard.nextval,
'sn001',
'영기가 쏜다.',
'영기가 미팅기념으로 쏜다고 하네요. 원하는 것을 올리시오.',sysdate,
(select nvl(max(ref),0)+1 from hkm_mboard),
0,0,0,0
);


select nvl(max(ref),0)+1 from hkm_mboard;
 
----
CREATE TABLE HKM_QNA(
SEQ NUMBER PRIMARY KEY,
ID VARCHAR2(50) NOT NULL,
TITLE VARCHAR2(200) NOT NULL,
CONTENT VARCHAR2(4000) NOT NULL,
WDATE DATE NOT NULL,
REF NUMBER NOT NULL,
STEP NUMBER NOT NULL,
DEPTH NUMBER NOT NULL,
PARENT NUMBER NOT NULL,
DELFLAG NUMBER(1) DEFAULT 0
);

CREATE SEQUENCE SEQ_HKM_QNA
START WITH 1 INCREMENT BY 1;

ALTER TABLE HKM_QNA ADD CONSTRAINT 
FK_HKM_QNA_MEMBER FOREIGN KEY (ID)
REFERENCES HKMOBILE_MEMBER (ID);

SELECT * FROM HKM_QNA;

create table hkm_calendar(
seq number primary key,
id varchar2(50) not null,
title  varchar2(100) not null,
content varchar2(4000) not null,
wdate varchar2(12) not null,
regdate date not null
);
CREATE SEQUENCE SEQ_hkm_calendar
START WITH 1 INCREMENT BY 1;

ALTER TABLE hkm_calendar ADD CONSTRAINT 
FK_hkm_calendar_MEMBER FOREIGN KEY (ID)
REFERENCES HKMOBILE_MEMBER (ID);

----------------------------
--모든 일정
SELECT  
SEQ,ID, TITLE, CONTENT, WDATE,REGDATE 
FROM HKm_CALENDAR ;

--wdate를 기준으로 번호 붙이기
SELECT ROW_NUMBER() OVER 
 (ORDER BY WDATE ASC )  RN, 
SEQ,ID, TITLE, CONTENT, WDATE,REGDATE 
 FROM HKm_CALENDAR ;

--날짜별 년월일20160113  8자
--로  번호 붙이기
SELECT ROW_NUMBER() OVER 
 (PARTITION BY SUBSTR(WDATE,1,8) ORDER BY WDATE ASC )  RN, 
SEQ,ID, TITLE, CONTENT, WDATE,REGDATE 
 FROM HKm_CALENDAR ;

 --날짜별로 정렬후 번호를 붙이고 날짜별 위에서 5개
 SELECT SEQ,ID, TITLE, CONTENT, WDATE,REGDATE FROM 
(
SELECT ROW_NUMBER() OVER 
 (PARTITION BY SUBSTR(WDATE,1,8) ORDER BY WDATE ASC )  RN, 
SEQ,ID, TITLE, CONTENT, WDATE,REGDATE 
 FROM HKm_CALENDAR 
 )
 WHERE RN BETWEEN 1 AND 5
 --kim, 201601(2016년 1월)의 일정 중
 --날짜별로 정렬후 번호를 붙이고 날짜별 위에서 5개
 
SELECT SEQ,ID, TITLE, CONTENT, WDATE,REGDATE FROM 
(
SELECT ROW_NUMBER() OVER 
 (PARTITION BY SUBSTR(WDATE,1,8) ORDER BY WDATE ASC )  RN, 
SEQ,ID, TITLE, CONTENT, WDATE,REGDATE 
 FROM HKm_CALENDAR 
 WHERE  ID='kim'  AND SUBSTR(WDATE,1,6) = '201601'
 )
 WHERE RN BETWEEN 1 AND 5
-------------------------------
create table hkm_poll(
POLLID     NUMBER NOT NULL,
ID         VARCHAR2(50) NOT NULL,
QUESTION   VARCHAR2(1000) NOT NULL,
SDATE      DATE NOT NULL,
EDATE      DATE NOT NULL,
ITEMCOUNT  NUMBER NOT NULL,
POLLTOTAL  NUMBER NOT NULL,
REGDATE    DATE NOT NULL,
   constraint  PK_hkm_poll primary key (POLLID)
);

CREATE sequence seq_hkm_poll START WITH 1 INCREMENT BY 1;

ALTER TABLE hkm_poll ADD CONSTRAINT fk_hkm_poll_member
FOREIGN KEY (ID)
REFERENCES HKMOBILE_MEMBER (ID);

CREATE table hkm_POLLSUB (
    POLLSUBID  NUMBER NOT NULL,
    POLLID     NUMBER NOT NULL,
    ANSWER     VARCHAR2(1000) NOT NULL,
    ACOUNT     NUMBER NOT NULL,
    constraint  pk_hkm_POLLSUB primary key(POLLSUBID)
);

CREATE sequence seq_hkm_POLLSUB START WITH 1 INCREMENT BY 1;
  
ALTER TABLE hkm_POLLSUB ADD CONSTRAINT fk_hkm_POLLSUB_poll
FOREIGN KEY (POLLID)
REFERENCES hkm_poll (POLLID);

CREATE table hkm_VOTER (
    VOTERID    NUMBER NOT NULL,
    POLLID     NUMBER,
    POLLSUBID  NUMBER NOT NULL,
    ID         VARCHAR2(50) NOT NULL,
    REGDATE    DATE NOT NULL,
    constraint  pk_hkm_VOTER primary key (VOTERID)
);

CREATE sequence seq_hkm_VOTER START WITH 1 INCREMENT BY 1;


ALTER TABLE hkm_VOTER ADD CONSTRAINT fk_hkm_VOTER_POLL
FOREIGN KEY (POLLID)
REFERENCES hkm_poll (POLLID);

ALTER TABLE hkm_VOTER ADD CONSTRAINT fk2_hkm_VOTER_POLLSUB
FOREIGN KEY (POLLSUBID)
REFERENCES hkm_POLLSUB (POLLSUBID);

ALTER TABLE hkm_VOTER ADD CONSTRAINT fk3_hkm_VOTER_member
FOREIGN KEY (ID)
REFERENCES HKMOBILE_MEMBER (ID);
--
select * from hkm_poll;
select * from hkm_POLLSUB;
select * from hkm_voter;
------------------------------


create table hkm_pds	(
seq number(8) primary key,
id varchar2(50) not null,
title varchar2(200) not null,
content varchar2(4000) not null,
filename varchar2(50) not null,
readcount number(8) not null,
downcount number(8) not null,
regdate date not null
);	

--drop sequence seq_hkm_pds 	;

create sequence seq_hkm_pds
start with 1 increment by 1;

alter table hkm_pds
add constraint fk_hkm_pds_member foreign key(id)
references HKMOBILE_MEMBER(id);


-----

create table hkstudy(
seq number primary key,
num number not null,
category number not null,
id varchar2(50) not null,
title varchar2(200) not null,
content varchar2(4000),
filename varchar2(50),
wdate date not null,
readcount number not null
);
create sequence seq_hkstudy
start with 1 increment by 1 ;

create table hkcategory(
category number primary key,
categoryname varchar2(50) not null
);

ALTER TABLE hkstudy
	ADD CONSTRAINT fk_hkstudy_hkcategory 
FOREIGN KEY (category)
		 REFERENCES hkcategory(category);

ALTER TABLE hkstudy
	ADD CONSTRAINT fk_hkstudy_hkmember 
FOREIGN KEY (id)
    REFERENCES hkmobile_member (id);

 insert into  hkcategory(category,categoryname)  
 values(1,'JAVA');
  insert into  hkcategory(category,categoryname)  
 values(2,'ANDROID');
   insert into  hkcategory(category,categoryname)  
 values(3,'SQL');
    insert into  hkcategory(category,categoryname)  
 values(4,'WEB');
     
SELECT * FROM hkcategory;
    
select * from HKm_POLL;
select * from HKM_POLLSUB;
select * from HKM_VOTER

SELECT COUNT(*)
FROM HKM_VOTER
WHERE POLLID=22 AND ID='kim'
    