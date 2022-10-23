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
import {ApiState} from "../../States/VideoStates";
import {useRecoilState} from "recoil";

const SearchResult = () => {
    let parameter = useParams().word;
    const navigate = useNavigate();

    const [videoData, setVideoData] = useRecoilState(ApiState);
    const [page, setPage] = useState(1);
    const [param, setParam] = useState(parameter);

    const searchList = ['고구마', '붕어빵', '강유미', '순대', '붕어싸만코'];

    const [searchText, setSearchText] = useState(param);

    const onSearchTextClick = (search)=>{
        setSearchText(search);
        navigate(`/search/${search}`);
    };

    const [searchData, setSearchData] = useState([]);
    let searchCount = 0;
    useEffect(() => {
        async function fetchData() {
            const result = await axios.get(
                'http://localhost:8080/search?word=' + parameter
            );
            // json콘솔로 찍으면 [Object object]로 보여서 바꾸기~~
            setVideoData(result.data);
            console.warn(result.data);
            searchCount = Object.keys(result).length;
        }
        fetchData();
        console.log(videoData);
    }, []);

    const onSearchClick = (category) => {
        navigate(`/category/${category}`);
    };
    return (
        <LayoutContainer>
            <Result>
                <SearchResultWrapper>
                    <SearchResultCount>{searchCount}</SearchResultCount>
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
                    {/*<PostVideoCard page = {2} param={param} data={videoData} count={3}/>*/}
                    <ApiVideoCard  color={"white"} param={param}/>
                </PromotionSection>
            </Main>
            <SideBar>
                <SideBarWrapper>
                    <SideBarSearchTitle>인기 검색어</SideBarSearchTitle>
                    <SideBarSearchItem>해리포터</SideBarSearchItem>
                    <SideBarSearchItem>공부</SideBarSearchItem>
                    <SideBarSearchItem>강유미</SideBarSearchItem>
                </SideBarWrapper>
                <SideBarRecommendWrapper>
                    <SideBarRecommendTitle>오늘의 키워드</SideBarRecommendTitle>
                    <SideBarRecommendItem>졸업작품</SideBarRecommendItem>
                    <SideBarRecommendItem>타이핑 소리</SideBarRecommendItem>

                </SideBarRecommendWrapper>
            </SideBar>

        </LayoutContainer>
    );
};

export default SearchResult;
