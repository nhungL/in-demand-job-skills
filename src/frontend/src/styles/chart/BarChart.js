import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Bar } from "react-chartjs-2";
import ChartDataLabels from 'chartjs-plugin-datalabels';
import styled from "styled-components";
import {StyledDivContainer} from "../styled-components/StyledMain";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    ChartDataLabels,
);

const StyledBarChart = styled.div({
    width: "70vw",
    height: "60vh",
})

export const BarChart = ({ chartData, title }) => {
    return (
        <StyledDivContainer>
            <StyledBarChart>
                <Bar
                    data={chartData}
                    options={
                        {
                            indexAxis: 'y',
                            elements: {
                                bar: {
                                    borderWidth: 2,
                                }
                            },
                            responsive:true,
                            maintainAspectRatio: false,
                            scales: {
                                x: {
                                    border: { display: true, color: "rgb(218, 209, 190)", },
                                    grid: { color: "rgb(47 47 47)"},
                                    ticks: { color: "#E3E3E3", beginAtZero: true, font: {size: 20}, },
                                    grace: 1,
                                },
                                y: {
                                    border: { display: true, color: "rgb(218, 209, 190)", },
                                    grid: { color: "rgb(47 47 47)"},
                                    ticks: { color: "#E3E3E3", font: {size: 20} , },
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
                                    display: false
                                },
                                labels: {
                                    fontColor: "rgb(241 254 255)",
                                },
                                datalabels: {
                                    display: true,
                                    color: "rgb(255,242,216)",
                                    align: "end",
                                    anchor: "end",
                                    formatter: function(value) {
                                        return value + '%';
                                    },
                                    labels: {
                                        title: {
                                            font: {
                                                size: 14,
                                            }
                                        }
                                    },
                                },
                            }
                        }
                    }
                />
            </StyledBarChart>
        </StyledDivContainer>
    );
};