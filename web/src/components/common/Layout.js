// import Head from './Head'
import Header from './Header'
import Sidemenu from './Sidemenu'
import Footer from './Footer'

function Layout({ children }) {
    return (
        <>
            {/* <Head></Head> */}
            <Header></Header>
            <div className="mbook-container grid is-gap-md">
                <div className="column is-2">
                    <Sidemenu></Sidemenu>
                </div>
                <div className="column is-10 box is-outline-left">
                    <main>{children}</main>
                    <Footer></Footer>
                </div>
            </div>
        </>
    )
}

export default Layout