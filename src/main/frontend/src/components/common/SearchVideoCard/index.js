import React, { useEffect, useState } from 'react';
import { PromotionChannel, PromotionTitle, PromotionWrapper, StyledLink, VideoThumbnail, VideoWrapper } from '../PostVideoCard/styled';
import Loading from '../../../Utils/Spinner';
import {useRecoilState, useRecoilValue} from 'recoil';
import {VideoState} from '../../../States/VideoStates';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const SearchVideoCard = ({data, color}) => {

    const [isLoading, setIsLoading] = useState(true);
    let [videoList, setVideoList] = useState([]);
    const [showVideo, setShowVideo] = useState([]);

    console.log(data);

    useEffect(()=>{
        setVideoList(data);
        console.log(data);
        // console.log("videoList : " + videoList);
    },[]);


    useEffect(() => {
        videoList = [...data];

    },[]);


    useEffect(()=>{
        setIsLoading(false);
    }, [showVideo]);

    return (
        <>
            {isLoading ? <Loading /> : null}
            {data &&
                data.map((show) => {
                    return (
                        <PromotionWrapper key={show.video_idx}>
                            <StyledLink to={`/detail/${show.videoId}`}  state={{ data: show }}>
                                <VideoWrapper>

                                    <VideoThumbnail className={show.id} src={show.thumbnails} alt=""/>
                                    <PromotionTitle color={color}>{show.title}</PromotionTitle>
                                    <PromotionChannel>{show.channelTitle}</PromotionChannel>

                                </VideoWrapper>
                            </StyledLink>
                        </PromotionWrapper>

                    );
                })}


        </>
    );
};

export default SearchVideoCard;