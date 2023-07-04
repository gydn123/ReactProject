import axios from "axios";
import { useState } from "react";

const [promotions, setPromotions] = useState([]);

const Testdata = async () => {
  // 백엔드 서버에서 데이터를 가져오는 코드를 작성합니다.
  const response = await axios.get("http://localhost:8080/promotion");
  setPromotions(response.data);
};

export default Testdata;
