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

update supply_status set supply_company='company', supply_name='name', supplied_name='name', supply_addr='010', purchase_text='none' where supply_num = 39;



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

select * from purchase_buy_recordall;
select * from supply_status;


update purchase_buy_recordall set status=4 where in_warehouse_date is not null;

create table product(
    p_id number(3) primary key,
    p_si varchar2(5char) not null,
    p_type varchar2(10char) not null,
    p_quantity number(2) not null,
    p_name varchar2(20char) not null,
    p_unitCost varchar2(10char) not null,
    p_minStock varchar2(10char) not null,
    p_maxStock varchar2(10char) not null,
    p_manufacturer number(3) not null
);
alter table product modify p_maxStock varchar2(10char) null;

create sequence product_seq;

insert into product values(product_seq.nextval, 'ml', 'a', 1, 'aaa', 11, 1, 1, 1);
insert into product values(333, 'ml', 'a', 1, 'aaa', 11, 1, 1, 1);

select * from product;
select * from purchase_buy_record order by record_buy_num desc;
select * from purchase_buy_recordall order by recordall_buy_num desc;
select * from supply_status order by supply_num desc;

select * from purchase_buy_recordall;
select * from purchase_buy_recordall order by status asc, purchase_date asc;

select *
from
(select * from product a join purchase_buy_record b on a.p_id = b.p_id) a
join
(select * from supply_status a join purchase_buy_recordall b on a.supply_num = b.supply_num) b
on a.recordall_buy_num = b.recordall_buy_num;

create table unit(
   unit varchar2(10char) primary key 
);

insert into unit values('mL');

select * from unit;

SELECT supply_status.supply_company
FROM supply_status
INNER JOIN purchase_buy_recordall ON supply_status.supply_num = purchase_buy_recordall.supply_num
WHERE supply_company like '%Си%';





