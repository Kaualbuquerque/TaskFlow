import React, { useState } from "react";

/* Styles */
import styles from "../styles/pages/tasks.module.css";

/* Icon */
import logo from "../../assets/icons/logo.png";

/* Components */
import Navbar from "../layout/Navbar";
import TaskBanner from "../layout/TaskBanner";
import Modal from "../layout/Modal";

function Tasks() {
    const [isModalOpen, setModalOpen] = useState(false);

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    return (
        <div className={styles.taskPage}>
            <Navbar style="navbarVertical" icon={logo}>
                <ul>
                    <li>List 1</li>
                    <li>List 2</li>
                    <li>List 3</li>
                    +
                </ul>
            </Navbar>
            <div className={styles.tasks}>
                <h2>List Title</h2>
                <button onClick={openModal}>Add a new task</button>
                <TaskBanner />
                <TaskBanner />
                <TaskBanner />
            </div>

            {/* Modal */}
            <Modal isOpen={isModalOpen} onClose={closeModal}>
                <h3>Add a New Task</h3>
                <form>
                    <input type="text" placeholder="Task Title" />
                    <button type="submit">Save Task</button>
                </form>
            </Modal>
        </div>
    );
}

export default Tasks;
