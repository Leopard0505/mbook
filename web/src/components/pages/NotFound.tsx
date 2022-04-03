import React from "react";

import "components/pages/NotFound.scss"

const NotFound: React.VFC = () => {
  return (
    <div className="container">
      <div className="boo-wrapper">
        <div className="boo">
          <div className="face"></div>
        </div>
        <div className="shadow"></div>
        <h1>404<br />Not Found</h1>
        <p>
          We couldn't find the page you
          <br />
          were looking for.
        </p>
      </div>
    </div>
  )
}

export default NotFound
