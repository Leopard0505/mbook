import React, { useState } from "react"
import { useNavigate } from "react-router-dom"
import { usePost } from "components/hooks/usePost"
import { useAuth } from "components/hooks/useAuth"

const Login: React.VFC = () => {
  const navigate = useNavigate()
  const { isAuth } = useAuth()
  if (isAuth()) {
    navigate("/")
  }
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
        navigate("/")
      },
      onError: (err) => {
        console.log(err.message)
      }
    })
  }

  return (
    <div className="box is-padding-md">
      <div className="card is-outline is-radius-sm" style={{"width": "500px", "margin": "auto"}}>
        <div className="box is-padding-md">
          <div className="box is-padding-md">
            <span className="text is-weight-900 is-xxl">MBOOK</span>
          </div>
          <form onSubmit={onFormSubmit}>
            <div className="box is-padding-md">
              <div>
                <label htmlFor="username">ユーザー名</label>
              </div>
              <div>
              <input className="input is-underline" id="username" type="text" name="username"
                placeholder="ex: MB00001" value={username} onChange={(event) => setUsername(event.target.value)}
                style={{"width": "300px"}} />
              </div>
            </div>
            <div className="box is-padding-md">
              <div>
                <label htmlFor="password">パスワード</label>
              </div>
              <div>
                <input className="input is-underline" id="password" type="password" name="password"
                  placeholder="ex: mb00001" value={password} onChange={(event) => setPassword(event.target.value)}
                  style={{"width": "300px"}} />
              </div>
            </div>
            <div className="box is-margin-top-md is-padding-md">
              <button className="button is-plain is-success" type="submit">登録</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default Login
