import React from 'react';
import CommentListCard from "./index";

const CommentCardList = (data) => {
    const commentList = data.data;
    console.log(commentList);
    return (
        <>
            {commentList.map(i => (
                <CommentListCard key = {commentList.i} dataset ={i} />
            ))}
        </>
    );
};

export default CommentCardList;