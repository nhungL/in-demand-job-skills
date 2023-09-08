import {FetchAllTitles} from "../../graphql/queries";
import {LineChart} from "../../styles/chart/LineChart";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";

export const AvgSalaryChart = () => {
    const { loading, error, data } = FetchAllTitles();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    const dataByTitle = data.allByTitle;

    const titleList = dataByTitle.map((data) => data.title);
    const avgSalary = dataByTitle.map((data) => data.avgSalary.toFixed(2));

    const chartData = {
        labels: titleList,
        datasets: [
            {
                label: "Average Salary",
                data: avgSalary,
                backgroundColor: "rgba(255,0,0,0.71)",
                borderColor: "rgba(255,0,0,0.71)",
                borderWidth: 3,
            },
        ],
    };

    return (
        <StyledChartContainer>
            <LineChart id="small" chartData={chartData} title={"Average Salary By Title"}/>
        </StyledChartContainer>
    );
}