import { Outlet } from 'react-router-dom';
import Header from '../components/Header';
import { useState } from 'react';

function ChatRoomLayout() {

    const [uuid, setUuid] = useState(crypto.randomUUID());

    return (
        <>
            <Header uuid={uuid} />
            <Outlet context={{ uuid }} />
        </>
    )
}

export default ChatRoomLayout;