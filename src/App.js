import "./App.css";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Promotion from "./component/Promotion";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "jquery/dist/jquery.min.js";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import Promotionprice from "./component/Promotionprice";
import Order from "./component/order";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/promotion" element={<Promotion />} />
        <Route path="/promotionprice" element={<Promotionprice />} />
        <Route path="/order" element={<Order />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
