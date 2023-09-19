import styled from "styled-components";
import {StyledDivContainer, StyledFooter} from "../../styles/styled-components/StyledMain";
import {MiniHeader} from "../../components/MiniHeader";
import {useEffect, useState} from "react";

const StyledAboutInfo = styled.div({
    h3: {
        margin: 0,
    },
    paddingBottom: "2rem",
});

const StyledContactInfo = styled.span({
    width: "2.5rem",
    height: "2.5rem",
    cursor: "pointer",
    webkitTransitionProperty: "all",
    webkitTransitionDuration: "0.3s",
    webkitTransitionTimingFunction: "ease",
    '&:hover': {
        transform: "scale(1.5)",
    },
    backgroundSize: "contain",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
});

function renderAboutInfo  (){
    const aboutInfoList = [
        { id: "Goal",
            body: "The project aims to extract and analyze the most in-demand job skills from recent job postings, " +
                "enabling individuals to focus on developing the skills " +
                "that are currently trending in the data-related job market.\n",
            url: null,
        },
        { id: "Technique",
            body: "The app leverages information from daily job postings retrieved from Google search results. " +
                "The emphasis is on roles linked to data field, located in the U.S.\n" +
                "These postings undergo analysis using a natural language processing (NLP) pipeline, " +
                "and the outcomes are consolidated to pinpoint the key skills and median salaries for each job title. \n",
            url: null,
        },
        { id: "Resources",
            body: "Job posting data is collected daily.\n",
            url: {
                link: "https://serpapi.com/google-jobs-api",
                text: "Powered by SerpApi - Google Jobs API"
            },
        },
        { id: "Links",
            body: "Github: ",
            url: {
                link: "https://github.com/nhungL/in-demand-job-skills",
                text: "Source Code",
            },
        },
    ];

    return aboutInfoList.map((item) => {
        return(
            <StyledAboutInfo key={item.id}>
                <div style={{
                    borderRight: "solid 2px rgb(166 211 211)",
                    borderLeft: "solid 2px rgb(166 211 211)",
                    width: "fit-content",
                    display: "inline-block",
                    borderRadius: "0.3rem",
                    padding: "0 1rem",
                }}>
                    <h3>{item.id}</h3>
                </div>
                <p>
                    {item.body}
                    {item.url !== null && (
                        <a  href={item.url.link}
                            style={{textDecoration:"none", color: "rgb(233 148 18)", }}>
                            {item.id === "Resources" ? (
                                <div style={{margin: "0.5rem"}}>
                                    {item.url.text}
                                    <div style={{marginTop: "1rem"}}>
                                        <img alt="SerpApi" src="https://avatars.githubusercontent.com/u/34724717?s=200&v=4" width="auto" height="50px"/>
                                    </div>
                                </div>
                                ) : item.url.text
                            }
                        </a>
                    )}
                </p>
            </StyledAboutInfo>
        )}
    )
}

function renderContactInfo  (){
    const contactInfoList = [
        { id: "LinkedIn", href: "https://www.linkedin.com/in/nhungluong/", image: `url("In-Blue-21@2x.png")`, },
        { id: "GitHub", href: "https://github.com/nhungL", image: `url("GitHub-Mark-64px.png")`,},
        { id: "Email", href: "mailto:nhung.luong1098@gmail.com", image: `url("gmail-icon-38472.png")`,},
        { id: "Twitter", href: "https://twitter.com/NhungLuong12", image: `url("Twitter-logo-blue.png")`,},
    ];

    return contactInfoList.map((item) => {
        return(
            <a key={item.id}
               href={item.href}
               style={{
                   alignItems: "center",
                   margin: "0 1rem",
                   display: "flex",
                   flexDirection: "column",
               }}
            >
                <StyledContactInfo
                    id={item.id}
                    style={{
                        backgroundImage: item.id ? item.image : "none",
                        border: item.id === "GitHub" ? "solid 2px white" : "solid 1px rgb(5, 19, 15)",
                        borderRadius: item.id === "GitHub" ? "50%" : "10%",
                        backgroundColor: item.id === "GitHub" ? "white" : "none",
                    }}
                />
            </a>
        )}
    )
}

export const AboutPage = () => {
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

    const hideSidebarBreakpoint = 1200;
    return (
        <div>
            {<MiniHeader pageTitle={"About"}/>}
            <StyledDivContainer>
                <StyledDivContainer>
                    <ul style={{padding: "0 10vh", }}>
                        {renderAboutInfo()}
                    </ul>
                </StyledDivContainer>
                <StyledFooter
                 style={{
                     position: windowWidth <= hideSidebarBreakpoint ? "relative" : "absolute",
                 }}>
                    <ul style={{display: "flex"}}>
                        {renderContactInfo()}
                    </ul>
                </StyledFooter>
            </StyledDivContainer>
        </div>
    );
};