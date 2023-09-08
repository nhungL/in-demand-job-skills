import {gql, useQuery} from "@apollo/client";

export const JOB_QUERIES = gql`
    query getAllJobs($jobFilter: JobFilter) {
        getAllJobs (inputFilter: $jobFilter) {
            jobId
            title
            companyName
            location
            remoteOption
            salary
            eduDegree
            skills
            postedAt
            scheduleType
        }
    }
`;

export const FetchAllJobs = (jobFilter) => {
    const { loading, error, data } = useQuery(JOB_QUERIES, {variables: jobFilter,});
    if (loading) {console.log("Loading...");}
    if (error)  {console.log("Error" + error.message);}
    console.log("FILTERED JOB Query:", data);

    return { loading, error, data };
}