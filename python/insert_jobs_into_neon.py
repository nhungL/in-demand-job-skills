import os
import json
import psycopg2
import pandas as pd
from dotenv import load_dotenv
from serpapi import GoogleSearch
from serpapi_jobs_data import search_multiple_keys
from clean_jobs_data import preprocess_data, get_finalized_data

# Load environment variables
load_dotenv()

# Function to connect to the PostgreSQL database
def connect_to_database():
    connection_string = os.getenv('DATABASE_URL')
    conn = psycopg2.connect(connection_string)
    # Create a cursor object
    cur = conn.cursor()
    return conn

def delete_tables(conn):
    try:
        cursor = conn.cursor()
        cursor.execute("DROP TABLE IF EXISTS jobs_all");
        cursor.execute("DROP TABLE IF EXISTS insertion_stat");

        conn.commit()
        cursor.close()
        print(f"Delete tables successfully")
    except psycopg2.Error as e:
        print("Insertion error:", e)
        conn.rollback()


# Function to insert job data into the database
def insert_job_data(conn, job_data, success_count):
    try:
        cursor = conn.cursor()

        # Create table if not exist
        create_table_jobs_all = """
            CREATE TABLE IF NOT EXISTS jobs_all (
                job_id VARCHAR(4000) PRIMARY KEY,
                title VARCHAR(255),
                company_name VARCHAR(255),
                location VARCHAR(255),
                via VARCHAR(255),
                description VARCHAR(15000),
                extensions VARCHAR(255),
                remote_option BOOLEAN,
                salary VARCHAR(255) ARRAY,
                edu_degree VARCHAR(255) ARRAY,
                skills VARCHAR(255) ARRAY,
                posted_at VARCHAR(255),
                schedule_type VARCHAR(255)
            );
        """

        # SQL INSERT statements
        insert_data_into_jobs_all = """
            INSERT INTO jobs_all (job_id, title, company_name, location, via, 
                                description, extensions, remote_option, salary,  
                                edu_degree, skills, posted_at, schedule_type)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);
        """
        data = (job_data['job_id'], job_data['title'], job_data['company_name'], job_data['location'], job_data['via'],
                 job_data['description'], job_data['extensions'], job_data['remote_option'], job_data['salary'],
                 job_data['edu_degree'], job_data['skills'], job_data['posted_at'], job_data['schedule_type'])

        cursor.execute(create_table_jobs_all)
        cursor.execute(insert_data_into_jobs_all, data)

        conn.commit()
        cursor.close()
        success_count += 1  # Increment the counter for successful insertions
        print(f"Job data inserted successfully. Total success insertions count: {success_count}")
        return success_count
    except psycopg2.IntegrityError as e:
        # print("Duplicate job")
        conn.rollback()
        return success_count
    except psycopg2.Error as e:
        print("General database error:", e)
        conn.rollback()
        return success_count

def update_insertion_data(conn, success_count):
    try:
        cursor = conn.cursor()

        # Create the table if it doesn't exist using sql.SQL and sql.Identifier
        create_table_insertion_stat = """
            CREATE TABLE IF NOT EXISTS insertion_stat (
                insertion_id SERIAL PRIMARY KEY,
                updated_at TIMESTAMP,
                job_added INT,
                total_jobs INT
            );
        """
        cursor.execute(create_table_insertion_stat)

        # Get the current timestamp
        cursor.execute('SELECT NOW();')
        current_time = cursor.fetchone()[0]

        # Get total jobs from table jobs_all
        cursor.execute("SELECT COUNT(*) AS total_jobs FROM jobs_all;")
        total_jobs = cursor.fetchone()[0]

        # Insert data into the table using SQL parameters (%s)
        insert_data_into_insertion_stat = """
            INSERT INTO insertion_stat (updated_at, job_added, total_jobs)
            VALUES (%s, %s, %s);
        """
        insertion_data = (current_time, success_count, total_jobs)

        cursor.execute(create_table_insertion_stat)
        cursor.execute(insert_data_into_insertion_stat, insertion_data)

        conn.commit()
        cursor.close()

        print(f"Insertion data updated successfully")
    except psycopg2.Error as e:
        print("Insertion error:", e)
        conn.rollback()

def main():
    search_keys = ["Data Scientist", "Data Analyst", "Data Engineer",
                   "Business Analyst", "Software Engineer", "Machine Learning"]
    search_pages = 4

    # Connect to the database
    conn = connect_to_database()

    # df_jobs_serpapi = search_multiple_keys(search_keys, search_pages)
    df_jobs_serpapi = pd.read_csv('data/gg_jobs_test.csv')
    df_preprocess = preprocess_data(df_jobs_serpapi)
    df_jobs_cleaned = get_finalized_data(df_preprocess)

    success_count = 0
    for index, row in df_jobs_cleaned.iterrows():
        success_count = insert_job_data(conn, row.to_dict(), success_count)

    print(f"Total new job inserted: {success_count}")
    if success_count != 0 :
        update_insertion_data(conn, success_count)

    # delete_tables(conn);

    # Close the database connection
    conn.close()

if __name__ == "__main__":
    main()