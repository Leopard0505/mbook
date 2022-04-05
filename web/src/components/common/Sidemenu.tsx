import React from "react"
import { Link } from "react-router-dom"

const Sidemenu: React.VFC = () => {
    return (
        <nav>
            <ul className="is-padding-md">
                <li className="is-padding-xxs">
                    <Link to="/" className="text is-success">購読本一覧</Link>
                </li>
                <li className="is-padding-xxs">
                    <Link to="/books/register" className="text is-success">購読本登録</Link>
                </li>
                <li className="is-padding-xxs">
                    <Link to="/books/create" className="text is-success">購読本（マスタ）登録</Link>
                </li>
            </ul>
        </nav>
    )
}

export default Sidemenu
