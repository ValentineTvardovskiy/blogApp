INSERT INTO blog_user (id, created, updated, name, email) VALUES
(1, NOW(), NOW(), 'John Doe', 'john@example.com'),
(2, NOW(), NOW(), 'Jane Doe', 'jane@example.com');

INSERT INTO article (id, created, updated, title, content, published_date, author_id) VALUES
(1, NOW(), NOW(), 'First Article', 'This is the first article', NOW(), 1),
(2, NOW(), NOW(), 'Second Article', 'This is the second article', NOW(), 2),
(3, NOW(), NOW(), 'Third Article', 'This is the third article', null, 2);

INSERT INTO comment (id, created, updated, content, article_id) VALUES
(1, NOW(), NOW(), 'Great article! Thanks for sharing.', 1),
(2, NOW(), NOW(), 'I really enjoyed reading this.', 1),
(3, NOW(), NOW(), 'Can you provide more details on this topic?', 1);

SELECT setval('blog_user_id_seq', (SELECT MAX(id) FROM article) + 1, false);
SELECT setval('article_id_seq', (SELECT MAX(id) FROM article) + 1, false);
SELECT setval('comment_id_seq', (SELECT MAX(id) FROM article) + 1, false);