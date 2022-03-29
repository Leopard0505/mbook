import { useLocation } from "react-router-dom";
import querystring from "query-string"

interface QueryStringRequest {
  params: string[]
}

export function useQueryString({
  params
}: QueryStringRequest): string {
  const search = useLocation().search
  const urlSearchParams = new URLSearchParams(search)
  const map = params.filter(key => key !== '' || key !== '')
                    .filter(key => urlSearchParams.get(key) != null)
                    .reduce((prev, key) => Object.assign(prev, { [key]: (urlSearchParams.get(key)) }), {})
  
  const query = querystring.stringify(map, { encode: false })
  return query ? `?${query}` : ''
}
