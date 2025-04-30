import { useEffect, useState } from 'react';
import { urlB64ToUint8Array } from '../utils/vapidUtils';
import { sendSubscriptionToServer, removeSubscriptionFromServer } from '../services/pushService';

const VAPID_PUBLIC_KEY = 'BBtyefD63XsMWfBgtJtzZ-Elh9lt3P68G6oKMoJMDUsjNAOEQjqvxOV-OgDYJuLWlIAc8YaQ2TCkzn2bkiIhDO0=';
const SERVICE_WORKER_PATH = 'sw.js';

export default function usePushNotifications() {
  const [isSubscribed, setIsSubscribed] = useState(false);
  const [swRegistration, setSwRegistration] = useState(null);

  useEffect(() => {
    if ('Notification' in window && 'serviceWorker' in navigator) {
        Notification.requestPermission().then(status => {
        if (status === 'granted') {
          navigator.serviceWorker.register(SERVICE_WORKER_PATH)
            .then(reg => {
              setSwRegistration(reg);
              reg.pushManager.getSubscription().then(sub => {
                setIsSubscribed(!!sub);
              });
            });
        } else if (status === 'denied') {
          console.error('Notification permission denied');
        } else {
          console.error('Notification permission dismissed');
        }
      });
    }
  }, []);

  const subscribe = async () => {
    if (!swRegistration) return;
    const applicationServerKey = urlB64ToUint8Array(VAPID_PUBLIC_KEY);
    const subscription = await swRegistration.pushManager.subscribe({
      userVisibleOnly: true,
      applicationServerKey
    });

    await sendSubscriptionToServer(subscription.endpoint, subscription.getKey('p256dh'), subscription.getKey('auth'));
    setIsSubscribed(true);
  };

  const unsubscribe = async () => {
    const subscription = await swRegistration.pushManager.getSubscription();
    if (subscription) {
      const endpoint = subscription.endpoint;
      await subscription.unsubscribe();
      await removeSubscriptionFromServer(endpoint);
      setIsSubscribed(false);
    }
  };

  return { isSubscribed, subscribe, unsubscribe };
}