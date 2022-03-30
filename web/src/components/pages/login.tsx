import { usePost } from "components/hooks/usePost"
import React, { useState } from "react"

const Login: React.VFC = () => {
  const { doPost, isLoading } = usePost<string>({
    method: 'post',
    url: 'http://localhost:8080/api/v1/auth/token'
  })
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  if (isLoading) {
    return <div>Loading ...</div>
  }

  const onFormSubmit = (event: React.FormEvent) => {
    event.preventDefault()
    doPost({
      params: {
        username,
        password
      },
      onSuccess: (token) => {
        if (token === undefined) return
        console.log(`create [${token}] success!`)
        localStorage.setItem('MBOOK_ACCESS_TOKEN', token)
        // TODO 画面遷移
      },
      onError: (err) => {
        console.log(err.message)
      }
    })
  }

  return (
    <div className="box is-padding-md">
      login
      <form onSubmit={onFormSubmit}>
        <div>
          ユーザー名：
          <input className="input is-underline" id="username" type="text" name="username" placeholder="ユーザー名" value={username} onChange={(event) => setUsername(event.target.value)} />
        </div>
        <div>
          パスワード：
          <input className="input is-underline" id="password" type="password" name="password" placeholder="パスワード" value={password} onChange={(event) => setPassword(event.target.value)} />
        </div>
        <div className="box">
          <button className="button is-plain is-success" type="submit">登録</button>
        </div>
      </form>
    </div>
  )
}

export default Login
