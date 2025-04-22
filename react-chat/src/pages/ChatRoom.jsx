function ChatRoom() {


    return (
        <div>
            <div className="chat-box">
                <div className="chat-message outgoing">
                    <div className="message">안녕하세요</div>
                    <div className="time">오후 3:30</div>
                </div>
                <div className="chat-message incoming">
                    <div className="message">안녕하세요</div>
                    <div className="time">오후 3:30</div>
                </div>
                <div className="chat-message incoming">
                    <div className="message">안녕하세요</div>
                    <div className="time">오후 3:30</div>
                </div>
            </div>

            <div className="input-box">
                <input type="text" placeholder="메시지를 입력하세요" />
                <button>전송</button>
            </div>
        </div>
    )
}

export default ChatRoom;