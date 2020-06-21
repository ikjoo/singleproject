/*
--1. D드라이브 delight 폴더 생성(테이블스페이스 폴더)
--2. 오라클 관리자계정 접속 (cmd -> sqlplus / as sysdba) 
--1. 테이블스페이스 생성
 create tablespace tbs_singleproject
 datafile 'F:\ikjoo\tb_singleproject.dbf' size 10M
 autoextend on next 5M;
 
--2. 디비 유저 , 생성
 create user singleproject
 identified by singleproject123
 default tablespace tbs_singleproject;

--권한부여
grant connect, resource to singleproject;

--락해제
alter user singleproject account unlock;*/

-- 유저
ALTER TABLE tbl_user
	DROP CONSTRAINT PK_tbl_user; -- 유저 기본키

-- 유저
DROP TABLE tbl_user;

-- 유저
CREATE TABLE tbl_user (
	no        NUMBER             NOT NULL, -- 번호
	userid    VARCHAR2(50)       NOT NULL, -- 아이디
	userpw    VARCHAR2(500byte)  NOT NULL, -- 비밀번호
	email1    VARCHAR2(900)      NULL,     -- 이메일1
	email2    VARCHAR2(900)      NULL,     -- 이메일2
	salt      VARCHAR2(100 char) NOT NULL, -- 암호화
	path      VARCHAR2(1500)   NULL     -- 공유 폴더

);

-- 유저
ALTER TABLE tbl_user
	ADD
		CONSTRAINT PK_tbl_user -- 유저 기본키
		PRIMARY KEY (
			no -- 번호
		);

--유저
drop sequence user_seq;
create sequence user_seq
start with 1
increment by 1
nocache;
		