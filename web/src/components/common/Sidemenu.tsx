import React from "react"

const Sidemenu: React.VFC = () => {
    return (
        <nav>
            <ul className="is-padding-md">
                <li className="is-padding-xxs">
                    <a href="/" className="text is-success">購読本一覧</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/books/register" className="text is-success">購読本登録</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/books/create" className="text is-success">購読本（マスタ）登録</a>
                </li>
            </ul>
        </nav>
    )
}

export default Sidemenu
