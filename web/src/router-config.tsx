import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import App from "App";
import CreateBook from "components/pages/books/create";
import RegisterBook from "components/pages/books/register";

export const RouterConfig: React.VFC = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<App />} />
          <Route path="/books/register" element={<RegisterBook />} />
          <Route path="/books/create" element={<CreateBook />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}