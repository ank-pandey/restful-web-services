insert into user_details (id, birth_date, name) 
values (1001, current_date(), 'TestName1');

insert into user_details (id, birth_date, name) 
values (1002, current_date(), 'TestName2');

insert into user_details (id, birth_date, name) 
values (1003, current_date(), 'TestName3');

insert into post (id, description, user_id) 
values (2001, 'This is my first Post', 1001);

insert into post (id, description, user_id) 
values (2002, 'This is my second Post', 1001);
insert into post (id, description, user_id) 
values (2003, 'This is my third Post', 1001);
insert into post (id, description, user_id) 
values (2004, 'This is my first Post', 1002);
insert into post (id, description, user_id) 
values (2005, 'This is my first Post', 1003);
insert into post (id, description, user_id) 
values (2006, 'This is my second Post', 1003);