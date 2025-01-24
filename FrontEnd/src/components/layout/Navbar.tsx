/* Styles */
import styles from "../styles/components/navbar.module.css"

/* Components */
import LinkButton from "./LinkButton"

/* Interface */
interface NavbarProps {
    style: string;
    icon: string;
    children?: React.ReactNode;
    btnText: string;
    btnAddress?:string;
}

function Navbar({ style, icon, children, btnAddress, btnText }: NavbarProps) {
    return (
        <div className={styles[style]}>
            <div>
                <a href="/">
                    <img src={icon} alt="" />
                    <p>TaskFlow</p>
                </a>
            </div>
            {style === "navbarHorizontal" ? (
                <LinkButton style="btnSmall" text={btnText} address={btnAddress}/>
            ) : (
                <div className={styles.children}>
                    {children}
                </div>
            )}
        </div>
    )
}

export default Navbar