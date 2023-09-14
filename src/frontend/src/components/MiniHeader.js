import {StyledMiniHeader} from "../styles/styled-components/StyledMiniHeader";
import {useEffect, useState} from "react";

export const MiniHeader = (props) => {
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
        windowWidth <= hideSidebarBreakpoint ? (

            <StyledMiniHeader>

            </StyledMiniHeader>
        ) : (
            <StyledMiniHeader style={{padding: "0.5rem",}}>
                <p style={{
                    fontWeight: "bold",
                    margin: 0,
                    textTransform: "uppercase",
                }}>{props.pageTitle}</p>
            </StyledMiniHeader>
        )
    );
}