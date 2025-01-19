/* Styles */
import styles from "../styles/components/navbar.module.css"

/* Components */
import LinkButton from "./LinkButton"

/* Interface */
interface NavbarProps {
    style: string;
    icon: string;
    children?: React.ReactNode
}

function Navbar({ style, icon, children }: NavbarProps) {
    return (
        <div className={styles[style]}>
            <div>
                <img src={icon} alt="" />
                <p>TaskFlow</p>
            </div>
            {style === "navbarHorizontal" ? (
                <LinkButton style="btnSmall" text="Sign in" address={"/lists"}/>
            ) : (
                <div className={styles.children}>
                    {children}
                </div>
            )}
        </div>
    )
}

export default Navbar