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

-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	DROP CONSTRAINT PK_tbl_reboard; -- 자료형 답변 게시판 기본키

-- 유저
DROP TABLE tbl_user;

-- 자료형 답변 게시판
DROP TABLE tbl_reboard;

-- 유저
CREATE TABLE tbl_user (
	no        NUMBER             NOT NULL, -- 번호
	userid    VARCHAR2(50)       NOT NULL, -- 아이디
	userpw    VARCHAR2(500byte)  NOT NULL, -- 비밀번호
	email1    VARCHAR2(900)      NULL,     -- 이메일1
	email2    VARCHAR2(900)      NULL,     -- 이메일2
	salt      VARCHAR2(100 char) NOT NULL, -- 암호화
	writeauth VARCHAR2(3)       DEFAULT 'N', -- 쓰기권한
	readauth  VARCHAR2(3)        DEFAULT 'Y', -- 읽기권한
	adminauth VARCHAR2(3)       DEFAULT 'N', -- 관리자권한
	path      VARCHAR2(2000)   NULL      -- 공유 폴더
);

-- 유저
ALTER TABLE tbl_user
	ADD
		CONSTRAINT PK_tbl_user -- 유저 기본키
		PRIMARY KEY (
			no -- 번호
		);

-- 자료형 답변 게시판
CREATE TABLE tbl_reboard (
	reboard_no       NUMBER        NOT NULL, -- 글번호
	reboard_title    VARCHAR2(300) NULL,     -- 글제목
	reboard_content  CLOB          NULL,     -- 글내용
	reboard_reg      DATE    DEFAULT sysdate, -- 작성일
	readcount        NUMBER    DEFAULT 0, -- 조회수
	groupno          NUMBER     DEFAULT 0, -- 그룹번호
	step             NUMBER      DEFAULT 0, -- 글의 단계
	sortno           NUMBER        DEFAULT 0, -- 글의 정렬순서
	delflag          char              DEFAULT 'N', -- 삭제
    ckimgup          CLOB          NULL, --에디터이미지파일명
	filename         VARCHAR2(150) NULL,     -- 업로드파일명
	filesize         NUMBER       DEFAULT 0, -- 파일사이즈
	downcount        NUMBER       DEFAULT 0, -- 다운수
	originalfilename VARCHAR2(150) NULL,     -- 이름변경전 업로드파일명
	no               NUMBER        NULL      -- 번호
);

-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	ADD
		CONSTRAINT PK_tbl_reboard -- 자료형 답변 게시판 기본키
		PRIMARY KEY (
			reboard_no -- 글번호
		);

--유저
drop sequence user_seq;
create sequence user_seq
start with 1
increment by 1
nocache;
		

--게시판
drop sequence reboard_seq;
create sequence reboard_seq
start with 1
increment by 1
nocache;