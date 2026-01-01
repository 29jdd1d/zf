USE class_league_management;

-- 插入默认管理员用户
INSERT INTO sys_user (username, password, real_name, phone, email, role, status) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '系统管理员', '13800138000', 'admin@example.com', 'ADMIN', 1);
-- 密码: 123456

-- 插入院系数据
INSERT INTO department (name, code, description, contact_person, contact_phone) VALUES
('计算机科学与技术学院', 'CS', '负责计算机相关专业教学和管理', '张老师', '13800138001'),
('电子信息工程学院', 'EE', '负责电子信息相关专业教学和管理', '李老师', '13800138002'),
('经济管理学院', 'EM', '负责经济管理相关专业教学和管理', '王老师', '13800138003');

-- 插入活动类型数据
INSERT INTO activity_type (name, code, description, points, sort_order) VALUES
('团日活动', 'LEAGUE_DAY', '团支部组织的主题团日活动', 10, 1),
('志愿服务', 'VOLUNTEER', '各类志愿服务活动', 15, 2),
('学术讲座', 'LECTURE', '学术讲座、研讨会等', 8, 3),
('文体活动', 'SPORTS_ARTS', '文体竞赛、文艺演出等', 10, 4),
('社会实践', 'PRACTICE', '社会实践、调研活动等', 20, 5),
('技能培训', 'TRAINING', '各类技能培训活动', 12, 6);

-- 插入积分规则数据
INSERT INTO points_rule (name, type, points, description, status) VALUES
('参加团日活动', 'ACTIVITY', 10, '参加团支部组织的团日活动', 'ACTIVE'),
('参加志愿服务', 'ACTIVITY', 15, '参加志愿服务活动', 'ACTIVE'),
('参加学术讲座', 'ACTIVITY', 8, '参加学术讲座', 'ACTIVE'),
('参加文体活动', 'ACTIVITY', 10, '参加文体活动', 'ACTIVE'),
('参加社会实践', 'ACTIVITY', 20, '参加社会实践活动', 'ACTIVE'),
('获得校级奖项', 'AWARD', 50, '获得校级荣誉或奖项', 'ACTIVE'),
('获得院级奖项', 'AWARD', 30, '获得院级荣誉或奖项', 'ACTIVE'),
('获得班级奖项', 'AWARD', 20, '获得班级荣誉或奖项', 'ACTIVE'),
('违纪处分', 'VIOLATION', -50, '受到违纪处分', 'ACTIVE');

-- 插入场地数据
INSERT INTO venue (name, location, capacity, facilities, contact_person, contact_phone, status) VALUES
('大礼堂', '行政楼3楼', 500, '投影仪、音响设备、空调', '刘老师', '13800138010', 'AVAILABLE'),
('多功能会议室', '教学楼A座201', 100, '投影仪、白板、空调', '陈老师', '13800138011', 'AVAILABLE'),
('室外运动场', '体育馆旁', 1000, '篮球场、足球场', '赵老师', '13800138012', 'AVAILABLE'),
('学术报告厅', '图书馆5楼', 300, '投影仪、音响设备、同声传译', '周老师', '13800138013', 'AVAILABLE');

-- 插入评优评先项目数据
INSERT INTO honor_award (name, type, level, description, quota, application_start_time, application_end_time, status) VALUES
('优秀团员', '个人奖项', '校级', '表彰在团的工作和学习中表现突出的团员', 50, '2026-03-01 00:00:00', '2026-03-31 23:59:59', 'OPEN'),
('优秀团干部', '个人奖项', '校级', '表彰在团的工作中表现突出的团干部', 30, '2026-03-01 00:00:00', '2026-03-31 23:59:59', 'OPEN'),
('优秀班级', '集体奖项', '校级', '表彰在班级建设中表现突出的班级', 10, '2026-03-01 00:00:00', '2026-03-31 23:59:59', 'OPEN'),
('先进团支部', '集体奖项', '校级', '表彰在团的工作中表现突出的团支部', 10, '2026-03-01 00:00:00', '2026-03-31 23:59:59', 'OPEN');
