-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into customer (c_id, c_firstname, c_lastname, c_email, c_phone, c_password, c_country, c_street, c_zip, c_houseno, c_city)
values ('johndoe', 'John', 'Doe', 'johndoe@gmail.com', '123456789', 'password', 'USA', 'Main Street', '12345', '1', 'New York');

insert into fieldtype (ft_id, ft_name, ft_description)
values (nextval('fieldtype_seq'), 'Tennis', 'Text field');

insert into field (f_close_time, f_open_time, f_timeslot_duration, f_id, f_type, f_name)
values ('20:00', '07:00', 60, nextval('field_seq'), 1, 'Tennis Court 1');
