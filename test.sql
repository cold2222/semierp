create table distri_record_test(
    record_num number(10) primary key,
    company_sell number(10) not null,
    delivery_date date not null,
    sell_date date
);

create sequence distri_record_test_seq;

insert into distri_record_test values(distri_record_test_seq.nextval, '1', sysdate, sysdate);


select * from distri_record_test;



create table supply_status(
    supply_num number(10) primary key,
    supply_company varchar2(20char) not null,
    supply_name varchar2(20char) not null,
    supplied_name varchar2(20char) not null,
    supply_addr varchar2(20char) not null,
    purchase_text varchar2(100char) not null
);

create sequence supply_status_seq;

insert into supply_status values(supply_status_seq.nextval, 'yamato', 'jh', 'kd', '0339393939', 'text');

select * from supply_status;


create table purchase_buy_recordall(
    recordall_buy_num number(10) primary key,
    supply_num number(10) not null,
    purchase_date date not null,
    transaction_date date not null,
    in_warehouse_date date,
    status number(1) not null
);
create sequence purchase_buy_recordall_seq;

select * from purchase_buy_recordall;

create table purchase_buy_record(
record_buy_num number(10) primary key,
recordall_buy_num number(10) not null,
p_id number(3) not null,
record_count number(10) not null,
record_price number(10) not null
);

create sequence purchase_buy_record_seq;

select * from purchase_buy_record;




