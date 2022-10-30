import React, { useEffect, useState } from 'react';
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

import { useNavigate, useParams } from 'react-router-dom';
import PostVideoCard from "../../components/common/PostVideoCard";
import ScrollTop from '../../Utils/ScrollTop';
import handleScroll from '../../Utils/ScrollTop/handleScroll';
import { useRecoilValue } from 'recoil';
import { VideoState } from '../../States/VideoStates';

const Category = () => {
    const videoData = useRecoilValue(VideoState);
    let parameter = useParams().word;
    const [isShowMore, setIsShowMore] = useState(false);
    const navigate = useNavigate();
    const [page, setPage] = useState(1);
    const [categorySearch, setCategorySearch] = useState('');
    const [param, setParam] = useState(parameter);

    // const [videoData, setVideoData] = useState('');

    const onCategoryClick = (category) => {
        setParam(category);
        navigate(`/category/${category}`);
    };
    useEffect(()=>{
        setParam(parameter);
    }, [parameter]);
    const handleChange = e => {
        setCategorySearch(e.target.value);
    };
    const categoryList = ["전체", "요리", "카페", "자연", "웃음", "수면", "팅글"];

    return (
        <CategoryContainer>
            <PromotionTitle>카테고리별 맞춤 선택</PromotionTitle>
            <PromotionSubTitle>ASMR with US!</PromotionSubTitle>

            <CategoryWrapper>

                {categoryList.map((category) => (
                    <CategoryBtn  key={category} onClick={()=>onCategoryClick(category)}>#{category}</CategoryBtn>

                ))}
            </CategoryWrapper>
            <hr/>
            <PromotionSection>
                <PostVideoCard page={page} param={param} count={12} data={videoData}/>

            </PromotionSection>
            <ViewMoreBtn>
                <ViewMoreBtnText onClick={()=> setPage(page+1)}>{ (isShowMore ? '닫기' : '더보기')}</ViewMoreBtnText>
            </ViewMoreBtn>
            <ScrollTop handleClick={handleScroll}/>
        </CategoryContainer>


    );
};

export default Category;