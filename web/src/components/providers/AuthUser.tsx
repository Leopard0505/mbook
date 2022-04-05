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
