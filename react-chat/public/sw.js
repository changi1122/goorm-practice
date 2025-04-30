self.addEventListener('install', () => {
    self.skipWaiting();
});

self.addEventListener('push', async function(event) {
    console.log('Push message received.');

    if (!(self.Notification && self.Notification.permission === 'granted')) {
        return;
    }

    var data = {};
    if (event.data) {
        data = await event.data.json();
    }
    var title = data.title;
    var message = data.message;
    var icon = "img/FM_logo_2013.png";

    console.log("받은 푸시 데이터: " + JSON.stringify(data));

    event.waitUntil(self.registration.showNotification(title, {
        body: message,
        tag: 'push-demo',
        icon: icon,
        data: { url: data.clickTarget },
        badge: icon,
        renotify: true
    }));
});

self.addEventListener('notificationclick', function(event) {
    console.log('[Service Worker] Notification click Received.');

    event.notification.close();

    const target = event.notification.data?.url;
    if (clients.openWindow && target) {
        event.waitUntil(clients.openWindow(target));
    }
});