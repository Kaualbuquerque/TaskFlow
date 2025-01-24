/* Styles */
import styles from "../styles/components/painel.module.css"

/* Icons */
import icon from "../../assets/icons/listLarge.png"

/* Components */
import LinkButton from "./LinkButton"

function Painel() {
    return (
        <div className={styles.painel}>
            <div>
                <h1>Organize your tasks and keep them on track, everywhere!</h1>
                <p>We provide a seamless task management solution to keep you organized and efficient.</p>
                <LinkButton style="btnLarge" text="Start now" address={"/login"}/>
            </div>
            <img src={icon} alt="Clip board"/>
        </div>
    )
}

export default Painel