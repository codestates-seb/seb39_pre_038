import React from 'react';
import Editor from '../Editor/Editor';

function AskQuestion() {
  return (
    <div>
      <Editor
        type="write"
        previewStyle="vertical"
        height="400px"
        initialEditType="markdown"
      />
    </div>
  );
}

export default AskQuestion;
