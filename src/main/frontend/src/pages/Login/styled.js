import styled from 'styled-components';
import {Link} from 'react-router-dom';

export const Button = styled.button`
    color : ${props => props.color || "white"};
    background : ${props => props.background || "#5d7c9c"} ;
    display : flex;
    font-size: 1rem;
    margin :1.5rem 0 1rem 0;
    width :100%;
    height :5.5vh;
    border-radius: 5px;
    outline : 0;
    border : 0;
    text-align: center;
    justify-content: center;
    align-items: center;
  font-family: geonggimedium, sans-serif;
    &:hover{
        background: skyblue;
        cursor: pointer;
      transition : all ease 0.2s 0s;

    }

`;

export const GoogleBtn = styled.img`
    margin-top : 0.2rem;
  margin-left : -0.2rem;
  width : 12rem;
  cursor : pointer;
`;
export const LoginDiv = styled.div`
    background: #e3e3e3;
    justify-content: center;
    align-items: center;
    display : flex;
    height: 100vh;

`;
export const LoginContainer = styled.div`
    background : #f0f0f0;
    padding : 0;
    flex-direction: column;
    width : 22rem;
    height : 27rem;
  margin-bottom : 3rem;
    border: none;
    border-radius: 1rem;
    justify-content: center;
    align-items: center;
    display: flex;
    @media (min-width : 1400px) {
        width : 25vw;
        height : 30vw;
    }
  //@media (max-width: 991px) {
  //  height :rem;
  //}
`;
export const LoginTitle = styled.p`
    color : #00415d;
    text-align : center;
    display : flex;
    justify-content: center;
    margin : 2rem 0 1rem 0;
  font-size : 2rem;
  font-weight: 600;
  font-family: cookieregular, sans-serif;
`;
export const FaceBookBtn = styled.img`
  width : 12.5rem;
  margin-top : 0.5rem;
  cursor : pointer;

`;
export const LoginInput = styled.input`
    width : 15rem;
    font-size : 1.1rem;
    margin-top : 2rem;
    display :flex;
    justify-content: center;
    align-items: center;
    border : none;
    border-bottom : 3px solid #575757;
    background : #f0f0f0;
  font-family : lee, sans-serif;
    ::placeholder,
     ::-webkit-input-placeholder {
    color: #8f8f8f;
    }
    :-ms-input-placeholder {
         color: #8f8f8f;
    }
    :focus{
        outline : none;
        transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    }

`;

export const LoginForm = styled.form`
`;

export const StyledLink = styled(Link)`
    text-decoration : none;
`;

export const LoginWrapper = styled.div`

`;
export const ImgBtnContainer = styled.div`
    display : flex;
    align-items: center;
    margin : 1rem 0 0 0;
  flex-direction: column;
`;

export const ImgBtn = styled.button`
    border : none;
    border-radius : 5px;
    width : 2.2rem;
    height : 2.2rem;
    cursor : pointer;
    background : ${props => props.color || 'none'};
  &:active {
    border : none;
  }
`;
export const LoginCheckTitle = styled.div`
    display : inline;
    color : #454545;
    font-size : 0.7rem;
    margin-left : 0.4rem;
  font-family: geonggilight, sans-serif;
`;

export const LoginCheck = styled.input`
    margin : 0.6rem 0 0 0;
    cursor : pointer;

`;

export const LoginSocialTitle = styled.p`
    color : #454545;
  font-family: cookieregular, sans-serif;
    text-align: center;
  font-size : 1rem;
    margin : 1.8rem 0 0 0;
`;
export const LoginSubWrapper = styled.div`
    display : inline;
`;

export const InfoFindLink = styled(Link)`
    display : flex;
  font-family: geonggilight, sans-serif;

  font-size : 0.7rem;
    cursor : pointer;
    justify-content: flex-end;
    margin-top : -0.8rem;
    text-decoration: none;
    color : #454545;
    :hover{
        color : #616161;
        font-weight : 600;
    }
`;

export const LoginWarnSpan = styled.span`
    color : red;
    font-size : 0.8rem;
    display : ${props => props.visiblity ? "#000000":"none"};
`;