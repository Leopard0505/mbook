import React, { useState } from "react"
import { useNavigate } from "react-router-dom"
import { useFetch } from "components/hooks/userFetch"
import { usePost } from "components/hooks/usePost"
import Layout from "components/common/Layout"
import { Book } from "interfaces/book"


const BookRegister: React.VFC = () => {
  const { data, isLoading, hasError, error } = useFetch<Book[]>({ url: 'http://localhost:8080/api/v1/books/' })
  const { doPost, isLoading: isPostLoading } = usePost<Book>({
    method: 'post'
  })
  const [bookId, setBookId] = useState('')
  const [roll, setRoll] = useState('')
  const [releaseDate, setReleaseDate] = useState('')
  const navigate = useNavigate()

  if (isLoading || isPostLoading) {
    return <div>Loading ...</div>
  }

  const doFormSubmit = (event: React.FormEvent) => {
    event.preventDefault()
    const book = data?.filter(book => book.book_id === Number(bookId))[0]
    doPost({
      url: `http://localhost:8080/api/v1/books/${bookId}/create`,
      params: {
        title: `${book?.title}（${roll}）`,
        release_date: releaseDate,
        special_edition: false
      },
      onSuccess: (book) => {
        console.log(`create [${book?.title}] success!`)
        navigate("/")
      },
      onError: (err) => {
        console.log(err.message)
      }
    })
  }

  return (
    <Layout>
      <div className="box is-padding-md">
        <h2 className="mbook-title">購読本登録</h2>
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
              <th className="text is-weight-700">タイトル</th>
              <th className="text is-weight-700">著者名</th>
              <th className="text is-weight-700">作画</th>
              <th className="text is-weight-700">出版社</th>
            </tr>
          </thead>
          <tbody>
            {data?.map(book => {
              return (
                <tr key={book.book_id}>
                  <td className="text is-success is-weight-700">
                    <a href={`/?title=${book.title}`}>{book.title} （{book.bookInfos.length}）</a>
                  </td>
                  <td>{book.original_author}</td>
                  <td>{book.drawer}</td>
                  <td>{book.publisher}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
        <hr className="mbook-border"></hr>
        <div className="card is-bg-light is-outline">
          <div className="box is-padding-xxl">
            <form onSubmit={doFormSubmit}>
              <div>
                タイトル
                <div className="select">
                  <select name="title" id="selectedTitle" value={bookId} onChange={(event) => setBookId(event.target.value)}>
                    <option value="">本のタイトルを選択してください...</option>
                    {data?.map(book => {
                      return (
                        <option key={book.book_id} value={book.book_id}>{book.title}</option>
                      )
                    })}
                  </select>
                </div>
              </div>
              <div>
                巻数
                <input className="input" type="number" name="roll" step="1" placeholder="10" value={roll} onChange={(event) => setRoll(event.target.value)} />
              </div>
              <div>
                発売日
                <input className="input" type="date" name="release_date" value={releaseDate} onChange={(event) => setReleaseDate(event.target.value)} />
              </div>
              <div className="box">
                <button className="button is-plain is-success" type="submit">購読本作成</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </Layout>
  )
}

export default BookRegister;
