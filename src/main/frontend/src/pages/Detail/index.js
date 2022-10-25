import React, {useState, useEffect} from 'react';
import {
    VideoTitle,
    VideoCreator,
    TitleWrapper,
    VideoSub,
    VideoWrapper,
    CommentContainer,
    CommentInputWrapper,
    CommentTitle,
    CommentInput,
    CommentBtn,
    SubWrapper,
    VideoSubTitle,
    HeartBtn,
    IconWrapper,
    CommentSection,
    FilledHeartBtn, VideoViewer,
    LayoutContainer
} from './styled';
import CommentList from "../../components/Comment/CommentList";
import { useRecoilValue, useSetRecoilState } from 'recoil';
import {commentListState} from "../../recoil/comment";
import axios from "axios";
import {useLocation, useParams} from "react-router-dom";
import {LoginState, UserEmailState} from "../../States/LoginStates";
// import {response} from "express";
let id = 0;

const getId = () => id++;
const Detail = () => {

    const video_id = useParams().video_id;
    const [isLiked, setIsLiked] = useState(false);
    const [isAdded, setIsAdded] = useState(false);

    const [text, setText] = useState("");
    const setComments = useSetRecoilState(commentListState);
    const comments = useRecoilValue(commentListState);
    const isLoggedIn = useRecoilValue(LoginState);
    const userEmail = useRecoilValue(UserEmailState);

    const [comment, setComment] = useState({});

    const getCommentList = () => {

        // axios.post("http://localhost:8080/getcomment/" + video_id, {})
        //     .then((res) => {
        //         const { data } = res;
        //         setComments(data);
        //         console.log(data);
        //     })
        //     .catch((e) => {
        //         console.error(e);
        //     });

        console.log("댓글 돌아감");
        fetch("http://localhost:8080/getcomment/" + video_id, {
            method: "post"
        })
            .then(response => response.json())
        //     .then((res) => {
        //     console.log("넘어옴");
        //     data2 = res;
        //     setComments(data);
        //     setTimeout(() => console.log(res), 2000);
        //     console.log(data);
        //     console.log(res.json());
        // })
            .then(res => {
                console.log(res);
                //const data = {...res};
                // console.log(data.map(asdf => {asdf.title = ))
                // res.map(idx, user, com =>{
                //     "idx" : idx,
                //         "user" : user,
                //         "com" : com,
                // });
                setComments(res);
                console.log(comments);
            });

    };
    setTimeout(() => console.log(comments), 2000);

    // 댓글달기
    const addComment = () => {
        // const now_date = new Date().toISOString().slice(0, 19).replace('T', ' ');

        const dataset = {
            comment : text,
            video_id : video_id,
            email : userEmail,
        };
        fetch("http://localhost:8080/addcomment", {
            method : "post",
            headers : {
                "content-type": "application/json",
            },
            body : JSON.stringify(dataset),
        })

            .then((res) => {
                console.log(res);
                // return window.location.reload();
            })
            .catch((e) => {
                console.error(e);
            });
    };

    useEffect(()=>{
        getCommentList();
        setVideoURL(`https://www.youtube.com/embed/${params}`);

        // 페이지 정보 중복 확인

        axios.get(
            'http://localhost:8080/detail/'+ params + '/check',
        ).then(res => {
            if (res.data===false) {
                saveVideo();
            }
        });
    }, []);

    const addCommentHandler = e =>{
        e.preventDefault();

        if(text === '') {
            alert('내용을 입력해주세요');
        } else {
            setComments(comments => comments.concat({ id: getId(), text, clicked : false}));

            addComment();
            setText('');

        }
    };
    const isLogged = useRecoilValue(LoginState);

    let params = useParams().video_id;

    const location = useLocation().state;

    const handleChange = e => {
        setText(e.target.value);
    };


    const [videoURL, setVideoURL] = useState(`https://www.youtube.com/embed/`);

    useEffect(()=>{
    });



    // 페이지 정보 DB 저장
    const saveVideo = () => {
        const data = {
            video_id : video_id,
            url : videoURL,
            title : location.data.title,
            description : location.data.description,
            creator : location.data.channelTitle,
            thumbnail : location.data.thumbnails.medium["url"],
        };
        axios.post(
            'http://localhost:8080/detail/'+ params + '/save',
            data
        );
    };

    // 좋아요 했나 안했나
    const [heart, setHeart] = useState([]);
    useEffect(() => {
        async function fetchData() {
            const data = {
                email: userEmail,
                heart : 0
            };
            await axios.post(
                'http://localhost:8080/detail/'+ params + '/liked',
                data
            ).then(res => {
                setHeart(res.data);
            });
        }
        fetchData();
    }, []);

    // 하트 클릭
    const getHeart = (data) => {
        const url = 'http://localhost:8080/detail/'+ params;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        }) .then(() => {
            return window.location.reload();
        });
    };

    return (
        <LayoutContainer>
            <TitleWrapper>
                <VideoTitle>{location.data.title}</VideoTitle>
                <VideoCreator>{location.data.channelTitle}</VideoCreator>
            </TitleWrapper>
            {isLoggedIn ? (
                <IconWrapper>
                    {heart.heart ?
                        <FilledHeartBtn onClick={()=> {  // 좋아요 -> 안좋아요
                            setIsLiked(!isLiked);
                            const data = {
                                email: userEmail,
                                heart : 0
                            };
                            getHeart(data);}
                        }/> :

                        <HeartBtn onClick={() => {  // 안좋아요 -> 좋아요
                            setIsLiked(!isLiked);
                            const data = {
                                email: userEmail,
                                heart : 1
                            };
                            getHeart(data);}
                        } />
                    }
                </IconWrapper>
            ) : null }
            <VideoWrapper>
                <VideoViewer id="ytplayer" type="text/html" width="85%" height="630" src={videoURL}
                    frameBorder="0" allowFullScreen/>
            </VideoWrapper>
            <SubWrapper>
                <VideoSubTitle>영상 부가 설명</VideoSubTitle>
                <VideoSub>{location.data.description}</VideoSub>
            </SubWrapper>
            <hr/>

            {isLogged ? (
                <CommentInputWrapper>
                    <form onSubmit={addCommentHandler}>
                        <CommentInput type={"text"} value={text} placeholder={"댓글 달기..."} onChange={handleChange}/>
                        <CommentBtn onClick={addCommentHandler}>SEND</CommentBtn>
                    </form>
                </CommentInputWrapper>
            ) : null}
            <CommentContainer>
                <CommentTitle>의견 나눔장</CommentTitle>

                <CommentSection>
                    <CommentList video_id = {video_id}/>
                </CommentSection>

            </CommentContainer>

        </LayoutContainer>
    );
};

export default Detail;
