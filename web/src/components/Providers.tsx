import React from "react";
import { AuthUserProvider } from "components/providers/index";

export const Providers: React.VFC<{children: React.ReactNode}> = ({ children }) => {
  return (
    <>
      <AuthUserProvider>
        {children}
      </AuthUserProvider>
    </>
  )
}
