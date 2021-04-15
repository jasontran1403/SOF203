CREATE DATABASE ListAccount;
USE ListAccount;

CREATE TABLE AccountList (
	username NVARCHAR(16),
    password NVARCHAR(16)
);

INSERT INTO AccountList VALUES (N'Hải', '123456');
INSERT INTO AccountList VALUES (N'Tính', '123456');
INSERT INTO AccountList VALUES (N'Nghĩa', '123456');
INSERT INTO AccountList VALUES (N'Nam', '123456');
INSERT INTO AccountList VALUES (N'Vương', '123456');