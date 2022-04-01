import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import BookList from "components/pages/books/list";
import BookCreate from "components/pages/books/create";
import BookRegister from "components/pages/books/register";
import Login from "components/pages/login";

export const RouterConfig: React.VFC = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<BookList />} />
          <Route path="/books/register" element={<BookRegister />} />
          <Route path="/books/create" element={<BookCreate />} />

          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}