insert into users(email,password,enabled)
	values('admin@gmail.com','$2a$10$mfeVuYS97iE.kCcsXzetjeK.4QQefecymMRtuRJwgXHc7jUHqrse2',true);
insert into authorities(user_email,authority)
	values('admin@gmail.com','ROLE_ADMIN');

insert into users(email,password, enabled, first_name, second_name)
    values ('Lawyer@gmail.com', '$2a$10$mfeVuYS97iE.kCcsXzetjeK.4QQefecymMRtuRJwgXHc7jUHqrse2', true, 'Ivan', 'Ivanov');

insert into authorities(user_email,authority)
	values('Lawyer@gmail.com','ROLE_USER');