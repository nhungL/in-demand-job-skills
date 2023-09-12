from helpers.string_helpers import *
def search_edu_degrees(row):
    degrees_keywords = {'bachelor': ['bachelor','bs', 'ba', 'bsc','beng'],
                        'master': ['master', 'msc', 'ma', 'mba', 'ms', 'meng'],
                        'doctorate': ['doctorate', 'phd']}

    mentioned_degrees = []

    for col in ['title', 'job_highlights', 'description']:
        text = clean_string(row[col].lower())
        for degree, keywords in degrees_keywords.items():
            if any(keyword in text for keyword in keywords):
                if degree not in mentioned_degrees:
                    mentioned_degrees.append(degree)

    return list(set(sorted(mentioned_degrees))) if mentioned_degrees else None