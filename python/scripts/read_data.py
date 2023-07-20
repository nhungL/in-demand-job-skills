import pandas as pd
import numpy as np
import re
import nltk
import string

df = pd.read_csv('python/data/random_data.csv')

# group job titles
def group_title(title):
    pattern_data_analyst = "data.*analyst|analy|statis|information"
    pattern_data_scientist = "data.*scien"
    pattern_data_engineer = "data.*engineer"
    pattern_ml_engineer = "machine learning|computer vision|deep learning"
    pattern_computer_scientist = "computer scientist|comput"
    pattern_data_modeler_architect = "data.*modeler|architect"
    pattern_bio_scientist = "bio|module|med|toxico|purifica|lab"
    pattern_research = "research"

    if re.search(pattern_data_analyst, title, re.IGNORECASE):
        title = "Data Analyst"
    elif re.search(pattern_data_scientist, title, re.IGNORECASE):
        title = "Data Scientist"
    elif re.search(pattern_data_engineer, title, re.IGNORECASE):
        title = "Data Engineer"
    elif re.search(pattern_ml_engineer, title, re.IGNORECASE):
        title = "ML Engineer"
    elif re.search(pattern_computer_scientist, title, re.IGNORECASE):
        title = "Computer Scientist"
    elif re.search(pattern_data_modeler_architect, title, re.IGNORECASE):
        title = "Data Modeler/Architect"
    elif re.search(pattern_bio_scientist, title, re.IGNORECASE):
        title = "Bio Scientist"
    elif re.search(pattern_research, title, re.IGNORECASE):
        title = "Research Scientist"
    else:
        title = "Others"

    return title

df['Job Title Grouped'] = df['Job Title'].apply(lambda x: group_title(x))

stopwords = nltk.corpus.stopwords.words('english')
ps = nltk.PorterStemmer()

def clean_text(desc):
    desc = "".join([word.lower() for word in desc if word not in string.punctuation])
    desc = desc.replace('\n', ' ')
    tokens = re.split('\W+', desc)
    desc = [ps.stem(word) for word in tokens if word not in stopwords]
    return desc

df['Job Description Cleaned'] = df['Job Description'].apply(lambda x: clean_text(x.lower()))

skills_list = []
lst = []
with open('python/data/skillsets.txt', 'r') as file:
    lst = file.read()

skills_list = lst.split(", ")
for i, skill in enumerate(skills_list):
    skills_list[i] = skill.lower()

def extract_skills(cleaned_desc):
    skills_set = set()
    for word in cleaned_desc:
        if word in skills_list:
            skills_set.add(word)

    skills = list(skills_set)
    return skills

df['Skills'] = df['Job Description Cleaned'].apply(lambda x: extract_skills(x))
def to_csv_file(df):
    df.to_csv('python/data/test.csv', index=False)

to_csv_file(df)