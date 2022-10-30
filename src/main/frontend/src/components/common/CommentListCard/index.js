import React from 'react';
import {CommentWrapper,CommentText,CommentVideoTitle,CommentVideoImg,TextWrapper,CommentTextExplain} from "./styled";
import {StyledLink} from "../PostVideoCard/styled";

const CommentListCard = (dataset) => {
    console.log(dataset.dataset);
    console.log(dataset.data);
    return (
        <CommentWrapper>
            <StyledLink to={`/detail/${dataset.dataset.comment_video.videoId}`} state={{ data: dataset.dataset.comment_video}} >
                <CommentVideoImg alt="thumbnail" src={dataset.dataset.comment_video.thumbnails}/>
                <TextWrapper>

                    <CommentTextExplain>작성한 댓글: </CommentTextExplain>
                    <CommentText>{dataset.dataset.comment_text}</CommentText>
                    <CommentVideoTitle>{dataset.dataset.comment_video.title}</CommentVideoTitle>

                </TextWrapper>
            </StyledLink>
        </CommentWrapper>
    );
};

export default CommentListCard;