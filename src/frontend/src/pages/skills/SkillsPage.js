import {SkillsStat} from "./SkillsStat";
import {MiniHeader} from "../../components/MiniHeader";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";

export const SkillsPage = () => {
    return (
        <div>
            {<MiniHeader pageTitle={"All Skill Stats"}/>}
            <StyledDivContainer style={{padding: "0 2rem 0 6rem", }} >
                {<SkillsStat/>}
            </StyledDivContainer>
        </div>
    );
};