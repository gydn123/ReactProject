import React, { useEffect, useState } from "react";
import axios from "axios";
import "./mypoint.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

function Mypoint() {
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

  // Locale 설정
  const toKRWString = (number) => {
    return new Intl.NumberFormat("ko-KR", {
      style: "currency",
      currency: "KRW",
    }).format(number);
  };

  return (
    <div>
      {/* menu.jsp, mypagemenu.jsp 추가/분리된 컴포넌트로 이름 변경 필요 */}
      {/* {<Menu />} */}
      {/* {<MyPageMenu />} */}
      <main className="container custom-main-padding border-bottom">
        <div className="row">
          <article className="offset-1 col-8">
            <h2 className="mb-5">
              사용 가능한 포인트는{" "}
              {toKRWString(data.mypoint[0]?.sum_point ?? 0)} 포인트 입니다.
            </h2>
            <hr />
            <h4 className="mb-3">포인트 적립 내역</h4>
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
                          onClick={() => refund(d.order_id)}
                          disabled
                        >
                          구매 확정
                        </button>
                      ) : d.checkrefund ? (
                        <button
                          className="btn btn-danger"
                          onClick={() => refund(d.order_id)}
                          disabled
                        >
                          환불 완료
                        </button>
                      ) : (
                        <button
                          className="btn btn-danger"
                          onClick={() => refund(d.order_id)}
                          disabled
                        >
                          포인트 적립 미정
                        </button>
                      )}
                    </h4>
                    <div className="col-md-4 mb-5 ">
                      <div className="card">
                        <div className="card-body">
                          <h5 className="card-title">
                            주문번호 : {d.order_id}
                          </h5>
                          <p className="card-text">{d.a_name}</p>
                          <p className="card-text">{d.promotion_name}</p>
                          <p className="card-text">{d.ticket_name}</p>
                          <p className="card-subtitle mb-2 text-muted">
                            {toKRWString(d.ticket_price * (1 - d.discount))} *{" "}
                            {d.quantity}
                          </p>
                          {/* 추가된 조건문 및 버튼 이벤트 처리 */}
                          {d.checkorder && d.use_point !== null && (
                            <>
                              <p className="card-text">
                                결제금액 :{" "}
                                {toKRWString(
                                  d.ticket_price *
                                    (1 - d.discount) *
                                    d.quantity -
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
                                d.ticket_price *
                                  (1 - d.discount) *
                                  d.quantity *
                                  0.05
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
                          <p className="card-text">
                            구매 날짜 : {d.order_date}
                          </p>
                        </div>
                      </div>
                    </div>
                  </>
                )}
              </div>
            ))}
          </article>
        </div>
      </main>
      {/* footer.jsp 추가/분리된 컴포넌트로 이름 변경 필요 */}
      {/*<Footer />*/}
    </div>
  );
}

export default Mypoint;
