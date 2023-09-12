import os
import json
import pandas as pd
import numpy as np
from serpapi import GoogleSearch

def scrape_gg_jobs_serpapi(query, search_pages):
    results_per_query = []
    serpapi_key = os.getenv('SERPAPI_API_KEY')
    for page in range(search_pages):
        start = page * 10
        params = {
            "engine": "google_jobs",
            "google_domain": "google.com",
            "q": query,
            "gl": "us",
            "hl": "en",
            "start": start,
            "api_key": serpapi_key,
        }
        search = GoogleSearch(params)
        search_results = search.get_dict()

        formatted_results = json.dumps(search_results, indent=4)
        data = json.loads(formatted_results)

        results = [job for job in data["jobs_results"]]
        results_per_query.extend(results)

        time = data["search_metadata"]["processed_at"]
        print(f"END SerpApi CALLS: {query} on page {page + 1} at {time}")

    return results_per_query

def search_multiple_keys(search_keys, search_pages):
    data_dict = {}
    for query in search_keys:
        print("SEARCH FOR: ", query)
        jobs = scrape_gg_jobs_serpapi(query, search_pages)
        for job in jobs:
            if job["job_id"] not in data_dict:
                job["search_key"] = query
                data_dict[job["job_id"]] = job
        print("\n")
    return pd.DataFrame.from_dict(data_dict).transpose()

