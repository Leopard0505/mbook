import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import BookList from "components/pages/books/BookList";
import BookCreate from "components/pages/books/BookCreate";
import BookRegister from "components/pages/books/BookRegister";
import Login from "components/pages/Login";

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