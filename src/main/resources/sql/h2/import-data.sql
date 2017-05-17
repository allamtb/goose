--配置

insert into CONFIG (key,VALUE) VALUES ('proxy','172.17.18.80:8080');
insert into CONFIG (key,VALUE) VALUES ('stopwordThreshold','3');

--模块
insert into moudal (moudal_id,moudal_name) VALUES  (1,'正文提取');
insert into moudal (moudal_id,moudal_name) VALUES  (2,'分詞');
insert into moudal (moudal_id,moudal_name) VALUES  (3,'停止詞計算');