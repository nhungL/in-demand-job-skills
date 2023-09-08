import {FetchAllTitles} from "../../graphql/queries";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {MiniHeader} from "../../components/MiniHeader";
import {LineChart} from "../../styles/chart/LineChart";

export const SalaryPage = () => {

    const { loading, error, data } = FetchAllTitles();

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    const dataByTitle = data.allByTitle;

    const titleList = dataByTitle.map((data) => data.title);
    const minValue = dataByTitle.map((data) => data.minSalary);
    const maxValue = dataByTitle.map((data) => data.maxSalary);
    const avgSalary = dataByTitle.map((data) => data.avgSalary.toFixed(2));

    const chartColors = [
        '#fcb992',
        '#eaa628',
        "rgba(255,0,0,0.71)",
    ]


    const chartData = {
        labels: titleList,
        datasets: [
            {
                label: "Min Salary",
                data: minValue,
                backgroundColor: chartColors[0],
                borderColor: chartColors[0],
                borderWidth: 3,
            },
            {
                label: "Max Salary",
                data: maxValue,
                backgroundColor: chartColors[1],
                borderColor: chartColors[1],
                borderWidth: 3,
            },
            {
                label: "Average Salary",
                data: avgSalary,
                backgroundColor: chartColors[2],
                borderColor: chartColors[2],
                borderWidth: 3,
            },
        ],
    };

    return (
        <div>
            {<MiniHeader pageTitle={"Salaries"}/>}
            <StyledDivContainer style={{padding: "0 2rem 0 4rem"}}>
                <StyledChartContainer>
                    <LineChart chartData={chartData} title={"Salaries By Title"}/>
                </StyledChartContainer>
            </StyledDivContainer>
        </div>
    );
}