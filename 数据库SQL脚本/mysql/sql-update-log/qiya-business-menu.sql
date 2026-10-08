-- 启芽星球：与业务功能平级的多级菜单、基础数据和示例内容
-- 可重复执行。菜单会按 10000-10299 重建；已有业务数据不会覆盖。
SET NAMES utf8mb4;

DELETE FROM `t_role_menu` WHERE `menu_id` BETWEEN 10000 AND 10299;
DELETE FROM `t_menu` WHERE `menu_id` BETWEEN 10000 AND 10299;

INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10000, '启芽星球', 1, 0, 2, '/qiya', NULL, NULL, NULL, NULL, 'StarOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10001, '工作台', 2, 10000, 1, '/qiya/workbench', '/business/qiya/workbench/workbench.vue', NULL, NULL, NULL, 'DashboardOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10002, '学习档案', 1, 10000, 2, '/qiya/archive', NULL, NULL, NULL, NULL, 'FolderOpenOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10003, '学生与家庭', 2, 10002, 1, '/qiya/student', '/business/qiya/student/student-list.vue', NULL, NULL, NULL, 'TeamOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10004, '学习数据', 2, 10002, 2, '/qiya/learn', '/business/qiya/learn/learn-board.vue', NULL, NULL, NULL, 'LineChartOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10005, '审核中心', 2, 10002, 3, '/qiya/review', '/business/qiya/review/review-list.vue', NULL, NULL, NULL, 'AuditOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10010, '英语', 1, 10000, 3, '/qiya/english', NULL, NULL, NULL, NULL, 'ReadOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10011, '词库', 2, 10010, 1, '/qiya/word', '/business/qiya/word/word-list.vue', NULL, NULL, NULL, 'BookOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10012, '闯关世界', 2, 10010, 2, '/qiya/world', '/business/qiya/world/world-list.vue', NULL, NULL, NULL, 'FlagOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10013, '磨耳朵', 2, 10010, 3, '/qiya/album', '/business/qiya/album/album-list.vue', NULL, NULL, NULL, 'SoundOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10014, '助记审核', 2, 10010, 4, '/qiya/mnemonic', '/business/qiya/mnemonic/mnemonic-list.vue', NULL, NULL, NULL, 'HighlightOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10020, '语文', 1, 10000, 4, '/qiya/chinese', NULL, NULL, NULL, NULL, 'FileTextOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10021, '诗词库', 2, 10020, 1, '/qiya/poem', '/business/qiya/poem/poem-list.vue', NULL, NULL, NULL, 'ReadOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10022, '诗人馆', 2, 10020, 2, '/qiya/poet', '/business/qiya/poet/poet-list.vue', NULL, NULL, NULL, 'UserOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10030, '数学', 1, 10000, 5, '/qiya/math', NULL, NULL, NULL, NULL, 'NumberOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10031, '知识点', 2, 10030, 1, '/qiya/knowledge', '/business/qiya/knowledge/knowledge-list.vue', NULL, NULL, NULL, 'ApartmentOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10040, '科目与年级', 2, 10000, 6, '/qiya/subject', '/business/qiya/subject/subject-list.vue', NULL, NULL, NULL, 'AppstoreOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10050, '家长与运营', 1, 10000, 7, '/qiya/family', NULL, NULL, NULL, NULL, 'HomeOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10051, '家庭会员', 2, 10050, 1, '/qiya/member', '/business/qiya/member/member-board.vue', NULL, NULL, NULL, 'CrownOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10052, '重置与付费', 2, 10050, 2, '/qiya/reset', '/business/qiya/reset/reset-list.vue', NULL, NULL, NULL, 'ReloadOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10053, '推广看板', 2, 10050, 3, '/qiya/channel', '/business/qiya/channel/channel-list.vue', NULL, NULL, NULL, 'NotificationOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10054, '今日与节气', 2, 10050, 4, '/qiya/ops', '/business/qiya/ops/ops-board.vue', NULL, NULL, NULL, 'CalendarOutlined', NULL, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10100, '查询', 3, 10001, 1, NULL, NULL, 1, 'qiya:workbench:query', 'qiya:workbench:query', NULL, 10001, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10101, '查询', 3, 10003, 1, NULL, NULL, 1, 'qiya:student:query', 'qiya:student:query', NULL, 10003, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10102, '查询', 3, 10004, 1, NULL, NULL, 1, 'qiya:workbench:query', 'qiya:workbench:query', NULL, 10004, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10103, '查询', 3, 10005, 1, NULL, NULL, 1, 'qiya:review:query', 'qiya:review:query', NULL, 10005, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10104, '新建', 3, 10005, 2, NULL, NULL, 1, 'qiya:review:add', 'qiya:review:add', NULL, 10005, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10105, '编辑', 3, 10005, 3, NULL, NULL, 1, 'qiya:review:update', 'qiya:review:update', NULL, 10005, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10106, '删除', 3, 10005, 4, NULL, NULL, 1, 'qiya:review:delete', 'qiya:review:delete', NULL, 10005, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10107, '审核', 3, 10005, 5, NULL, NULL, 1, 'qiya:review:decide', 'qiya:review:decide', NULL, 10005, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10108, '查询', 3, 10011, 1, NULL, NULL, 1, 'qiya:word:query', 'qiya:word:query', NULL, 10011, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10109, '新建', 3, 10011, 2, NULL, NULL, 1, 'qiya:word:add', 'qiya:word:add', NULL, 10011, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10110, '编辑', 3, 10011, 3, NULL, NULL, 1, 'qiya:word:update', 'qiya:word:update', NULL, 10011, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10111, '删除', 3, 10011, 4, NULL, NULL, 1, 'qiya:word:delete', 'qiya:word:delete', NULL, 10011, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10112, '查询', 3, 10012, 1, NULL, NULL, 1, 'qiya:world:query', 'qiya:world:query', NULL, 10012, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10113, '新建', 3, 10012, 2, NULL, NULL, 1, 'qiya:world:add', 'qiya:world:add', NULL, 10012, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10114, '编辑', 3, 10012, 3, NULL, NULL, 1, 'qiya:world:update', 'qiya:world:update', NULL, 10012, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10115, '删除', 3, 10012, 4, NULL, NULL, 1, 'qiya:world:delete', 'qiya:world:delete', NULL, 10012, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10116, '查询', 3, 10013, 1, NULL, NULL, 1, 'qiya:album:query', 'qiya:album:query', NULL, 10013, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10117, '新建', 3, 10013, 2, NULL, NULL, 1, 'qiya:album:add', 'qiya:album:add', NULL, 10013, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10118, '编辑', 3, 10013, 3, NULL, NULL, 1, 'qiya:album:update', 'qiya:album:update', NULL, 10013, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10119, '删除', 3, 10013, 4, NULL, NULL, 1, 'qiya:album:delete', 'qiya:album:delete', NULL, 10013, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10120, '查询', 3, 10014, 1, NULL, NULL, 1, 'qiya:review:query', 'qiya:review:query', NULL, 10014, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10121, '新建', 3, 10014, 2, NULL, NULL, 1, 'qiya:review:add', 'qiya:review:add', NULL, 10014, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10122, '编辑', 3, 10014, 3, NULL, NULL, 1, 'qiya:review:update', 'qiya:review:update', NULL, 10014, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10123, '删除', 3, 10014, 4, NULL, NULL, 1, 'qiya:review:delete', 'qiya:review:delete', NULL, 10014, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10124, '审核', 3, 10014, 5, NULL, NULL, 1, 'qiya:review:decide', 'qiya:review:decide', NULL, 10014, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10125, '查询', 3, 10021, 1, NULL, NULL, 1, 'qiya:poem:query', 'qiya:poem:query', NULL, 10021, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10126, '新建', 3, 10021, 2, NULL, NULL, 1, 'qiya:poem:add', 'qiya:poem:add', NULL, 10021, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10127, '编辑', 3, 10021, 3, NULL, NULL, 1, 'qiya:poem:update', 'qiya:poem:update', NULL, 10021, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10128, '删除', 3, 10021, 4, NULL, NULL, 1, 'qiya:poem:delete', 'qiya:poem:delete', NULL, 10021, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10129, '查询', 3, 10022, 1, NULL, NULL, 1, 'qiya:poet:query', 'qiya:poet:query', NULL, 10022, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10130, '新建', 3, 10022, 2, NULL, NULL, 1, 'qiya:poet:add', 'qiya:poet:add', NULL, 10022, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10131, '编辑', 3, 10022, 3, NULL, NULL, 1, 'qiya:poet:update', 'qiya:poet:update', NULL, 10022, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10132, '删除', 3, 10022, 4, NULL, NULL, 1, 'qiya:poet:delete', 'qiya:poet:delete', NULL, 10022, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10133, '查询', 3, 10031, 1, NULL, NULL, 1, 'qiya:knowledge:query', 'qiya:knowledge:query', NULL, 10031, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10134, '新建', 3, 10031, 2, NULL, NULL, 1, 'qiya:knowledge:add', 'qiya:knowledge:add', NULL, 10031, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10135, '编辑', 3, 10031, 3, NULL, NULL, 1, 'qiya:knowledge:update', 'qiya:knowledge:update', NULL, 10031, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10136, '删除', 3, 10031, 4, NULL, NULL, 1, 'qiya:knowledge:delete', 'qiya:knowledge:delete', NULL, 10031, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10137, '查询', 3, 10040, 1, NULL, NULL, 1, 'qiya:subject:query', 'qiya:subject:query', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10138, '新建', 3, 10040, 2, NULL, NULL, 1, 'qiya:subject:add', 'qiya:subject:add', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10139, '编辑', 3, 10040, 3, NULL, NULL, 1, 'qiya:subject:update', 'qiya:subject:update', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10140, '删除', 3, 10040, 4, NULL, NULL, 1, 'qiya:subject:delete', 'qiya:subject:delete', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10141, '内容已齐', 3, 10040, 5, NULL, NULL, 1, 'qiya:subject:ready', 'qiya:subject:ready', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10142, '开放', 3, 10040, 6, NULL, NULL, 1, 'qiya:subject:open', 'qiya:subject:open', NULL, 10040, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10143, '查询', 3, 10051, 1, NULL, NULL, 1, 'qiya:member:query', 'qiya:member:query', NULL, 10051, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10144, '查询', 3, 10052, 1, NULL, NULL, 1, 'qiya:reset:query', 'qiya:reset:query', NULL, 10052, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10145, '查询', 3, 10053, 1, NULL, NULL, 1, 'qiya:channel:query', 'qiya:channel:query', NULL, 10053, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10146, '新建', 3, 10053, 2, NULL, NULL, 1, 'qiya:channel:add', 'qiya:channel:add', NULL, 10053, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10147, '编辑', 3, 10053, 3, NULL, NULL, 1, 'qiya:channel:update', 'qiya:channel:update', NULL, 10053, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10148, '删除', 3, 10053, 4, NULL, NULL, 1, 'qiya:channel:delete', 'qiya:channel:delete', NULL, 10053, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10149, '查询', 3, 10054, 1, NULL, NULL, 1, 'qiya:ops:query', 'qiya:ops:query', NULL, 10054, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10150, '新建', 3, 10054, 2, NULL, NULL, 1, 'qiya:ops:add', 'qiya:ops:add', NULL, 10054, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10151, '编辑', 3, 10054, 3, NULL, NULL, 1, 'qiya:ops:update', 'qiya:ops:update', NULL, 10054, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());
INSERT INTO `t_menu` (`menu_id`, `menu_name`, `menu_type`, `parent_id`, `sort`, `path`, `component`, `perms_type`, `api_perms`, `web_perms`, `icon`, `context_menu_id`, `frame_flag`, `frame_url`, `cache_flag`, `visible_flag`, `disabled_flag`, `deleted_flag`, `create_user_id`, `create_time`, `update_time`) VALUES (10152, '删除', 3, 10054, 4, NULL, NULL, 1, 'qiya:ops:delete', 'qiya:ops:delete', NULL, 10054, 0, NULL, 0, 1, 0, 0, 1, NOW(), NOW());

INSERT INTO `t_role_menu` (`role_id`, `menu_id`, `update_time`, `create_time`)
SELECT DISTINCT rm.role_id, m.menu_id, NOW(), NOW()
FROM `t_role_menu` rm
INNER JOIN `t_menu` m ON m.menu_id BETWEEN 10000 AND 10299 AND m.deleted_flag = 0
WHERE rm.menu_id = 138
AND NOT EXISTS (SELECT 1 FROM `t_role_menu` x WHERE x.role_id = rm.role_id AND x.menu_id = m.menu_id);

CREATE TABLE IF NOT EXISTS `t_qiya_word` (
  `word_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `en` varchar(64) NOT NULL COMMENT '英文',
  `zh` varchar(64) NOT NULL COMMENT '中文',
  `phonetic` varchar(64) NULL COMMENT '音标',
  `grade_name` varchar(32) NULL COMMENT '年级',
  `theme_name` varchar(32) NULL COMMENT '主题',
  `source_name` varchar(32) NULL COMMENT '来源',
  `mastery` varchar(16) NULL COMMENT '掌握',
  `sentence` varchar(255) NULL COMMENT '例句',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽词库';

CREATE TABLE IF NOT EXISTS `t_qiya_world` (
  `world_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `world_name` varchar(32) NOT NULL COMMENT '世界',
  `level_name` varchar(64) NOT NULL COMMENT '关卡',
  `question_mix` varchar(64) NULL COMMENT '题型',
  `pass_rate` varchar(16) NULL COMMENT '通过率',
  `retry_avg` varchar(16) NULL COMMENT '平均重试',
  `weak_word` varchar(32) NULL COMMENT '掉点词',
  `note` varchar(255) NULL COMMENT '说明',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`world_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽闯关';

CREATE TABLE IF NOT EXISTS `t_qiya_album` (
  `album_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_name` varchar(64) NOT NULL COMMENT '专辑',
  `level_name` varchar(32) NULL COMMENT '适龄',
  `minutes` varchar(16) NULL COMMENT '时长',
  `enabled_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '启用',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽磨耳朵';

CREATE TABLE IF NOT EXISTS `t_qiya_poem` (
  `poem_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `author_name` varchar(32) NOT NULL COMMENT '作者',
  `dynasty` varchar(16) NULL COMMENT '朝代',
  `kind_name` varchar(16) NULL COMMENT '体裁',
  `source_note` varchar(64) NULL COMMENT '出处',
  `content` varchar(500) NOT NULL COMMENT '原文',
  `note_text` varchar(500) NULL COMMENT '注释',
  `translation` varchar(500) NULL COMMENT '译文',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`poem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽诗词';

CREATE TABLE IF NOT EXISTS `t_qiya_poet` (
  `poet_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `poet_name` varchar(32) NOT NULL COMMENT '诗人',
  `dynasty` varchar(16) NULL COMMENT '朝代',
  `intro` varchar(500) NULL COMMENT '简介',
  `script_text` varchar(500) NULL COMMENT '讲解',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`poet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽诗人';

CREATE TABLE IF NOT EXISTS `t_qiya_knowledge` (
  `knowledge_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `knowledge_name` varchar(64) NOT NULL COMMENT '知识点',
  `grade_name` varchar(32) NULL COMMENT '年级',
  `practice_type` varchar(64) NULL COMMENT '练法',
  `prev_name` varchar(64) NULL COMMENT '前置',
  `status_name` varchar(16) NULL COMMENT '状态',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`knowledge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽知识点';

CREATE TABLE IF NOT EXISTS `t_qiya_student` (
  `student_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `child_name` varchar(32) NOT NULL COMMENT '孩子',
  `grade_name` varchar(32) NULL COMMENT '年级',
  `parent_name` varchar(32) NULL COMMENT '家长',
  `recent_text` varchar(128) NULL COMMENT '最近在学',
  `status_name` varchar(16) NULL COMMENT '状态',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽学生';

CREATE TABLE IF NOT EXISTS `t_qiya_review` (
  `review_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_code` varchar(16) NOT NULL COMMENT '科目编码',
  `subject_name` varchar(16) NOT NULL COMMENT '科目',
  `target_name` varchar(64) NOT NULL COMMENT '对象',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `status_name` varchar(16) NOT NULL COMMENT '状态',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽审核';

CREATE TABLE IF NOT EXISTS `t_qiya_subject` (
  `subject_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_name` varchar(32) NOT NULL COMMENT '科目',
  `status_name` varchar(16) NULL COMMENT '状态',
  `grade_scope` varchar(32) NULL COMMENT '年级范围',
  `entry_text` varchar(64) NULL COMMENT '学生端入口',
  `content_ready` tinyint(1) NOT NULL DEFAULT 0 COMMENT '内容已齐',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽科目';

CREATE TABLE IF NOT EXISTS `t_qiya_reset` (
  `reset_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scene_name` varchar(64) NOT NULL COMMENT '场景',
  `reset_kind` varchar(32) NULL COMMENT '类型',
  `reset_count` int NULL COMMENT '次数',
  `pay_text` varchar(64) NULL COMMENT '会不会收费',
  `moved_text` varchar(128) NULL COMMENT '接着做了什么',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`reset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽重置';

CREATE TABLE IF NOT EXISTS `t_qiya_channel` (
  `channel_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_name` varchar(64) NOT NULL COMMENT '素材',
  `status_name` varchar(16) NULL COMMENT '状态',
  `show_place` varchar(64) NULL COMMENT '出现位置',
  `family_count` int NULL COMMENT '家庭数',
  `reopen_rate` varchar(16) NULL COMMENT '7日回打开',
  `habit_count` int NULL COMMENT '习惯次数',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽推广';

CREATE TABLE IF NOT EXISTS `t_qiya_ops` (
  `ops_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `today_task` varchar(64) NOT NULL COMMENT '今日任务',
  `festival_name` varchar(16) NOT NULL COMMENT '节气',
  `skin_name` varchar(32) NOT NULL COMMENT '皮肤',
  `member_open` tinyint(1) NOT NULL DEFAULT 0 COMMENT '会员开通',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ops_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='启芽今日';

INSERT INTO t_qiya_word (en, zh, phonetic, grade_name, theme_name, source_name, mastery, sentence) SELECT 'apple', '苹果', '/æpl/', '一年级', '食物', '拍照', '已掌握', 'This is an apple.' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_word WHERE en = 'apple' AND deleted_flag = 0);
INSERT INTO t_qiya_word (en, zh, phonetic, grade_name, theme_name, source_name, mastery, sentence) SELECT 'cat', '猫', '/kæt/', '一年级', '动物', '词库', '学习中', 'The cat is sleeping.' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_word WHERE en = 'cat' AND deleted_flag = 0);
INSERT INTO t_qiya_word (en, zh, phonetic, grade_name, theme_name, source_name, mastery, sentence) SELECT 'book', '书', '/bʊk/', '二年级', '校园', '词库', '薄弱', 'I read a book.' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_word WHERE en = 'book' AND deleted_flag = 0);
INSERT INTO t_qiya_word (en, zh, phonetic, grade_name, theme_name, source_name, mastery, sentence) SELECT 'moon', '月亮', '/muːn/', '一年级', '自然', '诗词', '未学', 'The moon is bright.' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_word WHERE en = 'moon' AND deleted_flag = 0);
INSERT INTO t_qiya_world (world_name, level_name, question_mix, pass_rate, retry_avg, weak_word, note) SELECT '森林入口', '第1关', '听音选词', '72%', '1.4', 'apple', '先听再选，错了回到这一关。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_world WHERE world_name = '森林入口' AND deleted_flag = 0);
INSERT INTO t_qiya_world (world_name, level_name, question_mix, pass_rate, retry_avg, weak_word, note) SELECT '河边营地', '第2关', '看图拼写', '54%', '2.1', 'book', '薄弱词会在这一关再出现一次。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_world WHERE world_name = '河边营地' AND deleted_flag = 0);
INSERT INTO t_qiya_album (album_name, level_name, minutes, enabled_flag) SELECT '早餐五分钟', '一年级', '5分钟', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_album WHERE album_name = '早餐五分钟' AND deleted_flag = 0);
INSERT INTO t_qiya_album (album_name, level_name, minutes, enabled_flag) SELECT '睡前故事', '二年级', '8分钟', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_album WHERE album_name = '睡前故事' AND deleted_flag = 0);
INSERT INTO t_qiya_poem (title, author_name, dynasty, kind_name, source_note, content, note_text, translation) SELECT '静夜思', '李白', '唐', '五言绝句', '语文一年级', CONCAT('床前明月光，', CHAR(10), '疑是地上霜。', CHAR(10), '举头望明月，', CHAR(10), '低头思故乡。'), '月亮照在床前，孩子先读一遍，再说说想家的感觉。', '明亮的月光洒在床前，好像地上结了一层霜。抬起头看见明月，低下头想起故乡。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_poem WHERE title = '静夜思' AND deleted_flag = 0);
INSERT INTO t_qiya_poem (title, author_name, dynasty, kind_name, source_note, content, note_text, translation) SELECT '咏鹅', '骆宾王', '唐', '五言诗', '语文一年级', CONCAT('鹅，鹅，鹅，', CHAR(10), '曲项向天歌。', CHAR(10), '白毛浮绿水，', CHAR(10), '红掌拨清波。'), '先学鹅叫的样子，再看白毛和红掌。', '鹅弯着脖子对着天唱歌，白色的身子浮在绿水上，红掌拨起清水波纹。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_poem WHERE title = '咏鹅' AND deleted_flag = 0);
INSERT INTO t_qiya_poet (poet_name, dynasty, intro, script_text) SELECT '李白', '唐', '唐代诗人。孩子先记住他喜欢月亮，再读静夜思。', '小朋友，李白看着月亮，想起了自己的家。我们一起把静夜思读慢一点。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_poet WHERE poet_name = '李白' AND deleted_flag = 0);
INSERT INTO t_qiya_poet (poet_name, dynasty, intro, script_text) SELECT '骆宾王', '唐', '小时候就写出了咏鹅。可以先学他观察小动物。', '骆宾王看见白鹅在水里游，就把颜色和动作说出来。我们也去找一只小动物说说看。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_poet WHERE poet_name = '骆宾王' AND deleted_flag = 0);
INSERT INTO t_qiya_knowledge (knowledge_name, grade_name, practice_type, prev_name, status_name) SELECT '20以内加法', '一年级', '口算', '认识数字', '练习中' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_knowledge WHERE knowledge_name = '20以内加法' AND deleted_flag = 0);
INSERT INTO t_qiya_knowledge (knowledge_name, grade_name, practice_type, prev_name, status_name) SELECT '表内乘法', '二年级', '口算', '20以内加法', '未练' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_knowledge WHERE knowledge_name = '表内乘法' AND deleted_flag = 0);
INSERT INTO t_qiya_knowledge (knowledge_name, grade_name, practice_type, prev_name, status_name) SELECT '认识时钟', '一年级', '选择', '认识数字', '已掌握' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_knowledge WHERE knowledge_name = '认识时钟' AND deleted_flag = 0);
INSERT INTO t_qiya_student (child_name, grade_name, parent_name, recent_text, status_name) SELECT '小芽', '一年级', '妈妈', '昨天读了静夜思', '在学' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_student WHERE child_name = '小芽' AND deleted_flag = 0);
INSERT INTO t_qiya_student (child_name, grade_name, parent_name, recent_text, status_name) SELECT '小禾', '二年级', '爸爸', '单词 book 还不稳定', '在学' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_student WHERE child_name = '小禾' AND deleted_flag = 0);
INSERT INTO t_qiya_review (subject_code, subject_name, target_name, content, status_name) SELECT 'en', '英语', 'apple', '苹果圆圆的，像一个小苹果。', '草稿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_review WHERE subject_code = 'en' AND target_name = 'apple' AND deleted_flag = 0);
INSERT INTO t_qiya_review (subject_code, subject_name, target_name, content, status_name) SELECT 'en', '英语', 'book', '这个句子有点脏', '草稿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_review WHERE subject_code = 'en' AND target_name = 'book' AND deleted_flag = 0);
INSERT INTO t_qiya_review (subject_code, subject_name, target_name, content, status_name) SELECT 'cn', '语文', '静夜思', '月亮', '草稿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_review WHERE subject_code = 'cn' AND target_name = '静夜思' AND content = '月亮' AND deleted_flag = 0);
INSERT INTO t_qiya_review (subject_code, subject_name, target_name, content, status_name) SELECT 'cn', '语文', '静夜思注释', '月亮照在床前，像给孩子一封来自故乡的信。', '草稿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_review WHERE subject_code = 'cn' AND target_name = '静夜思注释' AND deleted_flag = 0);
INSERT INTO t_qiya_review (subject_code, subject_name, target_name, content, status_name) SELECT 'math', '数学', '20以内加法', '因为3个再添2个就是5个。', '草稿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_review WHERE subject_code = 'math' AND target_name = '20以内加法' AND deleted_flag = 0);
INSERT INTO t_qiya_subject (subject_name, status_name, grade_scope, entry_text, content_ready) SELECT '英语', '已开放', '一年级至六年级', '只出现在学生地图，不加第五个主按钮', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_subject WHERE subject_name = '英语' AND deleted_flag = 0);
INSERT INTO t_qiya_subject (subject_name, status_name, grade_scope, entry_text, content_ready) SELECT '语文', '已开放', '一年级至六年级', '只出现在学生地图，不加第五个主按钮', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_subject WHERE subject_name = '语文' AND deleted_flag = 0);
INSERT INTO t_qiya_subject (subject_name, status_name, grade_scope, entry_text, content_ready) SELECT '数学', '已开放', '一年级至六年级', '只出现在学生地图，不加第五个主按钮', 1 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_subject WHERE subject_name = '数学' AND deleted_flag = 0);
INSERT INTO t_qiya_subject (subject_name, status_name, grade_scope, entry_text, content_ready) SELECT '科学', '未开放', '未定', '学生端不出现', 0 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_subject WHERE subject_name = '科学' AND deleted_flag = 0);
INSERT INTO t_qiya_reset (scene_name, reset_kind, reset_count, pay_text, moved_text) SELECT '错题再练', '练习重置', 12, '未开通', '先看错因，再做一次。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_reset WHERE scene_name = '错题再练' AND deleted_flag = 0);
INSERT INTO t_qiya_reset (scene_name, reset_kind, reset_count, pay_text, moved_text) SELECT '单词闯关重开', '付费场景', 4, '未开通', '失败后留在本关，不出现价格。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_reset WHERE scene_name = '单词闯关重开' AND deleted_flag = 0);
INSERT INTO t_qiya_reset (scene_name, reset_kind, reset_count, pay_text, moved_text) SELECT '磨耳朵重听', '练习重置', 7, '未开通', '同一段再听一遍。' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_reset WHERE scene_name = '磨耳朵重听' AND deleted_flag = 0);
INSERT INTO t_qiya_channel (material_name, status_name, show_place, family_count, reopen_rate, habit_count) SELECT '班级海报', '投放中', '家长端首页', 18, '36%', 7 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_channel WHERE material_name = '班级海报' AND deleted_flag = 0);
INSERT INTO t_qiya_channel (material_name, status_name, show_place, family_count, reopen_rate, habit_count) SELECT '睡前打卡卡', '草稿', '学生端今日', 6, '12%', 3 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_channel WHERE material_name = '睡前打卡卡' AND deleted_flag = 0);
INSERT INTO t_qiya_ops (today_task, festival_name, skin_name, member_open) SELECT '读一首静夜思', '寒露', '秋叶', 0 FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_qiya_ops WHERE deleted_flag = 0);