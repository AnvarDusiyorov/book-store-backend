insert into users(email,password)
	values('admin@gmail.com','$2a$10$mfeVuYS97iE.kCcsXzetjeK.4QQefecymMRtuRJwgXHc7jUHqrse2');
insert into authorities(user_email,authority)
	values('admin@gmail.com','ROLE_ADMIN');

insert into users(email,password, first_name, second_name)
    values ('Lawyer@gmail.com', '$2a$10$mfeVuYS97iE.kCcsXzetjeK.4QQefecymMRtuRJwgXHc7jUHqrse2','Ivan', 'Ivanov');

insert into authorities(user_email,authority)
	values('Lawyer@gmail.com','ROLE_USER');





