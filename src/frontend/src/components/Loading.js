import styled, {keyframes} from "styled-components";

const StyledLoadingContainer = styled.div ({
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
});

export const BounceAnimation = keyframes`
  0% { 
    margin-bottom: 0; 
  }

  50% { 
    margin-bottom: 1rem;
  }

  100% { 
    margin-bottom: 0;
  }
`;
export const Dot = styled.div`
  background-color: rgb(218, 209, 190);
  border-radius: 50%;
  width: 0.75rem;
  height: 0.75rem;
  margin: 0 0.25rem;
  /*Animation*/
  animation: ${BounceAnimation} 0.5s linear infinite;
  animation-delay: ${(props) => props.delay};
`;

export const Loading = () => {
    return (
        <StyledLoadingContainer>
            <h3 style={{
                color: "rgb(218, 209, 190)",
            }}>Loading</h3>

            <Dot delay="0s" />
            <Dot delay="0.1s" />
            <Dot delay="0.2s" />
        </StyledLoadingContainer>
    );
};