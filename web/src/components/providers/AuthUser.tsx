import { User } from "interfaces/user";
import React, { createContext, useContext, useState } from "react";

export type AuthUserContextType = {
  user: User | null;
  signin: (user: User, callback: () => void) => void;
  signout: (callback: () => void) => void;
}

const AuthUserContext = createContext<AuthUserContextType>({} as AuthUserContextType)

export const useAuthUserContext = (): AuthUserContextType => {
  return useContext<AuthUserContextType>(AuthUserContext)
}

export const AuthUserProvider: React.VFC<{children: React.ReactNode}> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null)
  console.log('AAA')
  // useEffect(() => {
  //   const MBOOK_ACCESS_TOKEN = localStorage.getItem("MBOOK_ACCESS_TOKEN")
  //   if (MBOOK_ACCESS_TOKEN == null) {
  //     setUser(null)
  //   } else {
  //     // TODO アクセストークンからユーザー情報を取得する（APIを叩く）
  //     console.log('access_token: %s', MBOOK_ACCESS_TOKEN)
  //     // 一時的にアクセストークンがある場合はサンプルユーザー情報を設定する
  //     setUser({
  //       userId: 1,
  //       name: "MB00001",
  //       role: "system-admin"
  //     })
  //   }
  // }, [])

  const signin = (newUser: User, callback: () => void) => {
    setUser(newUser)
    callback()
  }

  const signout = (callback: () => void) => {
    setUser(null)
    callback()
  }

  const value: AuthUserContextType = { user, signin, signout }
  return (
    <AuthUserContext.Provider value={value}>
      {children}
    </AuthUserContext.Provider>
  )
}
