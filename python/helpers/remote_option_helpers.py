import re
import nltk
import string
stopwords = nltk.corpus.stopwords.words('english')
ps = nltk.PorterStemmer()
def search_remote_option(df):
    remote_keywords = ["remote", "work from home"]
    stemmed_remote_keywords = [ps.stem(keyword) for keyword in remote_keywords]

    remote_mask = (
            df['title'].str.contains('|'.join(stemmed_remote_keywords), case=False) |
            df['description'].str.contains('|'.join(stemmed_remote_keywords), case=False) |
            df['extensions'].str.contains('|'.join(stemmed_remote_keywords), case=False)
    )

    df['remote_option'] = remote_mask
    # df['remote_option'] = df['remote_option'].map({True: 1, False: 0})
    # df['remote_option'] = df['remote_option'].astype(int)

    return df['remote_option']