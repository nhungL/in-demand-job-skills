import React, { useState } from 'react';
import styled from 'styled-components';
import {Link} from "react-router-dom";
import {StyledSubMenuButton} from "../styles/styled-components/StyledMenu";

const NavBar = styled.nav({
    padding: "0 1rem",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
});

const Menu = styled.ul({
    listStyle: "none",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    gap: "2rem",
});

const BurgerItem = styled.li({
    cursor: "pointer",
    transition: "all 0.3s ease-in-out",

    '&:hover' : {
        transform: "scale(1.2)",
    }
});

export const BurgerLink = styled(Link) ({
    textDecoration: "none",
    color: "black",
    textTransform: "uppercase",
    fontSize: "1.1rem",
    fontWeight: 500,
});

export const PrintButton = styled.span ({
    backgroundImage: `url("icons8-print-96.png")`,
    paddingRight: "1rem",

    width: "1.5rem",
    height: "1.5rem",
    cursor: "pointer",
    webkitTransitionProperty: "all",
    webkitTransitionDuration: "0.3s",
    webkitTransitionTimingFunction: "ease",
    '&:hover': {
        transform: "scale(1.2)",
    },
    backgroundSize: "contain",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
});

export const StyledIcon = styled.img ({
    width: "1rem",
    height: "1rem",
    paddingRight: "0.35rem",
});

export const BurgerNavbar = () => {
    const [open, setOpen] = useState(false);

    const toggleMenu = () => {
        setOpen(!open);
    };

    return (
        <NavBar>
            <Menu>
                <BurgerItem>
                    <BurgerLink to='/'>
                        <StyledIcon src="/icons8-home-96.png" alt="Home" />
                        Home
                    </BurgerLink>
                </BurgerItem>
                <BurgerItem>
                    <BurgerLink to='/settings'>
                        <StyledIcon src="/icons8-setting-96.png" alt="Settings" />
                        Settings
                    </BurgerLink>
                </BurgerItem>
            </Menu>
            <PrintButton onClick={() => window.print()}></PrintButton>
        </NavBar>
    );
}
