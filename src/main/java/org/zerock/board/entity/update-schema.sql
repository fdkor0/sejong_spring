CREATE TABLE tbl_member
(
    email   VARCHAR(255) NOT NULL,
    regdate datetime NULL,
    moddate datetime NULL,
    pwd     VARCHAR(255) NULL,
    name    VARCHAR(255) NULL,
    CONSTRAINT pk_tbl_member PRIMARY KEY (email)
);