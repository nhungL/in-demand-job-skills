import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {ScrapedDataStat} from "./ScrapedDataStat";
import {ScrapedDataGraph} from "./ScrapedDataGraph";

export const HomePage = () => {
    return (
        <div>
            <StyledDivContainer>
                <ScrapedDataStat/>
                <ScrapedDataGraph/>
            </StyledDivContainer>
        </div>
    );
}