import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ChatRoomLayout from './layouts/ChatRoomLayout';
import ChatRoom from './pages/ChatRoom';

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ChatRoomLayout />}>
            <Route index element={<ChatRoom />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App;
