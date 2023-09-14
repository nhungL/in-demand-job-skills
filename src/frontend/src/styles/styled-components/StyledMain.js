import styled from "styled-components";

export const StyledMainContainer = styled.div ({})
export const StyledHeader = styled.div ({
    backgroundColor: "rgb(218 209 190)",
    height: "6vh",
    display: "flex",
    flexDirection: "column",
    justifyContent: "flex-end",
    fontSize: `calc(3px + 2vmin)`,
})
export const StyledFooter = styled.div ({
    backgroundColor: "transparent",
    height: "10vh",
    width: "-webkit-fill-available",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center",
    color: "white",
    position: "absolute",
    bottom: "2rem",
})

export const StyledBodyContainer = styled.div ({
    height: "94vh",
    width: "-webkit-fill-available",
    display: "grid",
    gridTemplateColumns: "1fr 6fr",
    padding: "0 5vh 0 3vh",
    backgroundColor: "#05130f",
    color: "#E3E3E3",

    '@media (max-width: 1200px)': {
        display: "flex",
        flexDirection: "column",
        justifyContent: "flex-start",
        padding: "2vh 5vh"
    },
});

export const  StyledDivContainer = styled.div ({
});

export const StyledContent = styled.div ({
    padding: "1rem 0",
    position: "relative",
});
