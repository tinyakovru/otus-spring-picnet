INSERT INTO "users" (EMAIL,NICKNAME,pass) VALUES ('john@gmail.com','john','john');
INSERT INTO "users" (EMAIL,NICKNAME,pass) VALUES ('admin@gmail.com','admin','admin');
INSERT INTO "users" (EMAIL,NICKNAME,pass) VALUES ('user@gmail.com','user','user');

INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('kartinka 1','beutifull pic',    '/pic1613903535610_L.png','/pic1613903535610_S.png','/pic1613903535610_XS.png',1,1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('kartinka 2','very beutifull pic','/pic78950178_1614008930562_L.png','/pic78950178_1614008930562_S.png','/pic78950178_1614008930562_XS.png',1,1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('kartinka 3','very beutifull pic3','/pic1613909936529_L.png','/pic1613909936529_S.png','/pic1613909936529_XS.png',5,1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('landscape 4','oooo so amaysin',   '/pic1613922942951_L.png','/pic1613922942951_S.png','/pic1613922942951_XS.png',5,1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('kartinka 5','beutifull pic',     '/pic1613950260007_L.png','/pic1613950260007_S.png','/pic1613950260007_XS.png',1,1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id,status) VALUES ('kartinka 6','very beutifull pic','/pic1613991303064_L.png','/pic1613991303064_S.png','/pic1613991303064_XS.png',1,1);

INSERT INTO "tag" (name, user_id)  values ('photo',1);
INSERT INTO "tag" (name, user_id)  values ('art',2);
INSERT INTO "tag" (name, user_id)  values ('portraite',2);
INSERT INTO "tag" (name, user_id)  values ('sea',1);
INSERT INTO "tag" (name, user_id)  values ('car',3);
INSERT INTO "tag" (name, user_id)  values ('animal',2);

insert into comment (text, pic_id, user_id) values ('dfgafga asdfasdfads fasd fd fasd',1,2);
insert into comment (text, pic_id, user_id) values ('krasivo',1,3);
insert into comment (text, pic_id, user_id) values ('very good',1,2);
insert into comment (text, pic_id, user_id) values ('not good',2,2);

insert into pic_tag (pic_id, tag_id) VALUES (1,1);
insert into pic_tag (pic_id, tag_id) VALUES (2,1);
insert into pic_tag (pic_id, tag_id) VALUES (3,1);
insert into pic_tag (pic_id, tag_id) VALUES (4,1);
insert into pic_tag (pic_id, tag_id) VALUES (5,1);
insert into pic_tag (pic_id, tag_id) VALUES (6,1);

insert into pic_tag (pic_id, tag_id) VALUES (1,3);
insert into pic_tag (pic_id, tag_id) VALUES (1,4);
insert into pic_tag (pic_id, tag_id) VALUES (2,2);
insert into pic_tag (pic_id, tag_id) VALUES (2,5);
insert into pic_tag (pic_id, tag_id) VALUES (3,6);
insert into pic_tag (pic_id, tag_id) VALUES (4,2);

insert into favorite (pic_id, user_id) values (1,2);
insert into favorite (pic_id, user_id) values (1,3);
insert into favorite (pic_id, user_id) values (2,1);
insert into favorite (pic_id, user_id) values (2,2);
insert into favorite (pic_id, user_id) values (3,1);