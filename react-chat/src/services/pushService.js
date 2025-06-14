import { toBase64UrlSafe } from "../utils/vapidUtils";

const SERVER_URL = 'http://localhost:8080';

export async function sendSubscriptionToServer(uuid, endpoint, key, auth) {
  const encodedKey = toBase64UrlSafe(btoa(String.fromCharCode(...new Uint8Array(key))));
  const encodedAuth = toBase64UrlSafe(btoa(String.fromCharCode(...new Uint8Array(auth))));

  const res = await fetch(`${SERVER_URL}/subscribe`, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ uuid: uuid, publicKey: encodedKey, auth: encodedAuth, endPoint: endpoint })
  });
  return res.json();
}

export async function removeSubscriptionFromServer(uuid, endpoint) {
  const res = await fetch(`${SERVER_URL}/unsubscribe`, {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({ uuid: uuid, endPoint: endpoint })
  });
  return res.json();
}