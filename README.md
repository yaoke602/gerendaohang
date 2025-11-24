# Personal Navigation Dashboard

一个基于 Spring Boot + MySQL 的个人导航页面项目，支持数据库动态管理导航链接。

## ✨ 特性

- 🎨 **精美 UI** - 玻璃拟态设计，支持日夜间模式切换
- 🔍 **集成搜索** - 支持百度、Bing、谷歌三大搜索引擎
- ⏰ **实时时钟** - 显示当前时间和日期
- ❄️ **雪花特效** - 可切换的动态背景效果
- 📱 **响应式设计** - 完美适配桌面端和移动端
- 🗄️ **数据库驱动** - 所有导航数据存储在 MySQL 中，支持动态管理
- 🔄 **动态导航栏** - 导航分类从数据库自动生成

## 🛠️ 技术栈

### 后端
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL 8.0+
- Lombok

### 前端
- HTML5 + CSS3
- Vanilla JavaScript
- Google Fonts (Outfit, Noto Sans SC)

## 📦 项目结构

```
daohang/
├── backend/                          # Spring Boot 后端
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/nav/
│   │   │   │   ├── NavApplication.java          # 主程序入口
│   │   │   │   ├── controller/
│   │   │   │   │   └── NavigationController.java # REST API 控制器
│   │   │   │   ├── entity/
│   │   │   │   │   ├── Category.java            # 分类实体
│   │   │   │   │   └── Link.java                # 链接实体
│   │   │   │   └── repository/
│   │   │   │       └── CategoryRepository.java  # 数据访问层
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       │   └── index.html               # 前端页面
│   │   │       ├── application.properties       # 应用配置
│   │   │       ├── schema.sql                   # 数据库表结构
│   │   │       └── data.sql                     # 初始数据（可选）
│   │   └── pom.xml                              # Maven 配置
└── 导航2.html                                    # 独立前端版本（可选）
```
<img width="1862" height="830" alt="image" src="https://github.com/user-attachments/assets/95e13d94-6360-443c-acbc-2e879407baad" />

## 🚀 快速开始

### 前置要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 1. 克隆项目

```bash
git clone https://github.com/your-username/personal-nav-dashboard.git
cd personal-nav-dashboard
```

### 2. 配置数据库

修改 `backend/src/main/resources/application.properties`：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nav_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password  # 修改为您的 MySQL 密码
```

### 3. 初始化数据（首次运行）

如果需要初始数据，在 MySQL 中手动执行 `backend/src/main/resources/data.sql`。

### 4. 启动项目

```bash
cd backend
mvn spring-boot:run
```

### 5. 访问应用

打开浏览器访问：`http://localhost:8080`

## 📝 使用说明

### 管理导航链接

所有导航数据存储在 MySQL 数据库中，您可以通过以下方式管理：

#### 数据库表结构

**categories 表** - 导航分类
- `id` - 主键
- `name` - 分类名称（如：常用、工具、AI）
- `icon` - 显示图标（Emoji）
- `sort_order` - 排序顺序

**links 表** - 导航链接
- `id` - 主键
- `category_id` - 所属分类 ID
- `title` - 链接标题
- `description` - 链接描述
- `url` - 链接地址
- `icon` - 显示图标（Emoji）
- `sort_order` - 排序顺序

#### 添加新链接示例

```sql
-- 添加到"常用"分类（假设 category_id=1）
INSERT INTO links (category_id, title, description, url, icon) 
VALUES (1, 'GitHub', '全球最大的代码托管平台', 'https://github.com', '🐙');
```

#### 添加新分类示例

```sql
-- 添加新分类
INSERT INTO categories (name, icon, sort_order) 
VALUES ('开发工具', '🔧', 8);
```

### API 接口

- `GET /api/nav` - 获取所有导航数据（包括分类和链接）

## 🎨 自定义

### 修改主题颜色

编辑 `backend/src/main/resources/static/index.html` 中的 CSS 变量：

```css
:root {
    --accent: #6c5ce7;  /* 主题色 */
    --bg: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);  /* 背景渐变 */
}
```

### 禁用雪花特效

在页面右上角点击雪花按钮即可切换。

## 📄 License

MIT License

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📧 联系方式

如有问题，请通过 GitHub Issues 联系。

---

⭐ 如果这个项目对您有帮助，请给个 Star 支持一下！
