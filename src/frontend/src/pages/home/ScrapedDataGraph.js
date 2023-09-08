import {FetchAllTitles} from "../../graphql/queries";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {LineChart} from "../../styles/chart/LineChart";

export const ScrapedDataGraph = () => {

    const {loading, error, data} = FetchAllTitles();

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
            <h4>Graph shows Scraped Data Monthly/Daily</h4>
            <LineChart id="small" chartData={chartData} title={"Average Salary By Title"}/>
        </StyledChartContainer>
    );
}