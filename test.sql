create table sales_company(
    sc_no number(10) PRIMARY KEY,
    sc_e_id number(10) not null,
    sc_name varchar2(50 char) not null,
    sc_keeper varchar2(50 char) not null,
    sc_phone varchar2(20 char) not null,
    sc_addr varchar2(200 char) not null,
    sc_text varchar2(300 char)
);
create SEQUENCE sales_company_seq START WITH 1 INCREMENT BY 1;

create table import_company(
    ic_no number(10) PRIMARY KEY,
    ic_e_id number(10) not null,
    ic_name varchar2(50 char) not null,
    ic_keeper varchar2(50 char) not null,
    ic_phone varchar2(20 char) not null,
    ic_addr varchar2(200 char) not null,
    ic_text varchar2(300 char)
);
create SEQUENCE import_company_seq START WITH 1 INCREMENT BY 1;

select * from import_company;

create table sell_contract(
    s_contract_no number(10) PRIMARY KEY,
    s_sc_no number(10) not null,
    s_e_id number(10) not null,
    s_contract date not null,
    s_due_date date not null,
    s_delivery_date date,
    s_date date,
    s_status number(1) not null
);
create SEQUENCE sell_contract_seq START WITH 1 INCREMENT BY 1;
create table buy_contract(
    b_contract_no number(10) PRIMARY KEY,
    b_ic_no number(10) not null,
    b_e_id number(10) not null,
    b_contract date not null,
    b_arrival_date date not null,
    b_delivery_date date,
    b_date date,
    b_status number(1) not null
);
create SEQUENCE buy_contract_seq START WITH 1 INCREMENT BY 1;

create table sell_item(
    si_no number(10) PRIMARY KEY,
    si_s_contract_no number(10) not null,
    si_p_id number(3) not null,
    si_sell_count number(10) not null,
    si_unit_price number(10) not null
);
create SEQUENCE sell_item_seq START WITH 1 INCREMENT BY 1;

create table buy_item(
    bi_no number(10) PRIMARY KEY,
    bi_b_contract_no number(10) not null,
    bi_p_id number(3) not null,
    bi_buy_count number(10) not null,
    bi_unit_price number(10) not null
);
create SEQUENCE buy_item_seq START WITH 1 INCREMENT BY 1;

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

create table type(
    type varchar2(10 char) PRIMARY KEY
);
