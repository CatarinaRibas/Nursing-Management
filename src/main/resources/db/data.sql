USE nursing;

DELETE FROM users;
DELETE FROM patients;
DELETE FROM acts;

INSERT INTO users(ID,NAME,PASSWORD,EMAIL) VALUES
(1,"Catarina Pereira","cp21","catarina.pereira@gmail.com"),
(2,"Rui Magalhães","rm21","rui.magalhaes@gmail.com"),
(3,"Teresa Videira","tv21","teresa.videira@gmail.com");

INSERT INTO patients(ID,NAME,BIRTHDATE,ADDRESS,VILLAGE,PHONE,EMAIL) VALUES
(1,"Ana Rosário Santos Magalhães","1960-02-03","Rua D.Aparecida Nº2","seara velha",936379097,"ana.magalhaes@gmail.com"),
(2,"Catarina Sofia Ribas Costa Pereira","1994-05-18","Rua de Angola 154 3.1 B","seara velha",936379097,"catarina.pereira@gmail.com"),
(3,"Rui dos Santos Magalhães","1994-05-23","Vereda 2 Rua Dr. Ilidio Sardoeira Nº12","seara velha",936379097,"rui.magalhaes@gmail.com"),
(4,"Maria Teresa Santos Videira","1960-02-03","Rua Santo Nº1","soutelo",936379097,"teresa.videira@gmail.com"),
(5,"Sarah dos Santos Magalhães","1996-05-23","Rua Santo Nº1","soutelo",936379097,"sara.magalhaes@gmail.com");

INSERT INTO acts(ID,PATIENT_ID,NURSING_DATE,MINIMUM_BLOOD_PRESSURE,MAXIMUM_BLOOD_PRESSURE,HEART_BEAT,COMMENTS) VALUES
(1,1,"2021-10-13",7,12,90,"NA"),
(2,1,"2021-10-17",10,14,110,"Patiente muito alterada"),
(3,2,"2021-11-05",8,10,88,"NA");
