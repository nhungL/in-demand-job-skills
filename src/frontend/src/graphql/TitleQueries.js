import { gql, useQuery } from "@apollo/client";

export const TITLE_QUERIES = gql`
    query GetAllTitles($title: String) {
        getAllTitles(inputFilter: { title: $title }) {
            title
            minSalary
            maxSalary
            avgSalary
            count
            top10Skills {
                skill
                percByTitle
            }
            eduDegree {
                degree
                percByTitle
            }
        }
    }
`;

export const FetchAllTitles = (title) => {
    const {loading, error, data} = useQuery(TITLE_QUERIES, {
        variables: {title: title || null}
    });
    if (loading) {console.log("Loading...");}
    if (error)  {console.error("Error" + error.message);}
    // console.log("FetchAllTitles:", data);

    return { loading, error, data };
}


