CREATE TABLE classrooms (
    classroom_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    building_name VARCHAR(255),
    room_number VARCHAR(255)
);

CREATE TABLE transcripts (
    transcript_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    degree_title VARCHAR(255)
);

CREATE TABLE courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(100),
    course_name VARCHAR(255)
);

CREATE TABLE students (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    cgpa DOUBLE,
    date_of_enrollment DATE,
    transcript_id BIGINT,
    classroom_id BIGINT,
    CONSTRAINT fk_transcript FOREIGN KEY (transcript_id)
        REFERENCES transcripts(transcript_id),
    CONSTRAINT fk_classroom FOREIGN KEY (classroom_id)
        REFERENCES classrooms(classroom_id)
);

CREATE TABLE student_course (
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_sc_student FOREIGN KEY (student_id)
        REFERENCES students(student_id),
    CONSTRAINT fk_sc_course FOREIGN KEY (course_id)
        REFERENCES courses(course_id)
);
