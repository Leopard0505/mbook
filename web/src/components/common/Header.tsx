import React from "react"

const Header: React.VFC = () => {
	return (
		<>
			<div className="box is-padding-md is-flex is-between mbook">
				<h1 className="text is-light is-weight-900">
					<a href="/">MBOOK</a>
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
