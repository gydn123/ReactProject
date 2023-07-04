<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<link href="<c:url value="/resources/css/boardtest.css" />" rel="stylesheet" />
<style type="text/css">
ul.tabs {
	float: left;
	list-style: none;
}

ul.tabs li {
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li a {
	color: #666666;
	text-decoration: none;
}

.search-wrap .btn-search {
	background: #555;
	color: #fff;
	height: 40px;
}

#boardList td a {
	text-decoration: none;
	color: black;
}

.board-table {
	font-size: 15px;
}

.menu_select {
	position: relative;
	bottom: 10px;
	margin: auto;
	width: auto;
	float: right;
	margin: 0 10px;
}

.notice {
	text-align: center;
}

.container, .notice {
	max-width: 1500px;
	margin: auto;
}
.pagination-button {
	display:block;
	margin:0 3px;
	float:left;
	width:28px;
	height:28px;
	line-height:28px;
	text-align:center;
	background-color:#fff;
	font-size:13px;
	color:#999999;
	text-decoration:none;
}
</style>
<script type="text/javascript">
var pageSize = 10;
var currentPage = 1;
var pagevalue = 100;
$(document).ready(function(){
	var form = document.getElementById("#write_form");
	$('#write-top').on('click',function(){
		if(${member_id ne null}){
			location.href="/board/createBoard";
		}else{
			alert('로그인 해주세요!');
			location.href="/login";
		}
	});
	
	$('#write-bottom').on('click',function(){
		if(${member_id ne null}){
			location.href="/board/createBoard";
		}else{
			alert('로그인 해주세요!');
			location.href="/login";
		}
	});
	
	if(${param.b_type eq 1}){
		$('#li-1').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 2}){
		$('#li-2').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 3}){
		$('#li-3').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else if(${param.b_type eq 4}){
		$('#li-4').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}else{
		$('#li-0').css({"border-width":"1px 1px 0 1px","border-style":"solid","background":"#ededed","border-color":"#999999","font-weight":"bold","border-radius":"5px 5px 0 0"});
	}
	
	post_elastick(currentPage)
	//paging(pagevalue,currentPage,pageSize);
	/* var button = null;
	setTimeout(() => {
		button = document.querySelectorAll('.pagination-button');
		console.log(button);
	},300);
	
	
	setTimeout(() => {
		button.forEach((item) => {
			item.addEventListener('click', function() {
			   	currentPage = parseInt($(this).val());
			   	elastick(currentPage);
			   	console.log('클릭');
			});
		});
	},600); */
	$(document).on('click', '.pagination-button', function() {
		  currentPage = parseInt($(this).val());
		  elastick(currentPage);
		  console.log('클릭');
		});
	
	
});

	$(document).ready(function() {
		  $('#searchButton').click(function() {
		   // $('#elasti').html(""); // #area 요소 초기화
		    elastick(currentPage);
		  });	
		  /*$('.pagination-button').click(function(){
			 currentPage = parseInt($(this).val());
			 elastick(currentPage);
		  });*/
		  
		});
	
	
	
	
	// 페이지 사이즈 업데이트 함수
	function updatePageSize(value) {
	  pageSize = parseInt(value);
	  elastick(currentPage); // pageSize를 업데이트한 후 elastick() 함수 호출
	  //paging(pagevalue);
	}

		function elastick(currentPage) {
		  let searchValue = $('#text').val(); // 검색어 입력 필드에서 값을 가져옵니다.
		  let selectField = $('select[name=selectField]').val(); // 선택된 필드 값을 가져옵니다.
		  console.log("selectField: "+selectField);
		  console.log("searchValue: "+searchValue);
		  const query = {
				  "from": ((currentPage-1)*pageSize), // buttonvalue*pageSize 이렇게 해라
				  "size": pageSize,
				  "sort": [
				    {
				      "board_id": {
				        "order": "desc"
				      }
				    }
				  ],
				  "query": {
				    "wildcard": {
				    	[selectField]: {
				        "value": `*${'${searchValue}'}*`
				      }
				    }
				  }
				};
		  
		  const query1 = {
		    "query": {
		      "wildcard": {
		        [selectField]: {
		          "value": `*${'${searchValue}'}*`
		        }
		      }
		    }
		  };
		  const sel = [query];

		  const url = 'http://localhost:9200/board_index/_search';

		  $.ajax({
		    type: 'POST',
		    contentType: "application/json",
		    "url": url,
		    dataType: 'json',
		    data: JSON.stringify(sel[0]),
		    success: function(data) {
		    	var pagevalue = data.hits.total.value; //게시물 갯수
		    	var countpages = Math.ceil(pagevalue/pageSize); // 개시물수를 페이지사이즈만큼 나눈다음 올림
		    	
		      console.log(data);
		      console.log("pagevalue==="+pagevalue);
		      console.log("countpages==="+countpages);
		      
		      let tableHTML = "";
		      for (let hit of data.hits.hits) {
		        let source = hit._source;
		        let member_id = source.member_id;
		        let postdate = source.postdate;
		        
		        let ymd=postdate.substring(0,10);
		        let ymd2=ymd.replaceAll("-",".");

		        tableHTML += "<tr>";
		        tableHTML += "<td>" + hit._id + "</td>";
		        tableHTML += "<td>" + hit._source.b_type + "</td>";
		        tableHTML += "<td style='text-align:center;padding:0 0 0 10px'><a href='/board/view?board_id=" + hit._id + "'>" + hit._source.b_title + "</a></td>";
		        tableHTML += "<td>" + member_id + "</td>";
		        tableHTML += "<td>" + ymd2 + "</td>";
		        tableHTML += "<td>" + hit._source.favorite + "</td>";
		        tableHTML += "<td>" + hit._source.visitcount + "</td>";
		        tableHTML += "</tr>";
		      }
			  
		      // 여기에서 table 요소에 테이블 HTML을 추가합니다.
		      $('#elasti').html(tableHTML);
		      paging(pagevalue,currentPage,pageSize);
		    }
		  });
		};
		
		function post_elastick(currentPage){
			var pageSize = 10;
		  const query = {
				  "from": 0,
				  "size": 10,
				  "query": {
				    "match_all": {}
				  }
				};
		  const sel = [query];

		  const url = 'http://localhost:9200/board_index/_search';
		  $.ajax({
		    type: 'POST',
		    contentType: "application/json",
		    url: url,
		    dataType: 'json',
		    data: JSON.stringify(sel[0]),
		    success: function(data) {
		    	var pagevalue = data.hits.total.value; //게시물 갯수
		    	var countpages = Math.ceil(pagevalue/pageSize); // 개시물수를 페이지사이즈만큼 나눈다음 올림
		    	
		      console.log(data);
		      console.log("pagevalue==="+pagevalue);
		      console.log("countpages==="+countpages);
		      let tableHTML = "";
		      for (const hit of data.hits.hits) {
		        const source = hit._source;
		        const member_id = source.member_id;
		        const postdate = source.postdate;
		        const ymd=postdate.substring(0,10);
		        const ymd2=ymd.replaceAll("-",".");

		        tableHTML += "<tr>";
		        tableHTML += "<td>" + hit._id + "</td>";
		        tableHTML += "<td>" + hit._source.b_type + "</td>";
		        tableHTML += "<td style='text-align:center;padding:0 0 0 10px'><a href='/board/view?board_id=" + hit._id + "'>" + hit._source.b_title + "</a></td>";
		        tableHTML += "<td>" + member_id + "</td>";
		        tableHTML += "<td>" + ymd2 + "</td>";
		        tableHTML += "<td>" + hit._source.favorite + "</td>";
		        tableHTML += "<td>" + hit._source.visitcount + "</td>";
		        tableHTML += "</tr>";
		      }
			  
		      // 여기에서 table 요소에 테이블 HTML을 추가합니다.
		      $('#elasti').html(tableHTML);
		      paging(pagevalue,currentPage,pageSize);
		    }
		  });
		};
		
		/*function paging(pagevalue) {
			  $('.page_test').html("");
			  lowpage = Math.ceil(pagevalue / pageSize);
			  console.log("pagevalue@@@"+pagevalue);
			  console.log("pageSize@@@"+pageSize);
			  console.log("lowpage@@@"+lowpage);
			  if(lowpage < 11){
				  for (var i = 1; i <= lowpage; i++) {
					    var button = document.createElement('button');
					    button.innerText = i;
					    button.classList.add('pagination-button');
					    
					    button.addEventListener('click', function() {
					      currentPage = parseInt(this.innerText);
					      
					      elastick(currentPage); // 페이지 버튼을 클릭하면 데이터를 다시 가져오는 함수 호출
					    });
					    $('.page_test').append(button);
					  }
			  }else{
				  for (var i = 1; i <= maxpage; i++) {
					    var button = document.createElement('button');
					    button.innerText = i;
					    button.classList.add('pagination-button');
					    
					    button.addEventListener('click', function() {
					      currentPage = parseInt(this.innerText);
					      
					      elastick(currentPage); // 페이지 버튼을 클릭하면 데이터를 다시 가져오는 함수 호출
					    });
					    $('.page_test').append(button);
					  }
			  }
			  
			  
			  
			}*/
			function paging(pagevalue,currentPage,pageSize) {
				$('.page_test').html("");
				let lowpage = 1;
				let maxpage = 10;
				
				lowpage = Math.ceil(pagevalue / pageSize); //총 페이지 수
				
				const pagegroup = Math.ceil(currentPage/maxpage); //페이지 그룹
				
				let last = pagegroup * pageSize; // 화면에 보여질 마지막 페이지 번호
				if(last > lowpage)
					last = lowpage;
				let first = last - (pageSize - 1); // 화면에 보여질 첫번째 페이지 번호
				const next = last + 1;
				const prev = first - 1;		
				
				if (lowpage < 1){
					first = last;
				}
				if(first > 10){
					$('.page_test').append("<button class='pagination-button' value="+ (prev) +">Prev</button>");
				}
				for (let j = first; j<=last; j++){
					if(currentPage === (j)){
						$('.page_test').append("<button class='pagination-button' value="+ (j) +">"+j+"</button>");
					}else if(j > 0){
						$('.page_test').append("<button class='pagination-button' value="+ (j) +">"+j+"</button>");
					}
				}
				if(next > 10 && next < lowpage){
					$('.page_test').append("<button class='pagination-button' value="+ (next) +">next</button>");
				}
			
								  

				  /*if (lowpage < 11) {
				    for (var i = 1; i <= lowpage; i++) {
				      var button = createButton(i);
				      $('.page_test').append(button);
				    }
				  } else {
				    var start = (Math.floor((currentPage - 1) / 10) * 10) + 1;
				    var end = Math.min(start + 9, lowpage);

				    var prevButton = createButton("<");
				    prevButton.addEventListener('click', function() {
				      currentPage = Math.max(currentPage - 1, 1);
				      elastick(currentPage);
				    });
				    if(currentPage > 10){
				    $('.page_test').append(prevButton);
				    }

				    for (var i = start; i <= end; i++) {
				      var button = createButton(i);
				      $('.page_test').append(button);
				    }

				    var nextButton = createButton(currentPage + 10);
				    nextButton.addEventListener('click', function() {
				      currentPage = Math.min(currentPage + 1, lowpage);
				      elastick(currentPage);
				    });
				    if(currentPage < 11){
				    $('.page_test').append(nextButton);
				    }
				      console.log("넥스트 버튼!!"+currentPage);
				  }*/
				}
			 

				/*function createButton(pageNumber) {
				  var button = document.createElement('button');
				  button.innerText = pageNumber;
				  button.classList.add('pagination-button');

				  button.addEventListener('click', function() {
				    currentPage = parseInt(this.innerText);
				    elastick(currentPage);
				  });

				  return button;
				}*/

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp" />
	<section class="notice">
		<!-- board list area -->
		<div id="board-list" style="clear: both">
			<div class="page-title" style="text-align: center">
				<h1>엘라스틱 테스트</h1>
			</div>


			<div class="container">
				<div class="write_form_wrap">
					<div>
						<ul class="tabs">
							<li class="tab-link current" id="li-0"><a
								href="/board/board?b_type=0">전체</a></li>
							<li class="tab-link" id="li-1"><a
								href="/board/board?b_type=1">국내</a></li>
							<li class="tab-link" id="li-2"><a
								href="/board/board?b_type=2">해외</a></li>
							<li class="tab-link" id="li-3"><a
								href="/board/board?b_type=3">질문</a></li>
							<li class="tab-link" id="li-4"><a
								href="/board/board?b_type=4">잡담</a></li>
						</ul>
					</div>

					<div class="write_form">
						<button type="submit" id="write-top" class="btn-board-top">
							<img
								src="https://cdn-icons-png.flaticon.com/512/5218/5218705.png"
								style="width: 15px; top: 5px">글쓰기
						</button>

					</div>
				</div>
				<div class="menu_select" style="">
					<div class="text">
						<select class="form-select form-select-sm" id="view-select"
							aria-label=".form-select-sm example"
							onchange="updatePageSize(this.value);">
							<option id="select-0">선택</option>
							<option id="select-1" value="10">10</option>
							<option id="select-2" value="30">30</option>
							<option id="select-3" value="50">50</option>
						</select>
					</div>
				</div>
				<table class="board-table">
					<thead>
						<tr>
							<th scope="col" class="th-num" style="width: 5%">번호</th>
							<th scope="col" class="th-num" style="width: 10%">분류</th>
							<th scope="col" class="th-title" style="width: 30%">제목</th>
							<th scope="col" class="th-member" style="width: 10%">작성자</th>
							<th scope="col" class="th-date" style="width: 30%">등록일</th>
							<th scope="col" class="th-date" style="width: 5%">추천수</th>
							<th scope="col" class="th-date" style="width: 5%">조회수</th>
						</tr>
					</thead>
					<!-- 이부분 나중에 국가별로 나눠야됨  -->
					<!--  
					<c:choose>
						<c:when test="${boardtype eq 'korea'}">
						</c:when>					
						<c:when test="${boardtype eq 'global'}">
						</c:when>					
						<c:when test="${boardtype eq 'free'}">
						</c:when>	
					</c:choose>
					-->
					<tbody id="elasti">

					</tbody>
				</table>
				<!-- board paging start-->
				<div class="page_wrap">
					<span class="page_test" style="padding: 0 0 0 20%;"></span> <span
						class="write-bottom-wrap" style="float: right">
						<button type="submit" id="write-bottom" class="btn btn-blue top"
							style="height: 40px;">글쓰기</button>
					</span>
				</div>
				<!-- board paging end -->

			</div>
		</div>
		<div style="clear: both;"></div>
		<!-- board search area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<div class="search-wrap">

						<select name="selectField"
							style="width: 20%; height: 40px; float: left; text-align: center; font-size: 14px;">
							<option value="b_title">제목</option>
							<option value="b_content">내용</option>
							<option value="member_id">작성자</option>
						</select> <label for="search" class="blind">공지사항 내용 검색</label> <input
							id="text" type="search" name="search" value="">
						<button type="button" id="searchButton" class="btn btn-search"
							onclick="elastick(currentPage)">검색</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>