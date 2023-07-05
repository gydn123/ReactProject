import "./mypoint.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { useNavigate, useLocation } from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";

function Orderlist() {
  const navigate = useNavigate();
  const [data, setData] = useState({ orderlist: [], mypoint: [] });
  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/mypoint");
      const data1 = response.data;
      setData(data1);
    } catch (error) {
      console.error(error);
    }
  };
  console.log(data);
  const refund = () => {
    console.log("a");
  };

  const handleConfirm = (order_id, use_point, member_id) => {
    const url = "http://localhost:8080/check";
    const confirmdata = {
      order_id: order_id,
      use_point: use_point,
      member_id: member_id,
    };
    axios
      .post(url, confirmdata)
      .then((response) => {
        console.log(response.data);
        alert("구매해 주셔서 감사합니다!");

        navigate("/check");
      })
      .catch((error) => {
        console.error("Error occurred during request:", error);
      });
  };

  // Locale 설정
  const toKRWString = (number) => {
    return new Intl.NumberFormat("ko-KR", {
      style: "currency",
      currency: "KRW",
    }).format(number);
  };
  return (
    <div className="container custom-main-padding border-bottom">
      {data.orderlist.map((d, index) => (
        <div className="row" key={`${d.order_id}-${index}`}>
          {d.quantity !== 0 && (
            <>
              <div className="w-100"></div>
              <h4 className="mb-3">
                주문번호: {d.order_id}
                {d.checkorder ? (
                  <button
                    className="btn btn-success"
                    disabled={d.checkorder || d.checkrefund}
                  >
                    구매 확정 완료
                  </button>
                ) : d.checkrefund ? (
                  <button
                    className="btn btn-danger"
                    disabled={d.checkorder || d.checkrefund}
                  >
                    환불 처리 완료
                  </button>
                ) : (
                  <button
                    className="btn btn-outline-success"
                    onClick={() =>
                      handleConfirm(d.order_id, d.use_point, d.member_id)
                    }
                    disabled={d.checkorder || d.checkrefund}
                  >
                    구매 확정 하러 가기
                  </button>
                )}
              </h4>
              <div className="col-md-4 mb-5 ">
                <div className="card">
                  <div className="card-body">
                    <h5 className="card-title">주문번호 : {d.order_id}</h5>
                    <p className="card-text">{d.a_name}</p>
                    <p className="card-text">{d.promotion_name}</p>
                    <p className="card-text">{d.ticket_name}</p>
                    <p className="card-subtitle mb-2 text-muted">
                      {toKRWString(d.ticket_price * (1 - d.discount))} *{" "}
                      {d.quantity}
                    </p>
                    {d.checkorder && d.use_point !== null && (
                      <>
                        <p className="card-text">
                          결제금액 :{" "}
                          {toKRWString(
                            d.ticket_price * (1 - d.discount) * d.quantity -
                              d.use_point
                          )}
                        </p>
                        <p className="text-danger">
                          포인트 사용 : {toKRWString(d.use_point)}
                        </p>
                      </>
                    )}
                    {d.checkorder &&
                      (d.use_point === null || d.use_point === 0) && (
                        <p className="card-text">
                          결제금액 :{" "}
                          {toKRWString(
                            d.ticket_price * (1 - d.discount) * d.quantity
                          )}
                        </p>
                      )}
                    {d.checkrefund && (
                      <del>
                        <p className="card-text">
                          결제금액 :{" "}
                          {toKRWString(
                            d.ticket_price * (1 - d.discount) * d.quantity
                          )}
                        </p>
                      </del>
                    )}
                    {d.checkorder && (
                      <p className="text-danger">
                        포인트 적립 :{" "}
                        {toKRWString(
                          d.ticket_price * (1 - d.discount) * d.quantity * 0.05
                        )}
                      </p>
                    )}
                    {d.checkrefund && (
                      <del>
                        <p className="text-danger">
                          포인트 적립 :{" "}
                          {toKRWString(
                            d.ticket_price *
                              (1 - d.discount) *
                              d.quantity *
                              0.05
                          )}
                        </p>
                      </del>
                    )}
                    <p className="card-text">구매 날짜 : {d.order_date}</p>
                  </div>
                </div>
              </div>
            </>
          )}
          <div></div>
        </div>
      ))}
    </div>
  );
}

export default Orderlist;
