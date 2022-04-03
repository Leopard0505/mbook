import React from "react"
// import Head from './Head'
import Header from './Header'
import Sidemenu from './Sidemenu'
import Footer from './Footer'
import { Outlet } from "react-router-dom"

const Layout: React.VFC<{children?: React.ReactNode}> = () => {
    return (
        <>
            {/* <Head></Head> */}
            <Header></Header>
            <div className="mbook-container grid is-gap-md">
                <div className="column is-2">
                    <Sidemenu></Sidemenu>
                </div>
                <div className="mbook-content column is-10 box is-outline-left">
                    <main><Outlet /></main>
                    <Footer></Footer>
                </div>
            </div>
        </>
    )
}

export default Layout