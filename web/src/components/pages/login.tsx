import React, { useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import { usePost } from "components/hooks/usePost"
import Loader from "components/common/Loader"
import { AuthUserContextType, useAuthUserContext } from "components/providers/index"
import { User } from "interfaces/user"

type CustomLocation = {
  state: { from: { pathname: string } }
}

const Login: React.VFC = () => {
  const navigate = useNavigate()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const authUser: AuthUserContextType = useAuthUserContext()
  const location: CustomLocation = useLocation() as CustomLocation

  const { doPost, isLoading } = usePost<string>({
    method: 'post',
    url: 'http://localhost:8080/api/v1/auth/token'
  })

  if (isLoading) {
    return <Loader />
  }

  const onFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault()
    let token: string = ""
    await doPost({
      params: {
        username,
        password
      },
      onSuccess: (response) => {
        if (response === undefined) return
        console.log(`create [${response}] success!`)
        token = response
      },
      onError: (err) => {
        console.log(err.message)
      }
    })
    
    const user: User = {
      userId: 0,
      name: username,
      role: "system-admin",
      token: token
    }
    authUser.signin(user, () => {
      navigate(location.state.from.pathname, { replace: true })
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
