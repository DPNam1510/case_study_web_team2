drop database m3_kham_chua_benh;
create database m3_kham_chua_benh;
use m3_kham_chua_benh;
create table account(
username varchar(20) primary key,
password varchar(20) not null,
date_create date,
role varchar(20)
);
CREATE TABLE login_history(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    login_time DATETIME NOT NULL,
    ip_address VARCHAR(50),
    FOREIGN KEY (username) REFERENCES account(username)
);
CREATE TABLE password_history(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    changed_at DATETIME NOT NULL,
    FOREIGN KEY (username) REFERENCES account(username)
);
create table customer_type(
id int primary key auto_increment,
name varchar(20)
);
create table customer(
id int primary key auto_increment,
username varchar(50),
customer_type_id int,
foreign key (username) references account(username),
foreign key (customer_type_id) references customer_type(id),
name varchar(50),
gender boolean,
birthday date,
email varchar(20),
phone varchar(20),
address varchar(20)
);
create table service(
id int primary key auto_increment,
name varchar(50),
doctor_name varchar(20)
);
create table medical_forms(
id int primary key,
customer_id int,
foreign key (customer_id) references customer(id),
date_time date,
appointment_time datetime,
status varchar(20)
);
create table forms_detail(
id int primary key,
forms_id int,
service_id int,
foreign key (forms_id) references medical_forms(id),
foreign key (service_id) references service(id),
diagnosis_terminology varchar(100),
prescription varchar(100),
prescription_price double,
date_time datetime
);
create table pay_type(
id int primary key auto_increment,
name varchar(20)
);
create table dietary_supplements(
id int primary key auto_increment,
name varchar(20),
production_date date,
exp_date date,
description varchar(50),
price double
);
create table orders(
id int primary key,
customer_id int,
pay_type_id int,
foreign key (customer_id) references customer(id),
foreign key (pay_type_id) references pay_type(id),
date_time date
);
create table orders_detail(
id int primary key,
orders_id int,
supplements_id int,
foreign key (orders_id) references orders(id),
foreign key (supplements_id) references dietary_supplements(id),
quantity int,
date_time datetime
);
-- insert table 
INSERT INTO account(username, password, date_create, role) VALUES
('Dpnam', '151020', '2019-12-15', 'admin'),
('Huyg', '051197', '2019-12-15', 'admin'),
('Hieuhn', 'hieuhn', '2019-12-15', 'admin'),
('Hungnv', 'hung123', '2020-01-15', 'customer'),
('Lett', 'le123', '2020-06-20', 'customer'),
('Luyentv', 'luyen123', '2020-12-10', 'customer'),
('Dinhpt', 'dinh123', '2021-03-05', 'customer'),
('Huyhoang', 'huy123', '2021-08-18', 'customer'),
('Uyen123', 'uyen123', '2022-01-25', 'customer'),
('Tientv', 'tien123', '2022-05-30', 'customer'),
('Duyenlh', 'duyen123', '2022-09-12', 'customer'),
('Nghipv', 'nghi321', '2023-02-14', 'customer'),
('Thuyhoang', 'thuy123', '2023-06-28', 'customer'),
('Hieunt', 'hieunt', '2024-01-03', 'customer'),
('Hongtt', 'hongtt', '2024-04-15', 'customer'),
('Huylh', 'huy123', '2024-09-21', 'customer'),
('Leptd', 'leptd', '2025-01-10', 'customer'),
('Haotm', 'tranminhhao', '2025-05-08', 'customer');


INSERT INTO customer_type(name) VALUES
('Premium'),
('Gold'),
('Silver'),
('Normal');

INSERT INTO customer(username, customer_type_id, name, gender, birthday, email, phone, address) VALUES
('Hungnv', 3, 'Nguyen Van Hung', 1, '1985-05-12', 'hung@gmail.com', '0911111111', 'Da Nang'),
('Lett', 4, 'Tran Thi Le', 0, '1988-08-23', 'le@gmail.com', '0912222222', 'Hue'),
('Luyentv', 4, 'Le Van Luyen', 1, '1990-12-03', 'luyen@gmail.com', '0913333333', 'Kon Tum'),
('Dinhpt', 4, 'Pham Thi Dinh', 0, '1987-04-14', 'dinh@gmail.com', '0914444444', 'Phu Yen'),
('Huyhoang', 4, 'Hoang Huy', 1, '1992-07-21', 'huy@gmail.com', '0915555555', 'Hue'),
('Uyen123', 4, 'Nguyen Tran Thuc Uyen', 0, '1989-11-30', 'uyen@gmail.com', '0916666666', 'Ha Noi'),
('Tientv', 4, 'Tran Van Tien', 1, '1991-03-17', 'tien@gmail.com', '0917777777', 'Da Nang'),
('Duyenlh', 4, 'Le Hoang Duyen', 0, '1986-06-09', 'duyen@gmail.com', '0918888888', 'Hai Phong'),
('Nghipv', 4, 'Pham Van Nghi', 1, '1993-09-25', 'nghi@gmail.com', '0919999999', 'Nha Trang'),
('Thuyhoang', 3, 'Hoang Thuy', 0, '1985-02-05', 'thuy@gmail.com', '0911010101', 'Quang Tri'),
('Hieunt', 4, 'Nguyen Trong Hieu', 1, '1990-08-14', 'hieu@gmail.com', '0912020202', 'Quang Ngai'),
('Hongtt', 4, 'Tran Thi Hong', 0, '1987-12-19', 'hong@gmail.com', '0913030303', 'Nghe An'),
('Huylh', 4, 'Le Huynh Huy', 1, '1992-05-27', 'long@gmail.com', '0914040404', 'Ho Chi Minh'),
('Leptd', 4, 'Pham Thi Diem Le', 0, '1989-10-02', 'nguyen@gmail.com', '0915050505', 'Gia Lai'),
('Haotm', 4, 'Tran Minh Hao', 1, '1991-01-15', 'hao@gmail.com', '0916060606', 'Quang Binh');


INSERT INTO service(name, doctor_name) VALUES
('General Checkup', 'Dr. Nguyen'),
('Dental Cleaning', 'Dr. Tran'),
('Blood Test', 'Dr. Le'),
('Eye Exam', 'Dr. Hoang'),
('X-ray', 'Dr. Bui'),
('ECG', 'Dr. Pham'),
('Ultrasound', 'Dr. Vu'),
('Physiotherapy', 'Dr. Do'),
('Vaccination', 'Dr. Hoang'),
('Dermatology Consultation', 'Dr. Nguyen'),
('ENT Consultation', 'Dr. Tran'),
('Gastroenterology', 'Dr. Le'),
('Orthopedic Consultation', 'Dr. Bui'),
('Cardiology Consultation', 'Dr. Pham'),
('Neurology Consultation', 'Dr. Vu');


INSERT INTO medical_forms(id, customer_id, date_time, appointment_time, status) VALUES
(1, 1, '2020-02-10', '2020-02-10 09:00:00', 'Completed'),
(2, 2, '2020-05-18', '2020-05-18 10:30:00', 'Completed'),
(3, 3, '2020-09-25', '2020-09-25 14:00:00', 'Completed'),
(4, 4, '2021-01-12', '2021-01-12 09:30:00', 'Completed'),
(5, 5, '2021-06-03', '2021-06-03 11:00:00', 'Completed'),
(6, 6, '2021-11-20', '2021-11-20 13:30:00', 'Completed'),
(7, 7, '2022-03-15', '2022-03-15 08:45:00', 'Completed'),
(8, 8, '2022-07-22', '2022-07-22 10:15:00', 'Completed'),
(9, 9, '2023-01-05', '2023-01-05 14:20:00', 'Completed'),
(10, 10, '2023-04-19', '2023-04-19 09:50:00', 'Completed'),
(11, 11, '2023-09-10', '2023-09-10 11:40:00', 'Completed'),
(12, 12, '2024-02-02', '2024-02-02 10:05:00', 'Completed'),
(13, 13, '2024-06-18', '2024-06-18 13:15:00', 'Completed'),
(14, 14, '2024-11-07', '2024-11-07 09:25:00', 'Completed'),
(15, 15, '2025-03-12', '2025-03-12 10:40:00', 'Completed');


INSERT INTO forms_detail(id, forms_id, service_id, diagnosis_terminology, prescription, prescription_price, date_time) VALUES
(1, 1, 1, 'Sốt nhẹ', 'Paracetamol', 40000, '2020-02-10 09:20:00'),
(2, 2, 2, 'Viêm nướu nhẹ', 'Gel vệ sinh răng', 55000, '2020-05-18 10:45:00'),
(3, 3, 3, 'Thiếu máu nhẹ', 'Sắt + Vitamin B12', 80000, '2020-09-25 14:15:00'),
(4, 4, 4, 'Khô mắt', 'Thuốc nhỏ mắt', 45000, '2021-01-12 09:50:00'),
(5, 5, 5, 'Đau xương', 'Thuốc giảm đau', 70000, '2021-06-03 11:20:00'),
(6, 6, 6, 'Tim đập nhanh', 'Thuốc tim', 90000, '2021-11-20 13:50:00'),
(7, 7, 7, 'Khó chịu bụng', 'Thuốc tiêu hóa', 60000, '2022-03-15 09:10:00'),
(8, 8, 8, 'Đau cơ', 'Vitamin + khoáng', 75000, '2022-07-22 10:35:00'),
(9, 9, 9, 'Tiêm phòng cúm', 'Vaccine', 50000, '2023-01-05 14:40:00'),
(10, 10, 10, 'Mụn trứng cá', 'Kem trị mụn', 60000, '2023-04-19 10:10:00'),
(11, 11, 11, 'Viêm họng', 'Thuốc súc miệng', 55000, '2023-09-10 11:55:00'),
(12, 12, 12, 'Đau bụng', 'Thuốc tiêu hóa', 65000, '2024-02-02 10:25:00'),
(13, 13, 13, 'Chấn thương tay', 'Băng + thuốc giảm đau', 80000, '2024-06-18 13:35:00'),
(14, 14, 14, 'Đau tim', 'Thuốc tim mạch', 100000, '2024-11-07 09:45:00'),
(15, 15, 15, 'Đau đầu nặng', 'Thuốc giảm đau mạnh', 90000, '2025-03-12 11:00:00');


INSERT INTO pay_type(name) VALUES
('Cash'),
('Bank Transfer');

INSERT INTO dietary_supplements(name, production_date, exp_date, description, price) VALUES
('Vitamin C', '2020-01-01', '2023-01-01', 'Hỗ trợ miễn dịch', 100000),
('Omega 3', '2020-03-15', '2023-03-15', 'Tim mạch khỏe mạnh', 200000),
('Calcium', '2020-06-20', '2023-06-20', 'Xương chắc khỏe', 150000),
('Multivitamin', '2020-09-10', '2023-09-10', 'Bổ sung vitamin mỗi ngày', 120000),
('Zinc', '2021-01-05', '2024-01-05', 'Tăng cường miễn dịch', 80000),
('Vitamin D', '2021-04-12', '2024-04-12', 'Hỗ trợ hấp thu canxi', 90000),
('Magnesium', '2021-07-18', '2024-07-18', 'Giảm mệt mỏi cơ bắp', 110000),
('Probiotics', '2021-10-20', '2024-10-20', 'Cân bằng hệ tiêu hóa', 95000),
('Vitamin B Complex', '2022-01-15', '2025-01-15', 'Tăng năng lượng', 85000),
('Iron', '2022-04-10', '2025-04-10', 'Phòng thiếu máu', 70000),
('Collagen', '2022-07-05', '2025-07-05', 'Làm đẹp da', 150000),
('CoQ10', '2022-10-12', '2025-10-12', 'Tim mạch khỏe mạnh', 180000),
('Folic Acid', '2023-01-20', '2026-01-20', 'Phòng ngừa thiếu hụt dinh dưỡng', 65000),
('Biotin', '2023-04-15', '2026-04-15', 'Hỗ trợ tóc và móng', 75000),
('Calcium + Vitamin D', '2023-07-18', '2026-07-18', 'Xương chắc khỏe', 140000);


INSERT INTO orders(id, customer_id, pay_type_id, date_time) VALUES
(1, 1, 1, '2020-02-05'),
(2, 2, 2, '2020-05-20'),
(3, 3, 1, '2020-09-28'),
(4, 2, 2, '2021-02-10'),
(5, 3, 1, '2021-06-15'),
(6, 6, 2, '2021-11-22'),
(7, 4, 1, '2022-03-18'),
(8, 1, 2, '2022-07-25'),
(9, 1, 1, '2023-01-07'),
(10, 10, 2, '2023-04-18'),
(11, 10, 1, '2023-09-12'),
(12, 7, 2, '2024-02-08'),
(13, 13, 1, '2024-06-22'),
(14, 10, 2, '2024-11-05'),
(15, 15, 1, '2025-03-15');

INSERT INTO orders_detail(id, orders_id, supplements_id, quantity, date_time) VALUES
(1, 1, 1, 1, '2020-02-05 10:35:00'),
(2, 1, 4, 1, '2020-02-05 18:36:00'),
(3, 2, 2, 3, '2020-05-20 14:20:00'),
(4, 2, 5, 1, '2020-05-20 15:22:00'),
(5, 3, 3, 2, '2020-09-28 09:55:00'),
(6, 4, 6, 1, '2021-02-10 11:25:00'),
(7, 5, 7, 3, '2021-06-15 13:50:00'),
(8, 6, 8, 2, '2021-11-22 15:35:00'),
(9, 7, 1, 1, '2022-03-18 10:15:00'),
(10, 8, 9, 1, '2022-07-25 12:45:00'),
(11, 9, 10, 2, '2023-01-07 09:35:00'),
(12, 10, 4, 1, '2023-04-18 14:20:00'),
(13, 11, 9, 4, '2023-09-12 11:05:00'),
(14, 12, 15, 1, '2024-02-08 10:25:00'),
(15, 13, 7, 1, '2024-06-22 12:25:00'),
(16, 14, 15, 1, '2024-11-05 20:25:00'),
(17, 15, 8, 2, '2025-03-15 15:10:00');