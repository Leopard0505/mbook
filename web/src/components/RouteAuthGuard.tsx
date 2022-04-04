import React from "react";
import { RoleType } from "interfaces/user";
import { useAuthUserContext } from "components/providers/index";
import { Navigate, useLocation } from "react-router-dom";

type Props = {
  component: React.ReactNode;
  redirect: string;
  allowroles?: RoleType[]
}

export const RouteAuthGuard: React.VFC<Props> = (props) => {
  const authUser = useAuthUserContext().user
  const location = useLocation()

  let allowRoute = false
  if (authUser) {
    allowRoute = props.allowroles ? props.allowroles.includes(authUser.role) : true
  }

  if (!allowRoute) {
    return <Navigate to={props.redirect} state={{from:location.state}} replace={false} />
  }

  return <>{props.component}</>
}
