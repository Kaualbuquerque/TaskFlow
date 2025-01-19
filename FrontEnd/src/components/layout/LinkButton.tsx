import { Link } from "react-router-dom";

/* Styles */
import styles from "../styles/components/linkButton.module.css"

/* Interface */
interface bntProps {
    text: string;
    style: string;
    address?: any;
}

function LinkButton({ text, style, address }: bntProps) {
    return (
        <>
            <Link to={address}>
                <button className={styles[style]}>{text}</button>
            </Link>
        </>
    )
}

export default LinkButton