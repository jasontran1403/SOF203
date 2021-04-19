CREATE DATABASE Account;
USE Account;

CREATE TABLE ListAccount (
username VARCHAR(32), 
password VARCHAR(16), 
role VARCHAR(20));

CREATE TABLE ListStudent (
studentid VARCHAR(10) PRIMARY KEY,
fullname VARCHAR(30),
email VARCHAR(30),
phonenum VARCHAR(14),
sex VARCHAR(6),
address VARCHAR(50),
imgpath VARCHAR(100)
);


CREATE TABLE StudentResult (
id INT PRIMARY KEY auto_increment,
studentid VARCHAR(10),
java double,
javascript double,
htmlcss double,
average double,
FOREIGN KEY (studentid) REFERENCES ListStudent(studentid)
);

delimiter //

CREATE PROCEDURE SP_DELETE (IN stuid VARCHAR(10))
  BEGIN
      DELETE FROM StudentResult WHERE studentid = stuid;
      DELETE FROM ListStudent WHERE studentid = stuid;
  END//

delimiter ;

delimiter //

CREATE PROCEDURE SP_INSERT (IN cot1 VARCHAR(10), cot2 VARCHAR(30), cot3 varchar(30), cot4 varchar(14), cot5 varchar(6), cot6 varchar(50), cot7 varchar(100))
  BEGIN
      INSERT INTO ListStudent (studentid, fullname, email, phonenum, sex, address, imgpath) VALUES (cot1, cot2, cot3, cot4, cot5, cot6, cot7);
      INSERT INTO StudentResult (id, studentid, fullname, java, javascript, htmlcss, average) VALUES (null, cot1, cot2, null, null, null, null);
  END//

delimiter ;

DROP PROCEDURE SP_INSERT;
CALL SP_INSERT ('PS14692', N'Trần Nguyên Hải', 'haitn14692@fpt.edu.vn', '0934513968', 'Nam', N'12 Còn lâu mới nói', 'C:\\Users\\Jason\\Desktop\\multiple-choice-question\\SOF203\\images\\PS14692.png');
INSERT INTO StudentResult VALUES (null, 'PS14692', 9.5, 9.9, 9.6, 9.67);
select studentresult.studentid, ListStudent.fullname, java, javascript, htmlcss, average FROM StudentResult
INNER JOIN ListStudent ON ListStudent.studentid = StudentResult.studentid;

SELECT * FROM ListAccount;
SELECT * FROM ListStudent;
SELECT * FROM StudentResult;
DELETE FROM ListStudent WHERE studentid = '3';
CALL SP_DELETE('');

DROP TABLE ListAccount;
DROP TABLE StudentResult;
DROP TABLE ListStudent;