import axios from "axios";

// リクエスト送信前にヘッダーにAuthorizationを入れる
axios.interceptors.request.use(
  config => {
    Object.assign(config.headers, {
      // ローカルストレージから取り出す
      Authorization: `Bearer ${localStorage.getItem('MBOOK_ACCESS_TOKEN')}`
    })
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// アクセストークンを再取得
export const refreshToken = () => {
  // processing
  const axiosInstance = axios['post']
  axiosInstance('http://localhost:8080/api/v1/auth/', { user_id: 1 })
    .then(response => {
      // ローカルストレージに入れる
      localStorage.setItem('MBOOK_ACCESS_TOKEN', response.data)
    })
    .catch(error => {
      console.log(error)
    })
}

// レスポンスが403エラーの場合はアクセストークンを再取得する
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error.response.status === 403) {
      // refreshToken()
    }
  }
)
