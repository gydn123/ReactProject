import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import Board from "./pages/Board/main/board";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  //<React.StrictMode>
  <BrowserRouter>
    <App />
  </BrowserRouter>
  //</React.StrictMode>

  //   <Provider store={store}>
  //     <PersistGate persistor={persistor}>
  //       <BrowserRouter>
  //         <App/>
  //       </BrowserRouter>
  //     </PersistGate>
  //   </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))npo
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

// const board = ReactDOM.createRoot(document.getElementById('board'));
// board.render(
//   <React.StrictMode>
//     <Board />
//   </React.StrictMode>
// );

reportWebVitals();
