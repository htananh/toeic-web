-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: webtoeic
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorymember`
--

DROP TABLE IF EXISTS `categorymember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorymember` (
  `categorymemberid` int NOT NULL AUTO_INCREMENT,
  `categorymembername` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`categorymemberid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorymember`
--

LOCK TABLES `categorymember` WRITE;
/*!40000 ALTER TABLE `categorymember` DISABLE KEYS */;
INSERT INTO `categorymember` VALUES (1,'quản trị'),(2,'người dùng');
/*!40000 ALTER TABLE `categorymember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmtgrammar`
--

DROP TABLE IF EXISTS `cmtgrammar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmtgrammar` (
  `cmtgrammarid` int NOT NULL AUTO_INCREMENT,
  `cmtgrammarcontent` longtext,
  `memberid` int DEFAULT NULL,
  `grammarguidelineid` int DEFAULT NULL,
  PRIMARY KEY (`cmtgrammarid`),
  KEY `fk_cmtgrammar_member` (`memberid`),
  KEY `fk_cmtgrammar_grammarguideline` (`grammarguidelineid`),
  CONSTRAINT `fk_cmtgrammar_grammarguideline` FOREIGN KEY (`grammarguidelineid`) REFERENCES `grammarguideline` (`grammarguidelineid`),
  CONSTRAINT `fk_cmtgrammar_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmtgrammar`
--

LOCK TABLES `cmtgrammar` WRITE;
/*!40000 ALTER TABLE `cmtgrammar` DISABLE KEYS */;
INSERT INTO `cmtgrammar` VALUES (1,'hello',3,9),(2,'aaaaaaaaaaaaa',3,9),(3,'đầu ',3,9),(23,'xin chào',5,9),(24,'hello',5,10),(25,'iu em',3,10);
/*!40000 ALTER TABLE `cmtgrammar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmtvocabulary`
--

DROP TABLE IF EXISTS `cmtvocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmtvocabulary` (
  `cmtvocabularyid` int NOT NULL AUTO_INCREMENT,
  `cmtvocabularycontent` longtext,
  `vocabularyguidelineid` int DEFAULT NULL,
  `memberid` int DEFAULT NULL,
  PRIMARY KEY (`cmtvocabularyid`),
  KEY `fk_cmtvocabulary_member` (`memberid`),
  KEY `fk_cmtvocabulary_vocabularyguideline` (`vocabularyguidelineid`),
  CONSTRAINT `fk_cmtvocabulary_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberid`),
  CONSTRAINT `fk_cmtvocabulary_vocabularyguideline` FOREIGN KEY (`vocabularyguidelineid`) REFERENCES `vocabularyguideline` (`vocabularyguidelineid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmtvocabulary`
--

LOCK TABLES `cmtvocabulary` WRITE;
/*!40000 ALTER TABLE `cmtvocabulary` DISABLE KEYS */;
INSERT INTO `cmtvocabulary` VALUES (1,'hello123',7,3);
/*!40000 ALTER TABLE `cmtvocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination`
--

DROP TABLE IF EXISTS `examination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examination` (
  `examinationid` int NOT NULL AUTO_INCREMENT,
  `examinationame` varchar(55) DEFAULT NULL,
  `examinationimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`examinationid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination`
--

LOCK TABLES `examination` WRITE;
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
INSERT INTO `examination` VALUES (2,'đề thi 2','img4.png'),(4,'đề thi 1','assurance.jpg');
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examinationquestion`
--

DROP TABLE IF EXISTS `examinationquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examinationquestion` (
  `examinationquestionid` int NOT NULL AUTO_INCREMENT,
  `num` int DEFAULT NULL,
  `imagequestion` varchar(55) DEFAULT NULL,
  `audiogg` varchar(55) DEFAULT NULL,
  `audiomp3` varchar(55) DEFAULT NULL,
  `paragraph` longtext,
  `question` longtext,
  `option1` longtext,
  `option2` longtext,
  `option3` longtext,
  `option4` longtext,
  `correctanswer` varchar(55) DEFAULT NULL,
  `examinationid` int DEFAULT NULL,
  PRIMARY KEY (`examinationquestionid`),
  KEY `examinationid` (`examinationid`),
  CONSTRAINT `examinationquestion_ibfk_1` FOREIGN KEY (`examinationid`) REFERENCES `examination` (`examinationid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinationquestion`
--

LOCK TABLES `examinationquestion` WRITE;
/*!40000 ALTER TABLE `examinationquestion` DISABLE KEYS */;
INSERT INTO `examinationquestion` VALUES (19,2,'anh1.png','audiogg5.mp3','audiogg5.mp3','',' Which of the following items would qualify the customer for a free tent light?','A','B','C','D) strategic','D',2),(20,3,'anh2.png','audiogg4.mp3','audiogg5.mp3','','The store’s manager plans to put the new merchandise on display _____ to promote the line of fall fashions.','(A) soon','(B) very','(C) that','(D) still','A',2),(21,4,'','audiogg1.mp3','audiogg1.ogg','','Ms. Morgan recruited the individuals that the company _____ for the next three months.','(A) will employ','(B) to employ','(C) has been employed','(D) employ','A',2),(22,5,'','audiogg2.mp3','audiogg2.ogg','','The governmental department used to provide financial aid, but now it offers _____ services only.','(A) legal','(B) legalize','(C) legally','(D) legalizes','A',2),(23,6,'','audiogg3.mp3','audiogg3.ogg','','The company _____ lowered its prices to outsell its competitors and attract more customers.','(A) strategy','(B) strategically','(C) strategies','D) strategic','B',2),(24,7,'','','','Your choice of tents for $80. Whether you need a summer tent or a 3-season tent, one that sleeps 2 or 8, it’s only $80!* Our employees will help you choose the right tent for you','','A','B','C','D) strategicC','C',2),(25,2,'anh1.png','audiogg5.mp3','audiogg5.mp3','',' Which of the following items would qualify the customer for a free tent light?','A','B','C','D) strategic','D',4),(26,3,'anh2.png','audiogg4.mp3','audiogg5.mp3','','The store’s manager plans to put the new merchandise on display _____ to promote the line of fall fashions.','(A) soon','(B) very','(C) that','(D) still','A',4),(27,4,'','audiogg1.mp3','audiogg1.ogg','','Ms. Morgan recruited the individuals that the company _____ for the next three months.','(A) will employ','(B) to employ','(C) has been employed','(D) employ','A',4),(28,5,'','audiogg2.mp3','audiogg2.ogg','','The governmental department used to provide financial aid, but now it offers _____ services only.','(A) legal','(B) legalize','(C) legally','(D) legalizes','A',4),(29,6,'','audiogg3.mp3','audiogg3.ogg','','The company _____ lowered its prices to outsell its competitors and attract more customers.','(A) strategy','(B) strategically','(C) strategies','D) strategic','B',4),(30,7,'','','','Your choice of tents for $80. Whether you need a summer tent or a 3-season tent, one that sleeps 2 or 8, it’s only $80!* Our employees will help you choose the right tent for you','','A','B','C','D) strategicC','C',4);
/*!40000 ALTER TABLE `examinationquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammarguideline`
--

DROP TABLE IF EXISTS `grammarguideline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grammarguideline` (
  `grammarguidelineid` int NOT NULL AUTO_INCREMENT,
  `grammarname` longtext,
  `grammarimage` longtext,
  `content` longtext,
  PRIMARY KEY (`grammarguidelineid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammarguideline`
--

LOCK TABLES `grammarguideline` WRITE;
/*!40000 ALTER TABLE `grammarguideline` DISABLE KEYS */;
INSERT INTO `grammarguideline` VALUES (9,'Phân biệt A few và few, a little và little','img1.jpg','### **Khái niệm Few và A Few**\r\n\r\n\r\nFew và a few đều là những từ chỉ số lượng, nhưng có nghĩa khác nhau.\r\n\r\n**Few:** Few có nghĩa là \"ít, không nhiều\". Nó thường được sử dụng để chỉ một số lượng rất nhỏ hoặc gần như không có gì.\r\n\r\n**Ex:** **Few **people attended the meeting. ==>  Ít người tham dự cuộc họp\r\n\r\n**A few:** A few cũng có nghĩa là \"ít\", nhưng nó thường được sử dụng để chỉ một số lượng nhỏ nhưng không quá ít, đủ để đáp ứng nhu cầu hoặc làm điều gì đó.\r\n\r\n**Ex: **I have **a few **books on my shelf. ==> Tôi có một vài cuốn sách trên giá sách của tôi.\r\n\r\nVới **few**, chúng ta nhấn mạnh vào sự **ít ỏi, thiếu hụt**, trong khi với **a few,** chúng ta tập trung vào sự **đủ để sử dụng hoặc làm việc gì đó**\r\n### **Khái niệm Little và a little**\r\n**\"Little\" **và **\"a little\"** đều là những từ chỉ số lượng trong tiếng Anh.\r\n\r\n-\"**Little\"** có nghĩa là **\"ít, nhỏ\"** và được sử dụng để **chỉ một số lượng nhỏ, không đáng kể,** hoặc không đủ để đáp ứng nhu cầu.\r\n\r\n**Ex: **There is **little **milk left in the fridge. ==> Còn ít sữa trong tủ lạnh.\r\n \r\nTrong khi đó, **\"a little\"** cũng có nghĩa là \"ít\" nhưng lại mang ý nghĩa **tích cực hơn \"little\"**. Nó thường được sử dụng để chỉ một lượng nhỏ, nhưng đủ để đáp ứng nhu cầu hoặc giúp cải thiện tình huống.\r\n\r\n**Ex:** Can you please add **a little **more salt to the soup? ==> Bạn có thể cho thêm một chút muối vào súp được không?'),(10,'Cấu trúc Effect trong tiếng Anh - Chi tiết + Bài tập vận dụng','img2.png','**Effect có nghĩa là gì?**\r\nEffect là một danh từ mang nghĩa là kết quả, hiệu lực, tác dụng và ảnh hưởng\r\n\r\n**Ví dụ: **\r\nFactory emissions have had a disastrous **effect on** the environment.\r\n\r\nKhí thải của nhà máy có tác động tai hại đến môi trường sống.\r\n\r\n**Các cấu trúc thông dụng với Effect**\r\n- **Take effect:** Để tạo ra hoặc đạt được kết quả mong muốn\r\n\r\nVD: You have to wait two hour for the machine to **take effect**\r\n\r\nBạn phải đợi 2 tiếng để chiếc máy hoạt động \r\n\r\n- **For effect:** Nói hoặc làm điều gì để gây hiệu ứng, hoặc cố tình làm điều gì đó để gây sốc, thu hút sự chú ý của mọi người\r\n\r\nVD: She\'s not afraid to create scandals **for effect.**\r\n\r\nCô ấy không ngại tạo vụ bê bối để thu hút sự chú ý.\r\n\r\n- **In effect:** trên thực tế, hoặc trong thực tế\r\n\r\nVD: **In effect,** this house was sold to a stranger.\r\n\r\nTrên thực tế, ngôi nhà này đã được bán cho một người xa lạ.\r\n\r\n- **To that/the effect:** được sử dụng để thể hiện rằng những gì bạn đang bảo cáo chỉ là một dạng ngắn gọn và chung chung của những gì đã thực sự được nói.\r\n\r\nVD: She said something to **the effect** that he would move to another city in the next month.\r\n\r\nCô ấy nói điều gì ngắn gọn là anh ất sẽ chuyển đến một thành phố khác trong tháng tới.\r\n\r\n- **Come into effect:** để bắt đầu làm việc hoạch được sử dụng\r\n\r\nVD: The new law will come **into effect** in next month.\r\n\r\nBộ luật mới sẽ được thi hành vào tháng tới.\r\n\r\n- **With immediate effect/with effect from:** Được sử dụng để mô tả một thay đổi xảy ra ngay lập tức hoặc từ một ngày cụ thể.\r\n\r\nVD: Jane left **with immediate effect** after hearing him say breakup.\r\n\r\nJane rời đi ngay lập tức sau khi nghe anh ta nói chia tay.\r\n\r\nShe tried eating healthy to lose weigh but the didn’t any **effect**\r\n\r\nCô ấy đã thử ăn ăn uống lành mạnh để giảm cân nhưng chúng không có tác dụng\r\n\r\n** Các giới từ đi với Effect**\r\n**\r\nEffect on:** Tác động/ ảnh hưởng vào/lên ai, cái gì\r\n\r\nVD: The new regulation is having an** effect on** the work schedule of the employees.\r\n\r\n- **Effect of:** tác động/ ảnh hưởng của cái gì\r\n\r\nVD: We are trying our best to reduce the effect of air pollution.\r\n\r\n- **Effect of** something on someon/something: tác động của cái gì lên ai/cái gì\r\n\r\nVD: The negative **effect of** climate change on the wild life is increasing. '),(11,'Tất tần tật cách dùng và cấu trúc Mention trong tiếng Anh','img3.png','**Mention là gì?**\r\n\r\nVề chức năng, **Mention /ˈmenʃn/** đóng vài trò vừa là động từ, vừa là danh từ.  \r\n\r\nVới chức năng là **động từ, mention** thường được biết đến nghĩa: nhắc đến, đề cập đến, kể ra \r\nĐiều này có nghĩa, khi bạn mention đến điều gì đó tức là bạn chỉ nhắc tên, hoặc nêu ra một vài ý về điều đó chứ không kể lể dài dòng hay đi sâu vào chi tiết.  \r\n\r\nEg:\r\n\r\n- The course guide did not **mention** the format of the mid-term exam. (Hướng dẫn khóa học không nhắc đến hình thức của kỳ thi giữa kỳ.) \r\n\r\n- He **mentioned** in his letter that he was going to get married soon. (Anh ấy nhắc đến việc anh ấy sẽ kết hôn sớm trong lá thư của mình.) \r\n\r\nVới vai trò là một **danh từ, mention** là sự kể ra, sự đề cập, hành động nhắc tới điều gì đó \r\nEg:\r\n\r\n- He made no **mention** of his mother in his winning speech. (Anh ấy không hề nhắc đến mẹ trong bài phát biểu mừng chiến thắng của mình.) \r\n\r\n- This problem is worthy of **mention** because of several reasons. (Vấn đề này đáng được đưa ra vì một vài lý do.) \r\nNgoài ra, danh từ **mention** còn dùng để chỉ hành động tuyên dương, ghi nhận đóng góp của ai khác. \r\n\r\n**Cách dùng các cấu trúc mention**\r\n\r\n2.1. **Cấu trúc 1**:\r\n\r\n**S + mention + that + mệnh đề**\r\n\r\nEg:\r\n\r\n- She casually mentioned that she would leave her job for health reasons. (Cô ấy tình cờ đề cập rằng cô ấy sẽ nghỉ việc vì lý do sức khỏe.) \r\n\r\n- After dinner, my mother mentioned that my brother was punished at school. (Sau bữa tối, mẹ tôi nói rằng em trai tôi đã bị phạt ở trường.) \r\n\r\n**2.2. Cấu trúc 2: **\r\n\r\n**S + mention + V-ing**\r\n\r\nEg:\r\n\r\n- My friend mentioned doing homework in free time. (Bạn tôi đã đề cập đến việc làm bài tập về nhà trong thời gian rảnh) \r\n\r\n- My friend tried to mention taking a painting course to his parents but he failed. (Bạn tôi đã cố gắng đề cập đến việc tham gia một khóa học vẽ tranh với bố mẹ cậu ấy nhưng đã thất bại.) \r\n\r\n**2.3. Cấu trúc 3:**\r\n\r\n**Trợ động từ + S + mention + Wh-question…?**\r\n\r\nEg:\r\n\r\n- Did you mention your ideas to your teacher? It sounds interesting! (Bạn có đề cập ý tưởng của mình với giáo viên không? Nó nghe có vẻ thú vị đấy!)  \r\n\r\n- Did he mention where he would be coming? (Anh ấy có đề cập rằng anh ấy sẽ đi đâu không?) '),(12,'Cấu trúc No Sooner - Công thức, cách dùng và bài tập có đáp án','img4.png',NULL),(13,'Bật mí cách phân biệt cấu trúc how many và how much dễ nhớ nhất','img5.png',NULL),(14,'Cấu trúc this is the first time - Ý nghĩa, cách dùng & Bài tập','img7.png',NULL),(15,'Cấu trúc của một bài essay và các bước làm chi tiết','img6.png',NULL);
/*!40000 ALTER TABLE `grammarguideline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listenexecise`
--

DROP TABLE IF EXISTS `listenexecise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listenexecise` (
  `listenexeciseid` int NOT NULL AUTO_INCREMENT,
  `listenexecisename` varchar(55) DEFAULT NULL,
  `listenexeciseimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`listenexeciseid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listenexecise`
--

LOCK TABLES `listenexecise` WRITE;
/*!40000 ALTER TABLE `listenexecise` DISABLE KEYS */;
INSERT INTO `listenexecise` VALUES (1,'bài nghe 1','anh3tv.jpg');
/*!40000 ALTER TABLE `listenexecise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listenquestion`
--

DROP TABLE IF EXISTS `listenquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listenquestion` (
  `listenquestionid` int NOT NULL AUTO_INCREMENT,
  `imagename` varchar(55) DEFAULT NULL,
  `audiomp3` varchar(55) DEFAULT NULL,
  `num` int DEFAULT NULL,
  `question` longtext,
  `option1` longtext,
  `option2` longtext,
  `option3` longtext,
  `option4` longtext,
  `correctanswer` varchar(55) DEFAULT NULL,
  `listenexeciseid` int DEFAULT NULL,
  PRIMARY KEY (`listenquestionid`),
  KEY `fk_listenquestion_listenexecise` (`listenexeciseid`),
  CONSTRAINT `fk_listenquestion_listenexecise` FOREIGN KEY (`listenexeciseid`) REFERENCES `listenexecise` (`listenexeciseid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listenquestion`
--

LOCK TABLES `listenquestion` WRITE;
/*!40000 ALTER TABLE `listenquestion` DISABLE KEYS */;
INSERT INTO `listenquestion` VALUES (1,'hinhnghe1.jpg','hinhnghe1.jpg',2,'qs1','a','b','c','d','D',1),(2,'hinhnghe2.jpg','hinhnghe2.jpg',3,'qs2','a','b','c','d','C',1);
/*!40000 ALTER TABLE `listenquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `memberid` int NOT NULL AUTO_INCREMENT,
  `membername` varchar(55) DEFAULT NULL,
  `memberpass` varchar(55) DEFAULT NULL,
  `categorymemberid` int DEFAULT NULL,
  `fullname` varchar(55) DEFAULT NULL,
  `memberimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`memberid`),
  KEY `fk_member_categorymemberr` (`categorymemberid`),
  CONSTRAINT `fk_member_categorymemberr` FOREIGN KEY (`categorymemberid`) REFERENCES `categorymember` (`categorymemberid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (3,'anh15','1222',1,'Hồ Tấn Anh',NULL),(4,'anh15','12346',1,'Hồ Tấn Anh',NULL),(5,'anhne123','3456',1,'Nguyễn Văn A',NULL),(6,'admin','111111',2,'admin',NULL),(7,'linh','1234',1,'Võ Văn Linh',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readexecise`
--

DROP TABLE IF EXISTS `readexecise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readexecise` (
  `readexeciseid` int NOT NULL AUTO_INCREMENT,
  `readame` varchar(55) DEFAULT NULL,
  `readimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`readexeciseid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readexecise`
--

LOCK TABLES `readexecise` WRITE;
/*!40000 ALTER TABLE `readexecise` DISABLE KEYS */;
INSERT INTO `readexecise` VALUES (1,'bài đọc 1','assurance.jpg');
/*!40000 ALTER TABLE `readexecise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readingquestion`
--

DROP TABLE IF EXISTS `readingquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readingquestion` (
  `readingquestionid` int NOT NULL AUTO_INCREMENT,
  `num` int DEFAULT NULL,
  `paragraph` longtext,
  `question` longtext,
  `option1` longtext,
  `option2` longtext,
  `option3` longtext,
  `option4` longtext,
  `correctanswer` varchar(55) DEFAULT NULL,
  `readexeciseid` int DEFAULT NULL,
  PRIMARY KEY (`readingquestionid`),
  KEY `readexeciseid` (`readexeciseid`),
  CONSTRAINT `readingquestion_ibfk_1` FOREIGN KEY (`readexeciseid`) REFERENCES `readexecise` (`readexeciseid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readingquestion`
--

LOCK TABLES `readingquestion` WRITE;
/*!40000 ALTER TABLE `readingquestion` DISABLE KEYS */;
INSERT INTO `readingquestion` VALUES (4,2,'The concept of traffic-free shopping areas goes back a long time. During the Middle Ages, traffic-free shopping areas were built in Middle Eastern countries to allow people to shop in comfort and, more importantly, safety. As far back as 2000 years ago, road traffic was banned from central Rome during the day to allow for the free movement of pedestrians.\n\nThe modern, traffic-free shopping street was born in Europe in the 1960s, when both city populations and car ownership increased rapidly. Dirty exhaust from cars and the risks involved in crossing the road were beginning to make shopping an unpleasant and dangerous experience. Many believed the time was right for experimenting with car-free streets, and shopping areas seemed the best place to start.\n\nAt first, there was resistance from shopkeepers. They argued that people would avoid streets if they were unable to get to them in their cars. When the first streets in Europe were closed to traffic, there were even noisy demonstrations, as many shopkeepers predicted they would lose customers. With the arrival of the traffic-free shopping street, many shops, especially those selling things like clothes, food and smaller luxury items, prospered. Unfortunately, shops selling furniture and larger electrical appliances actually saw their sales drop. Many of these were forced to move elsewhere, away from the city centre.','Which of the following can be the best title for the passage?','A Need for Cashless Shopping',' B. A Need for Street Shopping',' C. Pedestrians Only',' D. Shopkeepers Only','A',1),(5,3,'','According to paragraph 2, shopping became an unpleasant and unsafe experience due to pollution and',' A. the decrease in car ownership',' B. the appearance of car-free shopping areas','C. the risks involved in crossing roads',' D. the experiment of car-free streets','C',1),(6,4,'','The word they in paragraph 3 refers to',' A. demonstrations','B. streets',' C. cars',' D. shopkeepers','B',1);
/*!40000 ALTER TABLE `readingquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `resultid` int NOT NULL AUTO_INCREMENT,
  `correctanswernum` int DEFAULT NULL,
  `incorrectanswernum` int DEFAULT NULL,
  `resulttime` datetime DEFAULT NULL,
  `examinationid` int DEFAULT NULL,
  `memberid` int DEFAULT NULL,
  PRIMARY KEY (`resultid`),
  KEY `fk_result_examination` (`examinationid`),
  KEY `fk_result_member` (`memberid`),
  CONSTRAINT `fk_result_examination` FOREIGN KEY (`examinationid`) REFERENCES `examination` (`examinationid`),
  CONSTRAINT `fk_result_member` FOREIGN KEY (`memberid`) REFERENCES `member` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (119,3,3,NULL,2,3),(120,4,2,NULL,4,3);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slidebanner`
--

DROP TABLE IF EXISTS `slidebanner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slidebanner` (
  `slidebannerid` int NOT NULL AUTO_INCREMENT,
  `slidename` longtext,
  `slidecontent` longtext,
  `slideimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`slidebannerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slidebanner`
--

LOCK TABLES `slidebanner` WRITE;
/*!40000 ALTER TABLE `slidebanner` DISABLE KEYS */;
/*!40000 ALTER TABLE `slidebanner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabularycontent`
--

DROP TABLE IF EXISTS `vocabularycontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabularycontent` (
  `vocabularycontentid` int NOT NULL AUTO_INCREMENT,
  `num` int DEFAULT NULL,
  `vocabularyguidecontentname` varchar(55) DEFAULT NULL,
  `transcrible` varchar(55) DEFAULT NULL,
  `audiomp3` varchar(55) DEFAULT NULL,
  `audiogg` varchar(55) DEFAULT NULL,
  `mean` varchar(55) DEFAULT NULL,
  `vocabularyguidelineid` int DEFAULT NULL,
  `vocabularycontentimage` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vocabularycontentid`),
  KEY `fk_vocabularycontent_vocabularyguideline` (`vocabularyguidelineid`),
  CONSTRAINT `fk_vocabularycontent_vocabularyguideline` FOREIGN KEY (`vocabularyguidelineid`) REFERENCES `vocabularyguideline` (`vocabularyguidelineid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabularycontent`
--

LOCK TABLES `vocabularycontent` WRITE;
/*!40000 ALTER TABLE `vocabularycontent` DISABLE KEYS */;
INSERT INTO `vocabularycontent` VALUES (5,1,'abide by ','/ə\'baid/','abideby.mp3','abideby.ogg',' tôn trọng, tuân theo, giữ (lời)',7,'abideby.jpg'),(6,2,'agreement','/ə\'gri:mənt/','agreement.mp3','agreement.ogg','hợp đồng, giao kèo, sự đồng ý/thỏa thuận với nhau',7,'agreement.jpg'),(7,3,'assurance','/ə\'ʃuərəns/','assurance.mp3','assurance.ogg','sự cam đoan, bảo đảm, chắc chắn; sự tin chắc, tự tin',7,'assurance.jpg'),(8,4,'cancellation','/,kænse\'leiʃn/','cancellation.mp3','cancellation.ogg','sự bãi bỏ, hủy bỏ',7,'cancellation.jpg');
/*!40000 ALTER TABLE `vocabularycontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabularyguideline`
--

DROP TABLE IF EXISTS `vocabularyguideline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabularyguideline` (
  `vocabularyguidelineid` int NOT NULL AUTO_INCREMENT,
  `vocabularyguidelinename` varchar(55) DEFAULT NULL,
  `vocabularyguidelineimage` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`vocabularyguidelineid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabularyguideline`
--

LOCK TABLES `vocabularyguideline` WRITE;
/*!40000 ALTER TABLE `vocabularyguideline` DISABLE KEYS */;
INSERT INTO `vocabularyguideline` VALUES (7,'Contacts','anh1tv.jpg'),(8,'Marketing','anh2tv.jpg'),(10,'Bussiness Planning','anh4tv.jpg');
/*!40000 ALTER TABLE `vocabularyguideline` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-22 17:15:33
