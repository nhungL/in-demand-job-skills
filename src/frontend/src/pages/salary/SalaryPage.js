import {FetchAllTitles} from "../../graphql/queries";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {MiniHeader} from "../../components/MiniHeader";
import {LineChart} from "../../styles/chart/LineChart";
import {Loading} from "../../components/Loading";
import {useEffect, useState} from "react";

export const SalaryPage = () => {

    const { loading, error, data } = FetchAllTitles();
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    useEffect(() => {
        // Update windowWidth when the window is resized
        const handleResize = () => {
            setWindowWidth(window.innerWidth);
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);
    const hideSidebarBreakpoint = 1200;

    if (loading) return (<Loading/>);
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
            {<MiniHeader pageTitle={"Salary Ranking"}/>}
            <StyledDivContainer style={{
                padding: windowWidth <= hideSidebarBreakpoint ? "0 2rem" : "0 2rem 0 4rem",
            }} >
                <StyledChartContainer>
                    <LineChart chartData={chartData} title={"Salaries By Title"}/>
                </StyledChartContainer>
            </StyledDivContainer>
        </div>
    );
}