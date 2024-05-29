import {FetchAllInsertionStats} from "../../graphql/InsertionStatQueries";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {LineChart} from "../../styles/chart/LineChart";
import {Loading} from "../../components/Loading";

export const ScrapedDataGraph = () => {
    const { loading, error, data }  = FetchAllInsertionStats();
    if (loading) return <Loading/>;
    if (error) return <p>Error: {error.message}</p>;

    const insertionStat = data?.getInsertionDataStat;
    console.log(insertionStat);

    const labels = insertionStat.map((item) => {
        const date = item.updatedAt;
        const { format } = require('date-fns');
        const day = format(new Date(date.substring(0, 10)), 'MMM/dd/yyyy');
        return day
    });
    const jobsAdded = insertionStat.map((item) => item.jobsAdded);
    const totalJobs = insertionStat.map((item) => item.totalJobs);

    const chartData = {
        labels: labels,
        datasets: [
            {
              label: "Jobs Added",
              data: jobsAdded,
              backgroundColor: "rgba(75, 192, 192, 0.2)",
              borderColor: "rgba(75, 192, 192, 1)",
              borderWidth: 1,
              fill: false,
            },
            {
              label: "Total Jobs",
              data: totalJobs,
              backgroundColor: "rgba(153, 102, 255, 0.2)",
              borderColor: "rgba(153, 102, 255, 1)",
              borderWidth: 1,
              fill: false,
            },
          ],
    };

    return (
        <StyledChartContainer>
            <LineChart id="large" chartData={chartData} title={"Jobs Added and Total Jobs Over Time"}/>
        </StyledChartContainer>
    );
}