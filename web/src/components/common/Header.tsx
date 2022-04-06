import React from "react"
import { Link } from "react-router-dom"

const Header: React.VFC = () => {
	return (
		<>
			<div className="box is-padding-md is-flex is-between mbook">
				<h1 className="text is-light is-weight-900">
					<Link to="/">MBOOK</Link>
				</h1>
				<nav>
					<ul>
						<li className="text is-light">
							<i aria-hidden="true" className="fas fa-angle-left is-fit" />
							HK1234
						</li>
					</ul>
				</nav>
			</div>
		</>
	)
}

export default Header
