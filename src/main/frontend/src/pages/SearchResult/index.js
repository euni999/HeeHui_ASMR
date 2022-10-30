import React, { useState, useEffect } from 'react';
import {
    LayoutContainer,
    NavBar,
    Main,
    SideBar,
    RelatedSearchTitle,
    NavWrapper,
    RelatedSearchItem,
    RelatedSearchWrapper,
    SearchResultWrapper,
    SearchResultCount,
    SearchResultText,
    Result,
    SideBarSearchTitle,
    SideBarWrapper,
    SideBarSearchItem,
    SideBarRecommendWrapper,
    SideBarRecommendTitle,
    SideBarRecommendItem,
    BlankSpace,
    PromotionSection
} from './styled';
import useFetch from '../../hooks/useYoutube';
import { useNavigate, useParams } from 'react-router-dom';
import ApiVideoCard from '../../components/common/ApiVideoCard';
import axios from "axios";
import PostVideoCard from "../../components/common/PostVideoCard";
import {useRecoilState, useRecoilValue, useSetRecoilState} from "recoil";
import {SearchVideoState, VideoCountState} from '../../States/VideoStates';
import SearchVideoCard from "../../components/common/SearchVideoCard";

const SearchResult = () => {
    let parameter = useParams().word;
    console.log(parameter);
    const navigate = useNavigate();

    const setVideoCount = useSetRecoilState(VideoCountState);

    const [videoData, setVideoData] = useRecoilState(SearchVideoState);
    const [videoData2, setVideoData2] = useState([]);
    const [page, setPage] = useState(1);
    const [param, setParam] = useState(parameter);

    const searchList = ['고구마', '붕어빵', '강유미', '순대', '붕어싸만코'];

    const [searchText, setSearchText] = useState(param);

    const onSearchTextClick = (search)=>{
        setSearchText(search);
        navigate(`/search/${search}`);
    };

    const [searchData, setSearchData] = useState([]);
    const videoCount = useRecoilValue(VideoCountState);

    useEffect(() => {
        setVideoData([]);

        console.log("asd");


    }, []);
    useEffect(() => {
        setVideoData([]);

        console.log("asddsdfewf");


        fetchData();



    }, [parameter]);


    async function fetchData() {
        const result = await axios.get(
            'http://localhost:8080/search?word=' + parameter
        );
        console.log(parameter);
        console.log(result);
        setVideoCount(result.data.length);
        setVideoData(result.data);

        console.warn(result.data);
    }

    const onSearchClick = (category) => {
        navigate(`/category/${category}`);
    };


    return (
        <LayoutContainer>
            <Result>
                <SearchResultWrapper>
                    <SearchResultCount>{videoCount}</SearchResultCount>
                    <SearchResultText>개의 결과가 존재합니다.</SearchResultText>
                </SearchResultWrapper>
            </Result>
            <BlankSpace/>
            <NavBar>
                <NavWrapper>
                    <RelatedSearchTitle>연관 검색어</RelatedSearchTitle>
                    <RelatedSearchWrapper>
                        {searchList.map((search, idx) => (
                            <RelatedSearchItem key={idx} onClick={()=>{onSearchTextClick(search);}}>{search}</RelatedSearchItem>
                        ))}
                    </RelatedSearchWrapper>
                </NavWrapper>
            </NavBar>
            <Main>
                <PromotionSection>
                    <SearchVideoCard data={videoData} color={"white"}/>
                    {/*<ApiVideoCard param={param} color={"white"}/>*/}
                    {/*<PostVideoCard page = {2} param={param} data={videoData} count={3} color={"white"}/>*/}
                    {/*<PostVideoCard data={videoData}  color={"white"} param={param}/>*/}
                </PromotionSection>
            </Main>
            <SideBar>
                <SideBarWrapper>
                    <SideBarSearchTitle>인기 검색어</SideBarSearchTitle>
                    <SideBarSearchItem to={'/search/해리포터'}>해리포터</SideBarSearchItem>
                    <SideBarSearchItem to={'/search/공부'}>공부</SideBarSearchItem>
                    <SideBarSearchItem to={'/search/먹방'}>먹방</SideBarSearchItem>
                </SideBarWrapper>
                <SideBarRecommendWrapper>
                    <SideBarRecommendTitle>오늘의 키워드</SideBarRecommendTitle>
                    <SideBarRecommendItem to={'/search/졸업작품'}>졸업작품</SideBarRecommendItem>
                    <SideBarRecommendItem to={'/search/타이핑 소리'}>타이핑 소리</SideBarRecommendItem>

                </SideBarRecommendWrapper>
            </SideBar>

        </LayoutContainer>
    );
};

export default SearchResult;
