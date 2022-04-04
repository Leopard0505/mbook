import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import BookList from "components/pages/books/BookList";
import BookCreate from "components/pages/books/BookCreate";
import BookRegister from "components/pages/books/BookRegister";
import Login from "components/pages/Login";
import NotFound from "components/pages/NotFound";
import Layout from "components/common/Layout";

import { RouteAuthGuard } from "components/RouteAuthGuard"

export const RouterConfig: React.VFC = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={
              <RouteAuthGuard component={<BookList />} redirect="/login" />} />
            <Route path="/books/register" element={
              <RouteAuthGuard component={<BookRegister />} redirect="/login" />} />
            <Route path="/books/create" element={
              <RouteAuthGuard component={<BookCreate />} redirect="/login" />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}