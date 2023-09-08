import styled from "styled-components";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";

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
    fontSize: "30px",
    td: {
        border: "none",
    },
});

export const ScrapedDataStat = () => {
    const statNames = ["Last Updated", "New Jobs Today", "Total Jobs Queried"]
    const statData = ["Time - Date", "# of jobs", "total of jobs"]
    return (
        <StyledDivContainer style={{padding: "0 1rem", marginBottom: "2rem", }}>
            <StyledDivContainer>
                <h4>Table shows Scraped Data Statistics</h4>
                <StyledTableContainer>
                    <StyledTableHeader>
                        <tr>
                            {statNames.map((item) => (
                                <th key={item}>
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
                    </StyledTableBody>
                </StyledTableContainer>
            </StyledDivContainer>
        </StyledDivContainer>
    );
}