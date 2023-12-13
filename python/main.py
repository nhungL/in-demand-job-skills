import psycopg2
import pandas as pd

from connections.neon_connect import connect_to_database
from python_jobs.insert_jobs_into_neon import insert_jobs
from python_jobs.clean_jobs_data import preprocess_data, get_finalized_data
from python_jobs.upload_serpapi_data_to_drive import upload_data
from python_jobs.serpapi_jobs_data import *

def main():
    search_keys = ["Data Scientist", "Data Analyst", "Data Engineer",
                   "Business Analyst", "Software Engineer", "Machine Learning"]
    search_time = "date_posted:today"
    search_pages = 4 #search 4 pages per each job title => 24 pages/searches per week => 240 jobs per week
    serpapi_details = serpapi_account_details()

    if serpapi_details['total_searches_left'] >= 24:
        try:
            # Connect to the database
            print("\nConnecting Neon database ...")
            conn = connect_to_database()
            print("Connect Neon database: DONE")

            # Call Serp API and get jobs
            print("\nCalling SerpAPI ...")
            df_jobs_serpapi = search_multiple_keys(search_keys, search_pages, search_time)
            # df_jobs_serpapi = pd.read_csv('python/data/data_20230919.csv')
            print("Get jobs from SerpAPI: DONE")

            # Upload raw data to drive
            print("\nStart uploading data to Drive ...")
            upload_data(df_jobs_serpapi)
            print("Upload data to Drive: DONE")

            # Process data
            print("\nPreprocessing data ...")
            df_preprocess = preprocess_data(df_jobs_serpapi)
            print("Preprocess data: DONE")

            # Finalize data
            print("\nFinalizing data ...")
            df_jobs_cleaned = get_finalized_data(df_preprocess)
            print("Finalize data: DONE")

            # Insert into DB
            print("\nStart inserting jobs into database ...")
            insert_jobs(conn, df_jobs_cleaned)
            print("Insert data: DONE")

            # Close DB connection
            conn.close()

        except psycopg2.Error as e:
            print("Insertion error:", e)
            conn.rollback()
    else:
        print("Not enough search credits left.")

if __name__ == "__main__":
    main()