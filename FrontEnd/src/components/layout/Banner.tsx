/* Styles */
import styles from "../styles/components/banner.module.css";

/* Interface */
interface BannerProps {
    title: string;
    text: string;
    icon: string;
    style: string;
    iconAlt?: string;
}

function Banner({ title, text, icon, iconAlt, style }: BannerProps) {
    return (
        <div className={styles[style]}>
            <div>
                <p>{text}</p>
                <h2>{title}</h2>
            </div>
            <img src={icon} alt={iconAlt || "Icon"} />
        </div>
    );
}

export default Banner;
