DROP TABLE IF EXISTS COMMENT;
DROP TABLE IF EXISTS AUTHORITY;
DROP TABLE IF EXISTS PIC_TAG;
DROP TABLE IF EXISTS TAG;
DROP TABLE IF EXISTS FAVORITE;
DROP TABLE IF EXISTS pic;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
ID BIGSERIAL NOT NULL PRIMARY KEY,
EMAIL VARCHAR (100) NOT NULL,
NICKNAME VARCHAR(255) NOT NULL,
PASS VARCHAR (40) NOT NULL,
SOL VARCHAR (40),
AVATAR VARCHAR(100),
RATING INT DEFAULT 0,
STATUS SMALLINT DEFAULT 0,
REG_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IDX_USER_RATING ON users (RATING);
CREATE UNIQUE INDEX IDX_USER_NICKNAME ON users(nickname);

CREATE TABLE AUTHORITY (
  user_id BIGINT NOT NULL,
  ROLE VARCHAR(50) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX IDX_AUTH_ID_ROLE ON AUTHORITY (user_id,ROLE);

CREATE TABLE PIC(
ID BIGSERIAL PRIMARY KEY,
TITLE VARCHAR(255),
DESCR VARCHAR(2000),
URL_L VARCHAR(200),
URL_S VARCHAR(200),
URL_XS VARCHAR(200),
RATING INT DEFAULT 0,
STATUS SMALLINT DEFAULT 0,
LOAD_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
MODER_DATE TIMESTAMP,
USER_ID BIGINT,
FOREIGN KEY (USER_ID) REFERENCES users(ID));

CREATE INDEX IDX_PIC_RAITING ON PIC (RATING);

CREATE TABLE TAG (
ID BIGSERIAL NOT NULL PRIMARY KEY ,
NAME VARCHAR (30) NOT NULL,
RATING INT DEFAULT 0,
USER_ID BIGINT NOT NULL,
FOREIGN KEY (USER_ID) references users(ID));

CREATE UNIQUE INDEX IDX_RATING_NAME ON TAG(NAME);

CREATE INDEX IDX_TAG_RATING ON TAG (RATING);

CREATE TABLE COMMENT (
ID BIGSERIAL NOT NULL PRIMARY KEY,
TEXT VARCHAR(2000) NOT NULL,
DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PIC_ID BIGINT NOT NULL,
USER_ID BIGINT NOT NULL,
STATUS SMALLINT NOT NULL DEFAULT 1,
FOREIGN KEY (PIC_ID) REFERENCES PIC(ID),
FOREIGN KEY (USER_ID) REFERENCES users(ID));

CREATE INDEX IDX_PIC_ID ON COMMENT (PIC_ID);

CREATE TABLE PIC_TAG (
PIC_ID BIGINT NOT NULL,
TAG_ID BIGINT NOT NULL,
CONSTRAINT PK_PIC_TAG PRIMARY KEY (PIC_ID,TAG_ID),
FOREIGN KEY (PIC_ID) REFERENCES PIC(ID),
FOREIGN KEY (TAG_ID) REFERENCES TAG(ID));

-- CREATE INDEX IDX_PIC_TAG_ONPIC ON PIC_TAG(PIC_ID);
CREATE INDEX IDX_PIC_TAG_ONTAG ON PIC_TAG(TAG_ID);

CREATE TABLE FAVORITE (
PIC_ID BIGINT NOT NULL,
USER_ID BIGINT NOT NULL,
CONSTRAINT PK_FAVORITE PRIMARY KEY (USER_ID,PIC_ID),
FOREIGN KEY (PIC_ID) REFERENCES PIC(ID),
FOREIGN KEY (USER_ID) REFERENCES users(ID)
);

