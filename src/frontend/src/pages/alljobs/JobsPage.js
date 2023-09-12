import {JobSummaries} from "./JobSummaries";
import {MiniHeader} from "../../components/MiniHeader";
import {AvgSalaryChart} from "./AvgSalaryChart";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";

export const JobsPage = () => {
    return (
        <div>
            {<MiniHeader pageTitle={"Job Summary"}/>}
            <StyledDivContainer>
                {<JobSummaries/>}
                {<AvgSalaryChart/>}
            </StyledDivContainer>
        </div>
    );
};