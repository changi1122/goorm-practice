import { useState } from 'react';
import useChatSocket from '../hooks/useChatSocket';
import styles from './ChatRoom.module.css';

function ChatRoom() {

    const [uuid, setUuid] = useState(crypto.randomUUID());
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
                <div className={`${styles.chatMessage} ${styles.outgoing}`}>
                    <div className={styles.message}>안녕하세요</div>
                    <div className={styles.time}>오후 3:30</div>
                </div>
                <div className={`${styles.chatMessage} ${styles.incoming}`}>
                    <div className={styles.message}>안녕하세요</div>
                    <div className={styles.time}>오후 3:30</div>
                </div>
                <div className={`${styles.chatMessage} ${styles.incoming}`}>
                    <div className={styles.message}>안녕하세요</div>
                    <div className={styles.time}>오후 3:30</div>
                </div>
                {
                    messages && messages.map((message, index) => (
                        <div key={index} className={`${styles.chatMessage} ${message.sender === uuid ? styles.outgoing : styles.incoming}`}>
                            <div className={styles.message}>{message.content}</div>
                            <div className={styles.time}>오후 3:30</div>
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