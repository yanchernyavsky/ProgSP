create database if not exists lab4;
use lab4;

create table if not exists Test_Table (
    test_pole int  NULL
);

INSERT INTO Test_Table (test_pole)
VALUE (2), (3), (4), (5);

create table if not exists Military (
    Id MEDIUMINT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Count VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id)
);

INSERT INTO Military (Name, Count)
VALUES
('Пистолет "Glock9"', "250"),
('Бронежилет "Комарик"', "80"),
('Танк Т34', "10"),
('Вертолет "Вертушка-Ватрушка"', "3");
drop table Military;
