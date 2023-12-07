create table distri_record_test(
    record_num number(10) primary key,
    company_sell number(10) not null,
    delivery_date date not null,
    sell_date date
);

create sequence distri_record_test_seq;

insert into distri_record_test values(distri_record_test_seq.nextval, '1', sysdate, sysdate);


select * from distri_record_test;