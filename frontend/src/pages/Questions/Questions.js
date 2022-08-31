import React, { useRef } from 'react';
import Editor from '../../components/Editor/Editor';
import './Questions.module.css';

function Questions() {
  const editorRef = useRef(null);

  return (
    <React.Fragment key={null}>
      <Editor type="write" ref={editorRef} />
    </React.Fragment>
  );
}

export default Questions;
