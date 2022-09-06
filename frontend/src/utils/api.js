export const GET_QUESTIONS = (query = '?page=1') =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${query}`;
export const POST_QUESTION =
  'http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/ask';
export const PATCH_QUESTION = (id = '') =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`;
export const DELETE_QUESTION = (id = '') =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`;

// 답변 생성과 디테일 내역
export const DETAIL_GET_QUESTION = (id = '') =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`;
export const POST_ANSWER =
  'http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/replies';
export const PATCH_ANSWER = (questionId, replyID) =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${questionId}/${replyID}`;
export const DELETE_ANSWER = (questionId, replyID) =>
  `http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/questions/${questionId}/${replyID}`;
