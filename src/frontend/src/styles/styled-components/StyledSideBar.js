import styled from "styled-components";
import {Link} from "react-router-dom";

export const StyledSideBar = styled.div ({
    borderRight: "0.2rem solid rgb(218, 209, 190)",
    margin: "1rem 0",
    borderRadius: "0.3rem",
    width: "fit-content",
    maxWidth: "14vw",
});

export const StyledMenuList = styled.div ({
    position: "relative",
    // borderRight: "0.2rem solid rgb(166 211 211)",
    padding: "1rem 0.5rem",
    borderRadius: "0.2rem",
    // backgroundColor: "#172828",
});

export const StyledMenuItem = styled(Link) ({
});

export const StyledMenuButton = styled.button ({
    width: "11vw",
    height: "10vh",
    padding: "0.5rem",
    border: "0.2rem solid rgb(218 209 190)",
    borderRadius: "1rem",
    margin: "0.5rem 0 0.5rem 0.5rem",
    backgroundColor: "transparent",
    fontSize: "1rem",
    color: "#E3E3E3",
    '&:hover' : {
        borderRadius: "0.6rem",
        cursor: "pointer",
        color: "black",
    },
});

export const StyledSubMenuList = styled.div ({
    position: "relative",
    borderRadius: "0.2rem",
    textAlign: "end",
    padding: "0 0.3rem",
    borderRight: "solid rgb(218 209 190)",
    height: "fit-content",
});

export const StyledSubMenuItem = styled(Link) ({
});

export const StyledSubMenuButton = styled.button ({
    width: "8vw",
    height: "6vh",
    padding: "0.2rem",
    border: "0.1rem solid rgb(218 209 190)",
    borderRadius: "0.6rem",
    margin: "0.1rem 0 0.5rem 0.5rem",
    backgroundColor: "transparent",
    fontSize: "0.9rem",
    color: "#E3E3E3",
    '&:hover' : {
        borderRadius: "0.9rem",
        cursor: "pointer",
        color: "black",
    },
});