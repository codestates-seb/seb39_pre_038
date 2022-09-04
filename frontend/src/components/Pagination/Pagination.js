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

function Pagination({ total, currentPage }) {
  const CreatePage = () => {
    const page = [];
    /*
    1. total의 개수가 8개 이하면 그냥 8개를 뿌린다.
    2. total의 개수가 9개 이상이고 현재 페이지가 4이하라면 [1][2][3][4][5] ... [tail]
    3. total의 개수가 9개 이상이고 현재 페이지가 마지막 페이지 - 4 이상이면 [head] ... [tail-4][tail-3][tail-2][tail-1][tail]
    4. total의 개수가 9개 이상이고 현재 페이지가 4보다 크고 total - 3 보다 작으면 [head] ... [current += 2] ... [tail]
   */
    if (total <= 8) {
      for (let i = 0; i < total; i += 1) {
        page.push(<button type="button">{`${i + 1}`}</button>);
      }
      return page;
    }

    if (total >= 9 && currentPage <= 4) {
      for (let i = 0; i < 5; i += 1) {
        page.push(<button type="button">{`${i + 1}`}</button>);
      }
      page.push(<span>...</span>, <button type="button">{`${total}`}</button>);
      return page;
    }

    if (total >= 9 && currentPage > total - 4) {
      page.push(<button type="button">{`${1}`}</button>, <span>...</span>);
      for (let i = 4; i >= 0; i -= 1) {
        page.push(<button type="button">{`${total - i}`}</button>);
      }
      return page;
    }

    page.push(<button type="button">{`${1}`}</button>, <span>...</span>);
    for (let i = currentPage - 2; i <= currentPage + 2; i += 1) {
      page.push(<button type="button">{`${i}`}</button>);
    }
    page.push(<span>...</span>, <button type="button">{`${total}`}</button>);
    return page;
  };

  return (
    <div>
      <CreatePage />
    </div>
  );
}

export default Pagination;
