CREATE TABLE IF NOT EXISTS jobs_info (
    job_id SERIAL PRIMARY KEY,
    api_job_id VARCHAR(4000) UNIQUE,
    title VARCHAR(255),
    company_name VARCHAR(255),
    location VARCHAR(255),
    via VARCHAR(255),
    description TEXT,
    extensions VARCHAR(255),
    remote_option BOOLEAN,
    posted_at VARCHAR(255),
    schedule_type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS salary (
    salary_id SERIAL PRIMARY KEY,
    job_id INT REFERENCES jobs_info(job_id),
    title VARCHAR(255), 
    min_salary INT NOT NULL,
    max_salary INT NOT NULL,
    UNIQUE(job_id, min_salary, max_salary)
);

CREATE TABLE IF NOT EXISTS skill (
    skill_id SERIAL PRIMARY KEY,
    job_id INT REFERENCES jobs_info(job_id),
    title VARCHAR(255), 
    skill VARCHAR(255) NOT NULL,
    UNIQUE(job_id, skill)
);

CREATE TABLE IF NOT EXISTS edu_degree (
    edu_degree_id SERIAL PRIMARY KEY,
    job_id INT REFERENCES jobs_info(job_id),
    title VARCHAR(255),
    degree VARCHAR(255) NOT NULL,
    UNIQUE(job_id, degree)
);

CREATE TABLE IF NOT EXISTS insertion_stat (
    insertion_id SERIAL PRIMARY KEY,
    updated_at TIMESTAMP,
    job_added INT,
    total_jobs INT
);