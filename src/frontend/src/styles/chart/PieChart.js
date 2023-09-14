import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend,
    ArcElement,
} from 'chart.js';
import { Pie } from "react-chartjs-2";
import ChartDataLabels from 'chartjs-plugin-datalabels';
import styled from "styled-components";
import {StyledDivContainer} from "../styled-components/StyledMain";

ChartJS.register(
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend,
    ChartDataLabels,
    ArcElement,
);

const StyledPieChart = styled.div({
    width: `15vh`,
    height: "15vh",
    display: "inline-grid",
    paddingBottom: "1rem",
})

export const PieChart = ({ chartData, title }) => {
    return (
        <StyledDivContainer>
            <StyledPieChart>
                <Pie
                    data={chartData}
                    options={
                        {
                            responsive:true,
                            maintainAspectRatio: false,
                            plugins: {
                                title: {
                                    display: false,
                                },
                                legend: {
                                    display: false
                                },
                                labels: {
                                    fontColor: "rgb(241 254 255)",
                                },
                                datalabels: {
                                    display: true,
                                    color: "black",
                                    formatter: function(value) {
                                        return value.toFixed(2) + '%';
                                    },
                                    labels: {
                                        title: {
                                            font: {
                                                size: 10,
                                            }
                                        }
                                    },
                                },
                            }
                        }
                    }
                />
            </StyledPieChart>
        </StyledDivContainer>
    );
};