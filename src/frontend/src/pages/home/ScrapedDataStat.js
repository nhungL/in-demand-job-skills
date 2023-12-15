import styled from "styled-components";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {FetchAllInsertionStats} from "../../graphql/InsertionStatQueries";
import {isNumber} from "chart.js/helpers";

const StyledTableContainer = styled.table ({
    height: "20vh",
    width: "-webkit-fill-available",
});

const StyledTableHeader = styled.thead ({
    th: {
        border: "none",
        padding: "0.5rem",
        fontSize: "15px",
    },
});

const StyledTableBody = styled.tbody ({
    fontSize: "45px",
    td: {
        border: "none",
    },
});


export const ScrapedDataStat = () => {
    const insertionData = FetchAllInsertionStats();
    const insertionStat = insertionData?.getInsertionDataStat;

    const statData = [];
    const statTrends = [];
    if (insertionStat && insertionStat.length > 0) {
        const latestStat = insertionStat[insertionStat.length - 1];
        console.log("GET UPDATED STAT:", latestStat);

        const date = latestStat.updatedAt;
        const { format } = require('date-fns');
        const day = format(new Date(date.substring(0, 10)), 'MMM/dd/yyyy');
        const hour = date.substring(11, 16);
        statData.push(hour.concat(" UTC"));
        statData.push(latestStat.jobsAdded);
        statData.push(latestStat.totalJobs);

        statTrends.push(day);
        let jobsAddedTrend = 0;
        let totalJobsTrend = 0;
        if (insertionStat.length > 1) {
            const prevStat = insertionStat[insertionStat.length - 2];
            if (prevStat.jobsAdded > 0) {
                jobsAddedTrend = ((latestStat.jobsAdded/ prevStat.jobsAdded)*100).toFixed(1)
            }
            totalJobsTrend = ((latestStat.jobsAdded/ prevStat.totalJobs)*100).toFixed(1)
        }
        statTrends.push(`${jobsAddedTrend}`);
        statTrends.push(`${totalJobsTrend}`);
    }

    const statNames = ["Last Updated", "New Jobs Added", "Total Jobs Collected"];


    return (
        <StyledDivContainer style={{padding: "0 1rem", marginBottom: "2rem",}}>
            <StyledDivContainer>
                <h3 style={{
                    padding: "0.2rem 0",
                    borderBottom: "2px solid rgb(164, 153, 131)",
                    borderTop: "2px solid rgb(164, 153, 131)",
                    boxShadow: "rgb(164, 153, 131) 0px 0px 20px 0px inset",
                }}>
                    Collected Data Stats
                </h3>
                <StyledTableContainer>
                    <StyledTableHeader>
                        <tr>
                            {statNames.map((item) => (
                                <th key={item} style={{color: "rgb(218 209 190)",}}>
                                    {item}
                                </th>
                            ))}
                        </tr>
                    </StyledTableHeader>
                    <StyledTableBody>
                        <tr>
                            {statData.map((data) => (
                                <td key={data}>
                                    {data}
                                </td>
                            ))}
                        </tr>
                        <tr style={{color: "rgb(185,176,164)", fontSize: "20px"}}>
                            {statTrends.map((data) => (
                                <td key={data}>
                                    {isNumber(data) ? (
                                        <div style={{
                                            display: "inline-flex",
                                            color: data < 0 ? "#aa0303" : "#00c300",
                                        }}>
                                            <span
                                                style={{
                                                    backgroundImage: data < 0 ? `url("icons8-up-arrow-32-red.png")` : `url("icons8-up-arrow-32-green.png")`,
                                                    backgroundSize: 'contain',
                                                    backgroundRepeat: "no-repeat",
                                                    display: 'inline-block',
                                                    width: '1.2rem',
                                                    height: '1.2rem',
                                                }}
                                            />
                                            {data}%
                                        </div>
                                    ) : (data)}
                                </td>
                            ))}
                        </tr>
                    </StyledTableBody>
                </StyledTableContainer>
            </StyledDivContainer>
        </StyledDivContainer>
    );
}