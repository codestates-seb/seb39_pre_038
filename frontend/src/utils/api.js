export const GET_QUESTIONS = (query = '?page=1') =>
  `/questions/${query}`;
export const POST_QUESTION =
  '/questions/ask';
export const PATCH_QUESTION = (id = '') =>
  `/questions/${id}`;
export const DELETE_QUESTION = (id = '') =>
  `/questions/${id}`;

// 답변 생성과 디테일 내역
export const DETAIL_GET_QUESTION = (id = '') =>
  `/questions/${id}`;
export const POST_ANSWER =
  '/questions/replies';
export const PATCH_ANSWER = (questionId, replyID) =>
  `/questions/${questionId}/${replyID}`;
export const DELETE_ANSWER = (questionId, replyID) =>
  `/questions/${questionId}/${replyID}`;
