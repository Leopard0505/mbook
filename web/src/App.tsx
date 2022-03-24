import { useFetch } from "components/hooks/userFetch";

interface BookInfo {
  book_info_id: number
  book_id: number
  title: string
  release_date: string
  special_edition: boolean
}
interface Book {
  book_id: number
  title: string
  original_author: string
  drawer: string
  publisher: string
  bookInfos: BookInfo[]
}

function App() {
  const { data, isLoading, hasError, error } = useFetch<Book[]>({ url: 'http://localhost:8080/api/v1/books/' })

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
                <td>{book.original_author}</td>
                <td>{book.publisher}</td>
                <td>{bookInfo.release_date}</td>
              </tr>
            ))
          })}
        </tbody>
      </table>
    </div>
  );
}

export default App;
