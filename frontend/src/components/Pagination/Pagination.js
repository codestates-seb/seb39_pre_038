import React from 'react';

/*
페이지네이션 기능 체크리스트
1. 페이지네이션을 할 경우 서버에 해당 page와 limit페이지 개수를 보낸다. limit default 15개
2. 전체 페이지의 요소의 개수를 limit로 parseInt()를 사용해서 정수값으로 표시한다.
3. 현재 통신문이 없으므로 데이터 정제 해당 버튼의 인덱스를 이용하여 정제 (idx + 1 * limit - 15, idx + 1 * limit) slice? for문? 

UI 구성 요소
1. 페이지네이션 default [1] hover 주황색
2. 맨 앞 5개 ... 마지막 페이지
3. 맨 앞 5개에서 5번으로 가는 경우 맨 첫 페이지 ... 해당 페이지 += 2 ... 마지막 페이지
4. 맨 펏 페이지 ... 마지막 페이지 - 5개
*/

/*
        total={total}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
*/

function Pagination({ total, currentPage, setCurrentPage }) {
  const CreatePage = () => {
    const page = [];
    const head = (
      <button key={1} type="button" value={1}>
        {1}
      </button>
    );
    const tail = (
      <button key={total} type="button" value={total}>
        {total}
      </button>
    );
    const ellipsis = <span key="ellipsis">...</span>;
    /*
    1. total의 개수가 8개 이하면 그냥 8개를 뿌린다.
    2. total의 개수가 9개 이상이고 현재 페이지가 4이하라면 [1][2][3][4][5] ... [tail]
    3. total의 개수가 9개 이상이고 현재 페이지가 마지막 페이지 - 4 이상이면 [head] ... [tail-4][tail-3][tail-2][tail-1][tail]
    4. total의 개수가 9개 이상이고 현재 페이지가 4보다 크고 total - 3 보다 작으면 [head] ... [current += 2] ... [tail]
   */
    if (total <= 8) {
      for (let i = 0; i < total; i += 1) {
        page.push(
          <button key={i + 1} type="button" value={i + 1}>{`${i + 1}`}</button>,
        );
      }
      return page;
    }

    if (total >= 9 && currentPage <= 4) {
      for (let i = 0; i < 5; i += 1) {
        page.push(
          <button key={i + 1} type="button" value={i + 1}>{`${i + 1}`}</button>,
        );
      }
      page.push(ellipsis, tail);
      return page;
    }

    if (total >= 9 && currentPage > total - 4) {
      page.push(head, ellipsis);
      for (let i = 4; i >= 0; i -= 1) {
        page.push(
          <button key={total - i} type="button" value={total - i}>{`${
            total - i
          }`}</button>,
        );
      }
      return page;
    }

    page.push(head, ellipsis);
    for (let i = currentPage - 2; i <= currentPage + 2; i += 1) {
      page.push(<button key={i} type="button" value={i}>{`${i}`}</button>);
    }
    page.push(ellipsis, tail);
    return page;
  };

  const handleOnClick = (e) => {
    const current = Number(e.target.value);
    setCurrentPage(current);
  };

  return (
    <div role="button" onClick={handleOnClick} aria-hidden="true">
      <CreatePage />
    </div>
  );
}

export default Pagination;
