import React, { useRef, useEffect, useState } from 'react';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';
import '@toast-ui/editor/dist/toastui-editor.css';

function Replies() {
  const [Title, setTitle] = useState();
  const [queInfo, setQueInfo] = useState();
  const [memberId, setMemberId] = useState();
  const [question, setQuestion] = useState('');
  // const [ansAvatarInfo, setAnsAvatarInfo] = useState();
  const [answer, setAnswer] = useState();
  const [queId, setQueId] = useState(0);
  const [ansId, setAnsId] = useState();
  const [totalAnswer, setTotalAnswer] = useState();

  useEffect(() => {
    axios
      .get('http://localhost:8080/posts')
      .then((res) => {
        console.log(res.data);
        setTitle(res.data[0].question_title);
        setQueInfo(res.data[0].question_date);
        setMemberId(res.data[0].memberid);
        setQuestion(res.data[0].question_content);
        setQueId(res.data[0].question_id);
        setTotalAnswer(res.data[0].answer);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  useEffect(() => {
    axios
      .get('http://localhost:8080/answer')
      .then((res) => {
        console.log(res.data);
        setAnswer(res.data.reply_content);
        setAnsId(res.data.reply_id);
        setMemberId(res.data.ansava);
        setQueId(res.data.question_id);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  const editorRef = useRef();

  const posting = () => {
    const editorIns = editorRef.current.getInstance();
    // const contentHtml = editorIns.getHTML();
    const contentMark = editorIns.getMarkdown();
    // console.log(contentHtml);
    console.log(contentMark);
    axios
      .post('http://localhost:8080/answer', {
        id: ansId + 1,
        body: contentMark,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleting = () => {
    axios.delete(`http://localhost:8080/posts/${queId}`);
  };

  return (
    <div>
      <h1>{Title}</h1>
      <div>{queInfo}</div>
      <section>
        <div>
          <button type="button" aria-label="vote" />0
          <button type="button" aria-label="vote" />
        </div>
        <article>{question}</article>
        <div>
          <span>asked 11mins ago</span>
          <span>{memberId}</span>
        </div>
      </section>
      <h3>{totalAnswer} Answer</h3>
      <span>
        Sorted by:
        <select>
          <option>Newest</option>
        </select>
      </span>
      <section>
        <div>
          <button type="button" aria-label="vote" />0
          <button type="button" aria-label="vote" />
        </div>
        <article>{answer}</article>
        <div>
          <span>answerd 11mins ago</span>
          <span>{memberId}</span>
        </div>
        <button
          type="button"
          onClick={() => {
            deleting();
          }}
        >
          Delete
        </button>
      </section>
      <h1>Your Answer</h1>
      <Editor
        ref={editorRef}
        previewStyle="vertical"
        height="300px"
        initialEditType="markdown"
        toolbarItems={[
          ['bold', 'italic'],
          ['link', 'quote', 'code', 'image', 'codeblock'],
          ['ol', 'ul', 'heading', 'hr'],
        ]}
      />
      <button
        type="button"
        aria-label="Posting"
        onClick={() => {
          posting();
        }}
      >
        Post Your Answer
      </button>
    </div>
  );
}

export default Replies;
