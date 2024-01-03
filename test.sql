    create table company(
        c_no number(10) PRIMARY KEY,
        c_name varchar2(50 char) not null,
        c_keeper varchar2(50 char) not null,
        c_phone varchar2(20 char) not null,
        c_addr varchar2(200 char) not null,
        c_text varchar2(300 char)
    );
ALTER TABLE company
DROP COLUMN c_e_id;
create SEQUENCE company_seq START WITH 1 INCREMENT BY 1;
insert into company values(1, 'namae', 'namae', 'name', 'name', 'none');
select * from company;
select * from company order by c_no desc;

create table contract(
    c_contract_no number(10) PRIMARY KEY,
    c_c_no number(10) not null,
    c_e_id number(10) not null,
    c_created_date date not null,
    c_due_date date not null,
    c_delivery_date date,
    c_completed_date date,
    c_status number(1) not null,
    c_type number(1) not null
);
drop table contract;
select * from company;
ALTER TABLE table_name ADD new_column_name datatype;
ALTER TABLE table_name DROP COLUMN old_column_name;
insert into contract values(98, 1, 2, '2023-12-28', '2023-12-28', null, null, 1, 1);
create SEQUENCE contract_seq START WITH 1 INCREMENT BY 1;
select * from company order by c_no desc;
select * from contract;
delete contract where c_c_no = 1;
select a.*, b.e_name, b.e_deptno, b.e_rank, c.c_name from contract a inner join employee b on a.c_e_id = b.e_no inner join company c on a.c_c_no = c.c_no where a.c_contract_no = 98;

select contract.*, company.c_name from contract, company where contract.c_c_no = company.c_no order by c_contract_no desc;

create table contract_items(
    ci_no number(10) PRIMARY KEY,
    ci_c_contract_no number(10) not null,
    ci_p_id number(3) not null,
    ci_count number(10) not null,
    ci_unit_price number(10) not null
);
drop table contract_items;
create SEQUENCE contract_items_seq START WITH 1 INCREMENT BY 1;

create table product(
    p_id number(3) PRIMARY KEY,
    p_si varchar2(5 char) not null,
    p_type varchar2(10 char) not null,
    p_quantity number(10),
    p_name varchar2(20 char) not null,
    p_unitcost number(10) not null,
    p_minstock number(10) not null,
    p_maxstock number(10) not null,
    p_manufacturer varchar2(100 char)
);

drop table product;

create SEQUENCE product_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE product MODIFY (p_manufacturer NUMBER(10));
insert into product values(1, 'L', 'oil', 3, 'dkdk', 3939, 9393, 3939, 1);
select * from product;
create table unit(
    unit varchar2(5 char) PRIMARY KEY
);
insert into unit values('g');
select * from type;
select * from contract;
select * from contract_items;
create table type(
    type varchar2(10 char) PRIMARY KEY
);
SELECT contract_seq.CURRVAL FROM dual;
delete contract;
drop sequence contract_seq;
create sequence contract_seq;
select * from contract;
select * from contract_items;
SELECT contract_seq.CURRVAL FROM dual;
delete contract_items;
create table employee(
    e_no number(10) primary key,
    e_pw varchar2(64char) not null,
    e_deptno number(3),
    e_name varchar2(20char) not null,
    e_rank varchar2(20char) not null,
    e_tel varchar2(15char) not null,
    e_email varchar2(30char) not null,
    e_joined_company date not null
);
drop table employee;
insert into employee values(5, 'dada', 102, 'ÁØÇö', 'd', '01065505547', 'jhjhjh@gmial.com', '2023-09-05');
insert into employee values(2, 'dado', 2, 'jfh', 'd', '01065755547', 'jdgyhjh@gmial.com', '2023-09-05');
insert into employee values(3, 'dadb', 3, 'jffh', 'd', '01068905547', 'jgdyhjh@gmial.com', '2023-09-05');
insert into employee values(4, 'dadf', 5, 'jhgd', 'd', '01062505547', 'jhjjfgjh@gmial.com', '2023-09-05');
insert into employee values(5, 'dad', 4, 'jhfg', 'd', '01065356547', 'jhjhjjhfh@gmial.com', '2023-09-05');
insert into employee values(6, 'da', 6, 'jhh', 'd', '0106587647', 'jhjhjhjf@gmial.com', '2023-09-05');
insert into employee values(7, 'a', 7, 'jhyj', 'd', '01066866547', 'jhjhjjsrh@gmial.com', '2023-09-05');
insert into employee values(8, 'dfsgada', 8, 'jjh', 'd', '01047505547', 'sjjhjhjh@gmial.com', '2023-09-05');
insert into employee values(9, 'daa', 9, 'jhh', 'dj', '01065786547', 'xjhjhjh@gmial.com', '2023-09-05');
insert into employee values(10, 'dsa', 10, 'jfh', 'd', '01046405547', 'dxjhjhjh@gmial.com', '2023-09-05');
insert into employee values(11, 'ddda', 11, 'jfih', 'd', '01075505547', 'ytjhjhjh@gmial.com', '2023-09-05');
insert into employee values(12, 'dfda', 12, 'jdh', 'd', '0106855547', 'tjhjhjh@gmial.com', '2023-09-05');
insert into employee values(13, 'dasa', 13, 'juyth', 'd', '01067805547', 'ujhjhjh@gmial.com', '2023-09-05');
select * from employee; 
select * from contract;
select * from contract_items;
select * from company order by c_no desc;
select * from employee where e_name like '%j%';
select * from employee where e_name like '%ÁØ%' AND (e_deptno = 101 OR e_deptno = 102);

SELECT * FROM contract JOIN contract_items ON contract.c_contract_no = contract_items.ci_c_contract_no WHERE contract.c_contract_no = 87;
