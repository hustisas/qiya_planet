### 数据库脚本

qiya_planet 默认使用 MySQL。初始化脚本基于 SmartAdmin，已将数据库名改为 `qiya_planet`。

#### 第一次

如果是第一次部署，执行 `qiya_planet.sql`。

原 SmartAdmin 脚本 `smart_admin_v3.sql` 仍保留作为来源对照，日常请使用 `qiya_planet.sql`。

#### 更新

后续结构变更放到 `sql-update-log` 目录，按版本从小到大执行。
