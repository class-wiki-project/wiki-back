--
-- Database: `mywiki`
--

---
--- @@ 생성 @@
---

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `student_number` varchar(100) DEFAULT NULL,
  `auth` int NOT NULL,
  `univ_name` varchar(100) NOT NULL,
  `reported_num` INT(11),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `subjects` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  `professor` varchar(100) DEFAULT NULL,
  `subject_year` int NOT NULL,
  `semester` int NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `wiki` (
  `wiki_id` int NOT NULL AUTO_INCREMENT,
  `update_date` datetime NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`wiki_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `wiki_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(400) NOT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `create_date` date NOT NULL,
  `update_date` date DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `hit_num` int(11) NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `user_id` (`user_id`),
  KEY `subject_id` (`subject_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `board_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`),
  CONSTRAINT `board_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `classification` (
  `classification_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `group_id` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `wiki_id` int(11) NOT NULL,
  PRIMARY KEY (`classification_id`),
  KEY `user_id` (`user_id`),
  KEY `wiki_id` (`wiki_id`),
  CONSTRAINT `classification_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `classification_ibfk_2` FOREIGN KEY (`wiki_id`) REFERENCES `wiki` (`wiki_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `favorite` (
  `fav_subject_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `subject_name` varchar(100) DEFAULT NULL,
  `icon_name` varchar(100) DEFAULT NULL,
  `professor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fav_subject_id`),
  KEY `user_id` (`user_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `notice_date` timestamp(5) NOT NULL,
  `comment_text` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `comment_ibfk_1` (`board_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `report`(
    `report_id` int(11) NOT NULL AUTO_INCREMENT,
    `report_user_id` int(11) NOT NULL,
    `reported_user_id` int(11) NOT NULL,
    `report_content` varchar(600) DEFAULT NULL,
    `reported_date` datetime NOT NULL,
	PRIMARY KEY (`report_id`),
    FOREIGN KEY (`report_user_id`) REFERENCES `users` (`user_id`),
    FOREIGN KEY (`reported_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `online` (
  `online_id` int NOT NULL AUTO_INCREMENT,
  `key_id` varchar(100) NOT NULL,
  'user_id' int NOT NULL,
  PRIMARY KEY (`online_id`)
)

--
-- `users`
--

INSERT INTO `mywiki`.`users` (`user_id`, `email`, `password`, `student_name`, `student_number`, `auth`, `univ_name`,`reported_num`) VALUES ('1', 'elice123@naver.com', 'elice123', '김태연', '1891012', '2', '한국대학교',0);
INSERT INTO `mywiki`.`users` (`user_id`, `email`, `password`, `student_name`, `student_number`, `auth`, `univ_name`,`reported_num`) VALUES ('2', 'bob222@naver.com', 'bob222', '박윤호', '1642022', '2', '한국대학교',2);
INSERT INTO `mywiki`.`users` (`user_id`, `email`, `password`, `student_name`, `student_number`, `auth`, `univ_name`,`reported_num`) VALUES ('3', 'zzz321@naver.com', 'zzz321', '최사랑', '1784062', '2', '한국대학교',1);
INSERT INTO `mywiki`.`users` (`user_id`, `email`, `password`, `student_name`, `student_number`, `auth`, `univ_name`,`reported_num`) VALUES ('4', 'han890@naver.com', 'han890', '이서연', '190817', '2', '한국대학교',0);
INSERT INTO `mywiki`.`users` (`user_id`, `email`, `password`, `student_name`, `student_number`, `auth`, `univ_name`,`reported_num`) VALUES ('5', 'rhksflwk@naver.com', 'rhksflwk12', '나는관리자', '10101010', '1', '한국대학교교수여',0);

--
-- `subjects`
--
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('1', '알고리즘', '김성동', '2019', '1');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('2', '웹프레임워크', '이하나', '2020', '1');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('3', '현대미술과 사회', '최수정', '2020', '2');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('4', '대학수학1', '이성영', '2019', '2');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('5', '창의적 글쓰기', '이희영', '2020', '1');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('6', '중국어 기초', '김삿갓', '2020', '1');
INSERT INTO `mywiki`.`subjects` (`subject_id`, `subject_name`, `professor`, `subject_year`, `semester`) VALUES ('7', '모바일 시스템', '한원수', '2018', '2');

--
-- `wiki`
--
INSERT INTO `mywiki`.`wiki` (`wiki_id`, `update_date`, `subject_id`) VALUES ('1', '2020-11-02 15:02:30', '1');
INSERT INTO `mywiki`.`wiki` (`wiki_id`, `update_date`, `subject_id`) VALUES ('2', '2020-09-18 11:48:00', '1');
INSERT INTO `mywiki`.`wiki` (`wiki_id`, `update_date`, `subject_id`) VALUES ('3', '2020-03-13 18:55:30', '2');


--
-- `category`
--
INSERT INTO `mywiki`.`category` (`category_id`, `category_name`) VALUES ('1', '과목게시판');
INSERT INTO `mywiki`.`category` (`category_id`, `category_name`) VALUES ('2', '자유게시판');
INSERT INTO `mywiki`.`category` (`category_id`, `category_name`) VALUES ('3', '공지사항');

--
-- `board`
--
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`, `subject_id`, `category_id`, `hit_num`) VALUES ('1', '실행이 안되는데요', '이거 실행 안되는데 하신분?', '2019-11-13 21:22:00', '2020-11-13 21:22:00', '1', '2', '1', '3');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`, `subject_id`, `category_id`, `hit_num`) VALUES ('2', '이거 어케 품?', '하나도 모르겠음', '2020-04-11 18:19:20', '2020-04-11 18:19:20', '2', '1', '1', '20');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`, `subject_id`, `category_id`, `hit_num`) VALUES ('3', '도와줘요ㅠㅠ', '글쓰기 하나도 못해요', '2020-05-02 13:00:33', '2020-05-12 17:00:33', '3', '5', '1', '13');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`, `category_id`, `hit_num`) VALUES ('4', '아ㅏㅏ하기싫다', 'ㅏㅏㅏㅏㅏㅏㅏ', '2020-09-16 00:33:12', null, '4', '2', '23');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`, `category_id`, `hit_num`) VALUES ('5', '알고르즘 교수님', '대머리', '2020-12-03 20:20:54', '2020-12-03 20:20:54', '3', '2', '35');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`,`category_id`, `hit_num`) VALUES ('6', '자체휴강', '자체휴강했당^,^', '2020-09-28 10:30:11', '2020-09-28 10:30:11', '2', '2', '22');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`,`category_id`, `hit_num`) VALUES ('7', '공지하노라', '코로나 조심 사람 조심^,^', '2020-12-24 10:30:11', null, '5', '3', '25');
INSERT INTO `mywiki`.`board` (`board_id`, `title`, `text`, `create_date`, `update_date`, `user_id`,`category_id`, `hit_num`) VALUES ('8', '돌아온 공지타임', '코로나 조심 사람 조심^,^22222', '2020-12-24 10:30:11', null, '5', '3', '100');
--
-- `<classification>`
--
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `group_id`, `user_id`, `wiki_id`) VALUES ('1', '1주차', '1.1', '2', '1');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `group_id`, `user_id`, `wiki_id`) VALUES ('2', '2주차', '1.2', '3', '1');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `text`, `group_id`, `user_id`, `wiki_id`) VALUES ('3', '알고리즘 정의', '알고리즘은 수학과 컴퓨터 과학, 언어학 또는 관련 분야에서 어떠한 문제를 해결하기 위해 정해진 일련의 절차나 방법을 공식화한 형태로 표현한 것, 계산을 실행하기 위한 단계적 절차를 의미한다. 알고리즘은 연산, 데이터 진행 또는 자동화된 추론을 수행한다.', '1.1.1', '2', '1');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `text`, `group_id`, `user_id`, `wiki_id`) VALUES ('4', '알고리즘 종류', ' 반복 알고리즘, 재귀 알고리즘, 분할 정복 알고리즘, 동적 프로그래밍, 탐욕 알고리즘', '1.2.1', '3', '1');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `text`, `group_id`, `user_id`, `wiki_id`) VALUES ('5', '웹프 중간고사 족보', '웹프 중간고사 족보!!!', '2', '4', '1');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `group_id`, `user_id`, `wiki_id`) VALUES ('6', '1주차', '1.1', '1', '2');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `text`, `group_id`, `user_id`, `wiki_id`) VALUES ('7', '창의적 글쓰기를 하기 위해서', '무조건 많이 읽어라', '1.1.1', '1', '2');
INSERT INTO `mywiki`.`classification` (`classification_id`, `title`, `text`, `group_id`, `user_id`, `wiki_id`) VALUES ('8', '모바일 시스템 족보', '족보!!', '2', '3', '3');
--
-- favorite
--
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('1', '1', '2', '웹프레임워크', '이하나');
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('2', '1', '1', '알고리즘', '김성동');
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('3', '3', '3', '현대미술과 사회', '최수정');
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('4', '1', '6', '중국어 기초', '김삿갓');
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('5', '4', '5', '창의적 글쓰기', '이희영');
INSERT INTO `mywiki`.`favorite` (`fav_subject_id`, `user_id`, `subject_id`, `subject_name`, `professor`) VALUES ('6', '4', '7', '모바일 시스템', '한원수');

--
-- comment
--

INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('1', '1', '3', '2019-11-13 22:22:00', '잘모르겠네여 잘해결해보시길');
INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('2', '4', '2', '2020-09-16 00:36:12', '나ㅏㅏㅏㅏ도');
INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('3', '4', '4', '2020-09-16 00:39:12', '낼 시험2개ㅠㅜㅜㅠ');
INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('4', '3', '2', '2020-12-20 00:39:12', '호빵찐빵만두');
INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('5', '2', '4', '2020-12-16 00:39:12', '밥주시지?');
INSERT INTO `mywiki`.`comment` (`comment_id`, `board_id`, `user_id`, `notice_date`, `comment_text`) VALUES ('6', '5', '1', '2020-09-07 00:39:12', '나 종강했다 너는');

--
-- `report`
--

INSERT INTO `mywiki`.`report` (`report_id`, `report_user_id`, `reported_user_id`, `report_content`, `reported_date`) VALUES ('1', '1', '3', '게시글을 도배 함', '2020-12-03 12:33:11');
INSERT INTO `mywiki`.`report` (`report_id`, `report_user_id`, `reported_user_id`, `report_content`, `reported_date`) VALUES ('2', '1', '2', '광고성 글을 올렸습니다', '2020-12-21 18:11:45');
INSERT INTO `mywiki`.`report` (`report_id`, `report_user_id`, `reported_user_id`, `report_content`, `reported_date`) VALUES ('3', '2', '3', '비방 글', '2020-12-21 20:20:33');
