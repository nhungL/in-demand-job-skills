def get_skills_list():
    with open('python/tmp/skillsets.txt', 'r') as file:
        lst = file.read()
    skills_list = lst.split('\n')
    for i, skill in enumerate(skills_list):
        skills_list[i] = skill
    return skills_list

def extract_skills(cleaned_desc, skills_list):
    skills_list_lower = [skill.lower() for skill in skills_list]
    skills_set = set()
    for word in cleaned_desc:
        if word.lower() in skills_list_lower:  # Convert word to lowercase for comparison
            formatted_word = skills_list[skills_list_lower.index(word)]
            skills_set.add(formatted_word)
    return list(skills_set) if skills_set else []
