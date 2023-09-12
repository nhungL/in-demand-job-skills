import json
import re
import nltk
import string
stopwords = nltk.corpus.stopwords.words('english')
ps = nltk.PorterStemmer()
def clean_string(text):
    text = text.replace('\n', ' ')
    cleaned_string = "".join([word.lower() for word in text if word not in string.punctuation])
    tokens = re.split('\W+', cleaned_string)
    cleaned_string = " ".join([ps.stem(word) for word in tokens])
    return cleaned_string

def clean_text(desc):
    desc = "".join([word.lower() for word in desc if word not in string.punctuation])
    desc = desc.replace('\n', ' ')
    tokens = re.split('\W+', desc)
    desc = [ps.stem(word) for word in tokens if word not in stopwords]
    return desc


'''
    Usages: 
    - Get salary listed in detected_extensions
'''
def process_extensions(detected_extenstion):
    replaced_extension = detected_extenstion.replace("'", "\"").replace("True", "true").replace("False", "false")
    return json.loads(replaced_extension)