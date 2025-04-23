import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

let stompClient;

export const connectSocket = (onMessage) => {
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = new Client({
        webSocketFactory: () => socket,
        onConnect: () => {
            stompClient.subscribe('/topic/chat', (message) => {
                onMessage(JSON.parse(message.body));
            });
        },
    });

    stompClient.activate();
}

export const sendMessage = (message) => {
    if (stompClient?.connected) {
        stompClient.publish({
            destination: '/app/chat',
            body: JSON.stringify(message),
        });
    }
};

export const disconnectSocket = () => {
    stompClient?.deactivate();
}