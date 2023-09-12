import {gql, useQuery} from "@apollo/client";

export const INSERTION_DATA_QUERIES = gql`
    query getInsertionDataStat {
        getInsertionDataStat {
            insertionId
            updatedAt
            jobsAdded
            totalJobs
        }
    }
`;

export const FetchAllInsertionStats = () => {
    const { loading, error, data } = useQuery(INSERTION_DATA_QUERIES);
    if (loading) {console.log("Loading...");}
    if (error)  {console.log("Error" + error.message);}
    console.log("INSERTION DATA Query:", data);

    return data;
}