import { useState } from "react";
import "./App.css";
import Navigation from "./customers/components/Navigation/Navigation";
import HomePage from "./customers/components/pages/HomePage/HomePage";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <Navigation />
      <div>
        <HomePage />
      </div>
    </div>
  );
}

export default App;
