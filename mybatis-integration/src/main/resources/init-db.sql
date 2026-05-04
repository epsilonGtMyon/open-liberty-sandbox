-- 自動では実行されません。
drop table if exists APP_LOG;

create table APP_LOG (
   SEQ number(10, 0) auto_increment 
  ,LOG_MESSAGE   varchar(100)
  ,CREATED_AT    timestamp
  ,UPDATED_AT    timestamp
  ,constraint PK_APP_LOG primary key (
     SEQ 
  )
);
