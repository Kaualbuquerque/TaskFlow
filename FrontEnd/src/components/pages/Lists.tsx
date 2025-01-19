/* Styles */
import styles from "../styles/pages/lists.module.css"

/* Icons */
import logo from "../../assets/icons/logo.png"

/* Components */
import Navbar from "../layout/Navbar"
import ListBanner from "../layout/ListBanner"

function Lists() {
    return (
        <div className={styles.listPage}>
            <Navbar style="navbarVertical" icon={logo}>
                <ul>
                    <li>List 1</li>
                    <li>List 2</li>
                    <li>List 3</li>
                    +
                </ul>
            </Navbar>
            <div className={styles.lists}>
                <ListBanner address={"/tasks"} />
                <ListBanner address={"/tasks"} />
                <ListBanner type="add" />
            </div>
        </div>
    )
}

export default Lists