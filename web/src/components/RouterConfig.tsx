import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import BookList from "components/pages/books/BookList";
import BookCreate from "components/pages/books/BookCreate";
import BookRegister from "components/pages/books/BookRegister";
import Login from "components/pages/Login";
import NotFound from "components/pages/NotFound";
import Layout from "components/common/Layout";

export const RouterConfig: React.VFC = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<BookList />} />
            <Route path="/books/register" element={<BookRegister />} />
            <Route path="/books/create" element={<BookCreate />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}