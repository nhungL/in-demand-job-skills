# import pandas as pd
# import numpy as np
# import matplotlib.pyplot as plt
# import seaborn as sns
# import os
#
# # Get the absolute path of the current script
# script_path = os.path.abspath(__file__)
# # Get the absolute path of the parent directory of the script
# script_dir = os.path.dirname(script_path)
# # Construct the absolute file path to the CSV file
# csv_file_path = os.path.join(script_dir, '../data/cleaned_data.csv')
#
# df = pd.read_csv(csv_file_path)
# # print(df.head())
#
# df_skills = df.loc[:, ['Job Title Grouped', 'Skills']]
# title_grouped = df_skills.groupby('Job Title Grouped')['Skills'].apply(set)
#
#
# def dict_skills(grouped_lst):
#     skills_lst = []
#     skills_dict = {}
#     for sublist in grouped_lst:
#         lst = sublist.strip('[]').replace("'", "").split(", ")
#         if len(lst) > 1:
#             for skill in lst:
#                 skills_lst.append(skill)
#                 skills_dict[skill] = skills_lst.count(skill)
#     return skills_dict
#
# nested_dict = {}
# for title, skills_lsts in title_grouped.items():
#     skills_dict = dict_skills(skills_lsts)
#     nested_dict[title] = skills_dict
#
# arr_title = []
# for title, skills_dict in nested_dict.items():
#     print(title, skills_dict.keys())


import csv
import requests

# Read the CSV file
csv_file_path = 'data.csv'  # Replace with your CSV file path
data = []
with open(csv_file_path, 'r') as file:
    reader = csv.DictReader(file)
    for row in reader:
        data.append(row)

# GraphQL API endpoint
graphql_endpoint = 'http://localhost:8080/graphql'  # Replace with your GraphQL API endpoint

# Craft the GraphQL mutation
mutation = '''
    mutation CreateJob($input: JobInput!) {
        createJob(input: $input) {
            id
            title
            skills
            # Add other fields you want to retrieve after creating a job
        }
    }
'''

# Iterate over each row in the CSV data
for record in data:
    variables = {
        'input': {
            'id': record['Job ID'],
            'title': record['Job Title Grouped'],
            'skills': record['Skills'].split(',') if record['Skills'] else []
        }
    }

    # Send the GraphQL mutation request
    response = requests.post(graphql_endpoint, json={'query': mutation, 'variables': variables})

    # Process the response
    result = response.json()
    if 'errors' in result:
        print(f"Error creating job: {result['errors']}")
    else:
        created_job = result['data']['createJob']
        print(f"Created job with ID: {created_job['id']}")



