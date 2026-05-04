-- 自動では実行されません。
drop table if exists APP_LOG;

create table APP_LOG (
   LOG_ID        number(10, 0) auto_increment 
  ,LOG_MESSAGE   varchar(100)
  ,LOGGED_AT     timestamp
  ,constraint PK_APP_LOG primary key (
     LOG_ID        
  )
);

insert into APP_LOG(LOG_MESSAGE, LOGGED_AT)
  values ('メッセージ', CURRENT_TIMESTAMP);
 