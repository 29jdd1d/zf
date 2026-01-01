# 校园班团管理系统

## 项目概述

校园班团管理系统是一个基于Spring Boot的企业级后端管理系统，用于管理高校的班级、团支部、学生信息、活动组织、评优评先、积分管理、通知公告和资料共享等功能。

## 技术栈

- **后端框架**: Spring Boot 2.7.18
- **持久层**: MyBatis Plus 3.5.3.1
- **数据库**: MySQL 8.0+
- **文件存储**: MinIO 8.5.7
- **认证**: JWT (jjwt 0.11.5)
- **API文档**: Knife4j 3.0.3
- **工具类**: Hutool 5.8.23

## 核心功能

### 1. 用户认证与授权
- 用户登录（JWT Token生成）
- 密码加密存储（BCrypt）
- Token验证拦截器
- 多角色支持（管理员、辅导员、学生）

### 2. 班级组织管理
- 院系管理（CRUD）
- 班级管理（CRUD）
- 团支部管理（CRUD）
- 班委会成员管理

### 3. 人员信息管理
- 学生信息管理（分页查询、条件搜索）
- 辅导员信息管理
- 团员信息管理

### 4. 活动管理
- 活动类型管理
- 活动模板管理
- 活动发布与编辑
- 活动报名功能
- 活动签到功能
- 场地管理

### 5. 评优评先管理
- 评优评先项目管理
- 申请提交
- 审批流程
- 结果公示

### 6. 团员积分管理
- 积分规则配置
- 积分自动计算
- 积分记录查询
- 积分排行榜

### 7. 通知公告
- 通知发布（支持富文本）
- 分类推送（全部/班级/个人）
- 阅读状态跟踪
- 附件上传

### 8. 资料共享
- 文件上传（MinIO）
- 文件下载
- 文件分类管理
- 访问权限控制

### 9. 数据统计与可视化
- 班级概览数据
- 活动参与率统计
- 积分排行榜
- 提供JSON接口供前端使用

## 项目结构

```
com.zf
├── ClassLeagueManagementApplication.java  # 主启动类
├── common                                   # 公共类
│   ├── Result.java                         # 统一返回结果
│   └── ResultCode.java                     # 返回状态码枚举
├── config                                   # 配置类
│   ├── Knife4jConfig.java                  # API文档配置
│   ├── MetaObjectHandlerConfig.java        # 字段自动填充配置
│   ├── MinioConfig.java                    # MinIO配置
│   ├── MybatisPlusConfig.java              # MyBatis Plus配置
│   └── WebMvcConfig.java                   # Web MVC配置
├── controller                               # 控制器层
│   ├── ActivityController.java             # 活动管理
│   ├── AuthController.java                 # 认证管理
│   ├── FileController.java                 # 文件管理
│   ├── StatisticsController.java           # 数据统计
│   ├── StudentController.java              # 学生管理
│   └── UserController.java                 # 用户管理
├── dto                                      # 数据传输对象
│   ├── ActivityDTO.java
│   ├── LoginDTO.java
│   ├── StudentDTO.java
│   └── UserDTO.java
├── entity                                   # 实体类（23个）
│   ├── Activity.java                       # 活动
│   ├── ActivityAttendance.java             # 活动签到
│   ├── ActivityRegistration.java           # 活动报名
│   ├── ActivityTemplate.java               # 活动模板
│   ├── ActivityType.java                   # 活动类型
│   ├── ClassCommittee.java                 # 班委会成员
│   ├── ClassInfo.java                      # 班级
│   ├── Department.java                     # 院系
│   ├── FileInfo.java                       # 文件信息
│   ├── HonorApplication.java               # 评优评先申请
│   ├── HonorAward.java                     # 评优评先项目
│   ├── Instructor.java                     # 辅导员
│   ├── LeagueBranch.java                   # 团支部
│   ├── LeagueMember.java                   # 团员
│   ├── Notice.java                         # 通知公告
│   ├── NoticeRead.java                     # 通知阅读记录
│   ├── PointsRecord.java                   # 积分记录
│   ├── PointsRule.java                     # 积分规则
│   ├── Resource.java                       # 资料共享
│   ├── Student.java                        # 学生
│   ├── User.java                           # 用户
│   └── Venue.java                          # 场地
├── exception                                # 异常处理
│   ├── BusinessException.java              # 业务异常
│   └── GlobalExceptionHandler.java         # 全局异常处理器
├── interceptor                              # 拦截器
│   └── JwtInterceptor.java                 # JWT认证拦截器
├── mapper                                   # Mapper接口（23个）
│   ├── ActivityAttendanceMapper.java
│   ├── ActivityMapper.java
│   ├── ActivityRegistrationMapper.java
│   ├── ActivityTemplateMapper.java
│   ├── ActivityTypeMapper.java
│   ├── ClassCommitteeMapper.java
│   ├── ClassInfoMapper.java
│   ├── DepartmentMapper.java
│   ├── FileInfoMapper.java
│   ├── HonorApplicationMapper.java
│   ├── HonorAwardMapper.java
│   ├── InstructorMapper.java
│   ├── LeagueBranchMapper.java
│   ├── LeagueMemberMapper.java
│   ├── NoticeMapper.java
│   ├── NoticeReadMapper.java
│   ├── PointsRecordMapper.java
│   ├── PointsRuleMapper.java
│   ├── ResourceMapper.java
│   ├── StudentMapper.java
│   ├── UserMapper.java
│   └── VenueMapper.java
├── service                                  # 服务层
│   ├── IActivityService.java
│   ├── IStatisticsService.java
│   ├── IStudentService.java
│   ├── IUserService.java
│   ├── MinioService.java
│   └── impl                                # 服务实现
│       ├── ActivityServiceImpl.java
│       ├── StatisticsServiceImpl.java
│       ├── StudentServiceImpl.java
│       └── UserServiceImpl.java
├── utils                                    # 工具类
│   ├── JwtUtil.java                        # JWT工具
│   ├── PasswordUtil.java                   # 密码加密工具
│   └── UserHolder.java                     # 用户信息持有者
└── vo                                       # 视图对象
    ├── LoginVO.java
    └── UserVO.java
```

## 数据库设计

系统包含23张数据表：

1. **sys_user** - 用户表
2. **department** - 院系表
3. **class_info** - 班级表
4. **league_branch** - 团支部表
5. **student** - 学生表
6. **instructor** - 辅导员表
7. **league_member** - 团员信息表
8. **class_committee** - 班委会成员表
9. **activity_type** - 活动类型表
10. **activity_template** - 活动模板表
11. **activity** - 活动表
12. **activity_registration** - 活动报名表
13. **activity_attendance** - 活动签到表
14. **venue** - 场地表
15. **honor_award** - 评优评先项目表
16. **honor_application** - 评优评先申请表
17. **points_record** - 积分记录表
18. **points_rule** - 积分规则表
19. **notice** - 通知公告表
20. **notice_read** - 通知阅读记录表
21. **resource** - 资料共享表
22. **file_info** - 文件信息表

## 快速开始

### 环境要求

- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+
- MinIO (可选，用于文件存储)

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/29jdd1d/zf.git
   cd zf
   ```

2. **创建数据库**
   ```bash
   # 执行数据库脚本
   mysql -u root -p < src/main/resources/sql/schema.sql
   mysql -u root -p < src/main/resources/sql/data.sql
   ```

3. **配置数据库连接**
   
   编辑 `src/main/resources/application.yml`：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/class_league_management
       username: root
       password: your_password
   ```

4. **配置MinIO（可选）**
   
   如果不使用MinIO，文件上传功能将不可用：
   ```yaml
   minio:
     endpoint: http://localhost:9000
     access-key: minioadmin
     secret-key: minioadmin
   ```

5. **启动项目**
   ```bash
   mvn spring-boot:run
   ```

6. **访问API文档**
   
   启动成功后访问：http://localhost:8080/api/doc.html

### 默认账号

- **用户名**: admin
- **密码**: 123456

## API接口文档

项目集成了Knife4j，启动后访问：http://localhost:8080/api/doc.html

主要接口模块：

- **认证管理** - 登录、登出
- **用户管理** - 用户CRUD、密码修改
- **学生管理** - 学生CRUD、条件查询
- **活动管理** - 活动CRUD、报名、签到
- **文件管理** - 上传、下载、删除、预览
- **数据统计** - 班级概览、活动统计、积分排行

## 配置说明

### JWT配置

```yaml
jwt:
  secret: class-league-management-secret-key-2026  # JWT密钥
  expiration: 86400000                             # Token过期时间(毫秒)
  header: Authorization                            # Token请求头名称
  prefix: Bearer                                   # Token前缀
```

### 文件上传配置

```yaml
spring:
  servlet:
    multipart:
      enabled: true
      max-file-size: 100MB      # 单个文件最大大小
      max-request-size: 100MB   # 整个请求最大大小
```

## 开发规范

- 遵循阿里巴巴Java开发规范
- 统一异常处理
- 统一返回格式
- 完整的注释文档
- RESTful API设计

## 许可证

MIT License

## 作者

zf - 2026