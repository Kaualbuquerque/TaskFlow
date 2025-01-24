/* Styles */
import styles from "../styles/pages/home.module.css"

/* Icons */
import logo from "../../assets/icons/logo.png"
import users from "../../assets/icons/users.png"
import clipboard from "../../assets/icons/listSmall.png"
import done from "../../assets/icons/listChecked.png"
import row from "../../assets/icons/row.png"

/* Components */
import Navbar from "../layout/Navbar"
import Painel from "../layout/Painel"
import Banner from "../layout/Banner"
import { Link } from "react-router-dom"

function Home() {

    return (
        <div className={styles.home}>
            <Navbar style="navbarHorizontal" icon={logo} btnText="Register" btnAddress="/register"/>
            <Painel />

            <div className={styles.banners}>
                <Banner text="Active users" title="1M+" icon={users} style="banner" />
                <Banner text="Created lists" title="3M+" icon={clipboard} style="banner" />
                <Banner text="Tasks accomplished" title="6M+" icon={done} style="banner" />
                <Link to="/register">
                    <Banner text="Get started for free" title="Features" icon={row} style="bannerAlt" />
                </Link>
            </div>
        </div>
    )
}

export default Home
