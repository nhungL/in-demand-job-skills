import {JobSummaries} from "./JobSummaries";
import {MiniHeader} from "../../components/MiniHeader";
import {AvgSalaryChart} from "./AvgSalaryChart";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";

export const JobsPage = () => {
    return (
        <div>
            {<MiniHeader pageTitle={"Jobs Summary"}/>}
            <StyledDivContainer>
                {<JobSummaries/>}
                {<AvgSalaryChart/>}
            </StyledDivContainer>
        </div>
    );
};