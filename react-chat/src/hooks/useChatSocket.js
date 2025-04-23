import { useEffect, useState } from "react";
import { connectSocket, sendStompMessage, disconnectSocket } from "../sockets";

const useChatSocket = () => {
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        connectSocket((message) => {
            setMessages((prevMessages) => [...prevMessages, message]);
        });

        return () => {
            disconnectSocket();
        };
    }, []);

    const sendMessage = (sender, content) => {
        sendStompMessage({ sender, content });
    }

    return [
        messages,
        sendMessage
    ];
}

export default useChatSocket;