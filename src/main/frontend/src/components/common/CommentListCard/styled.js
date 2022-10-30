import styled from 'styled-components';

export const CommentWrapper = styled.div`
    background : #fffdf0;
    width : 70vw;
    min-height :4rem;
    height : 100%;
    margin-top : 1rem;
    margin-left : 2rem;
    border-radius : 1rem;
      align-items : center;
        position : relative;
        cursor : pointer;


`;
export const CommentText = styled.p`
  font-family: lee, sans-serif;
  font-size : 1.6rem;
  color : black;
  margin-left : 1.7rem;
  cursor : pointer;
  margin-top : 0.5rem;
      display: inline-block;
  width : 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  font-weight: 600;
  white-space: nowrap;
 
  

`;
export const TextWrapper = styled.div`
    display : flex;
    flex-direction : column;
            position : absolute;
  top : 1rem;
  left : 10rem;
`;
export const CommentVideoImg = styled.img`
width : 10rem;
height : 7rem;
border-radius : 0.5rem;
  margin-left : 1rem;
  display : inline-block; 
margin-top : 0.2rem;
`;
export const CommentTextExplain = styled.p`
    font-size : 1rem;
    color : black;
  margin-left : 1.5rem;

`;

export const CommentVideoTitle = styled.p`
    color : black;
    margin-left : 1.8rem;
    font-size : 1.1rem;
    margin-top : 0.5rem;
      text-overflow: ellipsis;
  white-space: nowrap;
    overflow: hidden;
  width : 26vw;
      display: inline-block;




`;
