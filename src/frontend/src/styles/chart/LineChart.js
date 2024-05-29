import styled from "styled-components";
import {Line} from "react-chartjs-2";
import {StyledDivContainer} from "../styled-components/StyledMain";

import {Chart, registerables} from 'chart.js'

Chart.register(...registerables)

const StyledLineChart = styled.div({
    // width: "80vw",
    // height: "40vh",
})

export const LineChart = ({ id, chartData, title }) => {
    return (
        <StyledDivContainer>
            <StyledLineChart style={{
                width: id === "small" ? "80vw" : "70vw",
                height: id === "small" ? "40vh" : "60vh",
            }}>
                <Line
                    data={chartData}
                    options={
                        {
                            responsive:true,
                            maintainAspectRatio: false,
                            scales: {
                                x: {
                                    border: { display: true, color: "rgb(218, 209, 190)", },
                                    grid: { color: "rgb(47 47 47)"},
                                    ticks: {
                                        color: "#E3E3E3",
                                        beginAtZero: true,
                                        font: {size: 15},
                                        minRotation: 30,
                                        maxRotation:30,
                                    },
                                },
                                y: {
                                    border: { display: true, color: "rgb(218, 209, 190)", },
                                    grid: { color: "rgb(47 47 47)"},
                                    ticks: {
                                        color: "#E3E3E3",
                                        font: {size: 15} ,
                                        callback: function (value) {
                                            if (id === "num") {return value;}
                                            else {return "$" + value;}
                                        },
                                        padding: 10,
                                    },
                                },
                            },
                            plugins: {
                                title: {
                                    display: true,
                                    text: title,
                                    color: "rgb(241 254 255)",
                                    font: {
                                        size: 18,
                                        weight: "bold",
                                    },
                                },
                                legend: {
                                    display: true
                                },
                                labels: {
                                    fontColor: "rgb(241 254 255)",
                                },
                                datalabels: {
                                    display: function(ctx) {
                                        console.log(ctx);
                                        const chartLabel = ctx.dataset.label
                                        return chartLabel === "Average Salary";
                                    },
                                    color: "rgb(255,242,216)",
                                    align: (context) => {
                                        console.log(context);
                                        const align = [];
                                        if (context.dataIndex === 0) {align.push("right")}
                                        else if (context.dataIndex === (context.dataset.data.length - 1)) {
                                            align.push("left")
                                        }
                                        else {align.push("top")}
                                        return align;
                                    },
                                    offset: 6,
                                    formatter: function(value) {
                                        if (id === "num") {return value;}
                                        else {return "$" + value;}
                                    },
                                    labels: {
                                        title: {
                                            font: {
                                                size: 13,
                                                weight: "bold",
                                            }
                                        }
                                    },
                                },
                            }
                        }
                    }
                />
            </StyledLineChart>
        </StyledDivContainer>
    );
};