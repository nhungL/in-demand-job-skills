import { gql, useQuery } from "@apollo/client";

export const DEGREE_QUERY = gql`
    query GetDegreesByTitle($inputFilter: TitleFilter, $order: OrderDirection) {
        getDegrees(inputFilter: $inputFilter, order: $order) {
            degreeId
            title
            degree
            countByTitle
            percByTitle
            overallPerc
        }
    }
`;

export const FetchDegrees = (title) => {
    const {loading, error, data} = useQuery(DEGREE_QUERY, {
        variables: {
            inputFilter: { title: title },
            order: {direction: 'desc'} 
        }
    });
    if (loading) {console.log("Loading...");}
    if (error)  {console.error("Error" + error.message);}
    console.log("Degrees:", data);

    return { loading, error, data };
}