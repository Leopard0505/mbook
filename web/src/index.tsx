import React from 'react';
import ReactDOM from 'react-dom';

import { RouterConfig } from 'router-config'
import 'plugins/axios'

import Layout from 'components/common/Layout'

import 'index.scss';
import 'styles/globals.scss'

import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <Layout>
      <RouterConfig />
    </Layout>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
