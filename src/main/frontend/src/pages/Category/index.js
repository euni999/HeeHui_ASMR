import React, {useState} from 'react';
import {
    PromotionTitle,
    PromotionSubTitle,
    SearchInput,
    ViewMoreBtn,
    ViewMoreBtnText,
    SearchDiv,
    SearchBtn,
    CategoryBtn,
    CategoryContainer,
    CategoryWrapper
} from './styled';

import {PromotionSection} from '../Main/styled';

import {useNavigate} from "react-router-dom";
import PostVideoCard from "../../components/common/PostVideoCard";
import axios from "axios";
import {useRecoilValue} from "recoil";
import {VideoState} from "../../States/VideoStates";
import handleScroll from "../../Utils/ScrollTop/handleScroll";
import ScrollTop from "../../Utils/ScrollTop";

const Category = () => {

    const [isShowMore, setIsShowMore] = useState(false);
    const navigate = useNavigate();
    const [page, setPage] = useState(1);
    const [categorySearch, setCategorySearch] = useState('');
    const [param, setParam] = useState('');
    const video = useRecoilValue(VideoState);

    console.log(video);
    const [videoData, setVideoData] = useState('');
    const onSearch = e => {
        e.preventDefault();
        if(categorySearch === ''){
            alert("검색어를 입력해주세요.");
        } else {
            setParam(categorySearch);

            async function fetchData() {

                const result = await axios.get(
                    'http://localhost:8080/category?word=' + categorySearch
                );

                console.log(result);
                // json콘솔로 찍으면 [Object object]로 보여서 바꾸기~~
                setVideoData(result.data);
                console.warn(result.data);
            }
            fetchData();
            console.log(videoData);


            navigate(`/category/${categorySearch}`);
        }
        setCategorySearch('');
    };
    const onCategoryClick = (category) => {
        setParam(category);
        navigate(`/category/${category}`);
    };
    const handleChange = e => {
        setCategorySearch(e.target.value);
    };
    const categoryList = ["전체", "요리", "카페", "자연", "웃음", "수면", "팅글"];

    return (
        <CategoryContainer>
            <PromotionTitle>카테고리별 맞춤 선택</PromotionTitle>
            <PromotionSubTitle>ASMR with US!</PromotionSubTitle>
            <SearchDiv>
                <form onSubmit={onSearch}>
                    <SearchInput type={"text"} value={categorySearch} placeholder={"오늘의 키워드는?"} onChange={handleChange}/>
                    <SearchBtn type={"submit"} onClick={onSearch}/>
                </form>
            </SearchDiv>
            <CategoryWrapper>

                {categoryList.map((category) => (
                    <CategoryBtn  key={category} onClick={()=>onCategoryClick(category)}>#{category}</CategoryBtn>

                ))}
            </CategoryWrapper>
            <hr/>
            <PromotionSection>
                <PostVideoCard page={page} param={param} count={12} data={video}/>

            </PromotionSection>
            <ViewMoreBtn>
                <ViewMoreBtnText onClick={()=> setPage(page+1)}>{ (isShowMore ? '닫기' : '더보기')}</ViewMoreBtnText>
            </ViewMoreBtn>
            <ScrollTop handleClick={handleScroll}/>
        </CategoryContainer>


    );
};

export default Category;