import { useState } from 'react';
import { useOutletContext } from "react-router-dom";
import useChatSocket from '../hooks/useChatSocket';
import styles from './ChatRoom.module.css';

function ChatRoom() {

    const { uuid } = useOutletContext();
    const [input, setInput] = useState('');
    const [messages, sendMessage] = useChatSocket();

    const handlePressEnter = (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            handleSendMessage(e);
        }
    }

    const handleSendMessage = (e) => {
        e.preventDefault();
        sendMessage(uuid, input);
        e.target.reset();
    }

    return (
        <div>
            <div className={styles.chatBox}>
                {
                    messages && messages.map((message, index) => (
                        <div key={index} className={`${styles.chatMessage} ${message.sender === uuid ? styles.outgoing : styles.incoming}`}>
                            <div className={styles.message}>{message.content}</div>
                            <div className={styles.time}>{uuid}</div>
                        </div>
                    ))
                }
            </div>

            <div className={styles.inputBox}>
                <input type="text" placeholder="메시지를 입력하세요" onKeyDown={handlePressEnter} onChange={(e) => { setInput(e.target.value) }} value={input}/>
                <button onClick={handleSendMessage}>전송</button>
            </div>
        </div>
    )
}

export default ChatRoom;