import {atom} from 'recoil';
import {recoilPersist} from 'recoil-persist';

const {persistAtom} = recoilPersist();
export const VideoState = atom({
    key : 'VideoState',
    default : [],
    effects_UNSTABLE : [persistAtom],
});

export const VideoCountState = atom({
    key : 'VideoCountState',
    default : 0,
    effects_UNSTABLE : [persistAtom],
});
export const SearchVideoState = atom({
    key : 'SearchVideoState',
    default : [],
    effects_UNSTABLE : [persistAtom],
});