import { useEffect, useState } from "react";

/* Styles */
import styles from "../styles/pages/tasks.module.css";

/* Icon */
import logo from "../../assets/icons/logo.png";

/* Components */
import Navbar from "../layout/Navbar";
import TaskBanner from "../layout/TaskBanner";
import Modal from "../layout/Modal";

interface ListData {
    id: number;
    name: string;
    title: string;
    description: string;
    priority: string;
    deadline: string;
}

function Tasks() {
    const [isModalOpen, setModalOpen] = useState(false);

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    const [data, setData] = useState<ListData[]>([]); // Inicializado como null para verificar posteriormente
    const [loading, setLoading] = useState(true); // Estado de carregamento

    useEffect(() => {
        const getAPI = async () => {
            try {
                const response = await fetch("http://localhost:8080/task/list/1", { method: "GET" });
                if (!response.ok) {
                    throw new Error(`Erro: ${response.status} - ${response.statusText}`);
                }
                const data = await response.json();
                setData(data);
                console.log(data)
            } catch (error) {
                console.error("Erro ao buscar dados:", error);
            } finally {
                setLoading(false); // Carregamento finalizado
            }
        };

        getAPI();
    }, []);

    return (
        <div className={styles.taskPage}>
            <Navbar style="navbarVertical" icon={logo} btnText="">
                <ul>
                    {data.map((item) => (
                        <li key={item.id}>
                            <p>{item.name}</p>
                        </li>
                    ))}
                </ul>
            </Navbar>

            <div className={styles.tasks}>
                <h2>List Title</h2>
                <button onClick={openModal}>Add a new task</button>
                {
                    data.map((item) => (
                        <TaskBanner title={item.title} description={item.description} priority={item.priority} deadline={item.deadline} />
                    ))
                }
            </div>

            {/* Modal */}
            <Modal isOpen={isModalOpen} onClose={closeModal}>
                <h3>Add a New Task</h3>
                <form className={styles.form}>
                    <label htmlFor="title">Task title</label>
                    <input type="text" name="title" id="title" placeholder="Enter task title here" />
                    <label htmlFor="description">Task description</label>
                    <input type="text" name="description" placeholder="Enter task description here" />
                    <div>
                        <div>
                            <label htmlFor="deadline">Deadline</label>
                            <input type="date" name="deadline" />
                        </div>
                        <div>
                            <label>Status</label>
                            <select name="status">
                                <option value="" disabled selected>Status</option>
                                <option value="pending">Pending</option>
                                <option value="completed">Completed</option>
                            </select>
                        </div>
                        <div>
                            <label>priority</label>
                            <select name="priority">
                                <option value="" disabled selected>Priority</option>
                                <option value="low">Low</option>
                                <option value="mediun">Mediun</option>
                                <option value="high">High</option>
                            </select>
                        </div>
                    </div>
                    <button type="submit">Save Task</button>
                </form>
            </Modal>
        </div>
    );
}

export default Tasks;
