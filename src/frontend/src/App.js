import './App.css';
import {
    createBrowserRouter,
    createRoutesFromElements,
    Outlet,
    Route,
    RouterProvider,
    useLocation
} from 'react-router-dom';

import {BurgerNavbar} from "./components/Navbar";
import {HomePage} from "./pages/home/HomePage";
import {JobsPage} from "./pages/alljobs/JobsPage";
import {SkillsPage} from "./pages/skills/SkillsPage";
import {SkillsByTitle} from "./pages/skills/SkillsByTitle";
import {AboutPage} from "./pages/about/AboutPage";
import {SalaryPage} from "./pages/salary/SalaryPage";
import {SettingsPage} from "./pages/settings/SettingsPage";

import {
    StyledBodyContainer,
    StyledHeader,
    StyledContent,
    StyledMainContainer,
} from "./styles/styled-components/StyledMain";
import {
    StyledSideBar,
    StyledMenuButton, StyledMenuItem, StyledMenuList,
    StyledSubMenuButton, StyledSubMenuItem, StyledSubMenuList,
} from "./styles/styled-components/StyledSideBar";
import {useEffect, useState} from "react";
import {MiniHeaderBar} from "./components/MiniHeaderBar";

function App() {
    const router = createBrowserRouter(
        createRoutesFromElements(
            <Route path="/" element={<Root/>}>
                <Route index element={<HomePage/>} />
                <Route path="/jobs" element={<JobsPage/>}/>
                <Route path='/skills'>
                    <Route index element={<SkillsPage/>}/>
                    {/*<Route path='skills-by-title' element={<SkillsByTitle/>}/>*/}
                </Route>
                <Route path='/skills-by-title' element={<SkillsByTitle/>}/>
                <Route path='/salary' element={<SalaryPage/>}/>
                <Route path='/about' element={<AboutPage/>}/>
                <Route path='/settings' element={<SettingsPage/>}/>
            </Route>
        )
    );
    return (
        <div className="App">
            <RouterProvider router={router}/>
        </div>
    );
}

export const menuItemsList = [
    { id: 0, name: "Jobs Summary", path: "/jobs", subMenu: null, parentId: null },
    { id: 1, name: "Skills Ranking", path: "/skills", subMenu: null
        //     [
        //     {
        //         id: 2,
        //         name: "Skills by Title",
        //         path: "/skills/skills-by-title",
        //         subMenu: null,
        //         parentId: 1
        //     },
        // ]
    },
    { id: 2, name: "Skills by Title", path: "/skills-by-title", subMenu: null, parentId: 1},
    { id: 3, name: "Salary Ranking", path: "/salary", subMenu: null, parentId: null },
    { id: 4, name: "About", path: "/about", subMenu: null, parentId: null },
];

const Root = () => {
    const totalItemsCount = menuItemsList.length + menuItemsList.reduce((total, menuItem) => {
        return total + (menuItem.subMenu ? menuItem.subMenu.length : 0);
    }, 0)

    const [clickedItems, setClickedItems] = useState(
        Array(totalItemsCount).fill(false)
    );

    const location = useLocation();
    const isPathActive = (path) => location.pathname === path;

    useEffect(() => {
        // Update clickedItems based on the active route
        const updatedClickedItems = Array(totalItemsCount).fill(false);

        menuItemsList.forEach((menuItem) => {
            if (isPathActive(menuItem.path)) {
                updatedClickedItems[menuItem.id] = true;
            }

            if (menuItem.subMenu) {
                menuItem.subMenu.forEach((subItem) => {
                    if (isPathActive(subItem.path)) {
                        updatedClickedItems[subItem.id] = true;
                        updatedClickedItems[menuItem.id] = true;
                    }
                })
            }
        });

        setClickedItems(updatedClickedItems);
    }, [location.pathname]);

    console.log(clickedItems);

    function renderSubMenu(subMenu) {
        return (
            <StyledSubMenuList>
                {subMenu.map((subItem) => {
                    return (
                        <div style={{display: "inline-grid"}}>
                            <span
                                style={{
                                    backgroundImage: `url("icons8-up-left-hand-drawn-96.png")`,
                                    backgroundRepeat: "no-repeat",
                                    backgroundSize: "contain",
                                    height: "auto",
                                    width: "2rem",
                                }}
                            />
                            <StyledSubMenuItem key={subItem.id} to={subItem.path}>
                                <StyledSubMenuButton
                                    style={{
                                        backgroundColor: clickedItems[subItem.id] ? "rgb(218 209 190)" : "transparent",
                                        color: clickedItems[subItem.id] ? "black" : "white",
                                    }}
                                >
                                    {subItem.name}
                                </StyledSubMenuButton>
                            </StyledSubMenuItem>
                        </div>
                    );
                })}
            </StyledSubMenuList>
        )
    }

    function renderMenu () {
        return menuItemsList.map((menuItem) => {
            return (
                <StyledMenuItem key={menuItem.id} to={menuItem.path}>
                    <StyledMenuButton
                        style={{
                            backgroundColor: clickedItems[menuItem.id] ? "rgb(218 209 190)" : "transparent",
                            color: clickedItems[menuItem.id] ? "black" : "white",
                        }}
                    >
                        {menuItem.name}
                    </StyledMenuButton>
                    {clickedItems[menuItem.id] && menuItem.subMenu && (renderSubMenu(menuItem.subMenu))}
                </StyledMenuItem>
            );
        })
    }

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

    // Define a breakpoint for hiding the sidebar
    const hideSidebarBreakpoint = 1200;

  return (
      <StyledMainContainer>
          <StyledHeader>
              <BurgerNavbar/>
          </StyledHeader>

          {windowWidth <= hideSidebarBreakpoint ? (
              <StyledBodyContainer>
                  <MiniHeaderBar/>
                  <StyledContent>
                      <Outlet />
                  </StyledContent>
              </StyledBodyContainer>
          ) : (
              <StyledBodyContainer>
                  <StyledSideBar>
                      <StyledMenuList>{renderMenu()}</StyledMenuList>
                  </StyledSideBar>
                  <StyledContent>
                      <Outlet />
                  </StyledContent>
              </StyledBodyContainer>
          )}
      </StyledMainContainer>
  );
}

export default App;
