import { useCallback } from "react"

export function useAuth() {
  const isAuth = useCallback(() => {
    const MBOOK_ACCESS_TOKEN = localStorage.getItem("MBOOK_ACCESS_TOKEN")
    return MBOOK_ACCESS_TOKEN != null
  }, [])

  return {
    isAuth
  }
}
