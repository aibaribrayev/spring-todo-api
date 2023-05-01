insert into user_details(id,birth_date,name)
values(10001, current_date(), 'Burotto');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'Nureke');

insert into todo (id, description, due_date, status, user_id)
values (1, 'Finish homework', '2023-05-01 18:00:00.000000', 'Incomplete', 10002);

