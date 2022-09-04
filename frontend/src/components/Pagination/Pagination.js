import React, { useState } from 'react';

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

const dummyData = [];
for (let i = 0; i < 100; i += 1) {
  const item = {
    title: `Dummy Page ${i}`,
    content: `Dummy Page Content ${i}`,
  };
  dummyData.push(item);
}

function Pagination() {
  const [data] = useState(dummyData);

  const Pages = () => {
    const count = parseInt(data.length / 15, 10);
    const $el = [];
    for (let i = 0; i < count; i += 1) $el.push(<div>{`${i + 1}`}</div>);
    return $el;
  };

  return (
    <div style={{ display: 'flex', gap: '10px 10px' }}>
      <Pages />
    </div>
  );
}

export default Pagination;
