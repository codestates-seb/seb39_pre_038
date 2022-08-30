import React, { useRef, useEffect, useState } from 'react';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';
import '@toast-ui/editor/dist/toastui-editor.css';

function Posting() {
  const [Title, setTitle] = useState('');
  const [question, setQuestion] = useState('');
  const [queId, setQueId] = useState();
  const [btn, setBtn] = useState();

  const onChange = (e) => {
    setTitle(e.target.value);
  };

  useEffect(() => {
    const post = () => {
      const req = {
        id: queId,
        username: '',
        password: '',
        title: Title,
        body: question,
      };
      axios
        .post('http://localhost:8080/posts', req)
        .then((data) => {
          setQueId(data.id.length + 1);
        })
        .catch((e) => console.log('something worng:', e));
    };
    post();
    console.log(btn);
  }, [btn]);

  const editorRef = useRef();

  const posting = () => {
    const editorIns = editorRef.current.getInstance();
    // const contentHtml = editorIns.getHTML();
    const contentMark = editorIns.getMarkdown();
    setQuestion(contentMark);
    setBtn(!btn);
    // console.log(contentHtml);
    // console.log(contentMark);
  };
  return (
    <div>
      <h1>Ask a public question</h1>
      <section>
        <h1>Title</h1>
        <input type="text" onChange={onChange} />
        <h1>Body</h1>
        <Editor
          ref={editorRef}
          previewStyle="vertical"
          height="auto"
          initialEditType="markdown"
          toolbarItems={[
            ['bold', 'italic'],
            ['link', 'quote', 'code', 'image', 'codeblock'],
            ['ol', 'ul', 'heading', 'hr'],
          ]}
        />
      </section>
      <button type="button" onClick={posting}>
        Review your Question
      </button>
    </div>
  );
}

export default Posting;
