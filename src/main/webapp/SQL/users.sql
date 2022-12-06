--#1. ���̺� ������ ����
CREATE TABLE users (
  id          VARCHAR2(20),
  passwd      VARCHAR2(20)  NOT NULL,
  name        VARCHAR2(30)  NOT NULL,
  email       VARCHAR2(50)  NOT NULL,
  regdate     DATE DEFAULT  SYSDATE
);

--#2. �������� �߰�
ALTER TABLE users
  ADD ( CONSTRAINT user_id_pk     PRIMARY KEY(id),
        CONSTRAINT user_email_uk  UNIQUE (email));

--#3. ������ ���Ǽ��� ���� �������� ��Ȱ��ȭ
ALTER TABLE users
  DISABLE CONSTRAINT user_id_pk CASCADE
  DISABLE CONSTRAINT user_email_uk;

ALTER TABLE users
  ENABLE CONSTRAINT user_id_pk
  ENABLE CONSTRAINT user_email_uk;        

-- ��ü ��ȸ
SELECT id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD')
FROM users
ORDER BY regdate DESC;

-- ID�� ��ȸ(ȸ�� ��)
SELECT id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD HH24:MI:SS')
FROM users
WHERE id = 'bangry';

-- ID/PW�� ��ȸ(ȸ�� �α���)
SELECT id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD HH24:MI:SS')
FROM users
WHERE id = 'bangry' AND passwd = '1111';

-- ȸ�� ���
INSERT INTO users(id, name, passwd, email)
VALUES ('honggildong', 'ȫ�浿', 'honggildong@gmail.com', '1234');

ROLLBACK;
        
-- ������������ ���� ����� ��� ��ȯ
SELECT id, name, passwd, email, regdate
FROM ( SELECT CEIL(rownum / 10) request_page, id, name, passwd, email, regdate
       FROM   ( SELECT id, name, passwd, email, TO_CHAR(regdate, 'YYYY-MM-DD DAY') regdate
                FROM users
                ORDER  BY regdate DESC))
WHERE  request_page = 2;

-- ����������, ��ȸ ��ϰ����� ���� ����� ��� ��ȯ
SELECT id, name, passwd, email, regdate
FROM ( SELECT CEIL(rownum / 5) request_page, id, name, passwd, email, regdate
       FROM   ( SELECT id, name, passwd, email, TO_CHAR(regdate, 'YYYY-MM-DD DAY') regdate
                FROM users
                ORDER  BY regdate DESC))
WHERE  request_page = 2;

-- ����������, ��ȸ ��ϰ���, �˻�����, �˻����� ���� ����� ��� ��ȯ
SELECT id, name, passwd, email, regdate
FROM ( SELECT CEIL(rownum / 5) request_page, id, name, passwd, email, regdate
       FROM   ( SELECT id, name, passwd, email, TO_CHAR(regdate, 'YYYY-MM-DD DAY') regdate
                FROM users
--                WHERE id = 'bangry'
                WHERE name like '%��%'
                ORDER  BY regdate DESC))
WHERE  request_page = 1;


-- �˻�����, �˻����� ���� ����� ���� ��ȯ - ����¡ ó�� �� �ʿ�
SELECT COUNT(id) count
FROM   users
--WHERE id = 'bangry';
--WHERE name like '%��%';

