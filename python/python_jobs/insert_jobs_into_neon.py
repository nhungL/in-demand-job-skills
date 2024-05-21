import psycopg2

def delete_tables(conn):
    try:
        cursor = conn.cursor()
        cursor.execute("DROP TABLE IF EXISTS jobs_info");
        cursor.execute("DROP TABLE IF EXISTS insertion_stat");
        cursor.execute("DROP TABLE IF EXISTS skill");
        cursor.execute("DROP TABLE IF EXISTS edu_degree");
        cursor.execute("DROP TABLE IF EXISTS salary");

        conn.commit()
        cursor.close()
        print(f"Delete tables successfully")
    except psycopg2.Error as e:
        print("Insertion error:", e)
        conn.rollback()

def create_tables(conn, filename):
    try:
        cursor = conn.cursor()
        with open(filename, 'r') as f:
            sql_script = f.read()
        cursor.execute(sql_script)
        conn.commit()
        cursor.close()
        print(f"Create tables successfully")
    except psycopg2.Error as e:
        print("Error:", e)
        conn.rollback()


# Function to insert job data into the database
def insert_job_data(conn, job_data, success_count):
    try:
        cursor = conn.cursor()
        insert_data_into_jobs_info = """
            INSERT INTO jobs_info (api_job_id, title, company_name, location,
                                via, description, extensions, remote_option,
                                posted_at, schedule_type)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
            ON CONFLICT (job_id) DO NOTHING
            RETURNING job_id;
        """
        job = (job_data['job_id'], job_data['title'], job_data['company_name'], job_data['location'],
                    job_data['via'], job_data['description'], job_data['extensions'], job_data['remote_option'],
                    job_data['posted_at'], job_data['schedule_type'])

        cursor.execute(insert_data_into_jobs_info, job)
        generated_job_id = cursor.fetchone()[0]

        # insert related data
        insert_skill_data(conn, generated_job_id, job_data['title'], job_data['skills'])
        insert_edu_degree_data(conn, generated_job_id, job_data['title'], job_data['edu_degree'])
        insert_salary_data(conn, generated_job_id, job_data['title'], job_data['salary'])

        conn.commit()
        cursor.close()
        success_count += 1  # Increment the counter for successful insertions
        print(success_count)
        return success_count
    except psycopg2.IntegrityError as e:
        # print("Duplicate job")
        conn.rollback()
        return success_count
    except psycopg2.Error as e:
        print("General database error:", e)
        conn.rollback()
        return success_count

def insert_skill_data(conn, job_id, title, skills):
    try:
        cursor = conn.cursor()

        insert_skill_sql = """
            INSERT INTO skill (job_id, title, skill) VALUES (%s, %s, %s)
            ON CONFLICT (job_id, skill) DO NOTHING;
        """

        if skills and len(skills) > 0:
          for skill in skills:
              cursor.execute(insert_skill_sql, (job_id, title, skill))

    except psycopg2.Error as e:
        print("Error inserting skill data:", e)
        conn.rollback()

def insert_edu_degree_data(conn, job_id, title, edu_degree_data):
    try:
        cursor = conn.cursor()

        insert_edu_degree_sql = """
            INSERT INTO edu_degree (job_id, title, degree) VALUES (%s, %s, %s)
            ON CONFLICT (job_id, degree) DO NOTHING;
        """

        if edu_degree_data and len(edu_degree_data) > 0:
          for degree in edu_degree_data:
              cursor.execute(insert_edu_degree_sql, (job_id, title, degree))

    except psycopg2.Error as e:
        print("Error inserting degree data:", e)
        conn.rollback()

def insert_salary_data(conn, job_id, title, salary_data):
    try:
        cursor = conn.cursor()

        insert_salary_sql = """
            INSERT INTO salary (job_id, title, min_salary, max_salary)
            VALUES (%s, %s, %s, %s)
            ON CONFLICT (job_id, min_salary, max_salary) DO NOTHING;
        """

        if salary_data and len(salary_data) == 2:
          min_salary = salary_data[0]
          max_salary = salary_data[1]
          cursor.execute(insert_salary_sql, (job_id, title, min_salary, max_salary))

    except psycopg2.Error as e:
        print("Error inserting salary data:", e)
        conn.rollback()

def update_insertion_data(conn, success_count):
    try:
        cursor = conn.cursor()

        # Get the current timestamp
        cursor.execute('SELECT NOW();')
        current_time = cursor.fetchone()[0]

        # Get total jobs from table jobs_info
        cursor.execute("SELECT COUNT(*) AS total_jobs FROM jobs_info;")
        total_jobs = cursor.fetchone()[0]

        # Insert data into the table using SQL parameters (%s)
        insert_data_into_insertion_stat = """
            INSERT INTO insertion_stat (updated_at, job_added, total_jobs)
            VALUES (%s, %s, %s);
        """
        insertion_data = (current_time, success_count, total_jobs)
        cursor.execute(insert_data_into_insertion_stat, insertion_data)

        conn.commit()
        cursor.close()
        print(f"Insertion data updated successfully")

    except psycopg2.Error as e:
        print("Insertion error:", e)
        conn.rollback()

def insert_data(conn, df_jobs_cleaned):
    # create_tables(conn, 'python/sql/schema.sql')
    success_count = 0
    for index, row in df_jobs_cleaned.iterrows():
      success_count = insert_job_data(conn, row.to_dict(), success_count)

    print(f"Total new job inserted: {success_count}")
    if success_count != 0 :
        update_insertion_data(conn, success_count)

    # delete_tables(conn)

    # Close the database connection
    conn.close()