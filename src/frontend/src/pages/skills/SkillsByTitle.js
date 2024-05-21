import {useEffect, useState} from "react";
import {GetDistinctTitles} from "../../graphql/JobQueries";
import {BarChart} from "../../styles/chart/BarChart";
import {StyledDDList, StyledDDOption, StyledDDSelect} from "../../styles/styled-components/StyledDropDown";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {MiniHeader} from "../../components/MiniHeader";
import {Loading} from "../../components/Loading";
import { FetchSkillsByTitles } from "../../graphql/SkillQueries";

export const SkillsByTitle = () => {
    const [selectedTitle, setSelectedTitle] = useState("Business Analyst");
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

    const { loading: loadingTitles, error: errorTitles, data: titleData } = GetDistinctTitles();
    const distinctTitles = titleData?.getDistinctTitles || [];

    const { loading: loadingSkills, error: errorSkills, data: skillData } = FetchSkillsByTitles({ title: selectedTitle });

    if (loadingTitles || loadingSkills) return <Loading />;
    if (errorTitles) return <p>Error: {errorTitles.message}</p>;
    if (errorSkills) return <p>Error: {errorSkills.message}</p>;

    console.log("DISTINCT TITLES:", titleData);
    console.log("SKILL BY TITLE Query:", skillData);

    const dataSkillsByTitle = skillData?.getSkillsByTitle || [];

    const skillList = [];
    const skillCount = [];
    const skillPercent = [];

    dataSkillsByTitle.forEach((data) => {
            skillList.push(data.skill);
            skillCount.push(data.countBytitle);
            skillPercent.push(data.percByTitle.toFixed(2));
        }
    );

    const chartData = {
        labels: skillList,
        datasets: [
            {
                label: "Top 10 Demand Skills",
                data: skillPercent,
                backgroundColor: [
                    'rgba(168, 160, 145, 1)',
                    'rgba(166, 153, 140, 1)',
                    'rgba(161, 144, 133, 1)',
                    'rgba(157, 136, 126, 1)',
                    'rgba(152, 128, 119, 1)',
                    'rgba(148, 121, 114, 1)',
                    'rgba(143, 114, 108, 1)',
                    'rgba(139, 107, 103, 1)',
                    'rgba(134, 100, 98, 1)',
                    'rgba(130, 93, 93, 1)'
                ],
                borderColor: [
                    'rgb(255,242,216)',
                    'rgba(248, 225, 207, 1)',
                    'rgba(249, 217, 201, 1)',
                    'rgba(250, 208, 194, 1)',
                    'rgba(251, 200, 188, 1)',
                    'rgba(251, 191, 182, 1)',
                    'rgba(252, 183, 176, 1)',
                    'rgba(253, 174, 169, 1)',
                    'rgba(254, 166, 163, 1)',
                    'rgba(255, 157, 157, 1)',
                ],
                borderWidth: 1,
            },
        ],
    };

    const handleTitleChange = (e) => {
        // console.log("CHOSE TITLE: ", e);
        setSelectedTitle(e.target.value);
    };

    const hideSidebarBreakpoint = 1200;

    return (
        <StyledDivContainer>
            {errorTitles && <h3 style={{ color: 'red' }}>{errorTitles}</h3>}
            {<MiniHeader pageTitle={"Skills By Title"}/>}
            <StyledDivContainer style={{
                padding: windowWidth <= hideSidebarBreakpoint ? "0 2rem" : "0 2rem 0 6rem",
            }} >
                <StyledDDList>
                    <StyledDDSelect
                            id="defData"
                            onChange={(e) => {handleTitleChange(e)}}
                            value={selectedTitle}
                    >
                        {distinctTitles.map((title) => (
                            <StyledDDOption key={title} value={title}>
                                {title}
                            </StyledDDOption>
                        ))}
                    </StyledDDSelect>
                </StyledDDList>
                <StyledChartContainer>
                    {<BarChart chartData={chartData} title={"Top 10 Demanded Skills"} />}
                </StyledChartContainer>
            </StyledDivContainer>
        </StyledDivContainer>
    );
}