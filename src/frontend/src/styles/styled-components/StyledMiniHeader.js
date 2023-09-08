import styled from "styled-components";

export const StyledMiniHeader = styled.div ({
    backgroundColor: "rgb(164 153 131)",
    backgroundImage: "linear-gradient(180deg, rgb(255 255 255),transparent)",
    color: "black",
    borderRadius: "1rem",
    padding: "0.5rem",
    marginBottom: "2rem",
    p: {
        fontWeight: "bold",
        margin: 0,
        textTransform: "uppercase",
        // textShadow: "-1px 0 rgb(36, 96, 96), " +
        //     "0 1px rgb(36, 96, 96), " +
        //     "1px 0 rgb(36, 96, 96), " +
        //     "0 -1px rgb(36, 96, 96)",
    }
})