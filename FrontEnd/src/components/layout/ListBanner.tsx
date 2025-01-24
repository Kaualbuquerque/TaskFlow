/* Router */
import { Link } from "react-router-dom";

/* Styles */
import styles from "../styles/components/listBanner.module.css"

interface ListProps {
    type?: string;
    address?: any;
    name?: string;
}

function ListBanner({ type, address, name }: ListProps) {
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
                        <h3>{name}</h3>
                    </>
                )
                }
            </div >
        </Link>
    )
}

export default ListBanner 