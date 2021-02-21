INSERT INTO "users" (EMAIL,NICKNAME,pass,sol) VALUES ('john@gmail.com','john','PASSSS3','SOL2233');
INSERT INTO "users" (EMAIL,NICKNAME,pass,sol) VALUES ('admin@gmail.com','admin','admin','');
INSERT INTO "users" (EMAIL,NICKNAME,pass,sol) VALUES ('user@gmail.com','user','user','');

INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka1','beutifull pic',     'img1_l.jpg','img1_s.jpg','img1_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka2','very beutifull pic','img2_l.jpg','img2_s.jpg','img2_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka3','very beutifull pic3','img3_l.jpg','img3_s.jpg','img3_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('landscape4','oooo so amaysin',   'img4_l.jpg','img4_s.jpg','img4_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka5','beutifull pic',     'img5_l.jpg','img1_s.jpg','img1_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka6','very beutifull pic','img6_l.jpg','img2_s.jpg','img2_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka7','very beutifull pic3','img7_l.jpg','img3_s.jpg','img3_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('landscape8','oooo so amaysin',   'img8_l.jpg','img4_s.jpg','img4_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka9','beutifull pic',     'img9_l.jpg','img1_s.jpg','img1_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka10','very beutifull pic','img10_l.jpg','img2_s.jpg','img2_xs.jpg',1);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('kartinka11','very beutifull pic3','img11_l.jpg','img3_s.jpg','img3_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('landscape12','oooo so amaysin',   'img12_l.jpg','img4_s.jpg','img4_xs.jpg',5);
INSERT INTO "pic" (title,descr,url_l,url_s,url_xs,user_id) VALUES ('landscape13','oooo so amaysin',   'img13_l.jpg','img4_s.jpg','img4_xs.jpg',5);

INSERT INTO "tag" (name, user_id)  values ('landscape',1);
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
insert into pic_tag (pic_id, tag_id) VALUES (11,1);
insert into pic_tag (pic_id, tag_id) VALUES (12,1);
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