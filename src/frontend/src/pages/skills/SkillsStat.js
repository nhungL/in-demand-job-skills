import {useQuery} from "@apollo/client";
import {SKILL_QUERIES} from "../../graphql/queries";
import {BarChart} from "../../styles/chart/BarChart";
import {useState} from "react";
import {StyledDivContainer} from "../../styles/styled-components/StyledMain";
import {StyledChartContainer} from "../../styles/styled-components/StyledCharts";

export const SkillsStat = () => {
    const [numDataPoints, setNumDataPoints] = useState(10);

    const { loading, error, data } = useQuery(SKILL_QUERIES,
                                                            {variables:  {order: 'desc'}});

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error : {error.message}</p>;
    console.log("SKILL Query - ALL:", data);

    const dataSkills = data.getAllSkills;

    const skillList = [];
    const skillPercent = [];

    dataSkills.map((data) => {
            skillList.push(data.skill);
            skillPercent.push(data.percent);
        }
    );

    const slicedSkillList = skillList.slice(0, numDataPoints);
    const slicedSkillPercent = skillPercent.slice(0, numDataPoints);

    const chartData = {
        labels: slicedSkillList,
        datasets: [
            {
                label: "Skill Ranking",
                data: slicedSkillPercent,
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
    }

    // const seeMore = () => {
    //     setNumDataPoints(numDataPoints + 10);
    // };

    return(
        <StyledDivContainer>
            <StyledChartContainer>
                <BarChart chartData={chartData} title={"All Skills Ranking"}/>
            </StyledChartContainer>
        </StyledDivContainer>
    );
}