import {StyledMiniHeader} from "../styles/styled-components/StyledMiniHeader";

export const MiniHeader = (props) => {
    return (
        <StyledMiniHeader>
            <p>{props.pageTitle}</p>
        </StyledMiniHeader>
    )
}