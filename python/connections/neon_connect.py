import os
import psycopg2
from dotenv import load_dotenv

# Load .env file
load_dotenv()

def connect_to_database():
    connection_string = os.getenv('DATABASE_URL')
    conn = psycopg2.connect(connection_string)
    # Create a cursor object
    cur = conn.cursor()
    return conn