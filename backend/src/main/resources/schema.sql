DROP TABLE IF EXISTS links;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    icon VARCHAR(10),
    sort_order INT DEFAULT 0
);

CREATE TABLE links (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    url VARCHAR(500) NOT NULL,
    icon VARCHAR(10),
    sort_order INT DEFAULT 0,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
