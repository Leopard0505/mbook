import React from "react";
import { useFetch } from "components/hooks/userFetch";
import { Book } from "interfaces/book";
import { useLocation } from "react-router-dom";

const App: React.VFC = () => {
  const useQueryString = () => {
    const search = useLocation().search
    const queryString = new URLSearchParams(search)
    const query = queryString.get('title') != null ? `?title=${queryString.get('title')}` : ''
    return query
  }
  const query = useQueryString()
  const { data, isLoading, hasError, error } = useFetch<Book[]>({
    url: 'http://localhost:8080/api/v1/books/' + query
  })

  if (isLoading) {
    return <div>Loading ...</div>
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
                <td className="text is-right">{book.publisher}</td>
                <td className="text is-right">{bookInfo.release_date}</td>
              </tr>
            ))
          })}
        </tbody>
      </table>
    </div>
  );
}

export default App;
