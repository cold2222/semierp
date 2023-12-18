create table company(
    c_no number(10) PRIMARY KEY,
    c_e_id number(10) not null,
    c_name varchar2(50 char) not null,
    c_keeper varchar2(50 char) not null,
    c_phone varchar2(20 char) not null,
    c_addr varchar2(200 char) not null,
    c_text varchar2(300 char)
);
create SEQUENCE company_seq START WITH 1 INCREMENT BY 1;
insert into company values(1, 2, 'namae', 'namae', 'name', 'name', 'none');
select * from company;
select * from company order by c_no desc;

create table contract(
    c_contract_no number(10) PRIMARY KEY,
    s_c_no number(10) not null,
    c_e_id number(10) not null,
    c_created_date date not null,
    c_due_date date not null,
    c_delivery_date date,
    c_completed_date date,
    c_status number(1) not null,
    c_type number(1) not null
);
create SEQUENCE contract_seq START WITH 1 INCREMENT BY 1;
select * from company order by c_no desc;

create table contract_items(
    ci_no number(10) PRIMARY KEY,
    ci_c_contract_no number(10) not null,
    ci_p_id number(3) not null,
    ci_count number(10) not null,
    ci_unit_price number(10) not null
);
create SEQUENCE contract_items_seq START WITH 1 INCREMENT BY 1;

create table product(
    p_id number(3) PRIMARY KEY,
    p_si varchar2(5 char) not null,
    p_type varchar2(10 char) not null,
    p_quantity number(10) not null,
    p_name varchar2(20 char) not null,
    p_unitcost number(10) not null,
    p_minstock number(10) not null,
    p_maxstock number(10) not null,
    p_manufacturer number(3) not null
);
create SEQUENCE product_seq START WITH 1 INCREMENT BY 1;

create table unit(
    unit varchar2(5 char) PRIMARY KEY
);
select * from unit;

create table type(
    type varchar2(10 char) PRIMARY KEY
);
