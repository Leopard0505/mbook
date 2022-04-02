import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useFetch } from "components/hooks/userFetch";
import { useQueryString } from "components/hooks/useQueryString";
import Layout from "components/common/Layout";
import { useAuth } from "components/hooks/useAuth";
import { Book } from "interfaces/book";

const BookList: React.VFC = () => {
  const navigate = useNavigate()
  const { isAuth } = useAuth()
  if (!isAuth()) {
    navigate("/login")
  }
  const querystring = useQueryString({ params: ['title', 'publisher'] })
  const { data, isLoading, hasError, error } = useFetch<Book[]>({
    url: 'http://localhost:8080/api/v1/books/' + querystring
  })

  if (isLoading) {
    return <div>Loading ...</div>
  }

  return (
    <Layout>
      <div className="box is-padding-md">
        <h2 className="mbook-title">購読本一覧</h2>
        <hr className="mbook-border"></hr>
        <div className="box is-padding-horizontal is-flex is-right">
          <div className="joint">
            <input type="search" className="input mbook-search" name="text" placeholder="キーワード"></input>
            <button type="submit" className="button is-plain is-success">
              <i aria-hidden="true" className="fas fa-search"></i>
              検索
            </button>
          </div>
        </div>
        <hr className="mbook-border"></hr>
        {hasError && <b>{error}</b>}
        <table className="table is-line is-outline is-stripe">
          <thead>
            <tr>
              <th>タイトル</th>
              <th>著者名</th>
              <th>作画</th>
              <th>出版社</th>
              <th>発売日</th>
            </tr>
          </thead>
          <tbody>
            {data?.map(book => {
              return book.bookInfos.map((bookInfo) => (
                <tr key={bookInfo.book_info_id}>
                  <td>{bookInfo.title}</td>
                  <td className="text is-right">{book.original_author}</td>
                  <td className="text is-right">{book.drawer}</td>
                  <td className="text is-right">
                    <Link className="text is-success is-weight-700" to={{
                      search: `?publisher=${book.publisher}`
                    }}>{book.publisher}</Link>
                  </td>
                  <td className="text is-right">{bookInfo.release_date}</td>
                </tr>
              ))
            })}
          </tbody>
        </table>
      </div>
    </Layout>
  )
}

export default BookList;
