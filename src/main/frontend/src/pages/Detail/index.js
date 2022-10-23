import React, {useState, useEffect} from 'react';
import {
    VideoTitle,
    VideoCreator,
    TitleWrapper,
    VideoSub,
    VideoWrapper,
    VideoSection,
    CommentContainer,
    CommentInputWrapper,
    CommentTitle,
    CommentInput,
    CommentBtn,
    SubWrapper,
    VideoSubTitle,
    HeartBtn,
    IconWrapper,
    PageBtn,
    CommentSection,
    FilledHeartBtn, VideoViewer
} from './styled';
import CommentList from "../../components/Comment/CommentList";
import {useSetRecoilState,useRecoilValue} from "recoil";
import {commentListState} from "../../recoil/comment";
import axios from "axios";
import {useLocation, useParams} from "react-router-dom";

import {LoginState, UserEmailState} from "../../States/LoginStates";
let id = 0;

const getId = () => id++;
const Detail = () => {

    const [isLiked, setIsLiked] = useState(false);
    const [isAdded, setIsAdded] = useState(false);

    const [text, setText] = useState("");
    const setComments = useSetRecoilState(commentListState);

    const userEmail = useRecoilValue(UserEmailState);

    const addCommentHandler = e =>{
        e.preventDefault();

        if(text === '') {
            alert('내용을 입력해주세요');
        } else {
            setComments(comments => comments.concat({ id: getId(), text, clicked : false}));

            setText('');

        }
    };

    let params = useParams().video_id;
    console.log(params);

    const location = useLocation().state; // 추가된 부분

    const handleChange = e => {
        setText(e.target.value);
    };


    const [videoURL, setVideoURL] = useState(`https://www.youtube.com/embed/`);

    useEffect(()=>{
        setVideoURL(`https://www.youtube.com/embed/${params}`);
    });

    // 페이지 정보 중복 확인
    useEffect(() => {
        axios.get(
            'http://localhost:8080/detail/'+ params + '/check',
        ).then(res => {
            console.log("중복 확인중");
            console.log(res);
            if (res.data===true) {console.log("있다");}
            else {
                saveVideo();
                console.log("업다");
            }
        });

        console.log("디테일 정보 저장");
    },[] );


    // 페이지 정보 DB 저장
    const saveVideo = () => {
        const data = {
            video_id : params,
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
        console.log("디테일 정보 저장");
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

    // 하트 채우고 빼기
    const getHeart = (data) => {
        console.log("하트 눌럿서");
        const url = 'http://localhost:8080/detail/'+ params;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        }) .then(responseData => {
            console.log(responseData);
            return window.location.reload();
        });
    };

    return (
        <div>
            <TitleWrapper>
                <VideoTitle>{location.data.title}</VideoTitle>
                <VideoCreator>{location.data.channelTitle}</VideoCreator>
            </TitleWrapper>
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
                {/*<HeartBtn onClick={()=>setIsLiked(!isLiked)}/>*/}
                <PageBtn isAdded={isAdded} onClick={()=>setIsAdded(!isAdded)}/>
            </IconWrapper>
            <VideoWrapper>
                <VideoViewer
                    id="ytplayer" type="text/html" width="85%" height="630"
                    src={videoURL}//https://www.youtube.com/embed/m0x6tUkaI3c
                    frameBorder="0" allowFullScreen/>
                {/*<VideoSection/>*/}
            </VideoWrapper>
            <SubWrapper>
                <VideoSubTitle>영상 부가 설명</VideoSubTitle>
                <VideoSub>{location.data.description}</VideoSub>

            </SubWrapper>
            <hr/>
            <CommentContainer>
                <CommentTitle>의견 나눔장</CommentTitle>
                <CommentInputWrapper>
                    <form onSubmit={addCommentHandler}>
                        <CommentInput type={"text"} value={text} placeholder={"댓글 달기..."} onChange={handleChange}/>
                        <CommentBtn onClick={addCommentHandler}>SEND</CommentBtn>
                    </form>
                </CommentInputWrapper>
                <CommentSection>
                    <CommentList/>

                    {/*{comments.map((comment) => (*/}
                    {/*    <CommentWrapper key={comment.id}>*/}
                    {/*        <CommentCard {...comment} />*/}
                    {/*    </CommentWrapper>*/}
                    {/*))}*/}

                    {/*<CommentListStats />  // 상태값을 보여줄 컴포넌트*/}
                    {/*<CommentListFiters />  // 필터할 컴포넌트*/}
                    {/*<CommentItemCreator />*/}

                    {/*{commentList.map((commentItem) => (*/}
                    {/*    <CommentItem key={commentItem.id} item={commentItem} />*/}
                    {/*))}*/}
                </CommentSection>

            </CommentContainer>


        </div>
    );
};

export default Detail;
