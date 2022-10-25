import React, {useEffect, useRef, useState} from 'react';
import { commentListState } from '../../recoil/comment';
import {useRecoilValue, useSetRecoilState} from "recoil";
import {
    CommentIconWrapper, CommentItemStyle,
    CommentText,
    CommentUser,
    CommentWrapper, EditBtn,
    GarbageBtn,
    EditInput,
    EditWrapper,
    EditConfirmBtn
} from "./styled";
import {FilledHeartBtn, HeartBtn} from '../../pages/Detail/styled';
import { UserNameState } from '../../States/LoginStates';
import axios from 'axios';

const CommentItem = ({ data, video_id }) => {
    const setComments = useSetRecoilState(commentListState);
    const comments = useRecoilValue(commentListState);
    const [newText, setNewTest] = useState(data.text);
    const [isEdit, setIsEdit] = useState(false);
    const [isDeleted, setIsDeleted] = useState(0);
    const displayName = useRecoilValue(UserNameState);
    let userName;
    // if(displayName === ''){
    //     userName = '누군가';
    // } else {
    //     userName = displayName.split('@')[0];
    //
    // }
    const editInputRef = useRef(null);

    const handleChangeEditInput = e => {
        setNewTest(e.target.value);
    };
    useEffect(()=> {
        //edit 모드일 경우 포커싱
        if(isEdit) {
            editInputRef.current.focus();
        }
    },[isEdit]);

    const onEdit = () => {
        setIsEdit(true);
    };

    const onRemove = () => {
        setIsDeleted(1);
        console.log(`this is ${data.comment_idx}`);
        setComments(comments => comments.filter(comment => comment.id !== data.id));
        deleteComment();
    };


    const update = () =>{
        const now_date = new Date().toISOString().slice(0, 19).replace('T', ' ');

        const dataset = {
            comment: newText,
            comment_idx : data.comment_idx,
        };
        fetch("http://localhost:8080/updatecomment", {
            method : "post",
            headers : {
                "content-type": "application/json",
            },
            body : JSON.stringify(dataset),
        })
            .then((res) => {
                console.log(res);
                return window.location.reload();

            })
            .catch((e) => {
                console.error(e);
            });
    };

    // 댓글 삭제
    const deleteComment = () =>{
        // const dataset = {
        //     comment_idx : data.comment_idx,
        // };
        // fetch("http://localhost:3002/commentdelete", {
        //     method : "post",
        //     headers : {
        //         "content-type": "application/json",
        //     },
        //     body : JSON.stringify(dataset),
        // })
        //     .then((res) => {
        //         console.log(res);
        //         return window.location.reload();
        //
        //     })
        //     .catch((e) => {
        //         console.error(e);
        //     });

        axios.post(
            'http://localhost:8080/dropcomment',
            {
                comment_idx : data.comment_idx
            }
        ).then(() => {
            window.location.reload();
        });
    };

    useEffect(()=>{
        console.log(data.comment_idx);
    }, []);

    const onUpdate = () => {
        if(newText === '') {
            alert('내용을 입력해주세요');
        } else {
            const nextTCommentList = comments.map((comment) => ({
                ...comment,
                text: comment.id === data.id ? newText : comment.text, // 새로운 아이템 내용을 넣어줌
            }));
            setComments(nextTCommentList); // 새로운 리스트를 넣어줌

            setIsEdit(false); // 수정모드를 다시 읽기모드로 변경
            update();
        }
    };
    const toggleHeart = () => {
        setComments(comments =>
            comments.map(comment =>
                comment.id === data.id ? { ...data, clicked: !data.clicked } : comment
            )
        );

    };

    console.log(data);
    return (
        <>
            {data.isdeleted ? null
                :
                (
                    <CommentItemStyle key = {data.comment_idx}>
                        {isEdit ? (
                            <EditWrapper>
                                <CommentUser>...</CommentUser>
                                <EditInput
                                    type="text"
                                    value={newText}
                                    ref={editInputRef}
                                    onChange={handleChangeEditInput}/>

                            </EditWrapper>
                        ):(
                            <CommentWrapper>
                                <CommentUser>{data.name}</CommentUser>
                                <CommentText>{data.comment_text}</CommentText>
                            </CommentWrapper>
                        )}
                        <CommentIconWrapper isEdit={isEdit}>
                            {/*{data.clicked ? <FilledHeartBtn onClick={toggleHeart} size={"1.4rem"}/> : <HeartBtn onClick={toggleHeart} size={"1.4rem"}/>}*/}
                            {isEdit ? (
                                <EditConfirmBtn onClick={onUpdate}/>
                            ):(
                                <EditBtn onClick={onEdit}/>)}
                            <GarbageBtn onClick={onRemove}/>
                        </CommentIconWrapper>

                    </CommentItemStyle>

                )}
        </>
    );
};

export default CommentItem;