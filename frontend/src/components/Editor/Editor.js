import React, { forwardRef } from 'react';
import { Editor as Writer, Viewer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';

const Editor = forwardRef(
  ({ type, previewStyle, height, initialEditType, initialValue }, ref) => {
    if (type === 'write') {
      return (
        <Writer
          previewStyle={previewStyle}
          height={height}
          initialEditType={initialEditType}
          ref={ref}
          plugins={[colorSyntax, [codeSyntaxHighlight, { highlighter: prism }]]}
        />
      );
    }
    if (type === 'view') {
      return <Viewer initialValue={initialValue} ref={ref} />;
    }
    return null;
  },
);

export default Editor;
