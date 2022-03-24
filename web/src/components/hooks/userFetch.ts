import { useCallback, useEffect, useMemo, useState } from "react";
import axios, { AxiosError } from "axios";

interface FetchRequest<T> {
  url: string
  headers?: Headers
  skip?: boolean
  onSuccess?: (data?: T) => void
  onError?: (err: any) => void
}

type Refetch<T> = ({ url }?: RefetchArgs<T>) => Promise<T | null>

interface FetchResponse<T> {
  data?: T | null
  refetch: Refetch<T>
  error: any
  hasError: boolean
  isLoading: boolean
}

interface Headers {
  [key: string]: any
}

interface Params {
  [key: string]: any
}

interface RefetchArgs<T> {
  url?: string
  body?: Params
  onSuccess?: (data?: T) => void
  onError?: (err: AxiosError) => void
}

export function useFetch<T> ({
  url,
  headers,
  skip = false,
  onError
}: FetchRequest<T>): FetchResponse<T> {
  const [data, setData] = useState<T | null>(null)
  const [isLoading, setLoading] = useState(false)
  const [error, setError] = useState<AxiosError | null>(null)
  const [hasError, setHasError] = useState(false)

  const memoizeUrl = useMemo(() => url, [url])
  const refetch = useCallback<Refetch<T>>(
    async <T>(args?: RefetchArgs<T>) => {
      try {
        setLoading(true)

        const axiosInstance = axios['get']
        const res = await axiosInstance(`${memoizeUrl}`, {
          headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            ...headers
          }
        })

        const data = res.data
        setData(data)
        args?.onSuccess?.(data)
        return data
      } catch (err) {
        onError?.(err)
        setError(err as AxiosError)
        setHasError(true)
        return null
      } finally {
        setLoading(false)
      }
    },
    [headers, memoizeUrl, onError]
  )

  const clear = useCallback(() => {
    setData(null)
    setLoading(false)
    setHasError(false)
    setError(null)
  }, [])

  useEffect(() => {
    if (skip) return
    if (memoizeUrl) {
      const f = async () => {
        const res = await refetch({})
        res && setData(res)
      }
      f()
    }
    return () => clear()
  }, [skip, memoizeUrl, refetch, clear])

  return {
    data,
    refetch,
    error,
    hasError,
    isLoading
  }
}