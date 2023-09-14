import {FetchAllTitles} from "../../graphql/queries";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {PieChart} from "../../styles/chart/PieChart";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import styled from "styled-components";
import {Loading} from "../../components/Loading";

const StyledTableContainer = styled.table ({
    borderCollapse: "collapse",
    height: "30vh",
    width: "-webkit-fill-available",
});

const StyledTableHeader = styled.thead ({
    th: {
        border: "0.05rem solid rgb(164, 153, 131)",
        padding: "0.5rem",
        fontSize: "15px",
    },
});

const StyledTableBody = styled.tbody ({
    fontSize: "15px",
    td: {
        border: "0.05rem solid rgb(164, 153, 131)",
    },
});

const StyledData = styled.div ({

});

export const JobSummaries = () => {
    const titleData = FetchAllTitles();

    if (titleData.loading) return <Loading/>;
    if (titleData.error) return <p>Error : {titleData.error.message}</p>;

    const dataByTitle = titleData?.data.allByTitle;
    console.log("In Job Summaries: ", dataByTitle)

    const titleList = dataByTitle.map((data) => data.title)

    const degreeColors = {
        Bachelor: 'rgb(252,185,146)',
        Master: 'rgb(188,96,96)',
        Doctorate: 'rgb(192,75,75)',
    };
    const DegreeLegend = ({ degree }) => {
        return (
            <div style={{ display: 'flex', alignItems: 'center', fontSize: "15px", }}>
                <div style={{ width: '10px', height: '10px', backgroundColor: degreeColors[degree], marginRight: '5px' }}></div>
                {degree}
            </div>
        );
    };

    const chartDataList = new Map();
    dataByTitle.map((job) => {
        let eduData = {};
        const labels = job.eduDegree.map((d) => d.degree);
        const data = job.eduDegree.map((d) => d.percent);
        const colors = labels.map((label) => degreeColors[label]);

        eduData = {
            labels: labels,
            datasets: [
                {
                    label: "Top 10 Skills",
                    data: data,
                    backgroundColor: colors,
                    borderColor: "rgb(255,244,228)",
                    borderWidth: 1,
                },
            ],
        }
        chartDataList.set(job.title, eduData);
    })

    const rowHeaders = ['Job Count', 'Top 1 Skill', 'Degree'];


    return (
        <StyledDivContainer style={{padding: "0 1rem", marginBottom: "2rem", }}>
            <StyledDivContainer style={{overflow: "auto"}}>
                <StyledTableContainer>
                    <StyledTableHeader>
                        <tr>
                            <th>Summary</th>
                            {titleList.map((title) => (
                                <th key={title}>
                                    {title}
                                </th>
                            ))}
                        </tr>
                    </StyledTableHeader>
                    <StyledTableBody>
                        {rowHeaders.map((header, index) => (
                            <tr key={index}>
                                <td>
                                    <div style={{fontWeight: "bold", fontSize: "15px", padding: "0.05rem",}}>
                                        {header}
                                    </div>
                                    {header === 'Degree' && (
                                        <div style={{display: "inline-grid", paddingTop: "0.5rem"}}>
                                            {Object.keys(degreeColors).map((degree) => (
                                                <DegreeLegend key={degree} degree={degree} />
                                            ))}
                                        </div>
                                    )}
                                </td>
                                {dataByTitle.map((job) => (
                                    <td key={job.id}>
                                        {header === 'Job Count' && <StyledData>{job.count}</StyledData>}
                                        {header === 'Top 1 Skill' &&
                                            <StyledData>
                                                      {job.topSkills.slice(0, 1).map((s) => s.skill)}
                                            </StyledData>
                                        }
                                        {header === 'Degree' &&
                                            <StyledData>
                                                <StyledChartContainer>
                                                    {<PieChart chartData = {chartDataList.get(job.title)} title={""}/>}
                                                </StyledChartContainer>
                                            </StyledData>
                                        }
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </StyledTableBody>
                </StyledTableContainer>
            </StyledDivContainer>
        </StyledDivContainer>
    );
}