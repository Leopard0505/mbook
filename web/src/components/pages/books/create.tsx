import { usePost } from "components/hooks/usePost"
import { Book } from "interfaces/book"

function RegisterBook() {
  const { doPost, isLoading } = usePost<Book>({
    method: 'post',
    url: 'http://localhost:8080/api/v1/books/create'
  })

  if (isLoading) {
    return <div>Loading ...</div>
  }

  const onFormSubmit = (event: any) => {
    event.preventDefault()
    doPost({
      params: {
        title: event.target.title.value,
        original_author: event.target.original_author.value,
        drawer: event.target.drawer.value,
        publisher: event.target.publisher.value,
      },
      onSuccess: (book) => {
        console.log(`create [${book?.title}] success!`)
        // TODO 画面遷移
      },
      onError: (err) => {
        console.log(err.message)
      }
    })
  }

  return (
    <div className="box is-padding-md">
      <h2 className="mbook-title">購読本（マスタ）登録</h2>
      <hr className="mbook-border"></hr>
      <fieldset className="fieldset is-padding-xxl">
        <form onSubmit={onFormSubmit}>
          <div className="grid is-gap-md">
            <div className="column is-3">
              <label className="label is-middle" htmlFor="title">
                <span className="text">作品名</span>
              </label>
            </div>
            <div className="column is-9">
              <input className="input is-underline" id="title" type="text" name="title" placeholder="作品名" />
            </div>

            <div className="column is-3">
              <label className="label is-middle" htmlFor="original_author">
                <span className="text">原作者</span>
              </label>
            </div>
            <div className="column is-9">
              <input className="input is-underline" id="original_author" type="text" name="original_author" placeholder="原作者" />
            </div>

            <div className="column is-3">
              <label className="label is-middle" htmlFor="drawer">
                <span className="text">著者</span>
              </label>
            </div>
            <div className="column is-9">
              <input className="input is-underline" id="drawer" type="text" name="drawer" placeholder="著者" />
            </div>

            <div className="column is-3">
              <label className="label is-middle" htmlFor="publisher">
                <span className="text">出版社</span>
              </label>
            </div>
            <div className="column is-9">
              <input className="input is-underline" id="publisher" type="text" name="publisher" placeholder="出版社" />
            </div>
          </div>
          <div className="box">
            <button className="button is-plain is-success" type="submit">登録</button>
          </div>
        </form>
      </fieldset>
    </div>
  )
}

export default RegisterBook;
