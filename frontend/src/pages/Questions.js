import React, { useRef } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';

function Questions() {
  const editorRef = useRef(null);

  return (
    <React.Fragment key={null}>
      <Editor
        previewStyle="vertical"
        height="400px"
        initialEditType="markdown"
        initialValue="hello"
        ref={editorRef}
      />
    </React.Fragment>
  );
}

export default Questions;
