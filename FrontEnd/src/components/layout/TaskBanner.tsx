/* Styles */
import styles from "../styles/components/taskBanner.module.css"

function TaskBanner() {
    return (
        <div className={styles.taskBanner}>
            <label className={styles.customCheckbox}>
                <input type="checkbox" />
                <span className={styles.checkmark}></span>
            </label>
            <div>
                <div className={styles.description}>
                    <h1>Task Title</h1>
                    <p>Description</p>
                </div>

                <p className={styles.infos}><span>Priority: Low</span> <span>dd/mm/yyyy</span></p>
            </div>

        </div>
    )
}

export default TaskBanner