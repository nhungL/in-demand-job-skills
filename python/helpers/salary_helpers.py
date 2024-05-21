from helpers.string_helpers import *

import re
import nltk
import string
stopwords = nltk.corpus.stopwords.words('english')
ps = nltk.PorterStemmer()

def check_freg_variations(pay_frequency):
    freq_variations = {
        "hour": ["hourli"],
        "month": ["monthli"],
        "annual": ["annually", "yearli", "year"],
    }

    for standard_term, variations in freq_variations.items():
        if pay_frequency in variations:
            pay_frequency = standard_term
            break

    return pay_frequency

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
        pay_frequency = matches.group(3).strip()
        # print(lower_bound_str, upper_bound_str, pay_frequency)

        lower_bound_str = float(lower_bound_str[:-1]) * 1000 if lower_bound_str.endswith("K") else lower_bound_str
        upper_bound_str = float(upper_bound_str[:-1]) * 1000 if upper_bound_str.endswith("K") else upper_bound_str

        # Convert salary values to floats
        lower_bound = float(lower_bound_str)
        upper_bound = float(upper_bound_str)

        pay_frequency = check_freg_variations(pay_frequency)

        return [lower_bound, upper_bound, pay_frequency]
    else:
        return None



'''
    Search for payment frequency: pay annually or hourly
'''
def search_payment_freq(text):
    payment_freq_units = ["annual", "year", "month", "hour"]
    keywords = ['salary', 'base pay', 'pay rate', "compensation"]
    pay_frequency = ""

    text_with_salary = [line for line in text.split('\n') for word in keywords if word in line]
    for line in text_with_salary:
        for unit in payment_freq_units:
            if unit in line:
                pay_frequency = unit
    return pay_frequency



'''
    Search salary by finding `$` in descriptions
    Use regex to get min, max and paymnet frequency if provided
'''
def search_salary_in_desc(text):
    payment_freq_units = ["annual", "hour", "month"]
    text_with_salary = [line for line in text.split('\n') if '$' in line]

    for line in text_with_salary:
        pattern = r'\$([\d,.]+)\s*[-–—]\s*\$([\d,.]+)\s*(?:\/\s*|per\s*|an\s*|a\s*)?(\w+)?'
        matches = re.findall(pattern, line)
        if matches:
            min_salary = re.sub(r'\.0+$|\.$', '', matches[0][0].replace(',', ''))
            max_salary = re.sub(r'\.0+$|\.$', '', matches[0][1].replace(',', ''))
            pay_frequency = clean_string(matches[0][2])
            # print(matches)

            try:
                min_val = float(min_salary)
                max_val = float(max_salary)
            except ValueError as e:
                print("Invalid numeric string:", e)
                return None
                break

            if float(min_salary) > float(max_salary) or float(min_salary) == 0.0 or float(max_salary) == 0.0:
                return None

            if (len(min_salary.replace('.','')) >= 5 and float(min_salary) <= float(max_salary)):
                if len(str(round(float(min_salary)))) == 4:
                    pay_frequency = "month"
                else:
                    pay_frequency = "annual"

            pay_frequency = check_freg_variations(pay_frequency)

            if not pay_frequency or pay_frequency not in payment_freq_units:
                line = clean_string(line)
                if search_payment_freq(line):
                    pay_frequency = search_payment_freq(line)
                else:
                    return None

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

    if ('annual' == salary_lst[2] and min_salary < 15080) or min_salary < 15080:
        return []

    return [min_salary, max_salary]
