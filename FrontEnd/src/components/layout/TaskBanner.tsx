/* Styles */
import styles from "../styles/components/taskBanner.module.css"

interface TaskProps {
    title?: string;
    description?: string;
    priority?: string;
    deadline?: string;
}

function TaskBanner({ title, description, priority, deadline }: TaskProps) {
    return (
        <div className={styles.taskBanner}>
            <label className={styles.customCheckbox}>
                <input type="checkbox" />
                <span className={styles.checkmark}></span>
            </label>
            <div>
                <div className={styles.description}>
                    <h1>{title}</h1>
                    <p>{description}</p>
                </div>

                <p className={styles.infos}><span>Priority: {priority}</span> <span>{deadline}</span></p>
            </div>

        </div>
    )
}

export default TaskBanner