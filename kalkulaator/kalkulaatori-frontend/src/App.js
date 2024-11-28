// import logo from './logo.svg';
import { Link, Route, Routes } from 'react-router-dom';
import './App.css';
import Numbrid from './pages/Numbrid';
import Komakohaga from './pages/Komakohaga';
import Toiduained from './pages/Toiduained';
import Mang from './pages/Mang';

function App() {
  

  return (
    <div className="App">
      <Link to="/numbrid">
        <button>Numbrid</button>
      </Link>
      <Link to="/komakohaga">
        <button>Komakohaga</button>
      </Link>
      <Link to="/toiduained">
        <button>Toiduained</button>
      </Link>
      <Link to="/mang">
        <button>Mäng</button>
      </Link>
      <Routes>
        <Route path="numbrid" element={<Numbrid />} />
        <Route path="komakohaga" element={<Komakohaga />} />
        <Route path="toiduained" element={<Toiduained />} />
        <Route path="mang" element={<Mang />} />
      </Routes>
    </div>
  );
}

export default App;
