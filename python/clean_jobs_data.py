import pandas as pd
import numpy as np

from helpers.remote_option_helpers import *
from helpers.salary_helpers import *
from helpers.skills_helpers import *
from helpers.edu_degree_helpers import *
from helpers.string_helpers import *

def preprocess_data(df_jobs_all):
    # clean job title
    df_jobs_all['old_title'] = df_jobs_all['title']
    df_jobs_all['title'] = df_jobs_all['search_key']

    # create REMOTE_OPTION column
    df_jobs_all['remote_option']=search_remote_option(df_jobs_all)

    # create SALARY column
    # 1. Search in detected_extensions
    df_jobs_all['salary'] = df_jobs_all['detected_extensions'].apply(
                            lambda x: process_extensions(x)).apply(
                            lambda x: x.get('salary'))
    df_jobs_all['salary'] = df_jobs_all['salary'].apply(lambda x: format_salary_from_detected_extensions(x) if x != None else None)
    # 2. Search salary in description
    df_jobs_all['salary'].fillna(df_jobs_all['description'].apply(lambda x: search_salary_in_desc(x)), inplace=True)
    # 3. Convert to annual salary
    df_jobs_all['salary'] = df_jobs_all['salary'].apply(lambda x: convert_salary(x) if x != None else [])

    # create EDU_DEGREE column
    df_jobs_all['edu_degree'] = df_jobs_all.apply(search_edu_degrees, axis=1)

    # create SKILLS column
    df_skills = pd.DataFrame()
    df_skills['description'] = df_jobs_all['description']
    df_skills['cleaned_description'] = df_skills['description'].apply(lambda x: clean_text(x.lower()))
    # Convert all skills in the skills_list to lowercase
    skills_list = get_skills_list()
    df_skills['skills'] = df_skills['cleaned_description'].apply(lambda x: extract_skills(x, skills_list))
    df_jobs_all['skills'] = df_skills['skills']

    # create posted_at, schedule_type columns
    df_jobs_all['posted_at'], df_jobs_all['schedule_type'] = zip(*df_jobs_all['detected_extensions'].apply(
        lambda x: process_extensions(x)).apply(
        lambda x: (x.get('posted_at'), x.get('schedule_type'))))

    return df_jobs_all


def get_finalized_data(df):
    final_columns = ['job_id', 'title', 'company_name', 'location', 'via',
                     'description',  'extensions', 'remote_option', 'salary',
                     'edu_degree', 'skills', 'posted_at', 'schedule_type']

    final_df_jobs_all = df.loc[:, final_columns]
    return final_df_jobs_all