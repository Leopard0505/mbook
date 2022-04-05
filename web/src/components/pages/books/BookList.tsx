import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { useFetch } from "components/hooks/userFetch";
import { useQueryString } from "components/hooks/useQueryString";
import Loader from "components/common/Loader";
import { Book } from "interfaces/book";
import { useAuthUserContext } from "components/providers/index";

const BookList: React.VFC = () => {
  const authUser = useAuthUserContext().user
  const querystring = useQueryString({ params: ['title', 'publisher'] })
  const { refetch, data, isLoading, hasError, error } = useFetch<Book[]>({
    skip: true,
    url: 'http://localhost:8080/api/v1/books/' + querystring,
    headers: {
      Authorization: `Bearer ${authUser?.token}`
    }
  })

  useEffect(() => {
    (async() => {
      await refetch()
    })()
  }, [])

  if (isLoading) {
    return (
      <Loader />
    )
  }

  return (
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
  )
}

export default BookList;
