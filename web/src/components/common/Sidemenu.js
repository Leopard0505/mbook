function Sidemenu() {
    return (
        <nav>
            <ul className="is-padding-md">
                <li className="is-padding-xxs">
                    <span className="text">購読本</span>
                    <ul className="is-padding-left-md">
                        <li className="is-padding-xxs">
                            <a href="/" className="text">購読本一覧</a>
                        </li>
                        <li className="is-padding-xxs">
                            <a href="/new" className="text">購読本登録</a>
                        </li>
                        <li className="is-padding-xxs">
                            <a href="/books/new" className="text">購読本（マスタ）登録</a>
                        </li>
                    </ul>
                </li>
                <hr className="mbook-border"></hr>
                <li className="is-padding-xxs">
                    <a href="/" className="text">サイドメニュー3</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/" className="text">サイドメニュー4</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/" className="text">サイドメニュー5</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/" className="text">サイドメニュー6</a>
                </li>
                <li className="is-padding-xxs">
                    <a href="/" className="text">サイドメニュー7</a>
                </li>
            </ul>
        </nav>
    )
}

export default Sidemenu