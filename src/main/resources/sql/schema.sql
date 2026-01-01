-- 创建数据库
CREATE DATABASE IF NOT EXISTS class_league_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE class_league_management;

-- 1. 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    avatar VARCHAR(255) COMMENT '头像URL',
    role VARCHAR(20) NOT NULL COMMENT '角色: ADMIN, INSTRUCTOR, STUDENT',
    status TINYINT DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 2. 院系表
CREATE TABLE IF NOT EXISTS department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '院系ID',
    name VARCHAR(100) NOT NULL COMMENT '院系名称',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '院系代码',
    description TEXT COMMENT '院系描述',
    contact_person VARCHAR(50) COMMENT '联系人',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='院系表';

-- 3. 班级表
CREATE TABLE IF NOT EXISTS class_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '班级ID',
    name VARCHAR(100) NOT NULL COMMENT '班级名称',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '班级代码',
    department_id BIGINT NOT NULL COMMENT '所属院系ID',
    grade VARCHAR(20) NOT NULL COMMENT '年级',
    major VARCHAR(100) COMMENT '专业',
    instructor_id BIGINT COMMENT '辅导员ID',
    student_count INT DEFAULT 0 COMMENT '学生人数',
    description TEXT COMMENT '班级描述',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_department (department_id),
    INDEX idx_instructor (instructor_id),
    INDEX idx_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- 4. 团支部表
CREATE TABLE IF NOT EXISTS league_branch (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '团支部ID',
    name VARCHAR(100) NOT NULL COMMENT '团支部名称',
    class_id BIGINT NOT NULL COMMENT '所属班级ID',
    secretary_id BIGINT COMMENT '团支书ID',
    member_count INT DEFAULT 0 COMMENT '团员人数',
    establishment_date DATE COMMENT '成立日期',
    description TEXT COMMENT '团支部描述',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_class (class_id),
    INDEX idx_secretary (secretary_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团支部表';

-- 5. 学生表
CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '学生ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    student_no VARCHAR(50) NOT NULL UNIQUE COMMENT '学号',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别',
    birth_date DATE COMMENT '出生日期',
    id_card VARCHAR(18) COMMENT '身份证号',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    class_id BIGINT COMMENT '班级ID',
    department_id BIGINT COMMENT '院系ID',
    enrollment_date DATE COMMENT '入学日期',
    political_status VARCHAR(20) COMMENT '政治面貌',
    home_address TEXT COMMENT '家庭住址',
    emergency_contact VARCHAR(50) COMMENT '紧急联系人',
    emergency_phone VARCHAR(20) COMMENT '紧急联系电话',
    total_points INT DEFAULT 0 COMMENT '总积分',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_user (user_id),
    INDEX idx_student_no (student_no),
    INDEX idx_class (class_id),
    INDEX idx_department (department_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 6. 辅导员表
CREATE TABLE IF NOT EXISTS instructor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '辅导员ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    employee_no VARCHAR(50) NOT NULL UNIQUE COMMENT '工号',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    department_id BIGINT COMMENT '所属院系ID',
    office_location VARCHAR(100) COMMENT '办公地点',
    title VARCHAR(50) COMMENT '职称',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_user (user_id),
    INDEX idx_employee_no (employee_no),
    INDEX idx_department (department_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='辅导员表';

-- 7. 团员信息表
CREATE TABLE IF NOT EXISTS league_member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '团员ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    league_branch_id BIGINT COMMENT '团支部ID',
    membership_no VARCHAR(50) COMMENT '团员证号',
    join_date DATE COMMENT '入团日期',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE-在籍 TRANSFERRED-转出 QUIT-退团',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_student (student_id),
    INDEX idx_branch (league_branch_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团员信息表';

-- 8. 班委会成员表
CREATE TABLE IF NOT EXISTS class_committee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '班委ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    class_id BIGINT NOT NULL COMMENT '班级ID',
    position VARCHAR(50) NOT NULL COMMENT '职位: 班长, 团支书, 学习委员等',
    term_start DATE COMMENT '任期开始日期',
    term_end DATE COMMENT '任期结束日期',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE-在任 EXPIRED-已卸任',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_student (student_id),
    INDEX idx_class (class_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班委会成员表';

-- 9. 活动类型表
CREATE TABLE IF NOT EXISTS activity_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '活动类型ID',
    name VARCHAR(50) NOT NULL COMMENT '类型名称',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '类型代码',
    description TEXT COMMENT '类型描述',
    icon VARCHAR(255) COMMENT '图标',
    points INT DEFAULT 0 COMMENT '默认积分',
    sort_order INT DEFAULT 0 COMMENT '排序',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动类型表';

-- 10. 活动模板表
CREATE TABLE IF NOT EXISTS activity_template (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '模板ID',
    name VARCHAR(100) NOT NULL COMMENT '模板名称',
    type_id BIGINT NOT NULL COMMENT '活动类型ID',
    description TEXT COMMENT '活动描述模板',
    duration INT COMMENT '默认时长(分钟)',
    max_participants INT COMMENT '默认最大参与人数',
    points INT COMMENT '默认积分',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_type (type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动模板表';

-- 11. 活动表
CREATE TABLE IF NOT EXISTS activity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '活动ID',
    title VARCHAR(200) NOT NULL COMMENT '活动标题',
    type_id BIGINT NOT NULL COMMENT '活动类型ID',
    organizer_id BIGINT NOT NULL COMMENT '组织者ID',
    organizer_type VARCHAR(20) NOT NULL COMMENT '组织者类型: CLASS, LEAGUE_BRANCH, DEPARTMENT',
    description TEXT COMMENT '活动描述',
    location VARCHAR(200) COMMENT '活动地点',
    venue_id BIGINT COMMENT '场地ID',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    registration_deadline DATETIME COMMENT '报名截止时间',
    max_participants INT COMMENT '最大参与人数',
    current_participants INT DEFAULT 0 COMMENT '当前参与人数',
    points INT DEFAULT 0 COMMENT '活动积分',
    status VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT-草稿 PENDING-待审核 APPROVED-已通过 REJECTED-已拒绝 ONGOING-进行中 COMPLETED-已完成 CANCELLED-已取消',
    approval_status VARCHAR(20) COMMENT '审批状态',
    approver_id BIGINT COMMENT '审批人ID',
    approval_time DATETIME COMMENT '审批时间',
    approval_comment TEXT COMMENT '审批意见',
    cover_image VARCHAR(255) COMMENT '封面图片',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_type (type_id),
    INDEX idx_organizer (organizer_id),
    INDEX idx_status (status),
    INDEX idx_start_time (start_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

-- 12. 活动报名表
CREATE TABLE IF NOT EXISTS activity_registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '报名ID',
    activity_id BIGINT NOT NULL COMMENT '活动ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    registration_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
    status VARCHAR(20) DEFAULT 'REGISTERED' COMMENT '状态: REGISTERED-已报名 CANCELLED-已取消 ATTENDED-已参加',
    remark TEXT COMMENT '备注',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    UNIQUE KEY uk_activity_student (activity_id, student_id),
    INDEX idx_activity (activity_id),
    INDEX idx_student (student_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动报名表';

-- 13. 活动签到表
CREATE TABLE IF NOT EXISTS activity_attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '签到ID',
    activity_id BIGINT NOT NULL COMMENT '活动ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    check_in_time DATETIME COMMENT '签到时间',
    check_out_time DATETIME COMMENT '签退时间',
    status VARCHAR(20) DEFAULT 'ABSENT' COMMENT '状态: PRESENT-已签到 ABSENT-缺席 LATE-迟到 LEAVE-请假',
    remark TEXT COMMENT '备注',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    UNIQUE KEY uk_activity_student (activity_id, student_id),
    INDEX idx_activity (activity_id),
    INDEX idx_student (student_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动签到表';

-- 14. 场地表
CREATE TABLE IF NOT EXISTS venue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '场地ID',
    name VARCHAR(100) NOT NULL COMMENT '场地名称',
    location VARCHAR(200) COMMENT '场地位置',
    capacity INT COMMENT '容纳人数',
    facilities TEXT COMMENT '设施说明',
    contact_person VARCHAR(50) COMMENT '负责人',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    status VARCHAR(20) DEFAULT 'AVAILABLE' COMMENT '状态: AVAILABLE-可用 UNAVAILABLE-不可用 MAINTENANCE-维护中',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='场地表';

-- 15. 评优评先项目表
CREATE TABLE IF NOT EXISTS honor_award (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '项目ID',
    name VARCHAR(100) NOT NULL COMMENT '项目名称',
    type VARCHAR(50) NOT NULL COMMENT '类型: 个人奖项, 集体奖项',
    level VARCHAR(50) COMMENT '级别: 校级, 院级, 班级',
    description TEXT COMMENT '项目描述',
    quota INT COMMENT '名额',
    application_start_time DATETIME COMMENT '申请开始时间',
    application_end_time DATETIME COMMENT '申请截止时间',
    evaluation_criteria TEXT COMMENT '评选标准',
    status VARCHAR(20) DEFAULT 'OPEN' COMMENT '状态: OPEN-开放 CLOSED-关闭 COMPLETED-已完成',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评优评先项目表';

-- 16. 评优评先申请表
CREATE TABLE IF NOT EXISTS honor_application (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '申请ID',
    award_id BIGINT NOT NULL COMMENT '项目ID',
    applicant_id BIGINT NOT NULL COMMENT '申请人ID',
    applicant_type VARCHAR(20) NOT NULL COMMENT '申请类型: STUDENT-学生 CLASS-班级',
    application_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    materials TEXT COMMENT '申请材料说明',
    self_evaluation TEXT COMMENT '自我评价',
    status VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态: PENDING-待审核 APPROVED-已通过 REJECTED-已拒绝',
    reviewer_id BIGINT COMMENT '审核人ID',
    review_time DATETIME COMMENT '审核时间',
    review_comment TEXT COMMENT '审核意见',
    final_result VARCHAR(20) COMMENT '最终结果: AWARDED-获奖 NOT_AWARDED-未获奖',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_award (award_id),
    INDEX idx_applicant (applicant_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评优评先申请表';

-- 17. 积分记录表
CREATE TABLE IF NOT EXISTS points_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    points INT NOT NULL COMMENT '积分变动(正数为加分, 负数为减分)',
    type VARCHAR(50) NOT NULL COMMENT '类型: ACTIVITY-活动 AWARD-获奖 VIOLATION-违纪 MANUAL-手动调整',
    source_id BIGINT COMMENT '来源ID(如活动ID, 获奖ID)',
    source_type VARCHAR(50) COMMENT '来源类型',
    reason TEXT COMMENT '积分说明',
    operator_id BIGINT COMMENT '操作人ID',
    record_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_student (student_id),
    INDEX idx_type (type),
    INDEX idx_record_time (record_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分记录表';

-- 18. 积分规则表
CREATE TABLE IF NOT EXISTS points_rule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '规则ID',
    name VARCHAR(100) NOT NULL COMMENT '规则名称',
    type VARCHAR(50) NOT NULL COMMENT '规则类型: ACTIVITY, AWARD, VIOLATION',
    points INT NOT NULL COMMENT '积分值',
    description TEXT COMMENT '规则描述',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE-启用 INACTIVE-停用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分规则表';

-- 19. 通知公告表
CREATE TABLE IF NOT EXISTS notice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '通知ID',
    title VARCHAR(200) NOT NULL COMMENT '标题',
    content TEXT COMMENT '内容(支持富文本)',
    type VARCHAR(50) COMMENT '类型: ANNOUNCEMENT-公告 NOTICE-通知 NEWS-新闻',
    level VARCHAR(20) COMMENT '级别: URGENT-紧急 IMPORTANT-重要 NORMAL-普通',
    publisher_id BIGINT NOT NULL COMMENT '发布人ID',
    target_type VARCHAR(20) NOT NULL COMMENT '推送目标: ALL-全部 CLASS-班级 STUDENT-个人',
    target_id BIGINT COMMENT '目标ID(班级ID或学生ID)',
    publish_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    status VARCHAR(20) DEFAULT 'DRAFT' COMMENT '状态: DRAFT-草稿 PUBLISHED-已发布 WITHDRAWN-已撤回',
    read_count INT DEFAULT 0 COMMENT '阅读数',
    attachment_ids TEXT COMMENT '附件ID列表(JSON)',
    top_flag TINYINT DEFAULT 0 COMMENT '置顶标识: 0-否 1-是',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_publisher (publisher_id),
    INDEX idx_status (status),
    INDEX idx_publish_time (publish_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- 20. 通知阅读记录表
CREATE TABLE IF NOT EXISTS notice_read (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    notice_id BIGINT NOT NULL COMMENT '通知ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    read_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '阅读时间',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE KEY uk_notice_user (notice_id, user_id),
    INDEX idx_notice (notice_id),
    INDEX idx_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知阅读记录表';

-- 21. 资料共享表
CREATE TABLE IF NOT EXISTS resource (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '资料ID',
    title VARCHAR(200) NOT NULL COMMENT '标题',
    description TEXT COMMENT '描述',
    category VARCHAR(50) COMMENT '分类: 学习资料, 活动材料, 文档模板等',
    file_id BIGINT NOT NULL COMMENT '文件ID',
    uploader_id BIGINT NOT NULL COMMENT '上传人ID',
    target_type VARCHAR(20) NOT NULL COMMENT '共享范围: ALL-全部 CLASS-班级 DEPARTMENT-院系',
    target_id BIGINT COMMENT '目标ID',
    download_count INT DEFAULT 0 COMMENT '下载次数',
    status VARCHAR(20) DEFAULT 'PUBLISHED' COMMENT '状态: DRAFT-草稿 PUBLISHED-已发布 WITHDRAWN-已撤回',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_uploader (uploader_id),
    INDEX idx_category (category),
    INDEX idx_file (file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资料共享表';

-- 22. 文件信息表
CREATE TABLE IF NOT EXISTS file_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '文件ID',
    original_name VARCHAR(255) NOT NULL COMMENT '原始文件名',
    stored_name VARCHAR(255) NOT NULL COMMENT '存储文件名',
    file_path VARCHAR(500) NOT NULL COMMENT '文件路径',
    file_size BIGINT COMMENT '文件大小(字节)',
    file_type VARCHAR(50) COMMENT '文件类型',
    mime_type VARCHAR(100) COMMENT 'MIME类型',
    storage_type VARCHAR(20) DEFAULT 'MINIO' COMMENT '存储类型: MINIO, LOCAL',
    uploader_id BIGINT NOT NULL COMMENT '上传人ID',
    bucket_name VARCHAR(100) COMMENT '存储桶名称',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除',
    INDEX idx_uploader (uploader_id),
    INDEX idx_stored_name (stored_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件信息表';
