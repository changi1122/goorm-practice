import styles from './ChatRoom.module.css';

function ChatRoom() {

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
            </div>

            <div className={styles.inputBox}>
                <input type="text" placeholder="메시지를 입력하세요" />
                <button>전송</button>
            </div>
        </div>
    )
}

export default ChatRoom;