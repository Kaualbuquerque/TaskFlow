/* Router */
import { Link } from "react-router-dom";

/* Styles */
import styles from "../styles/components/listBanner.module.css"

interface ListProps {
    type?: string;
    address?: any;
}

function ListBanner({ type, address }: ListProps) {
    return (
        <Link to={address} className={styles.link}>
            <div className={styles.listBanner}>

                {type === "add" ? (
                    <>
                        <h3>New list</h3>
                        <p>+</p>
                    </>
                ) : (
                    <>
                        <h3>List 1</h3>
                        <p>0 / 10</p>
                    </>
                )
                }
            </div >
        </Link>
    )
}

export default ListBanner 