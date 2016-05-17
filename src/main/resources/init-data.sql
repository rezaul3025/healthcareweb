INSERT into specialization values(1,'Accident and emergency medicine');
INSERT into specialization values(2,'Allergology');
INSERT into specialization values(3,'Anaesthetics');
INSERT into specialization values(4,'Biological hematology');
INSERT into specialization values(5,'Cardiology');
INSERT into specialization values(6,'Child psychiatry');
INSERT into specialization values(7,'Clinical biology');
INSERT into specialization values(8,'Clinical chemistry');
INSERT into specialization values(9,'Clinical neurophysiology');
INSERT into specialization values(10,'Craniofacial surgery');
INSERT into specialization values(11,'Dental, oral and maxillo-facial surgery');
INSERT into specialization values(12,'Dermato-venerology');
INSERT into specialization values(13,'Dermatology');
INSERT into specialization values(14,'Endocrinology');
INSERT into specialization values(15,'Gastro-enterologic surgery');
INSERT into specialization values(16,'Gastroenterology');
INSERT into specialization values(17,'General hematology');


INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000001,'Dr','tom','mia','a1','a2','pc','rostock','017634344','tom@test.com','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000002,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000003,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000004,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000005,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000006,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000007,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000008,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(10000009,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(100000010,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);
INSERT into doctor(id, title, first_name,last_name,address_line1,address_line2,post_code,city,mobile,email,website,description) values(100000011,'Dr','Ali','Tom','a1','a2','pc','rostock','017634344','tom@test.de','www.web.ce',null);

INSERT into doctor_specialization(doctor_id,specialization_id) values(10000001,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000002,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000003,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000004,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000005,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000006,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000007,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000008,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000009,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(100000010,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(100000011,1);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000002,6);
INSERT into doctor_specialization(doctor_id,specialization_id) values(10000002,8);


INSERT into user_info(id, username, password, role, first_name, last_name,parent_id) values(2000001,'tom@tom.com','$2a$10$R/UCMObBXHO5kKF7Ly4Zjeq1wgw0l8Hcl6I05Uf4BMZs10SH4J09u','DOCTOR','Tom','Mia',10000001);
insert into user_info (id, username, password, role, first_name, last_name, parent_id) values (2000002,'rezaul.km105@gmail.com','$2a$10$R/UCMObBXHO5kKF7Ly4Zjeq1wgw0l8Hcl6I05Uf4BMZs10SH4J09u','USER','REZAUL','KARIM', 'NULL')


