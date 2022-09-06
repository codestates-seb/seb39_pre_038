import React, { useEffect, useRef } from 'react';
import styles from './Pagination.module.css';

function Pagination({ total, currentPage, setCurrentPage }) {
  const btnContainerRef = useRef(null);
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
    const ellipsisHead = <span key="ellipsis-head">...</span>;
    const ellipsisTail = <span key="ellipsis-tail">...</span>;

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
      page.push(ellipsisTail, tail);
      return page;
    }

    if (total >= 9 && currentPage > total - 4) {
      page.push(head, ellipsisHead);
      for (let i = 4; i >= 0; i -= 1) {
        page.push(
          <button key={total - i} type="button" value={total - i}>{`${
            total - i
          }`}</button>,
        );
      }
      return page;
    }

    page.push(head, ellipsisHead);
    for (let i = currentPage - 2; i <= currentPage + 2; i += 1) {
      page.push(<button key={i} type="button" value={i}>{`${i}`}</button>);
    }
    page.push(ellipsisTail, tail);
    return page;
  };

  const handleOnClick = (e) => {
    if (e.target.type !== 'button') return;
    const current = Number(e.target.value.trim());
    setCurrentPage(current);
  };

  useEffect(() => {
    const nodeList = btnContainerRef.current.childNodes;
    nodeList.forEach((node) => {
      if (node.value === currentPage.toString()) {
        node.classList.add(`${styles.select}`);
      }
    });
  }, [currentPage]);

  return (
    <div
      className={styles.container}
      role="button"
      onClick={handleOnClick}
      aria-hidden="true"
      ref={btnContainerRef}
    >
      <CreatePage />
    </div>
  );
}

export default React.memo(Pagination);
