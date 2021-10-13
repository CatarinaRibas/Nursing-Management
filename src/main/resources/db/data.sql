USE nursing;

DELETE FROM users;
DELETE FROM patients;
DELETE FROM acts;

INSERT INTO users(ID,USERNAME,PASSWORD,EMAIL) VALUES
(1,"Catarina Pereira","cp21","catarina.pereira@gmail.com"),
(2,"Rui Magalhães","rm21","rui.magalhaes@gmail.com"),
(3,"Teresa Videira","tv21","teresa.videira@gmail.com");

INSERT INTO patients(ID,NAME,BIRTHDATE,ADDRESS,VILLAGE,PHONE,EMAIL) VALUES
(1,"Ana Rosário Santos Magalhães","03/02/1960","Rua D.Aparecida Nº2","Seara Velha",936379097,"ana.magalhaes@gmail.com"),
(2,"Catarina Sofia Ribas Costa Pereira","18/05/1994","Rua de Angola 154 3.1 B","Seara Velha",936379097,"catarina.pereira@gmail.com"),
(3,"Rui dos Santos Magalhães","23/05/1994","Vereda 2 Rua Dr. Ilidio Sardoeira Nº12","Seara Velha",936379097,"rui.magalhaes@gmail.com");

INSERT INTO acts(ID,PATIENT_ID,NURSING_DATE,MINIMUM_BLOOD_PRESSURE,MAXIMUM_BLOOD_PRESSURE,HEART_BEAT,COMMENTS) VALUES
(1,1,"10/10/2021",7,12,90,"NA"),
(2,1,"13/10/2021",10,14,110,"Patiente muito alterada"),
(3,2,"13/10/2021",8,10,88,"NA");
