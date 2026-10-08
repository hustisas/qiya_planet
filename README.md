# qiya_planet

专注萌芽学习

**qiya_planet** 基于 [SmartAdmin](https://github.com/1024-lab/smart-admin) 二次改造。

- Java 包名：`com.study.planet.wp`
- 管理端：Vue3 + Vite + Ant Design Vue
- 移动端：uni-app（H5 / 微信小程序）
- 后端：SpringBoot 2 + Sa-Token + MyBatis-Plus

### 许可证

本项目使用 **MIT License**。

- 原作品 Copyright (c) 2020 1024-lab
- 修改部分 Copyright (c) 2026 hustisas
- 修改部分 Copyright (c) 2026 qiya_planet

完整授权文本见 [LICENSE](./LICENSE)，来源说明见 [NOTICE.md](./NOTICE.md)。

### 模块

- `smart-admin-api-java8-springboot2`：后端 API
- `smart-admin-web-javascript`：管理端
- `smart-app`：移动端 / 小程序
- `数据库SQL脚本`：初始化脚本，数据库名为 `qiya_planet`

### 本地启动

1. 导入 `数据库SQL脚本/mysql/qiya_planet.sql`
2. 修改后端 `sa-base.yaml` 中的数据库、Redis 连接
3. 启动后端 `AdminApplication`（包名 `com.study.planet.wp.admin`）
4. 管理端执行 `npm run dev`
5. 移动端执行 `npm run dev:h5` 或 `npm run dev:mp-weixin`

默认管理员账号为 `admin`，请在首次登录后立即修改密码。
