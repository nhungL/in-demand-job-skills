import { gql, useQuery } from "@apollo/client";

export const SKILL_BY_TITLE_QUERY = gql`
    query GetSkillsByTitle($inputFilter: TitleFilter, $order: OrderDirection) {
        getSkillsByTitle(inputFilter: $inputFilter, order: $order) {
                title
                skill
                countByTitle
                percByTitle
        }
    }
`;

export const FetchSkillsByTitles = (inputFilter, order) => {
    const {loading, error, data} = useQuery(SKILL_BY_TITLE_QUERY, {
        variables: {
            inputFilter: inputFilter || {},
            order: order || {direction: 'desc'},
        }
    });
    if (loading) {console.log("Loading...");}
    if (error)  {console.error("Error" + error.message);}

    return { loading, error, data };
}

export const OVERALL_SKILLS_QUERY = gql`
    query GetOverallSkills($order: OrderDirection) {
        getOverallSkillsStat(order: $order) {
                skill
                overallPerc
        }
    }
`;

export const FetchOverallSkillsStats = (order) => {
    const {loading, error, data} = useQuery(OVERALL_SKILLS_QUERY, { 
        variables: {
            order: order || {direction: 'desc'},
        }
    });
    if (loading) {console.log("Loading...");}
    if (error)  {console.error("Error" + error.message);}

    return { loading, error, data };
}