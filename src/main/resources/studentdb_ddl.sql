--
-- Drop data base if exist and create a new database.
--

DROP DATABASE IF EXISTS STUDENT_DATA_DB;
CREATE DATABASE STUDENT_DATA_DB;
USE STUDENT_DATA_DB;

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(11) DEFAULT NULL,
  `contact_no` VARCHAR(45) DEFAULT NULL,
  `address` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) DEFAULT NULL,
  `department_id` INT(11) NOT NULL,
  `semester` TINYINT(2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_student_semester` (`semester`),
  INDEX `idx_student_department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(11) DEFAULT NULL,
  `contact_no` VARCHAR(45) DEFAULT NULL,
  `address` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) DEFAULT NULL,
  `department_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_lecturer_department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `department_head_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_department_lecturer_department_head_id` FOREIGN KEY (`department_head_id`)
  REFERENCES lecturer(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `offering_department_id` INT(11) NOT NULL,
  `fee` DECIMAL(6,2) NOT NULL DEFAULT 0.00,
  `lecturer_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_course_offering_department_id` (`offering_department_id`),
  INDEX `idx_course_lecturer_id` (`lecturer_id`),
  CONSTRAINT `fk_course_department_offering_department_id` FOREIGN KEY (`offering_department_id`)
  REFERENCES department(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `student_id` INT(11) NOT NULL,
  `course_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_student_course_1` (`student_id`),
  INDEX `idx_student_course_2` (`course_id`, `student_id`),
  CONSTRAINT `fk_student_course_student_id` FOREIGN KEY (`student_id`)
  REFERENCES student(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
  CONSTRAINT `fk_student_course_course_id` FOREIGN KEY (`course_id`)
  REFERENCES course(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `semester_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester_course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `semester` TINYINT(2) NOT NULL,
  `course_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_semester_course_1` (`course_id`),
  INDEX `idx_semester_course_2` (`semester`, `course_id`),
  CONSTRAINT `fk_semester_course_course_id` FOREIGN KEY (`course_id`)
  REFERENCES course(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `department_id` INT(11) NOT NULL,
  `course_id` INT(11) NOT NULL,
  `date` DATE DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_exam_department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `student_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `student_id` INT(11) NOT NULL,
  `exam_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_student_exam_1` (`student_id`),
  INDEX `idx_student_exam_2` (`student_id`, `exam_id`),
  CONSTRAINT `fk_student_exam_student_id` FOREIGN KEY (`student_id`)
  REFERENCES student(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
  CONSTRAINT `fk_student_exam_exam_id` FOREIGN KEY (`exam_id`)
  REFERENCES exam(`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `course_id` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  `amount` DECIMAL(6,2) NOT NULL DEFAULT 0.00,
  `semester` TINYINT(2) NOT NULL,
  `payment_date` DATE DEFAULT NULL,
  `status` ENUM('PAID','PENDING','FREE') NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (`id`),
  INDEX `idx_payment_course_id` (`course_id`),
  INDEX `idx_payment_student_id` (`student_id`),
  INDEX `idx_payment_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;