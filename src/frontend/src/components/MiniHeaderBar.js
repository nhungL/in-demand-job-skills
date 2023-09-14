import {menuItemsList} from "../App";
import {Link, useLocation} from "react-router-dom";
import {StyledMiniHeader} from "../styles/styled-components/StyledMiniHeader";

export const MiniHeaderBar = () => {
    const location = useLocation();
    const isPathActive = (path) => location.pathname === path;
    return (
        <StyledMiniHeader>
            <nav style={{
                padding: "0 1rem",
                overflow: "auto",
            }}>
                <ul style={{
                    listStyle: "none",
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "flex-start",
                    gap: "2rem",
                    padding: "0 1rem",
                }}>
                    {menuItemsList.map((item) => (
                        <li key={item.id}
                            value={item.name}
                            style={{
                                cursor: "pointer",
                                transition: "all 0.3s ease-in-out",
                            }}
                        >
                            <Link
                                to={item.path}
                                style={{
                                    textDecoration: "none",
                                    color: "black",
                                    textTransform: isPathActive(item.path) ? "uppercase" : "none",
                                    fontWeight: isPathActive(item.path) ? "bold" : "unset",
                                    fontSize: "1rem",
                                }}
                            >
                                {item.name}
                            </Link>
                        </li>
                    ))}
                </ul>
            </nav>
        </StyledMiniHeader>
    );
}