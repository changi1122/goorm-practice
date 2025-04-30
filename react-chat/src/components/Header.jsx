import { useState } from 'react';
import usePushNotifications from '../hooks/usePushNotification';
import styles from './Header.module.css';

function Header() {
    const { isSubscribed, subscribe, unsubscribe } = usePushNotifications();

    function toggleSubscription() {
        if (isSubscribed) {
            unsubscribe();
        } else {
            subscribe();
        }
    }

    return (
        <header className={styles.header}>
            <h1 className={styles.title}>채팅방</h1>

            <button className={styles.button} onClick={toggleSubscription} aria-label="알림 토글">
                {isSubscribed ? (
                    <svg viewBox="0 0 24 24">
                        <path d="M12 2C10.35 2 9 3.35 9 5v1.09c-2.84.48-5 2.94-5 5.91v5l-2 2v1h20v-1l-2-2v-5c0-2.97-2.16-5.43-5-5.91V5c0-1.65-1.35-3-3-3zm0 20c1.1 0 2-.9 2-2h-4c0 1.1.9 2 2 2z" />
                    </svg>
                ) : (
                    <svg viewBox="0 0 24 24">
                        <path d="M20 17.17L4.83 2 3.41 3.41 6.2 6.2C5.48 7.24 5 8.56 5 10v5l-2 2v1h15.17l1.41 1.41 1.41-1.41L20 17.17zM12 22c1.1 0 2-.9 2-2h-4c0 1.1.9 2 2 2zm6-6v-5c0-2.22-1.21-4.15-3-5.19V5c0-1.1-.9-2-2-2-.33 0-.65.08-.94.21l9.13 9.13c.53-.6.81-1.38.81-2.21z" />
                    </svg>
                )}
            </button>
        </header>
    )
}

export default Header;