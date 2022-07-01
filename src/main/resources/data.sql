create database redditclone;
use redditclone;
select * from users;
select * from communities;
select * from bans;
select * from posts;
select * from reaction;

delete from reaction;

INSERT INTO users (avatar_url, description, display_name, email, password, registration_date, username, role)
VALUES ('avatar', 'ovo je admin sistema', 'ADMIN', 'admin@admin.com','$2a$04$dDm7WU35eSIg9KAZD.muq.pTlSrFz3aeWMnVOiT292iYBjpYRUmKe','1000-01-01','admin', 'ADMIN');

INSERT INTO communities(name, description, creation_date,  is_suspended, suspended_reason, ban_id, moderator_id)
VALUES ('Test1', ' ovo je moj prvi community', '1000-01-01',false,null,null,1);

