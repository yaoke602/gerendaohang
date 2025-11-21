INSERT INTO categories (id, name, icon, sort_order) VALUES (1, '常用', '✨', 1);
INSERT INTO categories (id, name, icon, sort_order) VALUES (2, '工具', '🛠️', 2);
INSERT INTO categories (id, name, icon, sort_order) VALUES (3, 'AI', '🤖', 3);
INSERT INTO categories (id, name, icon, sort_order) VALUES (4, '学习', '📚', 4);
INSERT INTO categories (id, name, icon, sort_order) VALUES (5, '影视', '🎬', 5);
INSERT INTO categories (id, name, icon, sort_order) VALUES (6, '博客', '📝', 6);
INSERT INTO categories (id, name, icon, sort_order) VALUES (7, '生活', '🛍️', 7);

-- 常用
INSERT INTO links (category_id, title, description, url, icon) VALUES (1, '百度', '有问题，百度一下', 'https://www.baidu.com', '🔍');
INSERT INTO links (category_id, title, description, url, icon) VALUES (1, 'Bilibili', '干杯！( ゜- ゜)つロ', 'https://www.bilibili.com', '📺');
INSERT INTO links (category_id, title, description, url, icon) VALUES (1, '微信网页版', '高效沟通', 'https://weixin.qq.com/', '💬');

-- 工具
INSERT INTO links (category_id, title, description, url, icon) VALUES (2, 'GitHub', '全球最大的代码托管平台', 'https://github.com', '🐙');
INSERT INTO links (category_id, title, description, url, icon) VALUES (2, '百度翻译', '多语言在线翻译', 'https://fanyi.baidu.com', '🈯');
INSERT INTO links (category_id, title, description, url, icon) VALUES (2, 'TinyPNG', '智能WebP, PNG和JPEG压缩', 'https://tinypng.com', '🐼');

-- AI
INSERT INTO links (category_id, title, description, url, icon) VALUES (3, 'ChatGPT', 'OpenAI 强大的语言模型', 'https://chat.openai.com', '🧠');
INSERT INTO links (category_id, title, description, url, icon) VALUES (3, '文心一言', '百度全新一代知识增强大语言模型', 'https://yiyan.baidu.com/', '🌌');

-- 学习
INSERT INTO links (category_id, title, description, url, icon) VALUES (4, 'MDN Web Docs', 'Web 开发者权威指南', 'https://developer.mozilla.org/zh-CN/', '🦖');
INSERT INTO links (category_id, title, description, url, icon) VALUES (4, '菜鸟教程', '学的不仅是技术，更是梦想', 'https://www.runoob.com/', '📖');
INSERT INTO links (category_id, title, description, url, icon) VALUES (4, 'Coursera', '全球顶尖在线课程', 'https://www.coursera.org/', '🎓');

-- 影视
INSERT INTO links (category_id, title, description, url, icon) VALUES (5, 'YouTube', '观看世界各地的视频', 'https://www.youtube.com', '▶️');
INSERT INTO links (category_id, title, description, url, icon) VALUES (5, '豆瓣电影', '电影评分与评论', 'https://www.douban.com/movie/', '🎬');

-- 博客
INSERT INTO links (category_id, title, description, url, icon) VALUES (6, '掘金', '代码不止，掘金不停', 'https://juejin.cn/', '🥇');
INSERT INTO links (category_id, title, description, url, icon) VALUES (6, '知乎', '有问题，就会有答案', 'https://www.zhihu.com/', '❓');
INSERT INTO links (category_id, title, description, url, icon) VALUES (6, 'CSDN', '专业开发者社区', 'https://blog.csdn.net/', '💻');

-- 生活
INSERT INTO links (category_id, title, description, url, icon) VALUES (7, '淘宝网', '淘！我喜欢', 'https://www.taobao.com', '🛒');
INSERT INTO links (category_id, title, description, url, icon) VALUES (7, '京东', '多快好省', 'https://www.jd.com', '🐶');
INSERT INTO links (category_id, title, description, url, icon) VALUES (7, '中国天气网', '关注天气，关爱生活', 'http://www.weather.com.cn/', '🌤️');
