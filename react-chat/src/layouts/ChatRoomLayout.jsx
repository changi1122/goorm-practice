import { Outlet } from 'react-router-dom';
import Header from '../components/Header';

function ChatRoomLayout() {
    return (
        <>
            <Header />
            <Outlet />
        </>
    )
}

export default ChatRoomLayout;