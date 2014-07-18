

-----------------------------------------------------------------------------
-- KSEN_ENROLLMENT_FEE
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_ENROLLMENT_FEE';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_ENROLLMENT_FEE CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_ENROLLMENT_FEE
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ENRL_FEE_TYPE VARCHAR2(255) NOT NULL
        , ENRL_FEE_STATE VARCHAR2(255) NOT NULL
        , DESCR_PLAIN VARCHAR2(4000)
        , DESCR_FORMATTED VARCHAR2(4000)
        , CURRENCY_TYPE VARCHAR2(255)
        , CURRENCY_QUANTITY NUMBER(22)
        , ORG_ID VARCHAR2(255)
        , REF_OBJECT_URI VARCHAR2(255)
        , REF_OBJECT_ID VARCHAR2(255)
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_ENROLLMENT_FEE
    ADD CONSTRAINT KSEN_ENROLLMENT_FEEP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_ENRL_FEE_I1 
  ON KSEN_ENROLLMENT_FEE 
  (ENRL_FEE_TYPE)
/
CREATE INDEX KSEN_ENRL_FEE_I2 
  ON KSEN_ENROLLMENT_FEE 
  (REF_OBJECT_URI)
/





-----------------------------------------------------------------------------
-- KSEN_ENROLLMENT_FEE_ATTR
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_ENROLLMENT_FEE_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_ENROLLMENT_FEE_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_ENROLLMENT_FEE_ATTR
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ATTR_KEY VARCHAR2(255)
        , ATTR_VALUE VARCHAR2(2000)
        , OWNER_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_ENROLLMENT_FEE_ATTR
    ADD CONSTRAINT KSEN_ENROLLMENT_FEE_ATTRP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_ENRL_FEE_ATTR_IF1 
  ON KSEN_ENROLLMENT_FEE_ATTR 
  (OWNER_ID)
/

