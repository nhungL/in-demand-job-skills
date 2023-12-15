import { gql, useQuery } from "@apollo/client";
export const FetchAllTitles = () => {
    const {loading, error, data} = useQuery(TITLE_QUERIES);
    if (loading) {console.log("Loading...");}
    if (error)  {console.error("Error" + error.message);}
    // console.log("FetchAllTitles:", data);

    return { loading, error, data };
}

export const TITLE_QUERIES = gql`
    query allByTitle($title: String) {
        allByTitle (inputFilter: {title: $title}) {
            title
            minSalary
            maxSalary
            avgSalary
            count
            topSkills {
                skill
                percent
            }
            eduDegree {
                degree
                percent
            }
        }
    }
`;

export const SKILL_QUERIES = gql`
    query getAllSkills($order: Sort) {
        getAllSkills (orderBy: {percent: $order}) {
            skill
            count
            percent
        }
    }
`;


