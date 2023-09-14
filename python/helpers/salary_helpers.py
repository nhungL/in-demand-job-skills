from helpers.string_helpers import *

import re
import nltk
import string
stopwords = nltk.corpus.stopwords.words('english')
ps = nltk.PorterStemmer()

'''
    Format salary string extracted from detected extensions
'''
def format_salary_from_detected_extensions(salary_string):
    pattern = r'([\d,.]+K?)\s*(?:[-–]\s*([\d,.]+K?))?\s*(?:an? )?([a-zA-Z]+)'

    matches = re.search(pattern, salary_string)

    if matches:
        # Extract salary values and payment frequency unit
        lower_bound_str = matches.group(1).replace(',', '')
        upper_bound_str = matches.group(2).replace(',', '') if matches.group(2) else lower_bound_str
        frequency_unit = matches.group(3).strip()
        # print(lower_bound_str, upper_bound_str, frequency_unit)

        # Add "K" to the salary values if it's not already present
        lower_bound_str = lower_bound_str[:-1] + "000" if lower_bound_str.endswith("K") else lower_bound_str
        upper_bound_str = upper_bound_str[:-1] + "000" if upper_bound_str.endswith("K") else upper_bound_str

        # Convert salary values to integers
        lower_bound = float(lower_bound_str)
        upper_bound = float(upper_bound_str)

        return [lower_bound, upper_bound, frequency_unit]
    else:
        return None



'''
    Search for payment frequency: pay annually or hourly
'''
def search_payment_freq(text):
    payment_freq_units = ["annual", "year", "month", "hour"]
    keywords = ['salary', 'base pay']
    pay_frequency = ""

    text_with_salary = [line for line in text.split('\n') for word in keywords if word in line]
    for line in text_with_salary:
        line = clean_string(line)
        for unit in payment_freq_units:
            if unit in line:
                pay_frequency = unit
    return pay_frequency



'''
    Search salary by finding `$` in descriptions
    Use regex to get min, max and paymnet frequency if provided
'''
def search_salary_in_desc(text):
    payment_freq_units = ["annual", "year", "hour", "month"]
    text_with_salary = [line for line in text.split('\n') if '$' in line]

    for line in text_with_salary:
        matches = re.findall(r'\$([\d,.]+)\s*-\s*\$([\d,.]+)\s*(?:\/\s*|per\s*)?(\w+)?', line)
        if matches:
            min_salary = re.sub(r'\.0+$|\.$', '', matches[0][0].replace(',', ''))
            max_salary = re.sub(r'\.0+$|\.$', '', matches[0][1].replace(',', ''))
            pay_frequency = clean_string(matches[0][2])
            # print(matches)

            if float(min_salary) > float(max_salary):
                return None

            if pay_frequency == "year" or (len(min_salary) >= 5 and float(min_salary) <= float(max_salary)):
                pay_frequency = "annual"

            if not pay_frequency or pay_frequency not in payment_freq_units:
                if search_payment_freq(line):
                    pay_frequency = search_payment_freq(line)

            salary = [float(min_salary), float(max_salary), pay_frequency]
            return salary



'''
    Convert all to annual salary
'''
def convert_salary(salary_lst):
    hours_per_week = 40
    weeks_per_month = 4.33
    months_per_year = 12
    weeks_per_year = 52

    min_salary = int(salary_lst[0])
    max_salary = int(salary_lst[1])

    if 'hour' == salary_lst[2]:
        working_hours_per_year = hours_per_week * weeks_per_year
        min_salary *= working_hours_per_year
        max_salary *= working_hours_per_year

    if 'month' == salary_lst[2]:
        working_months_per_year = months_per_year
        min_salary *= working_months_per_year
        max_salary *= working_months_per_year


    return [min_salary, max_salary]
