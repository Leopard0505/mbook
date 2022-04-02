import React from "react"
import ContentLoader from "react-content-loader"

const Loader: React.VFC = (props) => {
  return (
    <ContentLoader
      height="500"
      width="100%"
      viewBox="0 0 500 200"
      backgroundColor="#f3f3f3"
      foregroundColor="#ecebeb"
      {...props}
    >
      <rect x="0" y="10" rx="4" ry="4" width="100" height="20" />
      <rect x="0" y="40" rx="2" ry="2" width="100%" height="150" />
    </ContentLoader>
  )
}

export default Loader
