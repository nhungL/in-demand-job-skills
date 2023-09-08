import {useState} from "react";
import {FetchAllTitles, FetchTitle} from "../../graphql/queries";
import {BarChart} from "../../styles/chart/BarChart";
import {StyledDDList, StyledDDOption, StyledDDSelect} from "../../styles/styled-components/StyledDropDown";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {MiniHeader} from "../../components/MiniHeader";

export const SkillsByTitle = () => {
    const [selectedTitle, setSelectedTitle] = useState("Business Analyst");

    const titleList = FetchAllTitles();
    const filteredData = FetchTitle(selectedTitle);

    const errors = titleList.error || filteredData.error;
    const loading = titleList.loading || filteredData.loading;

    if (loading) {
        return <p>loading...</p>;
    }

    console.log("TITLE LIST: ", titleList);

    console.log("----QUERIES: ",filteredData);

    const skillList = [];
    const skillPercent = [];
    const filteredByTitle = filteredData.data?.allByTitle;
    const top10Skills = filteredByTitle[0].topSkills.slice(0, 10);
    top10Skills.map(s => {
        skillList.push(s.skill);
        skillPercent.push(s.percent.toFixed(2));
    });

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
        console.log("CHOSE TITLE: ", e);
        setSelectedTitle(e.target.value);
    };

    return (
        <StyledDivContainer>
            {errors && <h3 style={{ color: 'red' }}>{errors}</h3>}
            {<MiniHeader pageTitle={"Skills By Title"}/>}
            <StyledDivContainer style={{padding: "0 2rem 0 6rem", }}>
                <StyledDDList>
                    <StyledDDSelect
                            id="defData"
                            onChange={(e) => {handleTitleChange(e)}}
                            value={selectedTitle}
                    >
                        {titleList.data?.allByTitle.map((data) => (
                            <StyledDDOption key={data.title} value={data.title}>
                                {data.title}
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