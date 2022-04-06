import React from "react";
import { transitions, positions, Provider as AlertProvider, AlertOptions } from "react-alert";
import AlertTemplate from "react-alert-template-basic"
import { AuthUserProvider } from "components/providers/index";

const options: AlertOptions = {
  position: positions.TOP_RIGHT,
  timeout: 5000,
  transition: transitions.FADE
}

export const Providers: React.VFC<{children: React.ReactNode}> = ({ children }) => {
  return (
    <>
      <AuthUserProvider>
        <AlertProvider template={AlertTemplate} {...options}>
          {children}
        </AlertProvider>
      </AuthUserProvider>
    </>
  )
}
