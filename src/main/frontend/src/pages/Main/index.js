import React, {useEffect, useState} from 'react';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import sound from "../../assets/img/sound.jpg";
import oceanpan from "../../assets/img/oceanpan.jpg";
import dark from "../../assets/img/dark.jpg";
import tree from "../../assets/img/tree.jpg";
import headphone from "../../assets/img/headphone.jpg";
import ocean from "../../assets/img/ocean.jpg";
import atom from "../../assets/img/atom.jpg";
import {
    BannerImg,
    BannerText,
    BannerTitle,
    BannerWrapper,
    CategoryBtn,
    CategoryBtnContainer,
    CategoryContainer,
    CategoryList,
    CategoryText,
    CategoryTitle,
    CollectionImg,
    CollectionSection,
    CollectionText,
    CollectionTextWrapper,
    CollectionTitle,
    HomeWrapper,
    HotSection,
    MakerImg,
    MakerImgContainer,
    MakerSection,
    MakerSubImg,
    PromotionContainer,
    PromotionSection,
    PromotionTitle,
    SliderExplain,
    SliderSubtitle,
    SliderTitle,
    SliderViewMore,
    SliderWrapper,
    TextWrapper,
    VideoWrapper,
    ViewMoreBtn,
    ViewMoreBtnText,
} from './styled';
import {useRecoilState} from 'recoil';
import {VideoState} from "../../States/VideoStates";

import PostVideoCard from "../../components/common/PostVideoCard";

import SimpleSlider from "../../Utils/SimpleSlider";
import {useNavigate} from "react-router-dom";
import axios from 'axios';
import handleScroll from "../../Utils/ScrollTop/handleScroll";
import ScrollTop from "../../Utils/ScrollTop";
import PromotionCard from "../../components/common/PromotionCard";
import ApiVideoCard from "../../components/common/ApiVideoCard";

const Main = () => {
    const [isViewMore, setIsViewMore] = useState(false);
    const [isShowMore, setIsShowMore] = useState(false);
    const [page, setPage] = useState(1);
    const [param, setParam] = useState('');
    const [videoData, setVideoData] = useRecoilState(VideoState);
    const [videoData2, setVideoData2] = useState([]);

    // async function fetchData() {

    //     const result = await axios.get(
    //         'http://localhost:8080/video'
    //     );
    //     //json????????? ????????? [Object object]??? ????????? ?????????~~
    //     setVideoData(result);
    //     console.log(result.data);
    //     console.log(videoData);
    // };
    useEffect(() => {
        setVideoData([]);

        async function fetchData() {
            const result = await axios.get(
                'http://localhost:8080/video'
            );
            setVideoData(result.data);
            console.warn(result.data);
        }
        fetchData();
        console.log(videoData);
    }, []);
    // const FetchData = () => {
    //     fetch('http://localhost:8080/video',
    //         {method:'GET',headers:{'Content-Type':'application/json'},})
    //         .then(res=>res.json())
    //         .then(data=> setVideoData(data))
    //         .catch(error => console.error('Error:', error));
    //
    // };


    // const fetchVideoDataDB = () => {
    //     axios.get('http://localhost:3002/video',{})
    //         .then((res) => {
    //             const {data} = res;
    //             setVideoData(data);
    //         })
    //         .catch((err) => {
    //             console.log(err);
    //         });
    //
    // };



    function shuffle(arrayList) {
        const real = arrayList;
        return real.sort(() => Math.random() - 0.5);
    }

    const navigate = useNavigate();
    const categoryList = ["??????", "??????", "??????", "??????","??????", "??????"];
    const onCategoryClick = (param) => {
        navigate(`/category/${param}`);
    };
    return (
        <HomeWrapper>
            <SimpleSlider/>
            <SliderWrapper>
                <SliderTitle>????????? ?????? ASMR</SliderTitle>
                <SliderSubtitle>???????????? ???????????? ????????? ASMR??? ?????? ??? ?????????!</SliderSubtitle>

                <SliderExplain onClick={()=>setIsViewMore(!isViewMore)}>View more</SliderExplain>
                {isViewMore && <SliderViewMore>???????????? ??????????????? ????????? ????????? ??? ?????? ??????????????????!</SliderViewMore>}

            </SliderWrapper>

            <CategoryContainer>
                <CategoryTitle>Category</CategoryTitle>

                <CategoryList>
                    {categoryList.map((category) => (
                        <CategoryText key={category} onClick={()=>onCategoryClick(category)}>{category}</CategoryText>

                    ))}

                </CategoryList>
            </CategoryContainer>
            <BannerWrapper>
                <BannerImg src={sound} alt={"??????"}/>
                <TextWrapper>
                    <BannerTitle>????????? ????????? ?????? ??????</BannerTitle>
                    <BannerText>HH ASMR</BannerText>

                </TextWrapper>
            </BannerWrapper>

            <PromotionContainer>
                <PromotionTitle>?????? ?????????</PromotionTitle>
                <HotSection>
                    <PromotionCard/>
                </HotSection>

            </PromotionContainer>
            <VideoWrapper>
                <PromotionTitle>?????? ?????????</PromotionTitle>
                <PromotionSection>
                    <ApiVideoCard page={page} count={2} order={"date"}/>
                </PromotionSection>
                <ViewMoreBtn>
                    <ViewMoreBtnText onClick={()=> setPage(page+1)}>{isShowMore ? '??????' : '?????????'}</ViewMoreBtnText>
                </ViewMoreBtn>
            </VideoWrapper>

            <MakerSection>
                <PromotionTitle color="#757575">?????? ???????????????</PromotionTitle>
                {/*<CenterSlider/>*/}

                <MakerImgContainer>
                    <MakerSubImg src={oceanpan} alt={"??????"}/>
                    <MakerSubImg src={dark} alt={"??????"}/>
                    <MakerImg src={tree} alt={"??????"}/>
                    <MakerSubImg src={ocean} alt={"??????"}/>
                    <MakerSubImg src={headphone} alt={"??????"}/>
                </MakerImgContainer>
            </MakerSection>

            <VideoWrapper>
                <PromotionTitle>??????????????? ??????</PromotionTitle>
                <CategoryBtnContainer>
                    {categoryList.map((category) => (
                        <CategoryBtn key={category} onClick={()=>setParam(category)}>#{category}</CategoryBtn>
                    ))}

                </CategoryBtnContainer>
                <PromotionSection>
                    <PostVideoCard page={page} param={param} count={8} data={videoData}/>
                </PromotionSection>

            </VideoWrapper>
            <PromotionTitle>???????????? ???????????????</PromotionTitle>

            <CollectionSection>

                <CollectionImg src={atom} alt={"atom"}/>
                <CollectionTextWrapper>
                    <CollectionTitle>HotCreator</CollectionTitle>
                    <CollectionText>??????????????????</CollectionText>
                    <CollectionTitle>?????? ????????????</CollectionTitle>
                    <CollectionText>??????, ??????</CollectionText>
                    <CollectionTitle>????????? ?????? ????????? ??? ??????</CollectionTitle>
                    <CollectionText>+10,218???</CollectionText>
                    <CollectionTitle>?????? ????????? ?????? ????????? ??????</CollectionTitle>
                    <CollectionText>+22,340???</CollectionText>

                </CollectionTextWrapper>

            </CollectionSection>
            <ScrollTop handleClick={handleScroll}/>
        </HomeWrapper>
    );
};

export default Main;