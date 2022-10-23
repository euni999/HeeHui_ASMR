import React,{useEffect} from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Category from '../pages/Category';
import Login from '../pages/Login';
import Main from '../pages/Main';
import NotFound from '../pages/NotFound';
import SignUp from '../pages/SignUp';
import MyPage from '../pages/MyPage';
import Favorite from '../pages/Favorite';
import FindAccount from "../pages/FindAccount";
import SearchResult from "../pages/SearchResult";
import Detail from "../pages/Detail";

import {
    RecoilRoot
} from 'recoil';
// import { QueryClient, QueryClientProvider } from 'react-query';

// 글로벌 스타일 만들기 위한 createGlobalStyle
import { createGlobalStyle } from "styled-components";

// 스타일 초기화를 위한 reset
import reset from "styled-reset";
import NewVideo from "../pages/NewVideo";
import Inquiry from "../pages/Inquiry";
import Service from "../pages/Service";
import StudentPage from "../pages/StudentPage";
import axios from "axios";
import Event from "../pages/Event";

// 글로벌 스타일 만들기, reset 사용해서 스타일 초기화하기
const GlobalStyles = createGlobalStyle`
    ${reset}
    body {
        width : 100vw;
      margin : 0;
    }
`;
const App = ({auth}) => {
    return (
        <RecoilRoot>
            <GlobalStyles />

            <BrowserRouter>
                <Header/>
                <Routes>
                    {/*<Route element={<Layout/>}>*/}
                    <Route path="/" element={<Main/>}/>
                    <Route path="/signup" element={<SignUp/>}/>
                    <Route path="/category/:word" element={<Category/>}/>
                    <Route path="/findaccount" element={<FindAccount/>}/>
                    <Route path="/search/:word" element={<SearchResult/>}/>
                    <Route path="/detail/:video_id" element={<Detail/>}/>
                    <Route path="/mypage" element={<MyPage auth={auth}/>} />
                    <Route path="/favorite" element={<Favorite/>} />
                    <Route path="/new" element={<NewVideo/>} />
                    <Route path="/inquiry" element={<Inquiry/>} />
                    <Route path="/service" element={<Service/>} />
                    <Route path="/student" element={<StudentPage/>} />
                    <Route path="/event" element={<Event/>} />
                    <Route path="/login" element={<Login auth={auth}/>} />
                    <Route path="*" element={<NotFound/>}/>
                </Routes>
                <Footer/>
            </BrowserRouter>
        </RecoilRoot>
    );
};

export default App;