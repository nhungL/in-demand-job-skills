import {SkillsStat} from "./SkillsStat";
import {MiniHeader} from "../../components/MiniHeader";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {useEffect, useState} from "react";

export const SkillsPage = () => {
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    useEffect(() => {
        // Update windowWidth when the window is resized
        const handleResize = () => {
            setWindowWidth(window.innerWidth);
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    // Define a breakpoint for hiding the sidebar
    const hideSidebarBreakpoint = 1200;

    return (
        <div>
            {<MiniHeader pageTitle={"Skills Ranking"}/>}
            <StyledDivContainer style={{
                padding: windowWidth <= hideSidebarBreakpoint ? "0 2rem" : "0 2rem 0 6rem",
            }} >
                {<SkillsStat/>}
            </StyledDivContainer>
        </div>
    );
};