import axios from "axios";
import { Link, useLocation } from "react-router-dom";
import React, { useState, useEffect } from "react";
import "./order.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

function useQuery() {
  return new URLSearchParams(useLocation().search);
}

const Order = () => {
  const query = useQuery();
  const promotion_id = query.get("promotion_id");
  const [sum, setSum] = useState(0);
  const [mypoint, setMyPoint] = useState(0);
  const [data, setData] = useState({ price: [], mypoint: [] });
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const [count, setCount] = useState(0);

  useEffect(() => {
    fetchData1();
  }, []);

  const fetchData1 = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/order?promotion_id=${promotion_id}`
      );
      const data1 = response.data;
      setMyPoint(data1.mypoint[0]);
      setData(data1);
      setIsDataLoaded(true);
    } catch (error) {
      console.error(error);
    }
  };
  // const handleMinusClick = (event) => {
  //   countticket(event.target.className);
  //   const row = event.target.closest(".table-row");
  //   const priceValue = parseInt(
  //     row.querySelector(".price").textContent.replace(/[^0-9]/g, "")
  //   );
  //   const numbox = row.querySelector(".numbox");
  //   if (numbox.value <= 0) {
  //     return;
  //   }
  //   const minusNum = parseInt(numbox.value) - 1;
  //   numbox.value = minusNum;

  //   if (row.querySelector(".ticket-id").value !== null) {
  //     const dataa = `<td>${priceValue * minusNum}</td>`;
  //     row.querySelector(".total").innerHTML = dataa;
  //   }

  //   updateSum();
  // };

  const countTicket = (type, ticketId) => {
    // if (type === "plus") {
    //   if (count < 10) {
    //     let count1 = count + 1;
    //     setCount(count1);
    //   }
    // } else if (type === "minus") {
    //   if (count > 0) {
    //     let count1 = count - 1;
    //     setCount(count1);
    //   }
    // }
    const row = document
      .querySelector(`.table-row .ticket-id[value="${ticketId}"]`)
      .closest(".table-row");
    const numbox = row.querySelector(".numbox");

    if (type === "plus" && parseInt(numbox.value) < 10) {
      setCount({ ...count, [ticketId]: (count[ticketId] || 0) + 1 });
    } else if (type === "minus" && parseInt(numbox.value) > 0) {
      setCount({ ...count, [ticketId]: (count[ticketId] || 0) - 1 });
    }
  };

  const countClick = (type, ticketId) => {
    const row = document
      .querySelector(`.ticket-id[value="${ticketId}"]`)
      .closest(".table-row");
    const numbox = row.querySelector(".numbox");

    if (type === "plus" && count >= 10) {
      return;
    } else if (type === "minus" && count <= 0) {
      return;
    }

    countTicket(type, ticketId);

    const priceValue = parseInt(
      row.querySelector(".price").textContent.replace(/[^0-9]/g, "")
    );
    // const numbox = row.querySelector(".numbox");
    // if (numbox.value >= 10) {
    //   return;
    // }
    // const plusNum = parseInt(numbox.value) + 1;
    // numbox.value = plusNum;
    console.log(count + "@@@count");

    const newTicketCount =
      type === "minus"
        ? (count[ticketId] || 1) - 1
        : (count[ticketId] || 0) + 1;
    if (row.querySelector(".ticket-id").value !== null) {
      if (newTicketCount > 10) {
        return;
      }
      const dataa = `<td>${priceValue * newTicketCount}</td>`;
      row.querySelector(".total").innerHTML = dataa;
    }

    // if (row.querySelector(".ticket-id").value !== null) {
    //   const dataa = `<td>${priceValue * parseInt(numbox.value)}</td>`;
    //   row.querySelector(".total").innerHTML = dataa;
    // }

    updateSum();
  };

  const updateSum = () => {
    let totalSum = 0;
    const total = document.querySelectorAll(".total");
    total.forEach((element) => {
      const value = element.textContent.replace(/[^0-9]/g, "");
      totalSum += value ? parseInt(value) : 0;
    });
    setSum(totalSum);
  };

  const handleBuyClick = () => {
    if (window.confirm("구매 하시겠습니까?")) {
      const myArray = [];
      const usePoint = document.getElementById("mypoint").value;
      const tableRows = document.querySelectorAll(".table-row");
      tableRows.forEach((row) => {
        const myMap = new Map();
        const ticketId = row.querySelector(".ticket-id").value;
        const numbox = row.querySelector(".numbox");
        myMap.set("ticket_id", ticketId);
        myMap.set("quantity", numbox.value);
        myArray.push(myMap);
      });

      const myJSON = JSON.stringify(
        myArray.map((obj) => ({
          ticket_id: obj.get("ticket_id"),
          quantity: obj.get("quantity"),
        }))
      );

      console.log(myJSON);

      // Make AJAX request here
    }
  };

  return (
    <div className="container">
      <form action="/" method="post">
        <div className="product">
          <img
            src={isDataLoaded ? data.price[0]["promotion_img"] : ""}
            alt="Product Image1"
          />
          <div>
            <div className="name">
              {isDataLoaded ? data.price[0]["promotion_name"] : ""}
            </div>
            <div className="description">
              {isDataLoaded ? data.price[0]["promotion_content"] : ""}
            </div>
          </div>
        </div>
        <table>
          <thead>
            <tr>
              <th>행사명</th>
              <th>상품명</th>
              <th>가격</th>
              <th>수량</th>
              <th>합계</th>
            </tr>
          </thead>
          <tbody>
            {data.price.map((d) => (
              <tr className="table-row" key={d.ticket_id}>
                <td>{d.promotion_name}</td>
                <td>{d.ticket_name}</td>
                <td className="price">
                  {new Intl.NumberFormat("ko-KR", {
                    style: "currency",
                    currency: "KRW",
                  }).format(d.ticket_price * (1 - d.discount))}
                </td>
                <td>
                  <button
                    type="button"
                    className="minus"
                    onClick={() => countClick("minus", d.ticket_id)}
                  >
                    -
                  </button>
                  <input
                    type="number"
                    className="numbox"
                    value={count[d.ticket_id] || 0}
                    onChange={countTicket}
                    readOnly
                  />
                  <input
                    type="hidden"
                    className="ticket-id"
                    value={d.ticket_id}
                  />
                  <button
                    type="button"
                    className="plus"
                    onClick={() => countClick("plus", d.ticket_id)}
                  >
                    +
                  </button>
                </td>
                <td className="total"></td>
              </tr>
            ))}
          </tbody>
          <tfoot>
            <tr className="totalsum">
              <td>총 가격 :</td>
              <td>
                <input
                  type="number"
                  className="mt-4 mb-4"
                  id="sum"
                  value={sum}
                  readOnly
                />
              </td>
              <td>포인트 사용 :</td>
              <td>
                <input
                  type="number"
                  className="mt-4 mb-4"
                  id="mypoint"
                  value={mypoint ?? ""}
                  max={isDataLoaded ? data.price[0]["sum_point"] : ""}
                  min="0"
                  onChange={(e) => setMyPoint(e.target.value)}
                />
              </td>
              <td>
                <button
                  id="buy-btn"
                  type="button"
                  className="mt-4 mb-4"
                  style={{ float: "right" }}
                  onClick={handleBuyClick}
                >
                  구매 하기
                </button>
              </td>
            </tr>
          </tfoot>
        </table>
      </form>
    </div>
  );
};

export default Order;
