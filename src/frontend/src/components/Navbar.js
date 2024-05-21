import React, { useState } from 'react';
import styled from 'styled-components';
import {Link, useLocation} from "react-router-dom";
import {StyledSubMenuButton} from "../styles/styled-components/StyledSideBar";

const NavBar = styled.nav({
    padding: "0 1rem",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
});

const Menu = styled.div({
    listStyle: "none",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    gap: "2rem",
});

const BurgerItem = styled.div({
    listStyle: "none",
    padding: "0.5rem 0.7rem 1rem 0.5rem",
    cursor: "pointer",
    transition: "all 0.3s ease-in-out",

    '&:hover' : {
        transform: "scale(1.2)",
    }
});

export const BurgerLink = styled(Link) ({
    textDecoration: "none",
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
    const handlePrint = () => {
        // Set landscape orientation for printing
        var css = '@page { size: landscape;}' +
                '@media print { ' +
                '   body {' +
                '       -webkit-print-color-adjust: exact;' +
                '   }' +
                '.print-area {' +
                '    transform: scale(0.6); ' +
                '    transform-origin: top left; ' +
                '  }' +
                '}' +
                '',
            head = document.head || document.getElementsByTagName('head')[0],
            style = document.createElement('style');

        style.type = 'text/css';
        style.media = 'print';

        if (style.styleSheet){
            style.styleSheet.cssText = css;
        } else {
            style.appendChild(document.createTextNode(css));
        }

        head.appendChild(style);

        window.print();
    };

    const navItems = [
        {id: 1, name: "HOME", path: "/", iconSrc:"/icons8-home-96.png", iconSrcActive:"icons8-home-96-white.png"},
        {id: 2, name: "SETTINGS", path: "/settings", iconSrc: "/icons8-setting-96.png", iconSrcActive:"icons8-setting-96-white.png"}
    ]

    const location = useLocation();
    const isPathActive = (path) => location.pathname === path;

    return (
        <NavBar>
            <Menu>
                {navItems.map((item) => {
                    return (
                        <BurgerItem key={item.id} style={{
                            backgroundColor: isPathActive(item.path) ? "#05130f" : "transparent",
                            borderTopLeftRadius: isPathActive(item.path) ? "0.5rem" : "0",
                            borderTopRightRadius: isPathActive(item.path) ? "0.5rem" : "0",
                            borderTop: isPathActive(item.path) ? "4px solid rgb(187 38 1)" : "4px solid transparent",
                        }}>
                            <BurgerLink to={item.path} style={{
                                color: isPathActive(item.path) ? "#dad1bf" : "black",
                            }}>
                                <StyledIcon
                                    src={ isPathActive(item.path) ? item.iconSrcActive : item.iconSrc}
                                    alt={item.name} />
                                {item.name}
                            </BurgerLink>
                        </BurgerItem>
                    )
                })}
            </Menu>

            {/* <PrintButton onClick={handlePrint}></PrintButton> */}
        </NavBar>
    );
}
