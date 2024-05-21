import {gql, useQuery} from "@apollo/client";

export const JOB_QUERY = gql`
    query GetAllJobs($jobFilter: JobFilter) {
        getAllJobs (inputFilter: $jobFilter) {
            jobId
            title
            companyName
            location
            remoteOption
            postedAt
            scheduleType
        }
    }
`;

export const FetchAllJobs = (jobFilter) => {
    const { loading, error, data } = useQuery(JOB_QUERY, {variables: jobFilter,});
    if (loading) {console.log("Loading...");}
    if (error)  {console.log("Error" + error.message);}
    console.log("FILTERED JOB Query:", data);

    return { loading, error, data };
}

export const GET_DISTINCT_TITLES_QUERY = gql`
    query GetDistinctTitles {
        getDistinctTitles
    }
`;

export const GetDistinctTitles = () => {
    const { loading, error, data } = useQuery(GET_DISTINCT_TITLES_QUERY);
    if (loading) {console.log("Loading...");}
    if (error)  {console.log("Error" + error.message);}

    return { data };
}
