/* users Table Start */
CREATE TABLE users(
	userNo NUMBER PRIMARY KEY,
	id VARCHAR2(50) UNIQUE NOT NULL,
	userName VARCHAR2(100) NOT NULL,
	password VARCHAR2(50) NOT NULL,
	joinDate DATE NOT NULL
);

CREATE SEQUENCE seq_users_no
INCREMENT BY 1
START WITH 1
NOCACHE;

SELECT id FROM users;

DELETE FROM users;
DROP TABLE users;
DROP SEQUENCE seq_users_no;
/* users Table End*/
---------------------------------------
/* blog Table Start */
CREATE TABLE blog(
	id VARCHAR2(50) PRIMARY KEY,
	blogTitle VARCHAR2(200) NOT NULL,
	logoFile VARCHAR2(200),
	CONSTRAINT u_blog_fk FOREIGN KEY (id)
	REFERENCES users(id)
);

SELECT A.id, A.blogTitle, A.logoFile, c.cateName
FROM (SELECT u.id, b.blogTitle, b.logoFile
	  FROM blog b, users u
      WHERE b.id = u.id) A, category c
WHERE c.id = A.id;

DELETE FROM blog;
SELECT id FROM blog;
DROP TABLE blog;

/* blog Table End*/
---------------------------------------
/* category Table Start*/
CREATE TABLE category(
	cateNo NUMBER PRIMARY KEY,
	id VARCHAR2(50),
	cateName VARCHAR2(200) NOT NULL,
	description VARCHAR2(500),
	regDate DATE NOT NULL,
	CONSTRAINT u_category_fk FOREIGN KEY (id)
	REFERENCES users(id)
);

CREATE SEQUENCE seq_category_no
INCREMENT BY 1
START WITH 1
NOCACHE;

SELECT cateName, regDate
FROM category
WHERE cateNo = 0;

SELECT cateNo
	   ,id
	   ,cateName
	   ,description
	   ,regDate
	   ,(SELECT count(postNo)
		FROM post
		WHERE category.cateNo = post.cateNo
		) postCount 
FROM category
WHERE cateNo = 4;

SELECT cateNo
	  ,cateName
	  ,description
	  ,(SELECT count(postNo)
		FROM post
		WHERE category.cateNo = post.cateNo
		) postCount
FROM category
WHERE id = 'jimmy'
ORDER BY cateNo DESC;

SELECT * FROM CATEGORY; 
DELETE FROM category;
DROP TABLE category;
DROP SEQUENCE seq_category_no;

SELECT * FROM CATEGORY;
/* category Table End */
---------------------------------------
/* post Table Start */
CREATE TABLE post(
	postNo NUMBER PRIMARY KEY,
	cateNo NUMBER,
	postTitle VARCHAR2(300) NOT NULL,
	postContent VARCHAR2(4000) NOT NULL,
	regDate DATE NOT NULL,
	CONSTRAINT c_post_fk FOREIGN KEY (cateNo)
	REFERENCES category(cateNo)
);

SELECT postTitle, regDate, postContent, cateNo
FROM post
WHERE cateNo = 0;

SELECT count(*)
FROM post p, category c
WHERE c.cateNo = p.cateNo
AND p.cateNo = 6;

CREATE SEQUENCE seq_post_no
INCREMENT BY 1
START WITH 1
NOCACHE;

SELECT postTitle, postContent
FROM post
WHERE postNo = 9
SELECT * FROM post;

DELETE FROM post;
DROP TABLE post;
DROP SEQUENCE seq_post_no;
/* post Table End */
---------------------------------------
/* comments Table Start */
CREATE TABLE comments(
	cmtNo NUMBER PRIMARY KEY,
	postNo NUMBER,
	userNo NUMBER,
	cmtContent VARCHAR2(1000) NOT NULL,
	regDate DATE NOT NULL,
	CONSTRAINT p_comments_fk FOREIGN KEY (postNO)
	REFERENCES post(postNo),
	CONSTRAINT u_comments_fk FOREIGN KEY (userNo)
	REFERENCES users(userNo)
);

CREATE SEQUENCE seq_comments_no
INCREMENT BY 1
START WITH 1
NOCACHE;

SELECT u.userName, c.cmtContent, c.regDate
FROM users u, comment c
WHERE u.userNo = c.userNo
AND c.cmtNo = 4;

select * FROM COMMENTS;
DELETE FROM comments;
DROP TABLE comments;
DROP SEQUENCE seq_comments_no;
/* comments Table End */

/* Onother SQL */
SELECT A.id, A.cateNo, p.postNo
FROM (SELECT u.id, c.cateNo, c.regDate
	  FROM users u, category c
	  WHERE u.id = c.id
	  AND u.id = 'jimmy'
	  ORDER BY regDate DESC) A, post p
WHERE p.cateNo = 8;

SELECT A.userName, A.cmtContent, A.regDate
FROM (SELECT u.userName, c.cmtContent, c.regDate
	  FROM users u, comment c
	  WHERE u.userNo = c.userNo) A, post p
WHERE p.postNo = 8;

SELECT A.userName, A.cmtContent, A.regDate, A.postNo
			FROM (SELECT u.userName, c.cmtContent, c.regDate, c.postNo
	 			  FROM users u, comments c
	 			  WHERE u.userNo = c.userNo) A, post p
			WHERE p.postNo = A.postNo
			AND p.postNo = 0;
COMMIT
SELECT rownum as num, a.cateNo, a.regDate
FROM (SELECT c.cateNo, c.regDate
	FROM category c, blog b
	WHERE c.id = b.id
	AND c.id = 'jimmy'
	ORDER BY c.regDate DESC) a;

SELECT rownum as num, a.cateNo, a.postNo, a.regDate
FROM (SELECT p.cateNo, p.postNo, p.regDate
	FROM category c, post p
	WHERE c.cateNo = p.cateNo
	AND p.cateNo = 6
	ORDER BY p.regDate DESC) a;
